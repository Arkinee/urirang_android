package com.makeus.urirang.android.src.main.model;

import com.google.gson.annotations.SerializedName;

public class FcmResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
