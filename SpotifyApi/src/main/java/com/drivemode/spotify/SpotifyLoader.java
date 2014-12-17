package com.drivemode.spotify;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * @author KeishinYokomaku
 */
public abstract class SpotifyLoader<D> extends AsyncTaskLoader<Response<D>> {
    public static final String TAG = SpotifyLoader.class.getSimpleName();
    private final SpotifyApi mApi;
    private Response<D> mCachedResponse;

    public SpotifyLoader(Context context, SpotifyApi api) {
        super(context);
        mApi = api;
    }

    @Override
    public Response<D> loadInBackground() {
        try {
            mApi.blockingRefreshTokenIfNeeded();
            final D data = call(mApi.getApiService());
            mCachedResponse = Response.ok(data);
        } catch (Exception ex) {
            mCachedResponse = Response.error(ex);
        }
        return mCachedResponse;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCachedResponse != null) {
            deliverResult(mCachedResponse);
        }

        if (takeContentChanged() || mCachedResponse == null) {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mCachedResponse = null;
    }

    public abstract D call(SpotifyService service) throws Exception;
}
