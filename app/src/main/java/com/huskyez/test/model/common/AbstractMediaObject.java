package com.huskyez.test.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.huskyez.test.model.common.Ids;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMediaObject that = (AbstractMediaObject) o;
        return Objects.equals(ids, that.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ids);
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//
//    }
}
