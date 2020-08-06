package com.huskyez.movieapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OAuthServiceFactory {

    private static OAuthService instance = null;


    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TraktApiConfiguration.BASE_URL);


    public static OAuthService getService() {

        if (instance != null) {
            return instance;
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = retrofitBuilder.client(client).build();
        instance = retrofit.create(OAuthService.class);
        return instance;
    }
}
