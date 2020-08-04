package com.huskyez.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.test.model.show.ShowDetails;
import com.huskyez.test.repo.ImageRepository;
import com.huskyez.test.repo.ShowRepository;

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
