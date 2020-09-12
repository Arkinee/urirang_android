package com.makeus.urirang.android.src.login.info.models;

import com.google.gson.annotations.SerializedName;

public class signupResponse {

    @SerializedName("message")
    String message;

    @SerializedName("data")
    Data data;

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }
}
