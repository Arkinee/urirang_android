package com.makeus.urirang.android.src.withYou.comment.models;

import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("postId")
    private int postId;
    @SerializedName("topicId")
    private int topicId;
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
    @SerializedName("isLiked")
    private boolean isLiked;

    public int getTopicId() {
        return topicId;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

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

    public boolean isLiked() {
        return isLiked;
    }
}
