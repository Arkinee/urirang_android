package com.makeus.urirang.android.src.main.fragments.mypage.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class ResultResponse {

    @SerializedName("result")
    private ArrayList<TestResult> results;

    public ArrayList<TestResult> getResults() {
        return results;
    }
}
