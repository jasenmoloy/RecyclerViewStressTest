package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.jasenmoloy.recyclerviewstresstest.adapters.http.imgur.GalleryResponse;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ChuckNorrisJokeModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ImgurImageModel;
import com.jasenmoloy.recyclerviewstresstest.application.Constants;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.BaseGalleryResponseModel;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;
import com.jasenmoloy.recyclerviewstresstest.drivers.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView view;
    private MainModel model;

    private Subscription apiSub;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        model = new MainModel();
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        unsubscribeFromAll();

        if(savedInstanceBundle == null) { //Load up from scratch
            apiSub = App.getChuckNorrisApi().getItems(10000, "Chuck", "Norris")
                    .observeOn(Schedulers.io())
                    .map(MainPresenterReactive.convertChuckNorrisApiToList())
                    .zipWith(
                            App.getImgurApi().getGallery(
                                    Constants.IMGUR_CLIENTID,
                                    "hot",
                                    "viral",
                                    model.getImgurCurrentPage()).retry(3),
                            MainPresenterReactive.mergeImgurResults())
                    .onErrorResumeNext(MainPresenterReactive.onChuckNorrisApiError())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Action1<MainPresenterReactive.RestApiPair>() {
                        @Override
                        public void call(MainPresenterReactive.RestApiPair pair) {
                            model.addChuckNorrisJokes(pair.chuckNorrisJokes);
                            model.addImages(pair.imgurImages);
                            model.setImgurCurrentPage(model.getImgurCurrentPage() + 1);

                            generateCardData();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            onItemsError(throwable);
                        }
                    });
        } else {
            model = new MainModel(savedInstanceBundle.getBundle("model"));

            generateCardData();
        }
}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBundle("model", model.onSaveInstanceState());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        unsubscribeFromAll();
    }

    public void onItemsError(Throwable throwable) {
        Log.w("JAM", throwable.getMessage());

        view.showToast(throwable.getMessage());
    }

    @Override
    public void onRecyclerViewScrolledToBottom() {
        //Load another page of images from Imgur
        unsubscribeFromAll();

        apiSub = App.getImgurApi().getGallery(Constants.IMGUR_CLIENTID, "hot", "viral", model.getImgurCurrentPage())
                .subscribeOn(Schedulers.io())
                .retry(3) //Attempt 3 times before quitting
                .map(new Func1<Response<JsonObject>, List<GalleryImageModel>>() {
                    @Override
                    public List<GalleryImageModel> call(Response<JsonObject> response) {
                        List<GalleryImageModel> images = new ArrayList<>();

                        if(response != null) {
                            if (response.isSuccessful()) {
                                GalleryResponse galleryResponse = new GalleryResponse(response.body());
                                Collection<BaseGalleryResponseModel> baseResponses = galleryResponse.getGalleryResponseArray();

                                for (BaseGalleryResponseModel m : baseResponses) {
                                    if (!m.isAlbum) {
                                        GalleryImageModel galleryImage = (GalleryImageModel) m;
                                        images.add(galleryImage);
                                    }
                                }
                            } else {
                                try {
                                    Log.e("JAM", response.errorBody().string());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                                throw Exceptions.propagate(new IOException(
                                        "Imgur gallery request returned " +
                                                response.code() +
                                                ". Message: " +
                                                response.message()
                                ));
                            }
                        } else {
                            throw new NullPointerException("Retrofit response is null");
                        }

                        return images;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GalleryImageModel>>() {
                    @Override
                    public void call(List<GalleryImageModel> galleryImageModels) {
                        model.addImages(galleryImageModels);
                        model.setImgurCurrentPage(model.getImgurCurrentPage() + 1);

                        generateCardData();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onItemsError(throwable);
                    }
                });
    }

    private void unsubscribeFromAll() {
        if(apiSub != null) {
            apiSub.unsubscribe();
            apiSub = null;
        }
    }

    private void generateCardData() {
        List<BaseModel> cards = new ArrayList<>();
        String joke;
        GalleryImageModel image;

        //Add an Imgur image and then a Chuck Norris Joke
        while((joke = model.getNextJoke()) != null && (image = model.getNextImage()) != null) {
            cards.add(new ImgurImageModel(image));
            cards.add(new ChuckNorrisJokeModel(joke));
        }

        //Fill use the rest of the Imgur images
        while((image = model.getNextImage()) != null) {
            cards.add(new ImgurImageModel(image));
        }

        view.onDataItemsLoaded(cards);
    }
}
