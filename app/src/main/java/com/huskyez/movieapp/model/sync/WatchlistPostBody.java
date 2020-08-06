package com.huskyez.movieapp.model.sync;

import com.huskyez.movieapp.model.movie.Movie;
import com.huskyez.movieapp.model.show.Show;

import java.util.List;

public class WatchlistPostBody {

    public List<Movie> movies;
    public List<Show> shows;

    public WatchlistPostBody(List<Movie> movies, List<Show> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}
