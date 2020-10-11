package com.makeus.urirang.android.src.withYou.comment.interfaces;

public interface WithYouCommentByCommentView {

    void tryPostLikeCommentByCommentSuccess();

    void tryPostLikeCommentByCommentFailure(String message);

    void tryGetIsMyCommentByCommentSuccess(boolean isMyComment);

    void tryGetIsMyCommentByCommentFailure(String message);

}
