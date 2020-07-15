package com.example.test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiService {

    @Headers({"trakt-api-version: 2", "trakt-api-key: ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086"})
    @GET("movies/trending")
    Call<List<String>> getTrending();

}
