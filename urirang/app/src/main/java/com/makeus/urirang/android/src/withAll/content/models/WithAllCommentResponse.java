package com.makeus.urirang.android.src.withAll.content.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WithAllCommentResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<WithAllComment> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<WithAllComment> getData() {
        return data;
    }
}
