package com.example.test.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    //TODO: take this from a configuration file
    // SO MANY F***ING KEYS
    private static final String BASE_URL = "https://api.trakt.tv/";

    private static final String API_VERSION_HEADER = "trakt-api-version";
    private static final String API_VERSION = "2";

    private static final String API_KEY_HEADER = "trakt-api-key";
    private static final String API_KEY = "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086";

    private static final String TMDB_API_KEY_HEADER = "api-key";
    private static final String TMDB_API_KEY = "366bb8cd1b82ca2f219a0f72303f68e9";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);


    public static ApiService getService() {

        // add necessary api headers to every request
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader(API_VERSION_HEADER, API_VERSION)
                    .addHeader(API_KEY_HEADER, API_KEY)
                    .build();
            return chain.proceed(request);
        })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = retrofitBuilder.client(client).build();
        return retrofit.create(ApiService.class);
    }

}