package com.makeus.urirang.android.src.main.fragments.board.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardWithYouData {

    @SerializedName("topic")
    private Topic topic;
    @SerializedName("commentList")
    private ArrayList<CommentList> commentLists;

    public Topic getTopic() {
        return topic;
    }

    public ArrayList<CommentList> getCommentLists() {
        return commentLists;
    }
}
