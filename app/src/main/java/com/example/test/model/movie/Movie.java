package com.example.test.model.movie;

import androidx.annotation.NonNull;

import com.example.test.model.AbstractMediaObject;
import com.example.test.model.AbstractTitledMediaObject;
import com.example.test.model.Ids;

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
