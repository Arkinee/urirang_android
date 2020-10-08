package com.makeus.urirang.android.src.main.fragments.board.models;

import com.google.gson.annotations.SerializedName;

public class BoardWithYouResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private BoardWithYouData data;

    public String getMessage() {
        return message;
    }

    public BoardWithYouData getData() {
        return data;
    }
}
