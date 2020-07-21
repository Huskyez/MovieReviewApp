package com.example.test.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.repo.MovieRepository;
import com.example.test.repo.SearchResultRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MovieViewModel.class) {
            MovieRepository movieRepository = new MovieRepository();
            return (T) new MovieViewModel(movieRepository);
        }

        if (modelClass == SearchViewModel.class) {
            SearchResultRepository searchResultRepository = new SearchResultRepository();
            return (T) new SearchViewModel(searchResultRepository);
        }

        return null;
    }
}
