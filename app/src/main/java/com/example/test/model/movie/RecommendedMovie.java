package com.example.test.model.movie;

public class RecommendedMovie {

    private Integer user_count;
    private Movie movie;

    public RecommendedMovie() {
    }

    public RecommendedMovie(Integer user_count, Movie movie) {
        this.user_count = user_count;
        this.movie = movie;
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
