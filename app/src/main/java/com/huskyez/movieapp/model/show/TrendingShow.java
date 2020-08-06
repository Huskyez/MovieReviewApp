package com.huskyez.movieapp.model.show;

public class TrendingShow {

    private Integer watchers;
    private Show show;

    public TrendingShow() {
    }

    public TrendingShow(Integer watchers, Show show) {
        this.watchers = watchers;
        this.show = show;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
