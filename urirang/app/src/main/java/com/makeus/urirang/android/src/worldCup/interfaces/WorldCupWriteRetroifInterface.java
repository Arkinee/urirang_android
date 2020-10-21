package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.worldCup.write.model.WorldCupWriteResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface WorldCupWriteRetroifInterface {

    @Multipart
    @POST("/api/worldCup")
    Call<WorldCupWriteResponse> tryPostWorldCupWrite(@PartMap HashMap<String, RequestBody> params, @Part List<MultipartBody.Part> images);
}
