package com.example.test.api;

import com.example.test.model.token.AccessToken;
import com.example.test.model.token.AccessTokenRequestBody;
import com.example.test.model.token.RefreshTokenRequestBody;
import com.example.test.model.token.RevokeAccessTokenBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OAuthService {


    @POST("/oauth/token")
    Call<AccessToken> grantNewAccessToken(@Body AccessTokenRequestBody body);

    @POST("/oauth/token")
    Call<AccessToken> refreshAccessToken(@Body RefreshTokenRequestBody body);

    @POST("/oauth/revoke")
    Call<Void> revokeAccessToken(@Body RevokeAccessTokenBody body);


}
