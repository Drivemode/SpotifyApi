package com.drivemode.spotify.auth;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author KeishinYokomaku
 */
public class AccessTokenStore {
    public static final String TAG = AccessTokenStore.class.getSimpleName();
    private static final String KEY_ACCESS_TOKEN = "spotify_access_token";
    private static final String KEY_REFRESH_TOKEN = "spotify_refresh_token";
    private static final String KEY_EXPIRES_IN = "spotify_token_expires_at";
    private static final String KEY_RETRIEVED_AT = "spotify_token_retrieved_at";
    private static final String KEY_TOKEN_TYPE = "spotify_token_type";
    private final Application mApplication;

    public AccessTokenStore(Application application) {
        mApplication = application;
    }

    @SuppressLint("CommitPrefEdits")
    public synchronized void store(AccessToken accessToken) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mApplication);
        pref.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken.accessToken)
                .putString(KEY_REFRESH_TOKEN, accessToken.refreshToken)
                .putString(KEY_TOKEN_TYPE, accessToken.tokenType)
                .putLong(KEY_EXPIRES_IN, accessToken.expiresIn * 1000)
                .putLong(KEY_RETRIEVED_AT, System.currentTimeMillis())
                .commit();
    }

    @SuppressLint("CommitPrefEdits")
    public synchronized void update(AccessToken accessToken) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mApplication);
        pref.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken.accessToken)
                .putString(KEY_TOKEN_TYPE, accessToken.tokenType)
                .putLong(KEY_EXPIRES_IN, accessToken.expiresIn * 1000)
                .putLong(KEY_RETRIEVED_AT, System.currentTimeMillis())
                .commit();
    }

    public AccessToken read() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mApplication);
        AccessToken token = new AccessToken();
        token.accessToken = pref.getString(KEY_ACCESS_TOKEN, null);
        token.refreshToken = pref.getString(KEY_REFRESH_TOKEN, null);
        token.tokenType = pref.getString(KEY_TOKEN_TYPE, "Bearer");
        token.expiresIn = pref.getLong(KEY_EXPIRES_IN, 0);
        return token;
    }

    public boolean isExpired() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mApplication);
        long current = System.currentTimeMillis();
        long retrieved = pref.getLong(KEY_RETRIEVED_AT, 0);
        long duration = pref.getLong(KEY_EXPIRES_IN, 0);
        return current > (retrieved + duration);
    }
}