package com.huskyez.test.model.stats;

public class ShowStats {

    private Integer watched;
    private Integer collected;
    private Integer ratings;
    private Integer comments;

    public ShowStats() {
    }

    public ShowStats(Integer watched, Integer collected, Integer ratings, Integer comments) {
        this.watched = watched;
        this.collected = collected;
        this.ratings = ratings;
        this.comments = comments;
    }

    public Integer getWatched() {
        return watched;
    }

    public void setWatched(Integer watched) {
        this.watched = watched;
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
