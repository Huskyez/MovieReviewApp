package com.huskyez.test.model.movie;

import java.util.Objects;

public class WatchedMovie {

    private Integer plays;
    private String last_watched_at;
    private String last_updated_at;
    private Movie movie;

    public WatchedMovie(Integer plays, String last_watched_at, String last_updated_at, Movie movie) {
        this.plays = plays;
        this.last_watched_at = last_watched_at;
        this.last_updated_at = last_updated_at;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchedMovie that = (WatchedMovie) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }

    public WatchedMovie() {
    }

    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public String getLast_watched_at() {
        return last_watched_at;
    }

    public void setLast_watched_at(String last_watched_at) {
        this.last_watched_at = last_watched_at;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
