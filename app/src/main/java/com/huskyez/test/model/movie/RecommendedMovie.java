package com.huskyez.test.model.movie;

import java.util.Objects;

public class RecommendedMovie {

    private Integer user_count;
    private Movie movie;

    public RecommendedMovie() {
    }

    public RecommendedMovie(Integer user_count, Movie movie) {
        this.user_count = user_count;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendedMovie that = (RecommendedMovie) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }

    public Integer getUser_count() {
        return user_count;
    }

    public void setUser_count(Integer user_count) {
        this.user_count = user_count;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
