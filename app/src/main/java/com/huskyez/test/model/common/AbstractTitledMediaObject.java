package com.huskyez.test.model.common;

import android.os.Parcelable;

import com.huskyez.test.model.common.AbstractMediaObject;
import com.huskyez.test.model.common.Ids;

public abstract class AbstractTitledMediaObject extends AbstractMediaObject  {

    private String title;

    public AbstractTitledMediaObject(Ids ids, String title) {
        super(ids);
        this.title = title;
    }

    public AbstractTitledMediaObject(Ids ids) {
        super(ids);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
