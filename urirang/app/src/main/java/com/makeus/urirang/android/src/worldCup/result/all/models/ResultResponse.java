package com.makeus.urirang.android.src.worldCup.result.all.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<Result> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<Result> getData() {
        return data;
    }
}
