package com.example.test.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    //TODO: take this from a configuration file
    private static final String baseURL = "https://api.trakt.tv/";

    private static final String apiVersionHeader = "trakt-api-version";
    private static final String apiVersion = "2";

    private static final String apiKeyHeader = "trakt-api-key";
    private static final String apiKey = "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086";


    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL);


    public static ApiService getService() {

        // add necessary api headers to every request
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader(apiVersionHeader, apiVersion)
                    .addHeader(apiKeyHeader, apiKey)
                    .build();
            return chain.proceed(request);
        }).build();

        Retrofit retrofit = retrofitBuilder.client(client).build();

        return retrofit.create(ApiService.class);
    }

}
