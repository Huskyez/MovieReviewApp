package com.example.test.model;

import androidx.annotation.NonNull;

public class Ids {

    private Integer trakt;
    private String slug;
    private String imdb;
    private Integer tmdb;

    public Ids() {
    }

    public Ids(Integer trakt, String slug, String imdb, Integer tmdb) {
        this.trakt = trakt;
        this.slug = slug;
        this.imdb = imdb;
        this.tmdb = tmdb;
    }


    @NonNull
    @Override
    public String toString() {
        return "[" + trakt + ", " + slug + ", " + imdb + ", " + tmdb + "]";
    }

    public Integer getTrakt() {
        return trakt;
    }

    public void setTrakt(Integer trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public Integer getTmdb() {
        return tmdb;
    }

    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }
}
