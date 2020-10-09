package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class RelateContent {

    @SerializedName("id")
    private int id;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("url")
    private String link;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    public String getLink() {
        return link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
