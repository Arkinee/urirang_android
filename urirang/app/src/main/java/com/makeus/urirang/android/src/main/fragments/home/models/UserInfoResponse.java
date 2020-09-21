package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {

    @SerializedName("email")
    String email;
    @SerializedName("nickname")
    String nickname;
    @SerializedName("mbti")
    String mbti;
    @SerializedName("postNum")
    int postNum;
    @SerializedName("commentNum")
    int commentNum;

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
