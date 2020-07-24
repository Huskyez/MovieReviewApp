package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.test.model.movie.MovieDetails;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;

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
