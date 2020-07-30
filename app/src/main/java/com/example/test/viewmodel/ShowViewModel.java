package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.test.model.movie.AnticipatedMovie;
import com.example.test.model.movie.Movie;
import com.example.test.model.movie.RecommendedMovie;
import com.example.test.model.movie.TrendingMovie;
import com.example.test.model.show.AnticipatedShow;
import com.example.test.model.show.RecommendedShow;
import com.example.test.model.show.Show;
import com.example.test.model.show.TrendingShow;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.ShowRepository;

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
