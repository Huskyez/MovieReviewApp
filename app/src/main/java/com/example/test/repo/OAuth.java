package com.example.test.repo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.test.api.ApiService;
import com.example.test.api.OAuthService;
import com.example.test.api.OAuthServiceFactory;
import com.example.test.api.TraktApiConfiguration;
import com.example.test.model.token.AccessToken;
import com.example.test.model.token.AccessTokenRequestBody;
import com.example.test.model.token.RefreshTokenRequestBody;


import com.example.test.R;
import com.example.test.model.token.RevokeAccessTokenBody;

import java.io.IOException;
import java.nio.file.Path;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OAuth {

    private final String AUTH_GRANT_TYPE = "authorization_code";
    private final String RERFESH_GRANT_TYPE = "refresh_token";

    private OAuthService oAuthService;
    private Context context;


    public OAuth(Context context) {
        oAuthService = OAuthServiceFactory.getService();
        this.context = context;
    }

    private void saveAccessTokenToSharedPreferences(AccessToken accessToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", accessToken.getAccessToken());
        editor.putString("refresh_token", accessToken.getRefreshToken());
        editor.putLong("expires_at", accessToken.getExpires());
        editor.apply();
    }

    private void clearSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    // we need the context in order to save the token as it is received in SharedPreferences
    public void exchangeCodeForAccessToken(String code) {

        AccessTokenRequestBody body = new AccessTokenRequestBody(code,
                TraktApiConfiguration.CLIENT_ID,
                TraktApiConfiguration.CLIENT_SECRET,
                TraktApiConfiguration.REDIRECT_URI,
                AUTH_GRANT_TYPE);

        //TODO: maybe just do this synchronously

        Call<AccessToken> call = oAuthService.grantNewAccessToken(body);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    saveAccessTokenToSharedPreferences(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void exchangeRefreshTokenForAccessToken(String refreshToken) {
        RefreshTokenRequestBody body = new RefreshTokenRequestBody(refreshToken,
                TraktApiConfiguration.CLIENT_ID,
                TraktApiConfiguration.CLIENT_SECRET,
                TraktApiConfiguration.REDIRECT_URI,
                RERFESH_GRANT_TYPE);

        Call<AccessToken> call = oAuthService.refreshAccessToken(body);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    saveAccessTokenToSharedPreferences(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void revokeToken(String accessToken) {
        RevokeAccessTokenBody body = new RevokeAccessTokenBody(accessToken,
                TraktApiConfiguration.CLIENT_ID,
                TraktApiConfiguration.CLIENT_SECRET);

        Call<Void> call = oAuthService.revokeAccessToken(body);
        clearSharedPreferences();
        UserRepository.getInstance().clearData();
        ImageRepository.getInstance().clearImageCache();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
//        try {
//            call.execute();
//            clearSharedPreferences();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }





}


