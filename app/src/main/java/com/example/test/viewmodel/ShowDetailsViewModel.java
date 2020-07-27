package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.test.model.movie.MovieDetails;
import com.example.test.model.show.ShowDetails;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.MovieRepository;
import com.example.test.repo.ShowRepository;

public class ShowDetailsViewModel extends AbstractImageViewModel {


    private ShowRepository showRepository;

    public ShowDetailsViewModel(ShowRepository showRepository, ImageRepository imageRepository) {
        super(imageRepository);
        this.showRepository = showRepository;
    }

    public void searchShowDetails(String slug_id) {
        showRepository.searchShowDetails(slug_id);
    }

    public LiveData<ShowDetails> getShowDetails() {
        return showRepository.getShowDetails();
    }
}
