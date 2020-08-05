package com.huskyez.test.model.sync;

import com.huskyez.test.model.movie.RatedMovie;
import com.huskyez.test.model.show.RatedShow;

import java.util.List;

public class RatingPostBody {

    public List<RatedMovie> movies;
    public List<RatedShow> shows;

    public RatingPostBody(List<RatedMovie> movies, List<RatedShow> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}
