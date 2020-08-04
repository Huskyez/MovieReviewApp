package com.huskyez.test.api;

import com.huskyez.test.model.token.AccessToken;
import com.huskyez.test.model.token.AccessTokenRequestBody;
import com.huskyez.test.model.token.RefreshTokenRequestBody;
import com.huskyez.test.model.token.RevokeAccessTokenBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OAuthService {


    @POST("/oauth/token")
    Call<AccessToken> grantNewAccessToken(@Body AccessTokenRequestBody body);

    @POST("/oauth/token")
    Call<AccessToken> refreshAccessToken(@Body RefreshTokenRequestBody body);

    @POST("/oauth/revoke")
    Call<Void> revokeAccessToken(@Body RevokeAccessTokenBody body);


}
