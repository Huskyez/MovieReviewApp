package com.huskyez.test.model.show;

import com.huskyez.test.model.common.AbstractTitledMediaObject;
import com.huskyez.test.model.common.Ids;

public class Episode extends AbstractTitledMediaObject {

    private Integer season;
    private Integer number;

    public Episode(Ids ids, String title) {
        super(ids, title);
    }

    public Episode(Ids ids, String title, Integer season, Integer number) {
        super(ids, title);
        this.season = season;
        this.number = number;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
