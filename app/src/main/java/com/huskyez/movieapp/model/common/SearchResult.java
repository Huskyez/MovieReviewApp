package com.huskyez.movieapp.model.common;


import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.show.Show;

public class SearchResult {

    private String type;
    private Double score;

    private Movie movie;
    private Show show;

    public SearchResult() {
    }

    public SearchResult(String type, Double score, Movie movie, Show show) {
        this.type = type;
        this.score = score;
        this.movie = movie;
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
