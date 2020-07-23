package com.example.test.api;

import com.example.test.model.ImageSearchResult;
import com.example.test.model.Movie;
import com.example.test.model.MovieDetails;
import com.example.test.model.SearchResult;
import com.example.test.model.TrendingMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("movies/trending")
    Call<List<TrendingMovie>> getTrending();

    @GET("movies/popular")
    Call<List<Movie>> getPopular();

    @GET("https://api.themoviedb.org/3/{type}/{tmdb_id}/images")
    Call<ImageSearchResult> searchImages(@Path("type") String type, @Path("tmdb_id") Integer tmdb_id, @Query("api_key") String apiKey);

    @GET("search/{type}")
    Call<List<SearchResult>> search(@Path("type") String type, @Query("query") String query);

    @GET("movies/{slug_id}?extended=full")
    Call<MovieDetails> getMovieDetails(@Path("slug_id") String slug_id);

}
