package com.makeus.urirang.android.src.howAboutThis.models;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private int id;
    @SerializedName("postId")
    private int postId;
    @SerializedName("topicId")
    private int topicId;
    @SerializedName("testResultId")
    private int testResultId;

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getTestResultId() {
        return testResultId;
    }
}
