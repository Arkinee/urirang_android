package com.makeus.urirang.android.src.main.fragments.worldCup.interfaces;

import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupBestResponse;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupListResponse;
import com.makeus.urirang.android.src.worldCup.write.model.WorldCupWriteResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface WorldCupRetrofitInterface {

    @GET("/api/worldCup/best")
    Call<WorldCupBestResponse> tryGetBestWorldCup();

    @GET("/api/worldCup/list")
    Call<WorldCupListResponse> tryGetWorldCupList(@Query("page") int page, @Query("limit") int limit, @Query("sort") String sort);

    @POST("/api/worldCup")
    Call<WorldCupWriteResponse> tryPostWorldCup(@PartMap HashMap<String, RequestBody> params, @Part List<MultipartBody.Part> images);

}
