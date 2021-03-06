package com.huskyez.movieapp.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.movieapp.model.movie.AnticipatedMovie;
import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.movie.RecommendedMovie;
import com.huskyez.movieapp.model.movie.TrendingMovie;
import com.huskyez.movieapp.repo.ImageRepository;
import com.huskyez.movieapp.repo.MovieRepository;

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

    public LiveData<List<TrendingMovie>> getTrendingMovies() {
        return movieRepository.getTrendingMovies();
    }

    public void updateTrendingMovies() {
        movieRepository.searchTrendingMovies();
    }

    public LiveData<List<AnticipatedMovie>> getAnticipatedMovies() {
        return movieRepository.getAnticipatedMovies();
    }

    public void updateAnticipatedMovies() {
        movieRepository.searchAnticipatedMovies();
    }

    public void updateRecommendedMovies(String period) {
        movieRepository.searchRecommendedMovies(period);
    }

    public LiveData<List<RecommendedMovie>> getRecommendedMovies() {
        return movieRepository.getRecommendedMovies();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
