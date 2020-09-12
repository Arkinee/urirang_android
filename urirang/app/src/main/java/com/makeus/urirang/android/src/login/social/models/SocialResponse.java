package com.makeus.urirang.android.src.login.social.models;

import com.google.gson.annotations.SerializedName;

public class SocialResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("jwt")
    private String jwt;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getJwt() {
        return jwt;
    }
}
