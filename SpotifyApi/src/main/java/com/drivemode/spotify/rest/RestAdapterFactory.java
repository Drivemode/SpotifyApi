package com.drivemode.spotify.rest;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @author KeishinYokomaku
 */
public class RestAdapterFactory {
    public static final String TAG = RestAdapterFactory.class.getSimpleName();
    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/v1";
    public static final String SPOTIFY_AUTHENTICATE_ENDPOINT = "https://accounts.spotify.com/";
    private final OkClient mOkClient;
    private final Executor mExecutor;

    public RestAdapterFactory() {
        mOkClient = new OkClient(new OkHttpClient());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public RestAdapter provideWebApiAdapter(RequestInterceptor interceptor) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(mOkClient)
                .setExecutors(mExecutor, mExecutor)
                .setEndpoint(SPOTIFY_WEB_API_ENDPOINT)
                .setRequestInterceptor(interceptor)
                .build();
    }

    public RestAdapter provideAuthenticateApiAdapter() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(mOkClient)
                .setExecutors(mExecutor, mExecutor)
                .setEndpoint(SPOTIFY_AUTHENTICATE_ENDPOINT)
                .build();
    }
}
