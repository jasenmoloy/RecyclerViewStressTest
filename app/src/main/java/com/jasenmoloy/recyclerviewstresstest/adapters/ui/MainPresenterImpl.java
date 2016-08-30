package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ChuckNorrisJokeModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ImgurImageModel;
import com.jasenmoloy.recyclerviewstresstest.application.Constants;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;
import com.jasenmoloy.recyclerviewstresstest.drivers.App;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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

        apiSub = App.getChuckNorrisApi().getItems(10000, "Chuck", "Norris")
                .observeOn(Schedulers.io())
                .map(MainPresenterReactive.convertChuckNorrisApiToList())
                .zipWith(
                        App.getImgurApi().getGallery(
                                Constants.IMGUR_CLIENTID,
                                "hot",
                                "viral",
                                0).retry(3),
                        MainPresenterReactive.mergeImgurResults())
                .onErrorResumeNext(MainPresenterReactive.onChuckNorrisApiError())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<MainPresenterReactive.RestApiPair>() {
                    @Override
                    public void call(MainPresenterReactive.RestApiPair pair) {
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
                            images.get(imagesIdx).type,
                            images.get(imagesIdx).size,
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
                            images.get(imagesIdx).type,
                            images.get(imagesIdx).size,
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
