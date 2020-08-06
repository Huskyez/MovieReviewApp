package com.huskyez.movieapp.model.show;

import com.huskyez.movieapp.model.common.AbstractMediaObject;
import com.huskyez.movieapp.model.common.Ids;

import java.util.List;

public class Season extends AbstractMediaObject {

    private Integer number;
    private List<Episode> episodes;

    public Season(Ids ids) {
        super(ids);
    }

    public Season(Ids ids, Integer number, List<Episode> episodes) {
        super(ids);
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
