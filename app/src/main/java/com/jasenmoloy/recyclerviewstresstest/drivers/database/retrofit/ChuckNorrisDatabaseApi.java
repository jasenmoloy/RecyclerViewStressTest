package com.jasenmoloy.recyclerviewstresstest.drivers.database.retrofit;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import rx.Observable;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public interface ChuckNorrisDatabaseApi {

    String ENDPOINT = "http://api.icndb.com";

    class Item {
        @SerializedName("joke")
        public String text;

        @Override
        public String toString() {
            return Html.fromHtml(text).toString();
        }
    }

    class Response {
        @SerializedName("value")
        public Item[] items;
    }

    @GET("jokes/random/{quantity}")
    Observable<Response> getItems(@Path("quantity") int qty, @Query("firstName") String firstName, @Query("lastName") String lastName);
}
