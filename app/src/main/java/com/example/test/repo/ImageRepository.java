package com.example.test.repo;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.api.ApiService;
import com.example.test.api.ApiServiceFactory;
import com.example.test.model.Image;
import com.example.test.model.ImageSearchResult;
import com.example.test.model.TrendingMovie;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private ApiService apiService;

    // These contain a list of images from the last request
    private Map<Integer, Image> imageMap; // tmdb_id and image
    private MutableLiveData<Map<Integer, Image>> imageMutableLiveData;

    public ImageRepository() {
        apiService = ApiServiceFactory.getService();
        imageMutableLiveData = new MutableLiveData<>();
        imageMap = new HashMap<>();
    }

    public void searchImage(Integer tmdb_id, String type) {

        Call<ImageSearchResult> call = apiService.searchImages(type, tmdb_id, "366bb8cd1b82ca2f219a0f72303f68e9");

        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody());
                }
                assert response.body() != null;
                ImageSearchResult imageSearchResult = response.body();
                //find a english poster
                Optional<Image> optionalImage = imageSearchResult.getPosters().stream().filter(x -> x.getIso_639_1() == null || x.getIso_639_1().equals("en")).findFirst();
                // if no english poster is found, take the first one
                Image toAdd = optionalImage.orElseGet(() -> imageSearchResult.getPosters().get(0));
                // add image to cache
                imageMap.put(imageSearchResult.getId(), toAdd);
                // update the observable
                imageMutableLiveData.setValue(imageMap);
            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public LiveData<Map<Integer, Image>> getImageCache() {
        return imageMutableLiveData;
    }

    public Image getImage(Integer tmdb_id) {
        if (imageMap.containsKey(tmdb_id)) {
            return imageMap.get(tmdb_id);
        }
        return null;
    }

    public void clearImageCache() {
        imageMap.clear();
        imageMutableLiveData.setValue(imageMap);
    }

}