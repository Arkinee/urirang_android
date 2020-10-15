package com.makeus.urirang.android.src.withAll.interfaces;

import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;

public interface WithAllContentActivityView {

    void tryGetContentSuccess(WithAllContentResponse response);

    void tryGetContentFailure(String message);

    void tryPostLikeSuccess();
    void tryPostDislikeSuccess();
    void tryPostLikeDislikeFailure(String message);

}
