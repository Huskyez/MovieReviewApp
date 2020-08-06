package com.huskyez.movieapp.api;

import com.huskyez.movieapp.model.token.AccessToken;
import com.huskyez.movieapp.model.token.AccessTokenRequestBody;
import com.huskyez.movieapp.model.token.RefreshTokenRequestBody;
import com.huskyez.movieapp.model.token.RevokeAccessTokenBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OAuthService {


    @GET("https://trakt.tv/logout")
    Call<Void> logoutFromTrakt();

    @POST("/oauth/token")
    Call<AccessToken> grantNewAccessToken(@Body AccessTokenRequestBody body);

    @POST("/oauth/token")
    Call<AccessToken> refreshAccessToken(@Body RefreshTokenRequestBody body);

    @POST("/oauth/revoke")
    Call<Void> revokeAccessToken(@Body RevokeAccessTokenBody body);


}
