package com.huskyez.movieapp.model.show;

import com.huskyez.movieapp.model.common.AbstractTitledMediaObject;
import com.huskyez.movieapp.model.common.Ids;

public class Show extends AbstractTitledMediaObject {

    private Integer year;

    public Show(Ids ids, String title, Integer year) {
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
