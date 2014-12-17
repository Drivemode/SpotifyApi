package com.drivemode.spotify;

import com.drivemode.spotify.auth.AccessToken;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @author KeishinYokomaku
 */
public interface SpotifyAuthenticateService {
    public static final String TAG = SpotifyAuthenticateService.class.getSimpleName();

    @FormUrlEncoded
    @POST("/api/token")
    public AccessToken getAccessToken(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    @FormUrlEncoded
    @POST("/api/token")
    public void getAccessToken(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, Callback<AccessToken> callback);

    @FormUrlEncoded
    @POST("/api/token")
    public AccessToken refreshAccessToken(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    @FormUrlEncoded
    @POST("/api/token")
    public void refreshAccessToken(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, Callback<AccessToken> callback);
}
