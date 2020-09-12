package com.makeus.urirang.android.src.login.social.interfaces;



import com.makeus.urirang.android.src.login.social.models.SocialResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SocialRetrofitInterface {

    @GET("/api/user/kakaoLogin")
    Call<SocialResponse> tryGetIsMember(@Header("x-access-token") String kakaoToken);

}
