package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.test.model.movie.Movie;
import com.example.test.model.movie.TrendingMovie;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;

import java.util.List;

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

    public void updatePopularMovies() {
        movieRepository.searchPopularMovies();
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
