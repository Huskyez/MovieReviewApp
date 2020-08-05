package com.huskyez.test.model.common;

import com.huskyez.test.model.movie.Movie;
import com.huskyez.test.model.show.Show;

public class RatedItem {

    private String rated_at;
    private Integer rating;
    private String type;
    private Show show;
    private Movie movie;

    public RatedItem() {
    }

    public RatedItem(String rated_at, Integer rating, String type, Show show, Movie movie) {
        this.rated_at = rated_at;
        this.rating = rating;
        this.type = type;
        this.show = show;
        this.movie = movie;
    }

    public String getRated_at() {
        return rated_at;
    }

    public void setRated_at(String rated_at) {
        this.rated_at = rated_at;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
