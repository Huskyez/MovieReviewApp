package com.example.test.model.stats;

public class SeasonStats {

    private Integer ratings;
    private Integer comments;

    public SeasonStats() {
    }

    public SeasonStats(Integer ratings, Integer comments) {
        this.ratings = ratings;
        this.comments = comments;
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
