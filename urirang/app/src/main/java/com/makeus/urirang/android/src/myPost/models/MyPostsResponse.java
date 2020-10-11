package com.makeus.urirang.android.src.myPost.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPosts;

import java.util.ArrayList;

public class MyPostsResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<MyPosts> data;

    public String getMessage() {
        return message;
    }

    public ArrayList<MyPosts> getData() {
        return data;
    }
}
