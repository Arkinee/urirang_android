package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class CurrentTopicResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("commentNum")
    private int commentNum;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("Images")
    private ArrayList<Images> images;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ArrayList<Images> getImages() {
        return images;
    }
}
