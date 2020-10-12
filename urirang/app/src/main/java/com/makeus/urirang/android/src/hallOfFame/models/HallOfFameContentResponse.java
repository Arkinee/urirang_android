package com.makeus.urirang.android.src.hallOfFame.models;

import com.google.gson.annotations.SerializedName;
import com.makeus.urirang.android.src.login.info.models.Data;

public class HallOfFameContentResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private HallOfFameContentData data;

    public String getMessage() {
        return message;
    }

    public HallOfFameContentData getData() {
        return data;
    }
}
