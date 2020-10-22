package com.makeus.urirang.android.src.worldCup.result.my.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyCandidate implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("worldCupId")
    private int worldCupId;
    @SerializedName("title")
    private String title;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public int getWorldCupId() {
        return worldCupId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
