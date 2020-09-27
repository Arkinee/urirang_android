package com.makeus.urirang.android.src.main.fragments.board.models;

public class WithAllPost {

    private int id;
    private String title;
    private String nickname;
    private String createdAt;
    private String thumbnailUrl;
    private int comments;
    private int likes;
    private int views;

    public WithAllPost(int id, String title, String nickname, String createdAt, String thumbnailUrl, int comments, int likes, int views) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.thumbnailUrl = thumbnailUrl;
        this.comments = comments;
        this.likes = likes;
        this.views = views;
    }

    public int getComments() {
        return comments;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }
}
