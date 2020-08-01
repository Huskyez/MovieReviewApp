package com.example.test.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.WatchlistItem;
import com.example.test.model.movie.CollectionMovie;
import com.example.test.model.show.CollectionShow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListsRepository {

    private ApiService apiService;

    private List<CollectionMovie> movieCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionMovie>> movieCollectionLiveData = new MutableLiveData<>();

    private List<CollectionShow> showCollection = new ArrayList<>();
    private MutableLiveData<List<CollectionShow>> showCollectionLiveData = new MutableLiveData<>();

    private List<WatchlistItem> watchlist = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> watchlistLiveData = new MutableLiveData<>();

    private List<WatchlistItem> recommendations = new ArrayList<>();
    private MutableLiveData<List<WatchlistItem>> recommendationsLiveData = new MutableLiveData<>();

    private static UserListsRepository instance = null;

    public static UserListsRepository getInstance() {
        if (instance == null) {
            instance = new UserListsRepository();
            return instance;
        }

        return  instance;
    }

    private UserListsRepository() {
        apiService = ApiServiceFactory.getService();
        movieCollectionLiveData.setValue(movieCollection);
        showCollectionLiveData.setValue(showCollection);
        recommendationsLiveData.setValue(recommendations);
    }

    public void searchMovieCollection(String access_token) {

        String toSend = "Bearer " + access_token;

        Call<List<CollectionMovie>> call = apiService.getMovieCollection(toSend);

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

    public void searchShowCollection(String access_token) {

        String toSend = "Bearer " + access_token;
        Call<List<CollectionShow>> call = apiService.getShowCollection(toSend);

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

    public void searchWatchlist(String access_token, String type) {
        String toSend = "Bearer " + access_token;
        Call<List<WatchlistItem>> call = apiService.getWatchlist(toSend);// , type);

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
        return recommendationsLiveData;
    }

    
    public void searchRecommendations(String access_token, String type) {
        String toSend = "Bearer " + access_token;
        Call<List<WatchlistItem>> call = apiService.getRecommendations(toSend, type);

        call.enqueue(new Callback<List<WatchlistItem>>() {
            @Override
            public void onResponse(Call<List<WatchlistItem>> call, Response<List<WatchlistItem>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    recommendations = recommendations.stream().distinct().collect(Collectors.toList());
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
}
