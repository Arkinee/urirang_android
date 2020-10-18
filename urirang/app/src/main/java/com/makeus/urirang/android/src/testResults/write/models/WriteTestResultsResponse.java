package com.makeus.urirang.android.src.testResults.write.models;

import com.google.gson.annotations.SerializedName;

public class WriteTestResultsResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
