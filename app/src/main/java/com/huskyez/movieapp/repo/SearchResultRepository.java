package com.huskyez.movieapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huskyez.movieapp.api.ApiService;
import com.huskyez.movieapp.api.ApiServiceFactory;
import com.huskyez.movieapp.model.common.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultRepository {

    private static SearchResultRepository instance = null;

    private ApiService apiService;

    private MutableLiveData<List<SearchResult>> movieSearchResults;
    private MutableLiveData<List<SearchResult>> showsSearchResults;

    private MutableLiveData<List<SearchResult>> searchResults;

    private SearchResultRepository() {
        apiService = ApiServiceFactory.getService();
        searchResults = new MutableLiveData<>();
        searchResults.setValue(new ArrayList<>());

        movieSearchResults = new MutableLiveData<>();
        showsSearchResults = new MutableLiveData<>();
        movieSearchResults.setValue(new ArrayList<>());
        showsSearchResults.setValue(new ArrayList<>());
    }

    public static SearchResultRepository getInstance() {
        if (instance == null) {
            instance = new SearchResultRepository();
            return instance;
        }

        return instance;
    }

    public void search(String type, String query) {

        Call<List<SearchResult>> call = apiService.search(type, query);

        call.enqueue(new Callback<List<SearchResult>>() {
            @Override
            public void onResponse(Call<List<SearchResult>> call, Response<List<SearchResult>> response) {

                assert response.body() != null;
//                searchResults.setValue(response.body());

                if (response.body().size() == 0) {
                    movieSearchResults.setValue(response.body());
                    showsSearchResults.setValue(response.body());
                } else {
                    if (response.body().get(0).getType().equals("movie")) {
                        movieSearchResults.setValue(response.body());
                    } else {
                        showsSearchResults.setValue(response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SearchResult>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<SearchResult>> getSearchResults() {
        return searchResults;
    }

    public LiveData<List<SearchResult>> getMovieSearchResults() {
        return movieSearchResults;
    }

    public LiveData<List<SearchResult>> getShowSearchResults() {
        return showsSearchResults;
    }
}
