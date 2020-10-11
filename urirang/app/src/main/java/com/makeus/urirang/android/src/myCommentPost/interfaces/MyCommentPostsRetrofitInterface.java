package com.makeus.urirang.android.src.myCommentPost.interfaces;


import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPostsResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyCommentPostsRetrofitInterface {

    // 마이페이지 댓글 단 글 가져오기
    @GET("/api/user/commentList")
    Call<MyCommentPostsResponse> tryGetMyCommentPosts(@Query("page") int page, @Query("limit") int limit);

}
