package com.makeus.urirang.android.src.modifyProfile.model;

import com.google.gson.annotations.SerializedName;

public class ModifyResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
