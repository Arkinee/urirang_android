package com.makeus.urirang.android.src.howAboutThis.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("nickname")
    private String nickname;
    @SerializedName("mbti")
    private String mbti;
    @SerializedName("id")
    private int id;

    public String getNickname() {
        return nickname;
    }

    public String getMbti() {
        return mbti;
    }

    public int getId() {
        return id;
    }
}
