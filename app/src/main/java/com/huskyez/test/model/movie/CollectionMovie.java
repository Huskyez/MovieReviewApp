package com.huskyez.test.model.movie;

import java.util.Objects;

public class CollectionMovie {

    private String collected_at;
    private String updated_at;
    private Movie movie;

    public CollectionMovie() {
    }

    public CollectionMovie(String collected_at, String updated_at, Movie movie) {
        this.collected_at = collected_at;
        this.updated_at = updated_at;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionMovie that = (CollectionMovie) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }

    public String getCollected_at() {
        return collected_at;
    }

    public void setCollected_at(String collected_at) {
        this.collected_at = collected_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
