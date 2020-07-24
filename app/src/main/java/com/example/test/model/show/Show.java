package com.example.test.model.show;

import com.example.test.model.Ids;

public class Show {

    private String title;
    private Integer year;
    private Ids ids;

    public Show() {
    }

    public Show(String title, Integer year, Ids ids) {
        this.title = title;
        this.year = year;
        this.ids = ids;
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
