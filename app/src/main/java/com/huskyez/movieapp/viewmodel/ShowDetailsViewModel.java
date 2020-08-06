package com.huskyez.movieapp.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.movieapp.model.show.ShowDetails;
import com.huskyez.movieapp.repo.ImageRepository;
import com.huskyez.movieapp.repo.ShowRepository;

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
