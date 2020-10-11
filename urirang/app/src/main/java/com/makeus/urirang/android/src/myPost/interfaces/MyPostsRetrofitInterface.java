package com.makeus.urirang.android.src.myPost.interfaces;


import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPostsResponse;
import com.makeus.urirang.android.src.myPost.models.MyPostsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyPostsRetrofitInterface {

    // 마이페이지 내가 쓴 글 가져오기
    @GET("/api/user/postList")
    Call<MyPostsResponse> tryGetMyPosts(@Query("page") int page, @Query("limit") int limit);

}
