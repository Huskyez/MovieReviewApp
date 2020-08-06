package com.huskyez.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.huskyez.movieapp.R;
import com.huskyez.movieapp.model.show.Episode;
import com.huskyez.movieapp.model.show.Season;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Season> seasons = new ArrayList<>();

    public ExpandableListAdapter(Context context) {
        this.context = context;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public int getGroupCount() {
        return seasons.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return seasons.get(i).getEpisodes().size();
    }

    @Override
    public Object getGroup(int i) {
        return seasons.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return seasons.get(i).getEpisodes().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return seasons.get(i).getIds().getTmdb();
    }

    @Override
    public long getChildId(int i, int i1) {
        return seasons.get(i).getEpisodes().get(i1).getIds().getTmdb();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String groupTitle = "Season " + ((Season) getGroup(i)).getNumber();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_group_item, null);
        }
        TextView tvGroupTitle = view.findViewById(R.id.group_title);
        tvGroupTitle.setText(groupTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Episode episode = (Episode) getChild(i, i1);
        String childTitle = episode.getNumber() + ". " + episode.getTitle();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_child_item, null);
        }
        TextView tvChildTitle = view.findViewById(R.id.child_title);
        tvChildTitle.setText(childTitle);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
