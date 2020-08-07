package com.huskyez.movieapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huskyez.movieapp.api.ApiService;
import com.huskyez.movieapp.api.ApiServiceFactory;
import com.huskyez.movieapp.model.common.RatedItem;
import com.huskyez.movieapp.model.common.WatchlistItem;
import com.huskyez.movieapp.model.movie.CollectionMovie;
import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.movie.WatchedMovie;
import com.huskyez.movieapp.model.show.CollectionShow;
import com.huskyez.movieapp.model.show.Show;
import com.huskyez.movieapp.model.show.WatchedShow;
import com.huskyez.movieapp.model.sync.CollectionPostBody;
import com.huskyez.movieapp.model.sync.HistoryPostBody;
import com.huskyez.movieapp.model.sync.RatingPostBody;
import com.huskyez.movieapp.model.sync.WatchlistPostBody;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListsRepository {

    private ApiService apiService;
    private String access_token;
    private MutableLiveData<String> postResponse = new MutableLiveData<>();


    private List<CollectionMovie> movieCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionMovie>> movieCollectionLiveData = new MutableLiveData<>();

    private List<CollectionShow> showCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionShow>> showCollectionLiveData = new MutableLiveData<>();

    private List<WatchlistItem> watchlist = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> watchlistLiveData = new MutableLiveData<>();

    private List<WatchlistItem> recommendations = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> recommendationsLiveData = new MutableLiveData<>();

    private List<Movie> recommendedMovies = new ArrayList<>();
    private MutableLiveData<List<Movie>> recommendedMoviesLiveData = new MutableLiveData<>();

    private List<Show> recommendedShows = new ArrayList<>();
    private MutableLiveData<List<Show>> recommendedShowsLiveData = new MutableLiveData<>();
    
    private List<WatchedMovie> watchedMovies = new ArrayList<>();
    private MutableLiveData<List<WatchedMovie>> watchedMoviesLiveData = new MutableLiveData<>();

    private List<WatchedShow> watchedShows = new ArrayList<>();
    private MutableLiveData<List<WatchedShow>> watchedShowsLiveData = new MutableLiveData<>();

    private List<RatedItem> ratings = new ArrayList<>();
    private MutableLiveData<List<RatedItem>> ratingsLiveData = new MutableLiveData<>();

    private static UserListsRepository instance = null;

    public static UserListsRepository getInstance(String access_token) {
        if (instance == null) {
            instance = new UserListsRepository(access_token);
            return instance;
        }

        return  instance;
    }

    private UserListsRepository(String token) {

        access_token = "Bearer " + token;
        apiService = ApiServiceFactory.getService();
        movieCollectionLiveData.setValue(movieCollection);
        showCollectionLiveData.setValue(showCollection);
        recommendationsLiveData.setValue(recommendations);
    }

    public LiveData<String> getResponse() {
        return postResponse;
    }

    public void searchMovieCollection() {

        Call<List<CollectionMovie>> call = apiService.getMovieCollection(access_token);

        call.enqueue(new Callback<List<CollectionMovie>>() {
            @Override
            public void onResponse(Call<List<CollectionMovie>> call, Response<List<CollectionMovie>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    movieCollection.clear();
                    movieCollection.addAll(response.body());
                    movieCollectionLiveData.setValue(movieCollection);
                }
            }

            @Override
            public void onFailure(Call<List<CollectionMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
    public LiveData<List<CollectionMovie>> getMovieCollection() {
        return movieCollectionLiveData;
    }

    public void searchShowCollection() {

        Call<List<CollectionShow>> call = apiService.getShowCollection(access_token);

        call.enqueue(new Callback<List<CollectionShow>>() {
            @Override
            public void onResponse(Call<List<CollectionShow>> call, Response<List<CollectionShow>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    showCollection.clear();
                    showCollection.addAll(response.body());
                    showCollectionLiveData.setValue(showCollection);
                }
            }

            @Override
            public void onFailure(Call<List<CollectionShow>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
    public LiveData<List<CollectionShow>> getShowCollection() {
        return showCollectionLiveData;
    }


    public void addToCollection(CollectionPostBody collection) {

        Call<Void> call = apiService.addToCollection(access_token, collection);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchMovieCollection();
                searchShowCollection();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully added to Collection!");
                } else {
                    postResponse.setValue("Couldn't add to Collection!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void removeFromCollection(CollectionPostBody collection) {

        Call<Void> call = apiService.removeFromCollection(access_token, collection);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchMovieCollection();
                searchShowCollection();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully removed from Collection!");
                } else {
                    postResponse.setValue("Couldn't remove from Collection!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void searchWatchlist() {

        Call<List<WatchlistItem>> call = apiService.getWatchlist(access_token);

        call.enqueue(new Callback<List<WatchlistItem>>() {
            @Override
            public void onResponse(Call<List<WatchlistItem>> call, Response<List<WatchlistItem>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    watchlist.clear();
                    watchlist.addAll(response.body());
//                    watchlist = watchlist.stream().distinct().collect(Collectors.toList());
                    watchlistLiveData.setValue(watchlist);
                }
            }

            @Override
            public void onFailure(Call<List<WatchlistItem>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
    public LiveData<List<WatchlistItem>> getWatchlist() {
        return watchlistLiveData;
    }

    public void addToWatchlist(WatchlistPostBody watchlist) {
        Call<Void> call = apiService.addToWatchlist(access_token, watchlist);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchWatchlist();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully added to Watchlist!");
                } else {
                    postResponse.setValue("Couldn't add to Watchlist!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void removeFromWatchlist(WatchlistPostBody watchlist) {

        Call<Void> call = apiService.removeFromWatchlist(access_token, watchlist);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchWatchlist();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully removed from Watchlist!");
                } else {
                    postResponse.setValue("Couldn't remove from Watchlist!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void searchRecommendations() {

        Call<List<WatchlistItem>> call = apiService.getRecommendations(access_token);

        call.enqueue(new Callback<List<WatchlistItem>>() {
            @Override
            public void onResponse(Call<List<WatchlistItem>> call, Response<List<WatchlistItem>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    recommendations.clear();
                    recommendations.addAll(response.body());
                    recommendationsLiveData.setValue(recommendations);
                }
            }

            @Override
            public void onFailure(Call<List<WatchlistItem>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
    public LiveData<List<WatchlistItem>> getRecommendations() {
        return recommendationsLiveData;
    }


    public void searchWatchedMovies() {

        Call<List<WatchedMovie>> call = apiService.getWatchedMovies(access_token);

        call.enqueue(new Callback<List<WatchedMovie>>() {
            @Override
            public void onResponse(Call<List<WatchedMovie>> call, Response<List<WatchedMovie>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    watchedMovies.clear();
                    watchedMovies.addAll(response.body());
                    watchedMoviesLiveData.setValue(watchedMovies);
                }
            }

            @Override
            public void onFailure(Call<List<WatchedMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<WatchedMovie>> getWatchedMovies() {
        return watchedMoviesLiveData;
    }


    public void searchWatchedShows() {

        Call<List<WatchedShow>> call = apiService.getWatchedShows(access_token);

        call.enqueue(new Callback<List<WatchedShow>>() {
            @Override
            public void onResponse(Call<List<WatchedShow>> call, Response<List<WatchedShow>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    watchedShows.clear();
                    watchedShows.addAll(response.body());
                    watchedShowsLiveData.setValue(watchedShows);
                }
            }

            @Override
            public void onFailure(Call<List<WatchedShow>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<WatchedShow>> getWatchedShows() {
        return watchedShowsLiveData;
    }

    public void addToHistory(HistoryPostBody history) {
        Call<Void> call = apiService.addToHistory(access_token, history);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchWatchedMovies();
                searchWatchedShows();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully added to History!");
                } else {
                    postResponse.setValue("Couldn't add to History!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void removeFromHistory(HistoryPostBody history) {

        Call<Void> call = apiService.removeFromHistory(access_token, history);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchWatchedMovies();
                searchWatchedShows();
                if (response.isSuccessful()) {
                    postResponse.setValue("Successfully removed from History!");
                } else {
                    postResponse.setValue("Couldn't remove from History!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void searchRatings() {
        Call<List<RatedItem>> call = apiService.getRatings(access_token);

        call.enqueue(new Callback<List<RatedItem>>() {
            @Override
            public void onResponse(Call<List<RatedItem>> call, Response<List<RatedItem>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ratings.clear();
                    ratings.addAll(response.body());
                    ratingsLiveData.setValue(ratings);
                }
            }

            @Override
            public void onFailure(Call<List<RatedItem>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<RatedItem>> getRatings() {
        return ratingsLiveData;
    }

    public void addRating(RatingPostBody ratings) {
        Call<Void> call = apiService.addRating(access_token, ratings);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchRatings();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void removeRating(RatingPostBody ratings) {
        Call<Void> call = apiService.removeRating(access_token, ratings);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                searchRatings();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void searchMovieRecommendations() {

        Call<List<Movie>> call = apiService.getMovieRecommendations(access_token);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    recommendedMovies.clear();
                    recommendedMovies.addAll(response.body());
                    recommendedMoviesLiveData.setValue(recommendedMovies);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<Movie>> getRecommendedMovies() {
        return recommendedMoviesLiveData;
    }

    public void searchShowRecommendations() {

        Call<List<Show>> call = apiService.getShowRecommendations(access_token);

        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    recommendedShows.clear();
                    recommendedShows.addAll(response.body());
                    recommendedShowsLiveData.setValue(recommendedShows);
                }
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<Show>> getRecommendedShows() {
        return recommendedShowsLiveData;
    }

    public void resetResponse() {
        postResponse.setValue(null);
    }
}
