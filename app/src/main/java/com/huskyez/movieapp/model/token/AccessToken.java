package com.huskyez.movieapp.model.token;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private Integer expires;

    @SerializedName("refresh_token")
    private String refreshToken;

    private String scope;
    private Integer created_at;

    public AccessToken() {
    }

    public AccessToken(String accessToken, String tokenType, Integer expires, String refreshToken, String scope, Integer created_at) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expires = expires;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.created_at = created_at;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }
}
