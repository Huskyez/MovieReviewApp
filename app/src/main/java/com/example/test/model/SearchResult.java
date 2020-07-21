package com.example.test.model;


public class SearchResult {

    private String type;
    private Double score;

    private Movie movie;

    public SearchResult() {
    }

    public SearchResult(String type, Double score, Movie movie) {
        this.type = type;
        this.score = score;
        this.movie = movie;
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
}
