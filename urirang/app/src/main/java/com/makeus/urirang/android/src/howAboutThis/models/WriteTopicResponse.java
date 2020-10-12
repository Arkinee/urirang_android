package com.makeus.urirang.android.src.howAboutThis.models;

import com.google.gson.annotations.SerializedName;

public class WriteTopicResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private WriteTopic data;

    public String getMessage() {
        return message;
    }

    public WriteTopic getData() {
        return data;
    }
}
