package com.example.test.adapter;

import android.content.Context;

import com.example.test.model.show.Show;
import com.example.test.model.show.ShowDetails;
import com.example.test.views.show.ShowDetailActivity;

import java.util.List;

public class ShowsRecyclerViewAdapter extends AbstractRecyclerViewAdapter<Show> {

    public ShowsRecyclerViewAdapter(Context context, List<Show> mediaObjects) {
        super(context, mediaObjects, "tv", ShowDetailActivity.class);
    }
}
