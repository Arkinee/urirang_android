package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.worldCup.result.all.models.AllResultResponse;
import com.makeus.urirang.android.src.worldCup.result.all.models.ResultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WorldCupResultRetrofitInterface {

    //내 유형 결과 가져오기
    @GET("/api/worldCup/myMbtiResult/{worldCupId}")
    Call<AllResultResponse> tryGetMyMbtiResult(@Path("worldCupId") int worldCupId);

    // 전체 결과 가져오기
    @GET("/api/worldCup/totalResult/{worldCupId}")
    Call<ResultResponse> tryGetAllResult(@Path("worldCupId") int worldCupId, @Query("mbti") String mbti);

}
