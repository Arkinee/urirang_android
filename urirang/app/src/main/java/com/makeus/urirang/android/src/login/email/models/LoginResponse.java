package com.makeus.urirang.android.src.login.email.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }
}
