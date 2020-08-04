package com.huskyez.test.model.show;

import java.util.List;

public class WatchedShow {


    private Integer plays;
    private String last_watched_at;
    private String last_updated_at;
    private String reset_at;
    private Show show;
    private List<Season> seasons;

    public WatchedShow(Integer plays, String last_watched_at, String last_updated_at, String reset_at, Show show, List<Season> seasons) {
        this.plays = plays;
        this.last_watched_at = last_watched_at;
        this.last_updated_at = last_updated_at;
        this.reset_at = reset_at;
        this.show = show;
        this.seasons = seasons;
    }

    public WatchedShow() {
    }

    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public String getLast_watched_at() {
        return last_watched_at;
    }

    public void setLast_watched_at(String last_watched_at) {
        this.last_watched_at = last_watched_at;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public String getReset_at() {
        return reset_at;
    }

    public void setReset_at(String reset_at) {
        this.reset_at = reset_at;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public static class Season {
        public Integer number;
        public List<Epispde> episodes;
    }

    public static class Epispde {
        public Integer number;
        public Integer plays;
        public String last_watched_at;
    }
}
