package com.example.test.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.WatchlistItem;
import com.example.test.model.movie.CollectionMovie;
import com.example.test.model.movie.WatchedMovie;
import com.example.test.model.show.CollectionShow;
import com.example.test.model.show.WatchedShow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListsRepository {

    private ApiService apiService;
    private String access_token;

    private List<CollectionMovie> movieCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionMovie>> movieCollectionLiveData = new MutableLiveData<>();

    private List<CollectionShow> showCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionShow>> showCollectionLiveData = new MutableLiveData<>();

    private List<WatchlistItem> watchlist = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> watchlistLiveData = new MutableLiveData<>();

    private List<WatchlistItem> recommendations = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> recommendationsLiveData = new MutableLiveData<>();

    private List<WatchedMovie> watchedMovies = new ArrayList<>();
    private MutableLiveData<List<WatchedMovie>> watchedMoviesLiveData = new MutableLiveData<>();

    private List<WatchedShow> watchedShows = new ArrayList<>();
    private MutableLiveData<List<WatchedShow>> watchedShowsLiveData = new MutableLiveData<>();

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
}
