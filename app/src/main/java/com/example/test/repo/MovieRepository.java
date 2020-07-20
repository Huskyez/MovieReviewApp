package com.example.test.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.Image;
import com.example.test.model.ImageSearchResult;
import com.example.test.model.Movie;
import com.example.test.model.TrendingMovie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ApiService apiService;

    private MutableLiveData<List<TrendingMovie>> movies = new MutableLiveData<>();
    private MutableLiveData<List<Image>> images = new MutableLiveData<>();


    public MovieRepository() {
        apiService = ApiServiceFactory.getService();
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        Call<List<Movie>> call = apiService.getPopular();

        MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (!response.isSuccessful()) {
                    //TODO: do some error handling
                }
                movies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.getStackTrace();
            }
        });

        return movies;
    }

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return movies;
    }

    public void searchTrendingMovies() {
        Call<List<TrendingMovie>> call = apiService.getTrending();

        List<TrendingMovie> moviesList = new ArrayList<>();

        call.enqueue(new Callback<List<TrendingMovie>>() {
            @Override
            public void onResponse(Call<List<TrendingMovie>> call, Response<List<TrendingMovie>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                moviesList.addAll(response.body());
                movies.setValue(moviesList);
            }

            @Override
            public void onFailure(Call<List<TrendingMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void clearImages() {
        images.setValue(new ArrayList<>());
    }

    public LiveData<List<Image>> getImagesTrending() {
        return images;
    }

    public void searchImage(Integer tmdb_id, String type) {

        // ??????
//        final Image[] image = new Image[1];
        Call<ImageSearchResult> call = apiService.searchImages(type, tmdb_id, "366bb8cd1b82ca2f219a0f72303f68e9");
//        return call.execute().body();


        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                ImageSearchResult imageSearchResult = response.body();
                List<Image> imageList = images.getValue();
                assert imageList != null;
                imageList.add(imageSearchResult.getPosters().get(0));
                images.setValue(imageList);
            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

//    public ImageSearchResult getPreviousImageSearchResult() {
//        return imageSearchResult;
//    }

}
