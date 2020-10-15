package com.makeus.urirang.android.src.withAll.content.models;

import com.google.gson.annotations.SerializedName;

public class WithAllContentResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private WithAllContent data;

    public String getMessage() {
        return message;
    }

    public WithAllContent getData() {
        return data;
    }
}
