package com.makeus.urirang.android.src.login.info.interfaces;

import com.makeus.urirang.android.src.login.info.models.SignupResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InfoRetrofitInterface {

    @POST("/api/user/signUp")
    Call<SignupResponse> tryPostSignUp(@Body HashMap<String, Object> params);
}
