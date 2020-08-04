package com.huskyez.test.model.show;

public class Airs {

    private String day;
    private String time;
    private String timezone;

    public Airs() {
    }

    public Airs(String day, String time, String timezone) {
        this.day = day;
        this.time = time;
        this.timezone = timezone;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
