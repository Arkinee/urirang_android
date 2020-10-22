package com.makeus.urirang.android.src.worldCup.content.models;

import com.google.gson.annotations.SerializedName;

public class WorldCupContentResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private WorldCupContent data;

    public String getMessage() {
        return message;
    }

    public WorldCupContent getData() {
        return data;
    }
}
