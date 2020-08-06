package com.huskyez.movieapp.model.show;

import java.util.List;

public class CollectionShow {

    private String last_collected_at;
    private String last_updated_at;
    private Show show;
    private List<Season> seasons;

    public CollectionShow() {
    }

    public CollectionShow(String last_collected_at, String last_updated_at, Show show, List<Season> seasons) {
        this.last_collected_at = last_collected_at;
        this.last_updated_at = last_updated_at;
        this.show = show;
        this.seasons = seasons;
    }

    public String getLast_collected_at() {
        return last_collected_at;
    }

    public void setLast_collected_at(String last_collected_at) {
        this.last_collected_at = last_collected_at;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
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

    public static class Episode {
        private Integer number;
        private String collected_at;

        public Episode() {
        }

        public Episode(Integer number, String collected_at) {
            this.number = number;
            this.collected_at = collected_at;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getCollected_at() {
            return collected_at;
        }

        public void setCollected_at(String collected_at) {
            this.collected_at = collected_at;
        }
    }

    public static class Season {
        private Integer number;
        private List<Episode> episodes;

        public Season() {
        }

        public Season(Integer number, List<Episode> episodes) {
            this.number = number;
            this.episodes = episodes;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public List<Episode> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(List<Episode> episodes) {
            this.episodes = episodes;
        }
    }
}
