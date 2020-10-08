package com.makeus.urirang.android.src.main.interfaces;


import com.makeus.urirang.android.src.main.model.FcmResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    // FCM 토큰
    @POST("/api/user/renewFcmToken")
    Call<FcmResponse> tryPostFcmToken(@Body HashMap<String, Object> params);

}
