package com.makeus.urirang.android.src.main.fragments.worldCup.interfaces;

import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupBestResponse;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorldCupRetrofitInterface {

    @GET("/api/worldCup/best")
    Call<WorldCupBestResponse> tryGetBestWorldCup();

    @GET("/api/worldCup/list")
    Call<WorldCupListResponse> tryGetWorldCupList(@Query("page") int page, @Query("limit") int limit, @Query("sort") String sort);

}
