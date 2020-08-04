package com.huskyez.test.model.user;

import com.google.gson.annotations.SerializedName;

public class User {

    private String username;

    @SerializedName("private")
    private Boolean isPrivate;
    private String name;
    private Boolean vip;
    private Boolean vip_ep;
    private UserIds ids;
    private UserImage images;

    public User() {
    }

    public User(String username, Boolean isPrivate, String name, Boolean vip, Boolean vip_ep, UserIds ids, UserImage images) {
        this.username = username;
        this.isPrivate = isPrivate;
        this.name = name;
        this.vip = vip;
        this.vip_ep = vip_ep;
        this.ids = ids;
        this.images = images;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Boolean getVip_ep() {
        return vip_ep;
    }

    public void setVip_ep(Boolean vip_ep) {
        this.vip_ep = vip_ep;
    }

    public UserIds getIds() {
        return ids;
    }

    public void setIds(UserIds ids) {
        this.ids = ids;
    }

    public UserImage getImages() {
        return images;
    }

    public void setImages(UserImage images) {
        this.images = images;
    }
}
