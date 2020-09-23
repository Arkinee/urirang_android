package com.makeus.urirang.android.src.login.social.models;

import com.google.gson.annotations.SerializedName;

public class KakaoLoginResponse {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
