package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContentResponse;
import com.makeus.urirang.android.src.worldCup.result.my.models.MyResultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorldCupContentRetrofitInterface {

    @GET("/api/worldCup/{worldCupId}")
    Call<WorldCupContentResponse> tryGetWorldCupContent(@Path("worldCupId") int worldCupId);

    @POST("/api/worldCup/result")
    Call<LikeResponse> tryPostWorldCupContent(@Body HashMap<String, Object> params);

    @GET("/api/worldCup/myResult/{worldCupId}")
    Call<MyResultResponse> tryGetMyResult(@Path("worldCupId") int worldCupId);

}
