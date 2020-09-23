package com.makeus.urirang.android.src.login.social.interfaces;


import com.makeus.urirang.android.src.login.social.models.KakaoLoginResponse;
import com.makeus.urirang.android.src.login.social.models.SocialResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SocialRetrofitInterface {


    @GET("/api/user/kakaoValidate")
    Call<SocialResponse> tryGetIsMember(@Header("Authorization") String kakaoToken);

    @POST("/api/user/kakaoLogin")
    Call<KakaoLoginResponse> tryPostKakaoLogin(@Header("Authorization") String kakaoToken);

}
