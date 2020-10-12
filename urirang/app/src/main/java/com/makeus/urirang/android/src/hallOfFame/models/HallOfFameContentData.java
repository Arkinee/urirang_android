package com.makeus.urirang.android.src.hallOfFame.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HallOfFameContentData {

    @SerializedName("topic")
    private HallOfFameContentTopic topic;
    @SerializedName("commentList")
    private ArrayList<HallOfFameContentComment> comment;

    public HallOfFameContentTopic getTopic() {
        return topic;
    }

    public ArrayList<HallOfFameContentComment> getComment() {
        return comment;
    }
}
