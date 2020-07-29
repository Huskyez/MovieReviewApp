package com.example.test.api;

import com.example.test.model.accesstoken.AccessTokenRequestBody;
import com.example.test.model.accesstoken.RefreshTokenRequestBody;
import com.example.test.model.accesstoken.RevokeAccessTokenBody;
import com.google.gson.JsonObject;

import de.rheinfabrik.heimdall2.OAuth2AccessToken;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OAuthService {

    //OAuth authorization (redirects to trakt.tv)
    @GET("https://trakt.tv/oauth/authorize?response_type=code&client_id=ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086&redirect_uri=http://trakt.tv")
    Call<ResponseBody> OAuthAuthorization();

    @POST("/oauth/token")
    Observable<OAuth2AccessToken> grantNewAccessToken(@Body AccessTokenRequestBody body);

    @POST("/oauth/token")
    Observable<OAuth2AccessToken> refreshAccessToken(@Body RefreshTokenRequestBody body);

    @POST("/oauth/revoke")
    Observable<Void> revokeAccessToken(@Body RevokeAccessTokenBody body);


}
