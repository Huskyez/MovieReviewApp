package com.example.test.model.token;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RevokeAccessTokenBody implements Serializable {

    // Properties

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("client_id")
    public String clientId;

    @SerializedName("client_secret")
    public String clientSecret;

    // Constructor


    public RevokeAccessTokenBody(String accessToken, String clientId, String clientSecret) {
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
