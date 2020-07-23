package com.example.test.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultRepository {

    private static SearchResultRepository instance = null;

    private ApiService apiService;

    private MutableLiveData<List<SearchResult>> searchResults;

    private SearchResultRepository() {
        apiService = ApiServiceFactory.getService();
        searchResults = new MutableLiveData<>();
        searchResults.setValue(new ArrayList<>());
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
                searchResults.setValue(response.body());
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

}
