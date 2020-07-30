package com.example.test.api;

import com.example.test.model.ImageSearchResult;
import com.example.test.model.movie.AnticipatedMovie;
import com.example.test.model.movie.Movie;
import com.example.test.model.movie.MovieDetails;
import com.example.test.model.SearchResult;
import com.example.test.model.movie.RecommendedMovie;
import com.example.test.model.movie.TrendingMovie;
import com.example.test.model.show.AnticipatedShow;
import com.example.test.model.show.RecommendedShow;
import com.example.test.model.show.Show;
import com.example.test.model.show.ShowDetails;
import com.example.test.model.show.TrendingShow;
import com.example.test.model.stats.UserStats;
import com.example.test.model.user.UserSettings;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {


    //MOVIES
    @GET("movies/trending")
    Call<List<TrendingMovie>> getTrendingMovies();

    @GET("movies/popular")
    Call<List<Movie>> getPopularMovies();

    @GET("movies/anticipated")
    Call<List<AnticipatedMovie>> getAnticipatedMovies();

    @GET("movies/recommended/{period}")
    Call<List<RecommendedMovie>> getRecommendedMovies(@Path("period") String period);

    //SHOWS
    @GET("shows/trending")
    Call<List<TrendingShow>> getTrendingShows();

    @GET("shows/popular")
    Call<List<Show>> getPopularShows();

    @GET("shows/anticipated")
    Call<List<AnticipatedShow>> getAnticipatedShows();

    @GET("shows/recommended/{period}")
    Call<List<RecommendedShow>> getRecommendedShows(@Path("period") String period);

    //IMAGES
    @GET("https://api.themoviedb.org/3/{type}/{tmdb_id}/images")
    Call<ImageSearchResult> searchImages(@Path("type") String type, @Path("tmdb_id") Integer tmdb_id, @Query("api_key") String apiKey);

    //SEARCH
    @GET("search/{type}")
    Call<List<SearchResult>> search(@Path("type") String type, @Query("query") String query);


    //DETAILS
    @GET("movies/{slug_id}?extended=full")
    Call<MovieDetails> getMovieDetails(@Path("slug_id") String slug_id);

    @GET("shows/{slug_id}?extended=full")
    Call<ShowDetails> getShowDetails(@Path("slug_id") String slug_id);




    //USER
    @GET("users/settings")
    Call<UserSettings> getUserSettings(@Header("Authorization") String access_token);

    @GET("users/{id}/stats")
    Call<UserStats> getUserStats(@Path("id") String id);

}
