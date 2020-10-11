package com.makeus.urirang.android.src.main.fragments.home.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class HomeHowAboutThisImage {

    @SerializedName("id")
    private int id;
    @SerializedName("likes")
    private int likes;
    @SerializedName("Images")
    private ArrayList<Images> images;

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public ArrayList<Images> getImages() {
        return images;
    }
}
