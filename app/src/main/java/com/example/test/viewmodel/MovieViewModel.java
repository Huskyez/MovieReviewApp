package com.example.test.viewmodel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.model.Image;
import com.example.test.model.Movie;
import com.example.test.model.TrendingMovie;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieViewModel extends AbstractImageViewModel {

    private MovieRepository movieRepository;

//    private MutableLiveData<List<TrendingMovie>> trendingMovies;
//    private MutableLiveData<Map<Integer, Image>> trendingMovieImages;
//
//    private MutableLiveData<List<Pair<TrendingMovie, Image>>> trendingMovieData;

    public MovieViewModel(MovieRepository movieRepository, ImageRepository imageRepository) {
        super(imageRepository);
        this.movieRepository = movieRepository;
//        trendingMovies = new MutableLiveData<>();
//        trendingMovieImages = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    public void updateTrendingMovies() {
        movieRepository.searchTrendingMovies();
    }

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return movieRepository.getTrendingMovies();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
