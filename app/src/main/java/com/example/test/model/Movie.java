package com.example.test.model;

import androidx.annotation.NonNull;

public class Movie {

    private String title;
    private Integer year;

    private Ids ids;

    public Movie() {
    }

    public Movie(String title, Integer year, Ids ids) {
        this.title = title;
        this.year = year;
        this.ids = ids;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " - " + year + " - " + ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }
}
