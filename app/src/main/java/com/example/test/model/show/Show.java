package com.example.test.model.show;

import com.example.test.model.AbstractTitledMediaObject;
import com.example.test.model.Ids;

public class Show extends AbstractTitledMediaObject {

    private Integer year;

    public Show(Ids ids, String title) {
        super(ids, title);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
