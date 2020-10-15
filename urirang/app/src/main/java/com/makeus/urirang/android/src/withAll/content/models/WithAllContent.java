package com.makeus.urirang.android.src.withAll.content.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WithAllContent {

    @SerializedName("isLiked")
    private boolean isLiked;
    @SerializedName("post")
    private WithAllPost post;
    @SerializedName("commentList")
    private ArrayList<WithAllComment> commentsList;

    public boolean isLiked() {
        return isLiked;
    }

    public WithAllPost getPost() {
        return post;
    }

    public ArrayList<WithAllComment> getCommentsList() {
        return commentsList;
    }
}
