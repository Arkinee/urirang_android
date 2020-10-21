package com.makeus.urirang.android.src.main.fragments.worldCup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WorldCupBestResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private WorldCup data;

    public String getMessage() {
        return message;
    }

    public WorldCup getData() {
        return data;
    }
}
