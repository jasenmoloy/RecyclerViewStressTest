package com.jasenmoloy.recyclerviewstresstest.adapters.http.imgur;

import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jasenmoloy on 8/17/16.
 */
public interface ImgurApi {

    String ENDPOINT = "http://api.imgur.com";

    @GET("/3/gallery/{section}/{sort}/{page}")
    Observable<Response<JsonObject>> getGallery(@Header("client-id") String clientId,
                                                @Path("section") String section,
                                                @Path("sort") String sortType,
                                                @Path("page") int page);

}
