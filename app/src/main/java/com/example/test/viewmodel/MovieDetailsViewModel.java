package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.model.Image;
import com.example.test.model.Movie;
import com.example.test.model.MovieDetails;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;

import java.util.Map;

public class MovieDetailsViewModel extends AbstractImageViewModel {

    private MovieRepository movieRepository;

    public MovieDetailsViewModel(MovieRepository movieRepository, ImageRepository imageRepository) {
        super(imageRepository);
        this.movieRepository = movieRepository;
    }

    public void searchMovieDetails(String slug_id) {
        movieRepository.searchMovieDetails(slug_id);
    }

    public LiveData<MovieDetails> getMovieDetails() {
        return movieRepository.getMovieDetails();
    }

}
