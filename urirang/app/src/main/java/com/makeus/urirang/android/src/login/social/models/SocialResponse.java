package com.makeus.urirang.android.src.login.social.models;

import com.google.gson.annotations.SerializedName;

public class SocialResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
