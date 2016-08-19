package com.jasenmoloy.recyclerviewstresstest.drivers;

import android.app.Application;

import com.jasenmoloy.recyclerviewstresstest.drivers.database.retrofit.ChuckNorrisDatabaseApi;
import com.jasenmoloy.recyclerviewstresstest.drivers.database.retrofit.imgur.ImgurApi;
import com.jasenmoloy.recyclerviewstresstest.drivers.http.LoggingIntercepter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class App extends Application {

    private static App instance;
    private static ImgurApi imgurApi;
    private static ChuckNorrisDatabaseApi chuckNorrisApi;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingIntercepter())
                .build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        chuckNorrisApi = new Retrofit.Builder()
                .baseUrl(ChuckNorrisDatabaseApi.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ChuckNorrisDatabaseApi.class);

        imgurApi = new Retrofit.Builder()
                .baseUrl(ImgurApi.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ImgurApi.class);
    }

    public static ChuckNorrisDatabaseApi getChuckNorrisApi() {
        return chuckNorrisApi;
    }

    public static ImgurApi getImgurApi() {
        return imgurApi;
    }
}
