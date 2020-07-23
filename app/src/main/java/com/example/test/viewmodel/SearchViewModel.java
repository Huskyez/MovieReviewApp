package com.example.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.model.Image;
import com.example.test.model.SearchResult;
import com.example.test.repo.ImageRepository;
import com.example.test.repo.SearchResultRepository;

import java.util.List;
import java.util.Map;

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

    public void search(String type, String query) {
        searchResultRepository.search(type, query);
    }




}
