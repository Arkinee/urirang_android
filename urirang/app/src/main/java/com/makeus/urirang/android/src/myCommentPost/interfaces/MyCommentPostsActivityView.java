package com.makeus.urirang.android.src.myCommentPost.interfaces;

import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPosts;

import java.util.ArrayList;

public interface MyCommentPostsActivityView {

    void tryGetMyCommentPostsSuccess(ArrayList<MyCommentPosts> data);

    void tryGetMyCommentPostsFailure(String message);

}
