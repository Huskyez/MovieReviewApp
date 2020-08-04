package com.huskyez.test.model.common;

import androidx.annotation.NonNull;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ids ids = (Ids) o;
        return Objects.equals(trakt, ids.trakt) &&
                Objects.equals(slug, ids.slug) &&
                Objects.equals(imdb, ids.imdb) &&
                Objects.equals(tmdb, ids.tmdb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trakt, slug, imdb, tmdb);
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
