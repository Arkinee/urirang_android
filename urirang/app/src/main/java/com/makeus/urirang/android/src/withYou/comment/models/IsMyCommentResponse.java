package com.makeus.urirang.android.src.withYou.comment.models;

import com.google.gson.annotations.SerializedName;

public class IsMyCommentResponse {

    @SerializedName("isMyComment")
    private boolean isMyComment;

    public boolean isMyComment() {
        return isMyComment;
    }
}
