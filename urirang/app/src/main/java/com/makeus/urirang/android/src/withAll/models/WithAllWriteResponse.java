package com.makeus.urirang.android.src.withAll.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.howAboutThis.models.WriteTopic;

public class WithAllWriteResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private WriteTopic data;

    public String getMessage() {
        return message;
    }

    public WriteTopic getData() {
        return data;
    }
}
