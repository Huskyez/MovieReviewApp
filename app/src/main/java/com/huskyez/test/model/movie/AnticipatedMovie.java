package com.huskyez.test.model.movie;

import java.util.Objects;

public class AnticipatedMovie {

    private Integer list_count;
    private Movie movie;

    public AnticipatedMovie() {
    }

    public AnticipatedMovie(Integer list_count, Movie movie) {
        this.list_count = list_count;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnticipatedMovie that = (AnticipatedMovie) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }

    public Integer getList_count() {
        return list_count;
    }

    public void setList_count(Integer list_count) {
        this.list_count = list_count;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
