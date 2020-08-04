package com.huskyez.test.api;

import com.huskyez.test.model.sync.CollectionPostBody;
import com.huskyez.test.model.image.ImageSearchResult;
import com.huskyez.test.model.common.WatchlistItem;
import com.huskyez.test.model.movie.AnticipatedMovie;
import com.huskyez.test.model.movie.CollectionMovie;
import com.huskyez.test.model.movie.Movie;
import com.huskyez.test.model.movie.MovieDetails;
import com.huskyez.test.model.common.SearchResult;
import com.huskyez.test.model.movie.RecommendedMovie;
import com.huskyez.test.model.movie.TrendingMovie;
import com.huskyez.test.model.movie.WatchedMovie;
import com.huskyez.test.model.show.AnticipatedShow;
import com.huskyez.test.model.show.CollectionShow;
import com.huskyez.test.model.show.RecommendedShow;
import com.huskyez.test.model.show.Show;
import com.huskyez.test.model.show.ShowDetails;
import com.huskyez.test.model.show.TrendingShow;
import com.huskyez.test.model.show.WatchedShow;
import com.huskyez.test.model.stats.UserStats;
import com.huskyez.test.model.sync.WatchlistPostBody;
import com.huskyez.test.model.user.UserSettings;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {


    //// ------------------ MOVIES -------------------------------------------------------------------------------------------
    @GET("movies/trending?limit=30")
    Call<List<TrendingMovie>> getTrendingMovies();

    @GET("movies/popular?limit=30")
    Call<List<Movie>> getPopularMovies();

    @GET("movies/anticipated")
    Call<List<AnticipatedMovie>> getAnticipatedMovies();

    @GET("movies/recommended/{period}")
    Call<List<RecommendedMovie>> getRecommendedMovies(@Path("period") String period);

    //// ------------------ SHOWS -------------------------------------------------------------------------------------------
    @GET("shows/trending?limit=30")
    Call<List<TrendingShow>> getTrendingShows();

    @GET("shows/popular?limit=30")
    Call<List<Show>> getPopularShows();

    @GET("shows/anticipated")
    Call<List<AnticipatedShow>> getAnticipatedShows();

    @GET("shows/recommended/{period}")
    Call<List<RecommendedShow>> getRecommendedShows(@Path("period") String period);

    //// ------------------ IMAGES -------------------------------------------------------------------------------------------
    @GET("https://api.themoviedb.org/3/{type}/{tmdb_id}/images")
    Call<ImageSearchResult> searchImages(@Path("type") String type, @Path("tmdb_id") Integer tmdb_id, @Query("api_key") String apiKey);

    //// ------------------ SEARCH -------------------------------------------------------------------------------------------
    @GET("search/{type}")
    Call<List<SearchResult>> search(@Path("type") String type, @Query("query") String query);


    // ------------------ DETAILS -------------------------------------------------------------------------------------------
    @GET("movies/{slug_id}?extended=full")
    Call<MovieDetails> getMovieDetails(@Path("slug_id") String slug_id);

    @GET("shows/{slug_id}?extended=full")
    Call<ShowDetails> getShowDetails(@Path("slug_id") String slug_id);




    // ------------------ USER DATA -----------------------------------------------------------------------------------------------
    @GET("users/settings")
    Call<UserSettings> getUserSettings(@Header("Authorization") String access_token);

    @GET("users/{id}/stats")
    Call<UserStats> getUserStats(@Path("id") String id);


    // ------------------ USER COLLECTION -------------------------------------------------------------------------------------------
    @GET("sync/collection/movies")
    Call<List<CollectionMovie>> getMovieCollection(@Header("Authorization") String access_token);

    @GET("sync/collection/shows")
    Call<List<CollectionShow>> getShowCollection(@Header("Authorization") String access_token);

    @POST("sync/collection")
    Call<Void> addToCollection(@Header("Authorization") String access_token, @Body CollectionPostBody collection);

    @POST("sync/collection/remove")
    Call<Void> removeFromCollection(@Header("Authorization") String access_token, @Body CollectionPostBody collection);

//    @POST("sync/collection")
//    Call<Void> addMovieToCollection(@Header("Authorization") String access_token, @Body List<CollectionMovie> movies);
//
//    @POST("sync/collection")
//    Call<Void> addShowToCollection(@Header("Authorization") String access_token, @Body List<CollectionShow> shows);

//    @POST("sync/collection/remove")
//    Call<Void> removeMovieFromCollection(@Header("Authorization") String access_token, @Body List<CollectionMovie> movies);
//
//    @POST("sync/collection/remove")
//    Call<Void> removeShowFromCollection(@Header("Authorization") String access_token, @Body List<CollectionShow> movies);


    // ------------------ USER WATCHLIST -------------------------------------------------------------------------------------------
    @GET("sync/watchlist")
    Call<List<WatchlistItem>> getWatchlist(@Header("Authorization") String access_token);

    @POST("sync/watchlist")
    Call<Void> addToWatchlist(@Header("Authorization") String access_token, @Body WatchlistPostBody watchlist);
//    @POST("sync/watchlist")
//    Call<Void> addMovieToWatchlist(@Header("Authorization") String access_token, @Body List<Movie> movies);
//
//    @POST("sync/watchlist")
//    Call<Void> addShowToWatchlist(@Header("Authorization") String access_token, @Body List<Show> shows);

    @POST("sync/watchlist/remove")
    Call<Void> removeFromWatchlist(@Header("Authorization") String access_token, @Body WatchlistPostBody watchlist);

    // ------------------ USER RECOMMENDATIONS -------------------------------------------------------------------------------------------
    @GET("sync/recommendations/{type}")
    Call<List<WatchlistItem>> getRecommendations(@Header("Authorization") String access_token);


    // ------------------ USER WATCHED -------------------------------------------------------------------------------------------
    @GET("sync/watched/movies")
    Call<List<WatchedMovie>> getWatchedMovies(@Header("Authorization") String access_token);

    @GET("sync/watched/shows")
    Call<List<WatchedShow>> getWatchedShows(@Header("Authorization") String access_token);


}
