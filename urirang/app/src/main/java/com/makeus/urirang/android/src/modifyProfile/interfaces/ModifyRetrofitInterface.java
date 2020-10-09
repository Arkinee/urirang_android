package com.makeus.urirang.android.src.modifyProfile.interfaces;


import com.makeus.urirang.android.src.modifyProfile.model.ModifyResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface ModifyRetrofitInterface {

    // 유저 정보 수정
    @PUT("/api/user")
    Call<ModifyResponse> tryPutModifyProfile(@Body HashMap<String, Object> params);

}
