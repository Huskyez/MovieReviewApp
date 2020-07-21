package com.example.test.repo;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.Image;
import com.example.test.model.ImageSearchResult;
import com.example.test.model.Movie;
import com.example.test.model.SearchResult;
import com.example.test.model.TrendingMovie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ApiService apiService;

    private MutableLiveData<List<TrendingMovie>> movies = new MutableLiveData<>();
//    private MutableLiveData<List<Image>> images = new MutableLiveData<>();

    private List<TrendingMovie> moviesList = new ArrayList<>();
//    private List<Image> imagesList = new ArrayList<>();

//    List<Pair<TrendingMovie, Image>> pairList = new ArrayList<>();
//    private MutableLiveData<List<Pair<TrendingMovie, Image>>> pairMutableLiveData = new MutableLiveData<>();

    public MovieRepository() {
        apiService = ApiServiceFactory.getService();
        movies.setValue(moviesList);
//        images.setValue(imagesList);

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

        call.enqueue(new Callback<List<TrendingMovie>>() {
            @Override
            public void onResponse(Call<List<TrendingMovie>> call, Response<List<TrendingMovie>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
//                imagesList.clear();
                moviesList.clear();
//                pairList.clear();

                moviesList.addAll(response.body());
                movies.setValue(moviesList);
//                moviesList.forEach(x -> searchImage(x.getMovie().getIds().getTmdb(), "movie"));
//                response.body().forEach(x -> searchImage(x.getMovie().getIds().getTmdb(), "movie"));
            }

            @Override
            public void onFailure(Call<List<TrendingMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

//    public LiveData<List<Image>> getImagesTrending() {
//        return images;
//    }

    //TODO: refactor this, does too much stuff
//    public void searchImage(Integer tmdb_id, String type) {
//
//        Call<ImageSearchResult> call = apiService.searchImages(type, tmdb_id, "366bb8cd1b82ca2f219a0f72303f68e9");
//
//        call.enqueue(new Callback<ImageSearchResult>() {
//            @Override
//            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
//                if (!response.isSuccessful()) {
//                    System.out.println(response.errorBody());
//                }
//                assert response.body() != null;
//                ImageSearchResult imageSearchResult = response.body();
//                Optional<Image> optionalImage = imageSearchResult.getPosters().stream().filter(x -> x.getIso_639_1() == null || x.getIso_639_1().equals("en")).findFirst();
//                Image toAdd = optionalImage.orElseGet(() -> imageSearchResult.getPosters().get(0));
//                Optional<TrendingMovie> movie = moviesList.stream().filter(x -> x.getMovie().getIds().getTmdb().equals(imageSearchResult.getId())).findFirst();
//                movie.ifPresent(trendingMovie -> pairList.add(new Pair<>(trendingMovie, toAdd)));
//                pairMutableLiveData.setValue(pairList);
//            }
//
//            @Override
//            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
//                t.getStackTrace();
//            }
//        });
//    }

//    public LiveData<List<Pair<TrendingMovie, Image>>> getTrendingMoviesData() {
//        return pairMutableLiveData;
//    }

}
