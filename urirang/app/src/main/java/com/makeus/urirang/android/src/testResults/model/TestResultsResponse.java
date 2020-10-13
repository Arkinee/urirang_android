package com.makeus.urirang.android.src.testResults.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestResultsResponse {

    @SerializedName("result")
    private ArrayList<TestResults> result;

    public ArrayList<TestResults> getResult() {
        return result;
    }
}
