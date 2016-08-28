package com.jasenmoloy.recyclerviewstresstest.adapters.http.cndb;

import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import rx.Observable;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public interface ChuckNorrisDatabaseApi {
    String ENDPOINT = "http://api.icndb.com";

    @GET("jokes/random/{quantity}")
    Observable<Response<JsonObject>> getItems(@Path("quantity") int qty, @Query("firstName") String firstName, @Query("lastName") String lastName);
}
