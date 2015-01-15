package com.drivemode.spotify.rest;

import com.squareup.okhttp.OkHttpClient;

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

    public RestAdapterFactory() {
        mOkClient = new OkClient(new OkHttpClient());
    }

    public RestAdapter provideWebApiAdapter(RequestInterceptor interceptor) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(mOkClient)
                .setEndpoint(SPOTIFY_WEB_API_ENDPOINT)
                .setRequestInterceptor(interceptor)
                .build();
    }

    public RestAdapter provideAuthenticateApiAdapter() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(mOkClient)
                .setEndpoint(SPOTIFY_AUTHENTICATE_ENDPOINT)
                .build();
    }
}
