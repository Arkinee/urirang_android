package com.makeus.urirang.android.src.login.info.interfaces;

import com.makeus.urirang.android.src.login.info.models.signupResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InfoRetrofitInterface {

    @POST("/api/user/signUp")
    Call<signupResponse> tryPostSignUp(@Body HashMap<String, Object> params);
}
