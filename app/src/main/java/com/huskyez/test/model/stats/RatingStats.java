package com.huskyez.test.model.stats;

import java.util.Map;

public class RatingStats {

    private Integer total;
    private Map<String, Integer> distribution;

    public RatingStats() {
    }

    public RatingStats(Integer total, Map<String, Integer> distribution) {
        this.total = total;
        this.distribution = distribution;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Integer> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, Integer> distribution) {
        this.distribution = distribution;
    }
}
