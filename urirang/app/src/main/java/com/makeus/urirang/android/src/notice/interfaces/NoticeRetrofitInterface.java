package com.makeus.urirang.android.src.notice.interfaces;


import com.makeus.urirang.android.src.notice.models.NoticeResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NoticeRetrofitInterface {

    @GET("/api/user/noticeList")
    Call<NoticeResponse> tryGetNoticeList(@Query("page") int page, @Query("limit") int limit);

    @POST("/api/user/makeNoticeChecked")
    Call<LikeResponse> tryPostNoticeCheck(@Body HashMap<String, Object> params);

}
