package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.jasenmoloy.recyclerviewstresstest.drivers.App;
import com.jasenmoloy.recyclerviewstresstest.adapters.http.cndb.ChuckNorrisDatabaseApi;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainModel model;

    private Subscription imgurSub;
    private Subscription chuckNorrisSub;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        model = new MainModelImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        unsubscribeFromAll();

        chuckNorrisSub = App.getChuckNorrisApi()
                .getItems(10000, "Chuck", "Norris")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ChuckNorrisDatabaseApi.Response>() {
                    @Override
                    public void call(ChuckNorrisDatabaseApi.Response response) {
                        onItemNext(response.items);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onItemsError(throwable);
                    }
                });

//        imgurSub = App.getImgurApi()
//                .getGallery(Constants.IMGUR_CLIENTID, "hot", "viral", 0)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Response<JsonObject>>() {
//                    @Override
//                    public void call(Response<JsonObject> response) {
//                        GalleryResponse galleryResponse = new GalleryResponse(response.body());
//                        Collection<BaseGalleryResponseModel> baseResponses = galleryResponse.getGalleryResponseArray();
//
//                        for(BaseGalleryResponseModel m : baseResponses) {
//                            if(m.isAlbum) {
//                                GalleryImageModel galleryImage = (GalleryImageModel) m;
//
//                                Log.v("JAM", "Image: " + galleryImage.link);
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        onItemsError(throwable);
//                    }
//                });
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

    public void onItemNext(final ChuckNorrisDatabaseApi.Item[] items) {
        MainView.CardData data;

        for(int i = 0; i < items.length; i++) {
            final String joke = Html.fromHtml(items[i].text).toString();

            Log.v("JAM", "Joke: " + joke);

            data = new MainView.CardData() {
                @Override
                public String getDescription() {
                    return joke;
                }

                @Override
                public int getCardViewType() {
                    return 0;
                }
            };

            view.onDataItemLoaded(data);
        }
    }

    public void onItemsError(Throwable throwable) {
        Log.w("JAM", throwable.getMessage());

        view.showToast(throwable.getMessage());
    }

    private void unsubscribeFromAll() {
        if(chuckNorrisSub != null) {
            chuckNorrisSub.unsubscribe();
            chuckNorrisSub = null;
        }

        if(imgurSub != null) {
            imgurSub.unsubscribe();
            imgurSub = null;
        }
    }
}
