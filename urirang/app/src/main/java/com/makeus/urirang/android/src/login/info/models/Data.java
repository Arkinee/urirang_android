package com.makeus.urirang.android.src.login.info.models;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    int id;
    @SerializedName("email")
    String email;
    @SerializedName("mbti")
    String mbti;
    @SerializedName("nickname")
    String nickname;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMbti() {
        return mbti;
    }

    public String getNickname() {
        return nickname;
    }
}
