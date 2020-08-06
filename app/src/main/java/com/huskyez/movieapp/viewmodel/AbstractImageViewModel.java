package com.huskyez.movieapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huskyez.movieapp.model.image.Image;
import com.huskyez.movieapp.repo.ImageRepository;

import java.util.Map;

public abstract class AbstractImageViewModel extends ViewModel {

    private ImageRepository imageRepository;

    public AbstractImageViewModel(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public LiveData<Map<Integer, Image>> getImageCache() {
        return imageRepository.getImageCache();
    }

    public Image getImage(Integer tmdb_id) {
        return imageRepository.getImage(tmdb_id);
    }

    public void searchImage(Integer tmdb_id, String type) {
        imageRepository.searchImage(tmdb_id, type);
    }

    public void clearImageCache() {
        imageRepository.clearImageCache();
    }

}
