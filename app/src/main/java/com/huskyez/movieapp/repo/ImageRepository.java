package com.huskyez.movieapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huskyez.movieapp.api.ApiService;
import com.huskyez.movieapp.api.ApiServiceFactory;
import com.huskyez.movieapp.model.image.Image;
import com.huskyez.movieapp.model.image.ImageSearchResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private static ImageRepository instance = null;

    private ApiService apiService;

    // These contain a list of images from the last request
    private Map<Integer, Image> imageMap; // tmdb_id and image
    private MutableLiveData<Map<Integer, Image>> imageMutableLiveData;

    private ImageRepository() {
        apiService = ApiServiceFactory.getService();
        imageMutableLiveData = new MutableLiveData<>();
        imageMap = new HashMap<>();
    }

    public static ImageRepository getInstance() {
        if (instance == null) {
            instance = new ImageRepository();
            return instance;
        }

        return instance;
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

                if (imageSearchResult == null) {
                    return;
                }
                //find a english poster
                Optional<Image> optionalImage = imageSearchResult.getPosters().stream().filter(x -> x.getIso_639_1() == null || x.getIso_639_1().equals("en")).findFirst();
                // if no english poster is found, take the first one

                if (imageSearchResult.getPosters().isEmpty()) {
                    return;
                }

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
