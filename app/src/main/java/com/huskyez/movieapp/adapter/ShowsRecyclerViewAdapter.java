package com.huskyez.movieapp.adapter;

import android.content.Context;

import com.huskyez.movieapp.model.show.Show;
import com.huskyez.movieapp.views.show.ShowDetailActivity;

import java.util.List;

public class ShowsRecyclerViewAdapter extends AbstractRecyclerViewAdapter<Show> {

    public ShowsRecyclerViewAdapter(Context context, List<Show> mediaObjects) {
        super(context, mediaObjects, "tv", ShowDetailActivity.class);
    }
}
