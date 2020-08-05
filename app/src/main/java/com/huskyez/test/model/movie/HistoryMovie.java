package com.huskyez.test.model.movie;

import com.huskyez.test.model.common.AbstractTitledMediaObject;
import com.huskyez.test.model.common.Ids;

public class HistoryMovie extends Movie {

    public String watched_at;

    public HistoryMovie(String title, Integer year, Ids ids, String watched_at) {
        super(title, year, ids);
        this.watched_at = watched_at;
    }
}
