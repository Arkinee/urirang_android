package com.makeus.urirang.android.src.main.fragments.mypage.interfaces;


import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.main.fragments.mypage.models.ResultResponse;
import com.makeus.urirang.android.src.main.fragments.mypage.models.TestResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyPageRetrofitInterface {

    // 유저 정보
    @GET("/api/user/current")
    Call<UserInfoResponse> tryGetUserInfo();

    // 사용자의 테스트 결과 가져오기
    @GET("/api/user/getTestResultList")
    Call<ResultResponse> tryGetTestResults();

}
