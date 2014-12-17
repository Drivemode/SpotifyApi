package com.drivemode.spotify.auth;

import com.google.gson.annotations.SerializedName;

/**
 * @author KeishinYokomaku
 */
public class AccessToken {
    public static final String TAG = AccessToken.class.getSimpleName();
    @SerializedName("access_token") public String accessToken;
    @SerializedName("token_type") public String tokenType;
    @SerializedName("expires_in") public long expiresIn;
    @SerializedName("refresh_token") public String refreshToken;

    @Override
    public String toString() {
        return tokenType + " token(" + accessToken + ") will expires in " + expiresIn;
    }
}
