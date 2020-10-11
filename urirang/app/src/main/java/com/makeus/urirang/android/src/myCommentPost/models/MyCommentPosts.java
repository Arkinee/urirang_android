package com.makeus.urirang.android.src.myCommentPost.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class MyCommentPosts {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("likes")
    private int likes;
    @SerializedName("title")
    private String title;
    @SerializedName("commentNum")
    private int commentNum;
    @SerializedName("isAnonymous")
    private boolean isAnonymous;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("Images")
    private ArrayList<Images> images;
    @SerializedName("category")
    private String category;

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public String getCategory() {
        return category;
    }
}
