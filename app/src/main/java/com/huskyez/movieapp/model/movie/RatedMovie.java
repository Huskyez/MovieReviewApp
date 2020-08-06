package com.huskyez.movieapp.model.movie;

import com.huskyez.movieapp.model.common.Ids;

public class RatedMovie extends Movie {

    private String rated_at;
    private Integer rating;

    public RatedMovie(String title, Integer year, Ids ids, String rated_at, Integer rating) {
        super(title, year, ids);
        this.rated_at = rated_at;
        this.rating = rating;
    }

    public String getRated_at() {
        return rated_at;
    }

    public void setRated_at(String rated_at) {
        this.rated_at = rated_at;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
