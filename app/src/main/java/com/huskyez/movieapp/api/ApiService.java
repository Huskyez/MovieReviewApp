package com.huskyez.movieapp.api;

import com.huskyez.movieapp.model.common.RatedItem;
import com.huskyez.movieapp.model.show.Episode;
import com.huskyez.movieapp.model.show.Season;
import com.huskyez.movieapp.model.sync.CollectionPostBody;
import com.huskyez.movieapp.model.image.ImageSearchResult;
import com.huskyez.movieapp.model.common.WatchlistItem;
import com.huskyez.movieapp.model.movie.AnticipatedMovie;
import com.huskyez.movieapp.model.movie.CollectionMovie;
import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.movie.MovieDetails;
import com.huskyez.movieapp.model.common.SearchResult;
import com.huskyez.movieapp.model.movie.RecommendedMovie;
import com.huskyez.movieapp.model.movie.TrendingMovie;
import com.huskyez.movieapp.model.movie.WatchedMovie;
import com.huskyez.movieapp.model.show.AnticipatedShow;
import com.huskyez.movieapp.model.show.CollectionShow;
import com.huskyez.movieapp.model.show.RecommendedShow;
import com.huskyez.movieapp.model.show.Show;
import com.huskyez.movieapp.model.show.ShowDetails;
import com.huskyez.movieapp.model.show.TrendingShow;
import com.huskyez.movieapp.model.show.WatchedShow;
import com.huskyez.movieapp.model.stats.UserStats;
import com.huskyez.movieapp.model.sync.HistoryPostBody;
import com.huskyez.movieapp.model.sync.RatingPostBody;
import com.huskyez.movieapp.model.sync.WatchlistPostBody;
import com.huskyez.movieapp.model.user.UserSettings;

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

    @GET("shows/{slug_id}/seasons")
    Call<List<Season>> getSeasons(@Path("slug_id") String slug_id);

    @GET("shows/{slug_id}/seasons/{season}")
    Call<List<Episode>> getEpisodes(@Path("slug_id") String slug_id, @Path("season") Integer season);

    @GET("shows/{slug_id}/seasons?extended=episodes")
    Call<List<Season>> getSeasonsWithEpisodes(@Path("slug_id") String slug_id);

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


    // ------------------ USER WATCHLIST -------------------------------------------------------------------------------------------
    @GET("sync/watchlist")
    Call<List<WatchlistItem>> getWatchlist(@Header("Authorization") String access_token);

    @POST("sync/watchlist")
    Call<Void> addToWatchlist(@Header("Authorization") String access_token, @Body WatchlistPostBody watchlist);

    @POST("sync/watchlist/remove")
    Call<Void> removeFromWatchlist(@Header("Authorization") String access_token, @Body WatchlistPostBody watchlist);

    // ------------------ USER RECOMMENDATIONS -------------------------------------------------------------------------------------------
    @GET("sync/recommendations/{type}")
    Call<List<WatchlistItem>> getRecommendations(@Header("Authorization") String access_token);

    @GET("recommendations/movies?limit=20&ignore_collected=true")
    Call<List<Movie>> getMovieRecommendations(@Header("Authorization") String access_token);

    @GET("recommendations/shows?limit=20&ignore_collected=true")
    Call<List<Show>> getShowRecommendations(@Header("Authorization") String access_token);

    // ------------------ USER HISTORY -------------------------------------------------------------------------------------------
    @GET("sync/watched/movies")
    Call<List<WatchedMovie>> getWatchedMovies(@Header("Authorization") String access_token);

    @GET("sync/watched/shows")
    Call<List<WatchedShow>> getWatchedShows(@Header("Authorization") String access_token);

    @POST("sync/history")
    Call<Void> addToHistory (@Header("Authorization") String access_token, @Body HistoryPostBody history);

    @POST("sync/history/remove")
    Call<Void> removeFromHistory (@Header("Authorization") String access_token, @Body HistoryPostBody history);


    // ------------------ USER RATINGS -------------------------------------------------------------------------------------------
    @GET("sync/ratings")
    Call<List<RatedItem>> getRatings(@Header("Authorization") String access_token);

    @POST("sync/ratings")
    Call<Void> addRating(@Header("Authorization") String access_token, @Body RatingPostBody ratings);

    @POST("sync/ratings/remove")
    Call<Void> removeRating(@Header("Authorization") String access_token, @Body RatingPostBody ratings);


}
