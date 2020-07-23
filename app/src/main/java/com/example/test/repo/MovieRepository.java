package com.example.test.repo;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.Image;
import com.example.test.model.ImageSearchResult;
import com.example.test.model.Movie;
import com.example.test.model.MovieDetails;
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

    private static MovieRepository instance = null;

    private ApiService apiService;

    private List<TrendingMovie> moviesList = new ArrayList<>();
    private MutableLiveData<List<TrendingMovie>> movies = new MutableLiveData<>();

    private MovieDetails movieDetails;
    private MutableLiveData<MovieDetails> movieDetailsLiveData = new MutableLiveData<>();

//    private List<Image> imagesList = new ArrayList<>();

//    List<Pair<TrendingMovie, Image>> pairList = new ArrayList<>();
//    private MutableLiveData<List<Pair<TrendingMovie, Image>>> pairMutableLiveData = new MutableLiveData<>();

    private MovieRepository() {
        apiService = ApiServiceFactory.getService();
        movies.setValue(moviesList);
        movieDetailsLiveData.setValue(movieDetails);
//        images.setValue(imagesList);

    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
            return instance;
        }

        return instance;
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

    public void searchMovieDetails(String slug_id) {
        Call<MovieDetails> call = apiService.getMovieDetails(slug_id);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                movieDetails = response.body();
                movieDetailsLiveData.setValue(movieDetails);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<MovieDetails> getMovieDetails() {
        return movieDetailsLiveData;
    }
}
