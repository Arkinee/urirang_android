package com.makeus.urirang.android.src.hallOfFame.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.howAboutThis.models.User;

import java.util.ArrayList;

public class HallOfFameContentComment {

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
    private String userNickname;
    @SerializedName("userMbti")
    private String mbti;
    @SerializedName("likes")
    private int likes;
    @SerializedName("isAnonymous")
    private boolean isAnonymous;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("User")
    private User user;
    @SerializedName("Images")
    private ArrayList<Images> images;

    public int getId() {
        return id;
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

    public String getUserNickname() {
        return userNickname;
    }

    public String getUserMbti() {
        return mbti;
    }

    public int getLikes() {
        return likes;
    }

    public int getUserId() {
        return userId;
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

    public User getUser() {
        return user;
    }

    public ArrayList<Images> getImages() {
        return images;
    }
}
