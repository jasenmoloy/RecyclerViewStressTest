package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.text.Html;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jasenmoloy.recyclerviewstresstest.adapters.http.imgur.GalleryResponse;
import com.jasenmoloy.recyclerviewstresstest.application.Constants;
import com.jasenmoloy.recyclerviewstresstest.application.models.imgur.GalleryImageModelAndroid;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.BaseGalleryResponseModel;
import com.jasenmoloy.recyclerviewstresstest.drivers.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by jasenmoloy on 8/28/16.
 */
public class MainPresenterReactive {
    static class RestApiPair {
        List<String> chuckNorrisJokes;
        List<GalleryImageModelAndroid> imgurImages;

        public RestApiPair() {
            chuckNorrisJokes = new ArrayList<>();
            imgurImages = new ArrayList<>();
        }
    }

    public static Func1<Response<JsonObject>, RestApiPair> convertChuckNorrisApiToList() {
        return new Func1<Response<JsonObject>, RestApiPair>() {
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
        };
    }

    public static Func2<RestApiPair, Response<JsonObject>, RestApiPair> mergeImgurResults() {
        return new Func2<RestApiPair, Response<JsonObject>, RestApiPair>() {
            @Override
            public RestApiPair call(RestApiPair pair, Response<JsonObject> response) {
                if(response != null) {
                    if(response.isSuccessful()) {
                        GalleryResponse galleryResponse = new GalleryResponse(response.body());
                        Collection<BaseGalleryResponseModel> baseResponses = galleryResponse.getGalleryResponseArray();

                        for (BaseGalleryResponseModel m : baseResponses) {
                            if (!m.isAlbum) {
                                GalleryImageModelAndroid galleryImage = (GalleryImageModelAndroid) m;
                                pair.imgurImages.add(galleryImage);
                            }
                        }
                    } else {
                        throw Exceptions.propagate(new IOException("Imgur gallery request returned " + response.code()));
                    }
                } else {
                    throw new NullPointerException("Retrofit response is null");
                }

                return pair;
            }
        };
    }

    public static Func1<Throwable, Observable<? extends RestApiPair>> onChuckNorrisApiError() {
        return new Func1<Throwable, Observable<? extends RestApiPair>>() {
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
                                            GalleryImageModelAndroid galleryImage = (GalleryImageModelAndroid) m;
                                            pair.imgurImages.add(galleryImage);
                                        }
                                    }
                                }

                                return pair;
                            }
                        });
            }
        };
    }
}
