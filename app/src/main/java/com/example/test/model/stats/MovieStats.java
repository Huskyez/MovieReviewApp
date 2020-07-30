package com.example.test.model.stats;

public class MovieStats {

    private Integer plays;
    private Integer watched;
    private Integer minutes;
    private Integer collected;
    private Integer ratings;
    private Integer comments;

    public MovieStats() {
    }

    public MovieStats(Integer plays, Integer watched, Integer minutes, Integer collected, Integer ratings, Integer comments) {
        this.plays = plays;
        this.watched = watched;
        this.minutes = minutes;
        this.collected = collected;
        this.ratings = ratings;
        this.comments = comments;
    }

    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public Integer getWatched() {
        return watched;
    }

    public void setWatched(Integer watched) {
        this.watched = watched;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getCollected() {
        return collected;
    }

    public void setCollected(Integer collected) {
        this.collected = collected;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
