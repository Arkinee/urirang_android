package com.makeus.urirang.android.src.howAboutThis.models;

import com.google.gson.annotations.SerializedName;

public class HowAboutThis {

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
    @SerializedName("updateAt")
    private String updateAt;
    @SerializedName("User")
    private User user;
    @SerializedName("Images")
    private Images images;

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

    public String getUpdateAt() {
        return updateAt;
    }

    public User getUser() {
        return user;
    }

    public Images getImages() {
        return images;
    }
}
