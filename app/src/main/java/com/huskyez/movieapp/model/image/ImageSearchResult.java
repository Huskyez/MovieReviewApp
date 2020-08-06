package com.huskyez.movieapp.model.image;

import java.util.ArrayList;
import java.util.List;

public class ImageSearchResult {

    private Integer id;
    private List<Image> backdrops = new ArrayList<>();
    private List<Image> posters = new ArrayList<>();

    public ImageSearchResult() {
    }

    public ImageSearchResult(Integer id, List<Image> backdrops, List<Image> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }
}
