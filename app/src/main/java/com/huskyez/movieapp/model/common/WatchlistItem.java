package com.huskyez.movieapp.model.common;

import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.show.Show;

import java.util.Objects;

public class WatchlistItem {

    private Integer rank;
    private String listed_at;
    private String type;
    private Movie movie;
    private Show show;

    public WatchlistItem() {
    }

    public WatchlistItem(Integer rank, String listed_at, String type, Movie movie, Show show) {
        this.rank = rank;
        this.listed_at = listed_at;
        this.type = type;
        this.movie = movie;
        this.show = show;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistItem that = (WatchlistItem) o;
        return Objects.equals(rank, that.rank) &&
                Objects.equals(listed_at, that.listed_at) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, listed_at, type);
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getListed_at() {
        return listed_at;
    }

    public void setListed_at(String listed_at) {
        this.listed_at = listed_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
