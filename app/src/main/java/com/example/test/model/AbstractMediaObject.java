package com.example.test.model;

public abstract class AbstractMediaObject {


    private Ids ids;

    public AbstractMediaObject(Ids ids) {
        this.ids = ids;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }
}
