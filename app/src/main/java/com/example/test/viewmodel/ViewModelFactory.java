package com.example.test.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;
import com.example.test.repo.SearchResultRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ImageRepository imageRepository = ImageRepository.getInstance();
        MovieRepository movieRepository = MovieRepository.getInstance();
        SearchResultRepository searchResultRepository = SearchResultRepository.getInstance();

        if (modelClass == MovieViewModel.class) {

            return (T) new MovieViewModel(movieRepository, imageRepository);
        }

        if (modelClass == SearchViewModel.class) {

            return (T) new SearchViewModel(searchResultRepository, imageRepository);
        }

        if (modelClass == MovieDetailsViewModel.class) {
            return (T) new MovieDetailsViewModel(movieRepository, imageRepository);
        }

        return null;
    }
}
