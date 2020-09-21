package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HomeRetrofitInterface {

    @GET("/api/user/current")
    Call<UserInfoResponse> tryGetUserInfo();

}
