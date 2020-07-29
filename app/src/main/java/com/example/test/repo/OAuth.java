package com.example.test.repo;

import android.util.Log;

import com.example.test.api.ApiService;
import com.example.test.api.OAuthService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OAuth {

    private final static String CLIENT_ID = "6fec35152112a76320cff5806cd8e72c36f70dfb8ea4e9347d8cc0db4774a50b";
    private final static String CLIENT_SECRET = "ef4409df9826390c334d61ab1558a26a79b077953e76d15d4a2cdace14319009";

    private static OAuthService oAuthService;

    private Retrofit retrofit;

    public OAuth() {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://api.trakt.tv/").build();
        oAuthService = retrofit.create(OAuthService.class);
    }

//    public void getAuthorization() {
//        Call<ResponseBody> call = oAuthService.OAuthAuthorization();
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("OAuth", response.code() + "");
//                Log.d("OAuth", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.getStackTrace();
//            }
//        });


    public OAuthService getoAuthService() {
        return oAuthService;
    }
}


