package com.example.test.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.movie.AnticipatedMovie;
import com.example.test.model.movie.Movie;
import com.example.test.model.movie.MovieDetails;
import com.example.test.model.movie.RecommendedMovie;
import com.example.test.model.movie.TrendingMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository instance = null;

    private ApiService apiService;

    private List<Movie> popularMovies = new ArrayList<>();
    private MutableLiveData<List<Movie>> popularMoviesLiveData = new MutableLiveData<>();

    private List<TrendingMovie> trendingMovies = new ArrayList<>();
    private MutableLiveData<List<TrendingMovie>> trendingMoviesLiveData = new MutableLiveData<>();

    private List<AnticipatedMovie> anticipatedMovies = new ArrayList<>();
    private MutableLiveData<List<AnticipatedMovie>> anticipatedMoviesLiveData = new MutableLiveData<>();

    private List<RecommendedMovie> recommendedMovies = new ArrayList<>();
    private MutableLiveData<List<RecommendedMovie>> recommendedMoviesLiveData = new MutableLiveData<>();

    private MovieDetails movieDetails;
    private MutableLiveData<MovieDetails> movieDetailsLiveData = new MutableLiveData<>();




    private MovieRepository() {
        apiService = ApiServiceFactory.getService();
        trendingMoviesLiveData.setValue(trendingMovies);
        movieDetailsLiveData.setValue(movieDetails);
        anticipatedMoviesLiveData.setValue(anticipatedMovies);
        recommendedMoviesLiveData.setValue(recommendedMovies);
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
            return instance;
        }

        return instance;
    }

    public void searchPopularMovies() {
        Call<List<Movie>> call = apiService.getPopularMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (!response.isSuccessful()) {
                    //TODO: do some error handling
                }
                assert response.body() != null;
                popularMovies.clear();
                popularMovies.addAll(response.body());
                popularMoviesLiveData.setValue(popularMovies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return popularMoviesLiveData;
    }

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return trendingMoviesLiveData;
    }

    public void searchTrendingMovies() {
        Call<List<TrendingMovie>> call = apiService.getTrendingMovies();

        call.enqueue(new Callback<List<TrendingMovie>>() {
            @Override
            public void onResponse(Call<List<TrendingMovie>> call, Response<List<TrendingMovie>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                trendingMovies.clear();
                trendingMovies.addAll(response.body());
                trendingMoviesLiveData.setValue(trendingMovies);
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

    public void searchAnticipatedMovies() {
        Call<List<AnticipatedMovie>> call = apiService.getAnticipatedMovies();

        call.enqueue(new Callback<List<AnticipatedMovie>>() {
            @Override
            public void onResponse(Call<List<AnticipatedMovie>> call, Response<List<AnticipatedMovie>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                anticipatedMovies.clear();
                anticipatedMovies.addAll(response.body());
                anticipatedMoviesLiveData.setValue(anticipatedMovies);
            }

            @Override
            public void onFailure(Call<List<AnticipatedMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<AnticipatedMovie>> getAnticipatedMovies() {
        return anticipatedMoviesLiveData;
    }

    public void searchRecommendedMovies(String period) {

        Call<List<RecommendedMovie>> call = apiService.getRecommendedMovies(period);

        call.enqueue(new Callback<List<RecommendedMovie>>() {
            @Override
            public void onResponse(Call<List<RecommendedMovie>> call, Response<List<RecommendedMovie>> response) {
                assert response.body() != null;
                recommendedMovies.clear();
                recommendedMovies.addAll(response.body());
                recommendedMoviesLiveData.setValue(recommendedMovies);
            }

            @Override
            public void onFailure(Call<List<RecommendedMovie>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<List<RecommendedMovie>> getRecommendedMovies() {
        return recommendedMoviesLiveData;
    }
}
