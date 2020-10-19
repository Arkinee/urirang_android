package com.makeus.urirang.android.src.testResults.content.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Test implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
