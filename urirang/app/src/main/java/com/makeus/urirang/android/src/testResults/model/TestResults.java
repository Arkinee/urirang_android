package com.makeus.urirang.android.src.testResults.model;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class TestResults {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("testId")
    private int testId;
    @SerializedName("resultTest")
    private String resultText;
    @SerializedName("Test")
    private Test test;
    @SerializedName("Images")
    private ArrayList<Images> images;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public String getResultText() {
        return resultText;
    }

    public Test getTest() {
        return test;
    }

    public ArrayList<Images> getImages() {
        return images;
    }
}
