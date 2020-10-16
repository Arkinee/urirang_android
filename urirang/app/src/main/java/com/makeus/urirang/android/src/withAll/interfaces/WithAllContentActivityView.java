package com.makeus.urirang.android.src.withAll.interfaces;

import com.makeus.urirang.android.src.withAll.content.models.WithAllComment;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;

import java.util.ArrayList;

public interface WithAllContentActivityView {

    void tryGetContentSuccess(WithAllContentResponse response);

    void tryGetContentOnlySuccess(WithAllContentResponse response);

    void tryGetContentFailure(String message);

    void tryPostLikeSuccess();

    void tryPostDislikeSuccess();

    void tryPostLikeDislikeFailure(String message);

    void tryGetCommentListSuccess(ArrayList<WithAllComment> results);

    void tryGetCommentListFailure(String message);

    void tryPostCommentWriteSuccess(String message);

    void tryPostCommentWriteFailure(String message);

}
