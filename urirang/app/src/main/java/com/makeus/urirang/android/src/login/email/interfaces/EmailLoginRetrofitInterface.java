package com.makeus.urirang.android.src.login.email.interfaces;

import com.makeus.urirang.android.src.login.email.models.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EmailLoginRetrofitInterface {

    @POST("/api/user")
    Call<LoginResponse> tryPostLogin(@Body HashMap<String, Object> params);



}
