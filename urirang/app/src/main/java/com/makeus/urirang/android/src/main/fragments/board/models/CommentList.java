package com.makeus.urirang.android.src.main.fragments.board.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.User;

public class CommentList {

    public CommentList() {
    }

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("postId")
    private int postId;
    @SerializedName("commentId")
    private int commentId;
    @SerializedName("content")
    private String content;
    @SerializedName("userNickName")
    private String userNickName;
    @SerializedName("userMbti")
    private String userMbti;
    @SerializedName("likes")
    private int like;
    @SerializedName("isAnonymous")
    private boolean isAnonymous;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("User")
    private User user;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

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
}
