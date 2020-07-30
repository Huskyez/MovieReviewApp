package com.example.test.model.user;

public class UserIds {
    private String slug;
    private String uuid;

    public UserIds() {
    }

    public UserIds(String slug, String uuid) {
        this.slug = slug;
        this.uuid = uuid;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
