package com.drivemode.spotify;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * @author KeithYokoma
 */
public class ClientConfig {
    private final String mClientId;
    private final String mClientSecret;
    private final String mRedirectUri;

    /* package */ ClientConfig(@NonNull String clientId, @NonNull String clientSecret, @NonNull String redirectUri) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mRedirectUri = redirectUri;
    }

    public @NonNull String getClientId() {
        return mClientId;
    }

    public @NonNull String getClientSecret() {
        return mClientSecret;
    }

    public @NonNull String getRedirectUri() {
        return mRedirectUri;
    }

    public static class Builder {
        private String mClientId;
        private String mClientSecret;
        private String mRedirectUri;

        public Builder() {}

        public @NonNull Builder setClientId(@NonNull String clientId) {
            mClientId = clientId;
            return this;
        }

        public @NonNull Builder setClientSecret(@NonNull String clientSecret) {
            mClientSecret = clientSecret;
            return this;
        }

        public @NonNull Builder setRedirectUri(@NonNull String redirectUri) {
            mRedirectUri = redirectUri;
            return this;
        }

        public @NonNull ClientConfig build() {
            validate();
            return new ClientConfig(mClientId, mClientSecret, mRedirectUri);
        }

        /* package */ void validate() {
            if (TextUtils.isEmpty(mClientId)) {
                throw new IllegalStateException("Client id may not be null or empty.");
            }
            if (TextUtils.isEmpty(mClientSecret)) {
                throw new IllegalStateException("Client secret may not be null or empty.");
            }
            if (TextUtils.isEmpty(mRedirectUri)) {
                throw new IllegalStateException("Redirect uri may not be null or empty.");
            }
        }
    }
}
