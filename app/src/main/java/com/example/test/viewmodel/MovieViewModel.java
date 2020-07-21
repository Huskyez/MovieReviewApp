package com.example.test.viewmodel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.model.Image;
import com.example.test.model.Movie;
import com.example.test.model.TrendingMovie;
import com.example.test.repo.MovieRepository;

import java.io.IOException;
import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<List<TrendingMovie>> trendingMovies;
    private MutableLiveData<List<Image>> trendingMovieImages;


    public MovieViewModel(MovieRepository movieRepository) {
        trendingMovies = new MutableLiveData<>();
        trendingMovieImages = new MutableLiveData<>();
        this.movieRepository = movieRepository;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    public void updateTrendingMovies() {
        movieRepository.searchTrendingMovies();
//        trendingMovies.setValue(movieRepository.getTrendingMovies());
    }

    public void searchImage(Integer tmdb_id, String type) {
        movieRepository.searchImage(tmdb_id, type);
    }

    public LiveData<List<Image>> getImagesTrending() {
        return movieRepository.getImagesTrending();
    }

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return movieRepository.getTrendingMovies();
    }

    public LiveData<List<Pair<TrendingMovie, Image>>> getTrendingMoviesData() {
        return movieRepository.getTrendingMoviesData();
    }

//    public void clearImages() {
//        movieRepository.clearImages();
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
