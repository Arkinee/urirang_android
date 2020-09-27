package com.makeus.urirang.android.src.hallOfFame.models;

import com.google.gson.annotations.SerializedName;

public class PreviousSubject {

    @SerializedName("id")
    private int id;
    @SerializedName("id")
    private String imageUrl;
    private String title;
    private String mbti;
    private String nickname;
    private String selectedAt;
    private int comment;
    private int index;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getMbti() {
        return mbti;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSelectedAt() {
        return selectedAt;
    }

    public int getComment() {
        return comment;
    }

    public int getIndex() {
        return index;
    }
}
