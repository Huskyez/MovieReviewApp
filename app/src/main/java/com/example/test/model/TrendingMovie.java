package com.example.test.model;

public class TrendingMovie {

    private Integer watchers;
    private Movie movie;

    public TrendingMovie() {
    }

    public TrendingMovie(Integer watchers, Movie movie) {
        this.watchers = watchers;
        this.movie = movie;
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
