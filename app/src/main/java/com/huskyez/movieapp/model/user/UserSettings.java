package com.huskyez.movieapp.model.user;

public class UserSettings {

    private User user;

    public UserSettings() {
    }

    public UserSettings(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
