package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePostResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<HomePost> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<HomePost> getData() {
        return data;
    }
}
