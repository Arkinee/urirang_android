package com.makeus.urirang.android.src.main.fragments.worldCup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WorldCupListResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<WorldCup> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<WorldCup> getData() {
        return data;
    }
}
