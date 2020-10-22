package com.makeus.urirang.android.src.worldCup.result.my.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyResult implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("worldCupId")
    private int worldCupId;
    @SerializedName("worldCupCandidateId")
    private int worldCupCandidateId;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("WorldCupCandidate")
    private MyCandidate candidate;
    @SerializedName("rank")
    private int rank;
    @SerializedName("ratio")
    private int ratio;

    public int getWorldCupCandidateId() {
        return worldCupCandidateId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getWorldCupId() {
        return worldCupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public MyCandidate getCandidate() {
        return candidate;
    }

    public int getRank() {
        return rank;
    }

    public int getRatio() {
        return ratio;
    }
}
