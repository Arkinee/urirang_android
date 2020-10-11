package com.makeus.urirang.android.src.howAboutThis.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HowAboutThisResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private ArrayList<HowAboutThis> data;

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<HowAboutThis> getData() {
        return data;
    }
}
