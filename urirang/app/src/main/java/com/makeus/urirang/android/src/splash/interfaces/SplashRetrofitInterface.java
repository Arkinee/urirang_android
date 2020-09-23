package com.makeus.urirang.android.src.splash.interfaces;


import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.splash.models.FcmResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

public interface SplashRetrofitInterface {

    @GET("/api/user/current")
    Call<UserInfoResponse> tryGetUserInfo();

    @PATCH("/app/fcm")
    Call<FcmResponse> tryPatchFcm(@Body HashMap<String, Object> params);
    
}
