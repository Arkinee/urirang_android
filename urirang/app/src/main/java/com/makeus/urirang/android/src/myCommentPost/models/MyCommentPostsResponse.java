package com.makeus.urirang.android.src.myCommentPost.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyCommentPostsResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<MyCommentPosts> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<MyCommentPosts> getData() {
        return data;
    }
}
