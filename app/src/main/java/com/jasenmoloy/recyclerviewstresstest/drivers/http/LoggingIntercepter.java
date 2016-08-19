package com.jasenmoloy.recyclerviewstresstest.drivers.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jasenmoloy on 8/18/16.
 */
public class LoggingIntercepter implements Interceptor {

    public static final String TAG = LoggingIntercepter.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long time = System.nanoTime();

        Log.v(TAG,
                String.format(
                        "Sending request %s on %s%n%s",
                        request.url(),
                        chain.connection(),
                        request.headers()
                )
        );

        Response response = chain.proceed(request);

        long time2 = System.nanoTime();

        Log.v(TAG,
                String.format("Received Response for %s in %.1fms%n%s",
                        response.request().url(),
                        (time2 - time) / 1e6d,
                        response.headers()
                )
        );

        return response;
    }
}
