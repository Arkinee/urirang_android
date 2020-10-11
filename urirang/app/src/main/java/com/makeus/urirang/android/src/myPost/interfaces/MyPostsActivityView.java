package com.makeus.urirang.android.src.myPost.interfaces;

import com.makeus.urirang.android.src.myPost.models.MyPosts;

import java.util.ArrayList;

public interface MyPostsActivityView {

    void tryGetMyPostsSuccess(ArrayList<MyPosts> data);

    void tryGetMyPostsFailure(String message);

}
