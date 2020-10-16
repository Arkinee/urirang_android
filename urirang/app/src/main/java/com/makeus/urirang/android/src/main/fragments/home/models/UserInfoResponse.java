package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("mbti")
    private String mbti;
    @SerializedName("postNum")
    private int postNum;
    @SerializedName("commentNum")
    private int commentNum;

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMbti() {
        return mbti;
    }

    public String getEmail() {
        return email;
    }

    public int getPostNum() {
        return postNum;
    }

    public int getCommentNum() {
        return commentNum;
    }
}
