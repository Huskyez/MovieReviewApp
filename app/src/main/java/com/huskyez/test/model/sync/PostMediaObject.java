package com.huskyez.test.model.sync;

import com.huskyez.test.model.common.Ids;

public class PostMediaObject {

    public String collected_at;
    public String title;
    public Integer year;
    public Ids ids;

    public PostMediaObject(String collected_at, String title, Integer year, Ids ids) {
        this.collected_at = collected_at;
        this.title = title;
        this.year = year;
        this.ids = ids;
    }
}
