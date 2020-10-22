package com.makeus.urirang.android.src.worldCup.result.all.models;

import com.google.gson.annotations.SerializedName;

public class AllResultResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private MyTypeResult data;

    public String getMessage() {
        return message;
    }

    public MyTypeResult getData() {
        return data;
    }
}
