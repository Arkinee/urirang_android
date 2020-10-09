package com.makeus.urirang.android.src.main.fragments.mypage.models;

import com.google.gson.annotations.SerializedName;

public class Test {
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
