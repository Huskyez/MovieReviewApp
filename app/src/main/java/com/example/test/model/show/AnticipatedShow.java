package com.example.test.model.show;

public class AnticipatedShow {

    private Integer list_count;
    private Show show;

    public AnticipatedShow() {
    }

    public AnticipatedShow(Integer list_count, Show show) {
        this.list_count = list_count;
        this.show = show;
    }

    public Integer getList_count() {
        return list_count;
    }

    public void setList_count(Integer list_count) {
        this.list_count = list_count;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
