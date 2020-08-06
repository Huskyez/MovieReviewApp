package com.huskyez.movieapp.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.movieapp.model.movie.MovieDetails;
import com.huskyez.movieapp.repo.ImageRepository;
import com.huskyez.movieapp.repo.MovieRepository;

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
