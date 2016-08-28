package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jasenmoloy.recyclerviewstresstest.adapters.http.imgur.GalleryResponse;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ChuckNorrisJokeModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ImgurImageModel;
import com.jasenmoloy.recyclerviewstresstest.application.Constants;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.BaseGalleryResponseModel;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;
import com.jasenmoloy.recyclerviewstresstest.drivers.App;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainPresenterImpl implements MainPresenter {

    class RestApiPair {
        List<String> chuckNorrisJokes;
        List<GalleryImageModel> imgurImages;

        public RestApiPair() {
            chuckNorrisJokes = new ArrayList<>();
            imgurImages = new ArrayList<>();
        }
    }

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

        apiSub = App.getChuckNorrisApi().getItems(10000, "Chuck", "Norris")
                .observeOn(Schedulers.io())
                .map(new Func1<Response<JsonObject>, RestApiPair>() {
                    @Override
                    public RestApiPair call(Response<JsonObject> response) {
                        RestApiPair pair = new RestApiPair();

                        if (response != null) {
                            JsonArray jokes = response.body().get("value").getAsJsonArray();
                            String joke;

                            for (int i = 0; i < jokes.size(); i++) {
                                joke = jokes.get(i).getAsJsonObject()
                                        .get("joke").getAsString();

                                pair.chuckNorrisJokes.add(Html.fromHtml(joke).toString());
                            }
                        }

                        return pair;
                    }
                })
                .zipWith(App.getImgurApi().getGallery(Constants.IMGUR_CLIENTID, "hot", "viral", 0).retry(3),
                        new Func2<RestApiPair, Response<JsonObject>, RestApiPair>() {
                            @Override
                            public RestApiPair call(RestApiPair pair, Response<JsonObject> response) {
                                if(response != null) {
                                    GalleryResponse galleryResponse = new GalleryResponse(response.body());
                                    Collection<BaseGalleryResponseModel> baseResponses = galleryResponse.getGalleryResponseArray();

                                    for (BaseGalleryResponseModel m : baseResponses) {
                                        if (!m.isAlbum) {
                                            GalleryImageModel galleryImage = (GalleryImageModel) m;
                                            pair.imgurImages.add(galleryImage);
                                        }
                                    }
                                }
                                return pair;
                            }
                        })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends RestApiPair>>() {
                    @Override
                    public Observable<? extends RestApiPair> call(Throwable throwable) {
                        return App.getImgurApi().getGallery(Constants.IMGUR_CLIENTID, "hot", "viral", 0)
                                .retry(3)
                                .map(new Func1<Response<JsonObject>, RestApiPair>() {
                                    @Override
                                    public RestApiPair call(Response<JsonObject> response) {
                                        RestApiPair pair = new RestApiPair();

                                        if(response != null) {
                                            GalleryResponse galleryResponse = new GalleryResponse(response.body());
                                            Collection<BaseGalleryResponseModel> baseResponses = galleryResponse.getGalleryResponseArray();

                                            for (BaseGalleryResponseModel m : baseResponses) {
                                                if (!m.isAlbum) {
                                                    GalleryImageModel galleryImage = (GalleryImageModel) m;
                                                    pair.imgurImages.add(galleryImage);
                                                }
                                            }
                                        }

                                        return pair;
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<RestApiPair>() {
                    @Override
                    public void call(RestApiPair pair) {
                        model.setChuckNorrisJokes(pair.chuckNorrisJokes);
                        model.setImages(pair.imgurImages);

                        generateCardData(model.getChuckNorrisJokes(), model.getImages());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onItemsError(throwable);
                    }
                });
}

    @Override
    public void onSaveInstanceState(Bundle outState) {

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

    private void unsubscribeFromAll() {
        if(apiSub != null) {
            apiSub.unsubscribe();
            apiSub = null;
        }
    }

    private void generateCardData(List<String> jokes, List<GalleryImageModel> images) {
        List<BaseModel> cards = new ArrayList<>();
        int jokesIdx = 0;
        int imagesIdx = 0;

        while(jokesIdx < jokes.size() && imagesIdx < images.size()) {
            cards.add(
                    new ImgurImageModel(
                            images.get(imagesIdx).title,
                            Uri.parse(images.get(imagesIdx).link),
                            images.get(imagesIdx).description)
            );
            imagesIdx++;

            cards.add(
                    new ChuckNorrisJokeModel(jokes.get(jokesIdx))
            );
            jokesIdx++;
        }

        while(imagesIdx < images.size()) {
            cards.add(
                    new ImgurImageModel(
                            images.get(imagesIdx).title,
                            Uri.parse(images.get(imagesIdx).link),
                            images.get(imagesIdx).description)
            );
            imagesIdx++;
        }

        while(jokesIdx < jokes.size()) {
            cards.add(
                    new ChuckNorrisJokeModel(jokes.get(jokesIdx))
            );
            jokesIdx++;
        }

        view.onDataItemsLoaded(cards);
    }
}
