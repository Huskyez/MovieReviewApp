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

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private ImageRepository imageRepository;

    private MutableLiveData<List<TrendingMovie>> trendingMovies;
    private MutableLiveData<Map<Integer, Image>> trendingMovieImages;

    private MutableLiveData<List<Pair<TrendingMovie, Image>>> trendingMovieData;


    public MovieViewModel(MovieRepository movieRepository) {
        trendingMovies = new MutableLiveData<>();
        trendingMovieImages = new MutableLiveData<>();
        this.movieRepository = movieRepository;

//        imageRespository.getImageCache().observe(this, map -> {
//
//        });

    }

    public LiveData<List<Movie>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    public void updateTrendingMovies() {
        movieRepository.searchTrendingMovies();
    }

//    public void searchImage(Integer tmdb_id, String type) {
//        movieRepository.searchImage(tmdb_id, type);
//    }

//    public LiveData<List<Image>> getImagesTrending() {
//        return movieRepository.getImagesTrending();
//    }

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return movieRepository.getTrendingMovies();
    }

//    public LiveData<List<Pair<TrendingMovie, Image>>> getTrendingMoviesData() {
//        List<Pair<TrendingMovie, Image>> pairList = new ArrayList<>();
//
//        return movieRepository.getTrendingMoviesData();
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
