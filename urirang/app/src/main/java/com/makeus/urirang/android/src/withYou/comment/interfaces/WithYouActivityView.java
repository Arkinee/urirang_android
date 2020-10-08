package com.makeus.urirang.android.src.withYou.comment.interfaces;

import com.makeus.urirang.android.src.withYou.comment.models.WithYouComment;

import java.util.ArrayList;

public interface WithYouActivityView {

    void tryGetWithYouListSuccess(ArrayList<WithYouComment> data);

    void tryGetWithYouListFailure(String message);

    void tryPostLikeCommentSuccess();

    void tryPostLikeCommentFailure(String message);

    void tryPostWriteCommentSuccess();
    void tryPostWriteCommentFailure(String message);

}
