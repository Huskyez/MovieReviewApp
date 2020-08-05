package com.huskyez.test.model.show;

import com.huskyez.test.model.common.AbstractMediaObject;
import com.huskyez.test.model.common.Ids;

public class Season extends AbstractMediaObject {

    private Integer number;

    public Season(Ids ids) {
        super(ids);
    }

    public Season(Integer number, Ids ids) {
        super(ids);
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
