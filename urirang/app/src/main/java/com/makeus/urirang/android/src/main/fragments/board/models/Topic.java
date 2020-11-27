package com.makeus.urirang.android.src.main.fragments.board.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.howAboutThis.models.User;

import java.util.ArrayList;

public class Topic {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
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
    @SerializedName("User")
    private User user;
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

    public User getUser() {
        return user;
    }

    public ArrayList<Images> getImages() {
        return images;
    }
}
