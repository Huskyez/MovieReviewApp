package com.huskyez.movieapp.model.sync;

import com.huskyez.movieapp.model.movie.HistoryMovie;
import com.huskyez.movieapp.model.show.Episode;
import com.huskyez.movieapp.model.show.Season;
import com.huskyez.movieapp.model.show.Show;

import java.util.List;

public class HistoryPostBody {

    public List<HistoryMovie> movies;
    public List<Show> shows;
    public List<Season> seasons;
    public List<Episode> episodes;

    public HistoryPostBody(List<HistoryMovie> movies, List<Show> shows, List<Season> seasons, List<Episode> episodes) {
        this.movies = movies;
        this.shows = shows;
        this.seasons = seasons;
        this.episodes = episodes;
    }
}
