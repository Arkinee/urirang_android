package com.makeus.urirang.android.src.main.fragments.board.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardWithAllResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private ArrayList<BoardWithAllData> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<BoardWithAllData> getData() {
        return data;
    }
}
