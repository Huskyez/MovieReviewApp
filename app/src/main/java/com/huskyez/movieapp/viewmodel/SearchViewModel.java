package com.huskyez.movieapp.viewmodel;

import androidx.lifecycle.LiveData;

import com.huskyez.movieapp.model.common.SearchResult;
import com.huskyez.movieapp.repo.ImageRepository;
import com.huskyez.movieapp.repo.SearchResultRepository;

import java.util.List;

public class SearchViewModel extends AbstractImageViewModel {

    private SearchResultRepository searchResultRepository;

//    private MutableLiveData<List<SearchResult>> searchResultList;

    public SearchViewModel(SearchResultRepository searchResultRepository, ImageRepository imageRepository) {
        super(imageRepository);
        this.searchResultRepository = searchResultRepository;
    }



    public LiveData<List<SearchResult>> getSearchResults() {
        return searchResultRepository.getSearchResults();
    }

    public LiveData<List<SearchResult>> getMovieSearchResults() {
        return searchResultRepository.getMovieSearchResults();
    }

    public LiveData<List<SearchResult>> getShowSearchResults() {
        return searchResultRepository.getShowSearchResults();
    }


    public void search(String type, String query) {
        searchResultRepository.search(type, query);
    }




}
