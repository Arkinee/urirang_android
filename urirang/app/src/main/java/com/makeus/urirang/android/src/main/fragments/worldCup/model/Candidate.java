package com.makeus.urirang.android.src.main.fragments.worldCup.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Candidate implements Serializable {
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
    @SerializedName("voteNum")
    private int voteNum;

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

    public int getVoteNum() {
        return voteNum;
    }
}
