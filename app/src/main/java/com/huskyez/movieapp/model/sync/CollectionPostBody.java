package com.huskyez.movieapp.model.sync;

import java.util.List;

public class CollectionPostBody {

    public List<PostMediaObject> movies;
    public List<PostMediaObject> shows;

    public CollectionPostBody(List<PostMediaObject> movies, List<PostMediaObject> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}
