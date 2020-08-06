package com.huskyez.movieapp.model.stats;

public class NetworkStats {

    private Integer friends;
    private Integer followers;
    private Integer following;

    public NetworkStats() {
    }

    public NetworkStats(Integer friends, Integer followers, Integer following) {
        this.friends = friends;
        this.followers = followers;
        this.following = following;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }
}
