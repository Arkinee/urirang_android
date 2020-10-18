package com.makeus.urirang.android.src.notice.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.User;

public class Notice {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("commenterId")
    private int commentId;
    @SerializedName("postId")
    private Integer postId;
    @SerializedName("topicId")
    private Integer topicId;
    @SerializedName("message")
    private String message;
    @SerializedName("isChecked")
    private boolean isChecked;
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

    public int getCommentId() {
        return commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isChecked() {
        return isChecked;
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
}
