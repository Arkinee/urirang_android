package com.makeus.urirang.android.src.withAll.content.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class WithAllPost {
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("likes")
    private int likes;
    @SerializedName("views")
    private int views;
    @SerializedName("commentNum")
    private int commentNum;
    @SerializedName("type")
    private String type;
    @SerializedName("isAnonymous")
    private boolean isAnonymous;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("Images")
    private ArrayList<Images> images;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public String getType() {
        return type;
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
}
