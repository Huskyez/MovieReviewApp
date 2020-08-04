package com.huskyez.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.test.model.show.AnticipatedShow;
import com.huskyez.test.model.show.RecommendedShow;
import com.huskyez.test.model.show.Show;
import com.huskyez.test.model.show.TrendingShow;
import com.huskyez.test.repo.ImageRepository;
import com.huskyez.test.repo.ShowRepository;

import java.util.List;

public class ShowViewModel extends AbstractImageViewModel {

    private ShowRepository showRepository;

    public ShowViewModel(ShowRepository showRepository, ImageRepository imageRepository) {
        super(imageRepository);
        this.showRepository = showRepository;
    }

    public LiveData<List<Show>> getPopularShows() {
        return showRepository.getPopularShows();
    }

    public void updatePopularShows() {
        showRepository.searchPopularShows();
    }

    public LiveData<List<TrendingShow>> getTrendingShows() {
        return showRepository.getTrendingShows();
    }

    public void updateTrendingShows() {
        showRepository.searchTrendingShows();
    }

    public LiveData<List<AnticipatedShow>> getAnticipatedShows() {
        return showRepository.getAnticipatedShows();
    }

    public void updateAnticipatedShows() {
        showRepository.searchAnticipatedShows();
    }

    public void updateRecommendedShows(String period) {
        showRepository.searchRecommendedShows(period);
    }

    public LiveData<List<RecommendedShow>> getRecommendedShows() {
        return showRepository.getRecommendedShows();
    }
}
