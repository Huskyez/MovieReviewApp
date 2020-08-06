package com.huskyez.movieapp.model.image;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("aspect_ratio")
    private Double aspectRatio;

    @SerializedName("file_path")
    private String path;

    private Integer height;
    private Integer width;

    // whatever this is
    private String iso_639_1;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("vote_average")
    private Double voteAverage;

    public Image() {
    }

    public Image(Double aspectRatio, String path, Integer height, Integer width, String iso_639_1, Integer voteCount, Double voteAverage) {
        this.aspectRatio = aspectRatio;
        this.path = path;
        this.height = height;
        this.width = width;
        this.iso_639_1 = iso_639_1;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
