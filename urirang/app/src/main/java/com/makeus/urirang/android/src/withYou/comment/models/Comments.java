package com.makeus.urirang.android.src.withYou.comment.models;

import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("userNickName")
    private String userNickName;
    @SerializedName("mbti")
    private String userMbti;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("content")
    private String content;
    @SerializedName("like")
    private int like;
    @SerializedName("isLiked")
    private boolean isLiked;

    public String getUserMbti() {
        return userMbti;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public int getLike() {
        return like;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
