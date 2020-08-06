package com.huskyez.movieapp.model.movie;

import java.util.Objects;

public class TrendingMovie {

    private Integer watchers;
    private Movie movie;

    public TrendingMovie() {
    }

    public TrendingMovie(Integer watchers, Movie movie) {
        this.watchers = watchers;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrendingMovie that = (TrendingMovie) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "TrendingMovie{" +
                "watchers=" + watchers +
                ", movie=" + movie +
                '}';
    }
}
