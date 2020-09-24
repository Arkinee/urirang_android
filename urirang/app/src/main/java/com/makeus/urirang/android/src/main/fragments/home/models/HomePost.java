package com.makeus.urirang.android.src.main.fragments.home.models;

public class HomePost {

    private String mbti;
    private String nickname;
    private String createdAt;
    private String title;
    private int like;
    private int views;

    public HomePost(String mbti, String nickname, String createdAt, String title, int like, int views) {
        this.mbti = mbti;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.title = title;
        this.like = like;
        this.views = views;
    }

    public String getMbti() {
        return mbti;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public int getLike() {
        return like;
    }

    public int getViews() {
        return views;
    }
}
