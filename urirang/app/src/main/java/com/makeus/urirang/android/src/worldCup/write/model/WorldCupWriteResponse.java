package com.makeus.urirang.android.src.worldCup.write.model;

import com.google.gson.annotations.SerializedName;

public class WorldCupWriteResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
