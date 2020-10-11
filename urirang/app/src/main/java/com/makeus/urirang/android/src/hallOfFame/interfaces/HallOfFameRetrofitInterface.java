package com.makeus.urirang.android.src.hallOfFame.interfaces;

import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HallOfFameRetrofitInterface {

    @GET("/api/topic/history")
    Call<HallOfFameResponse> tryGetTopicHistoryList(@Query("page") int page, @Query("limit") int limit, @Query("sort") String sort);

}
