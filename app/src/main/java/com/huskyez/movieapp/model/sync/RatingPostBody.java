package com.huskyez.movieapp.model.sync;

import com.huskyez.movieapp.model.movie.RatedMovie;
import com.huskyez.movieapp.model.show.RatedShow;

import java.util.List;

public class RatingPostBody {

    public List<RatedMovie> movies;
    public List<RatedShow> shows;

    public RatingPostBody(List<RatedMovie> movies, List<RatedShow> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}
