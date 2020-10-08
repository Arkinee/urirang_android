package com.makeus.urirang.android.src.main.fragments.board.interfaces;


import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllResponse;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithYouResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BoardRetrofitInterface {

    // 모두랑 조회
    @GET("/api/post/list")
    Call<BoardWithAllResponse> tryGetWithAllList(@Query("mbti") String mbti, @Query("keyword") String keyword, @Query("page") int page, @Query("limit") int limit);

    // 너희랑 조회
    @GET("/api/topic/current")
    Call<BoardWithYouResponse> tryGetWithYouInfo(@Query("limit") int limit);

}
