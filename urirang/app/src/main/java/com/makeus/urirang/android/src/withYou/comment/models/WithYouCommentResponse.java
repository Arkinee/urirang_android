package com.makeus.urirang.android.src.withYou.comment.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WithYouCommentResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<WithYouComment> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<WithYouComment> getData() {
        return data;
    }
}
