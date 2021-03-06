package com.huskyez.movieapp.model.movie;

import com.huskyez.movieapp.model.common.AbstractTitledMediaObject;
import com.huskyez.movieapp.model.common.Ids;

public class Movie extends AbstractTitledMediaObject {

    private Integer year;

    public Movie(Ids ids, String title) {
        super(ids, title);
    }

    public Movie(String title, Integer year, Ids ids) {
        super(ids, title);
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
