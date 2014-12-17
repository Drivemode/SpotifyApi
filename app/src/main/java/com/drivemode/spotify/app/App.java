package com.drivemode.spotify.app;

import android.app.Application;

import com.drivemode.spotify.ClientConfig;
import com.drivemode.spotify.SpotifyApi;

/**
 * @author KeishinYokomaku
 */
public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        SpotifyApi.initialize(this, new ClientConfig.Builder()
                .setClientId("your client id")
                .setClientSecret("your client secret")
                .setRedirectUri("your callback uri")
                .build());
    }
}
