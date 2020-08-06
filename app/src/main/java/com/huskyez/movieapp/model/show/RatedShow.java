package com.huskyez.movieapp.model.show;

import com.huskyez.movieapp.model.common.Ids;

public class RatedShow extends Show {
    private Integer rating;

    public RatedShow(Ids ids, String title, Integer year, Integer rating) {
        super(ids, title, year);
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
