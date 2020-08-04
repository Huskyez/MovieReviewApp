package com.huskyez.test.model.sync;

import com.huskyez.test.model.movie.CollectionMovie;
import com.huskyez.test.model.show.CollectionShow;

import java.util.List;

public class CollectionPostBody {

    public List<PostMediaObject> movies;
    public List<PostMediaObject> shows;

    public CollectionPostBody(List<PostMediaObject> movies, List<PostMediaObject> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}
