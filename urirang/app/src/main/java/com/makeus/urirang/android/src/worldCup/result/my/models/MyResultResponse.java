package com.makeus.urirang.android.src.worldCup.result.my.models;

import com.google.gson.annotations.SerializedName;

public class MyResultResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private MyResult data;

    public String getMessage() {
        return message;
    }

    public MyResult getData() {
        return data;
    }
}
