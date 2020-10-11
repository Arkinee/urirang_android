package com.makeus.urirang.android.src.hallOfFame.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HallOfFameResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private ArrayList<PreviousSubject> data;

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<PreviousSubject> getData() {
        return data;
    }
}
