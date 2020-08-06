package com.huskyez.movieapp.model.movie;

import com.huskyez.movieapp.model.common.Ids;

public class HistoryMovie extends Movie {

    public String watched_at;

    public HistoryMovie(String title, Integer year, Ids ids, String watched_at) {
        super(title, year, ids);
        this.watched_at = watched_at;
    }
}
