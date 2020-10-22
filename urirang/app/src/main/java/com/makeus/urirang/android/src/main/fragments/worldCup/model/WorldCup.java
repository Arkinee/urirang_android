package com.makeus.urirang.android.src.main.fragments.worldCup.model;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.User;

import java.io.Serializable;
import java.util.ArrayList;

public class WorldCup implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("roundNum")
    private String roundNum;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("joinNummmmm")
    private int joinNum;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("User")
    private User user;
    @SerializedName("WorldCupCandidates")
    private ArrayList<Candidate> worldCupCandidates;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getRoundNum() {
        return roundNum;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getJoinNum() {
        return joinNum;
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

    public ArrayList<Candidate> getWorldCupCandidates() {
        return worldCupCandidates;
    }
}
