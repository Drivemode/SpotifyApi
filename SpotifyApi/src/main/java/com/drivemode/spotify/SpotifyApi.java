package com.drivemode.spotify;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.drivemode.spotify.auth.AccessToken;
import com.drivemode.spotify.auth.AccessTokenStore;
import com.drivemode.spotify.rest.RestAdapterFactory;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Creates and configures a REST adapter for Spotify Web API.
 *
 * Basic usage:
 * SpotifyApi wrapper = new SpotifyApi();
 *
 * Setting access token is optional for certain endpoints
 * so if you know you'll only use the ones that don't require authorisation
 * you can skip this step:
 * wrapper.setAccessToken(authenticationResponse.getAccessToken());
 *
 * SpotifyService spotify = wrapper.getService();
 *
 * Album album = spotify.getAlbum("2dIGnmEIy1WZIcZCFSj6i8");
 */
public class SpotifyApi {
    public static final String TAG = SpotifyApi.class.getSimpleName();
    private static volatile SpotifyApi sSingleton;
    private final Application mApplication;
    private final ClientConfig mConfig;
    private final AccessTokenStore mTokenStore;
    private final RestAdapterFactory mAdapterFactory;
    private SpotifyService mSpotifyService;
    private SpotifyAuthenticateService mAuthenticateService;

    /* package */ SpotifyApi(Application application, ClientConfig config) {
        mApplication = application;
        mConfig = config;
        mTokenStore = new AccessTokenStore(application);
        mAdapterFactory = new RestAdapterFactory();
    }

    public static void initialize(@NonNull Application application, @NonNull ClientConfig config) {
        if (sSingleton == null) {
            synchronized (SpotifyApi.class) {
                if (sSingleton == null) {
                    sSingleton = new SpotifyApi(application, config);
                }
            }
        }
    }

    public static synchronized SpotifyApi getInstance() {
        if (sSingleton == null) {
            throw new IllegalStateException("SpotifyApi is not yet initialized.");
        }
        return sSingleton;
    }

    public static synchronized void destroy() {
        sSingleton = null;
    }

    /**
     * @return The SpotifyApi instance
     */
    public synchronized SpotifyService getApiService() {
        if (mSpotifyService == null) {
            RestAdapter adapter = mAdapterFactory.provideWebApiAdapter(new WebApiAuthenticator());
            mSpotifyService = adapter.create(SpotifyService.class);
        }
        return mSpotifyService;
    }

    public synchronized SpotifyAuthenticateService getAuthService() {
        if (mAuthenticateService == null) {
            RestAdapter adapter = mAdapterFactory.provideAuthenticateApiAdapter();
            mAuthenticateService = adapter.create(SpotifyAuthenticateService.class);
        }
        return mAuthenticateService;
    }

    public boolean isAuthrorized() {
        AccessToken token = mTokenStore.get(); // XXX
        return !TextUtils.isEmpty(token.accessToken);
    }

    public void authorize(Context context, String[] scopes) {
        authorize(context, scopes, false);
    }

    public void authorize(Context context, String[] scopes, boolean showDialog) {
        String scope = TextUtils.join(" ", scopes);
        Uri uri = Uri.parse("https://accounts.spotify.com/authorize").buildUpon()
                .appendQueryParameter("client_id", mConfig.getClientId())
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("redirect_uri", mConfig.getRedirectUri())
                .appendQueryParameter("scope", scope)
                .appendQueryParameter("show_dialog", String.valueOf(showDialog))
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public void onCallback(Uri data, final AuthenticationListener listener) {
        Log.v(TAG, data.toString());
        String code = data.getQueryParameter("code");
        getAuthService().getAccessToken("authorization_code", code, mConfig.getRedirectUri(), mConfig.getClientId(), mConfig.getClientSecret(), new Callback<AccessToken>() {
            @Override
            public void success(AccessToken accessToken, Response response) {
                Log.v(TAG, "success retrieving access token: " + accessToken.toString());
                mTokenStore.store(accessToken);
                listener.onReady();
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

    public void refreshTokenIfNeeded() {
        if (!mTokenStore.isExpired()) {
            Log.v(TAG, "no need to refresh");
            return;
        }
        AccessToken token = mTokenStore.get();
        getAuthService().refreshAccessToken("refresh_token", token.refreshToken, mConfig.getClientId(), mConfig.getClientSecret(), new Callback<AccessToken>() {
            @Override
            public void success(AccessToken accessToken, Response response) {
                Log.v(TAG, "success refreshing access token: " + accessToken.toString());
                mTokenStore.update(accessToken);
            }

            @Override
            public void failure(RetrofitError error) {}
        });
    }

    public void blockingRefreshTokenIfNeeded() {
        if (!mTokenStore.isExpired()) {
            Log.v(TAG, "no need to refresh");
            return;
        }
        AccessToken token = mTokenStore.get();
        AccessToken newToken = getAuthService().refreshAccessToken("refresh_token", token.refreshToken, mConfig.getClientId(), mConfig.getClientSecret());
        mTokenStore.store(newToken);
    }

    public ClientConfig getConfig() {
        return mConfig;
    }

    public AccessTokenStore getTokenStore() {
        return mTokenStore;
    }

    /**
     * The request interceptor that will add the header with OAuth
     * token to every request made with the wrapper.
     */
    private class WebApiAuthenticator implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            AccessToken token = mTokenStore.get();
            if (token != null) {
                request.addHeader("Authorization", token.tokenType + " " + token.accessToken);
            }
        }
    }

    public interface AuthenticationListener {
        public void onReady();
        public void onError();
    }
}
