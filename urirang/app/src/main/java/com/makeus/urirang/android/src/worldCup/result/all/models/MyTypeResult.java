package com.makeus.urirang.android.src.worldCup.result.all.models;

import com.google.gson.annotations.SerializedName;

public class MyTypeResult {

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
    @SerializedName("rank")
    private int rank;
    @SerializedName("ratio")
    private int ratio;

    public int getRatio() {
        return ratio;
    }

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

    public int getRank() {
        return rank;
    }
}
