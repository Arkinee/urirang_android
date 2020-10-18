package com.makeus.urirang.android.src.notice.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoticeResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private ArrayList<Notice> data;

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Notice> getData() {
        return data;
    }
}
