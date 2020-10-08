package com.makeus.urirang.android.src.withYou.comment.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WithYouComment {

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
    private int likes;
    @SerializedName("isAnonymous")
    private boolean isAnonymous;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("Comments")
    private ArrayList<Comments> comments;
    @SerializedName("isLiked")
    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
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

    public int getTopicId() {
        return topicId;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getUserMbti() {
        return userMbti;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public ArrayList<Comments> getComments() {
        return comments;
    }
}
