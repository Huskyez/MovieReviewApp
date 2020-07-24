package com.example.test.model.movie;

public class AnticipatedMovie {

    private Integer list_count;
    private Movie movie;

    public AnticipatedMovie() {
    }

    public AnticipatedMovie(Integer list_count, Movie movie) {
        this.list_count = list_count;
        this.movie = movie;
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
