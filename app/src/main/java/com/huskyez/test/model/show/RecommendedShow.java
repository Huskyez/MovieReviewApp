package com.huskyez.test.model.show;

public class RecommendedShow {

    private Integer user_count;
    private Show show;

    public RecommendedShow() {
    }

    public RecommendedShow(Integer user_count, Show show) {
        this.user_count = user_count;
        this.show = show;
    }

    public Integer getUser_count() {
        return user_count;
    }

    public void setUser_count(Integer user_count) {
        this.user_count = user_count;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
