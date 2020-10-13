package com.makeus.urirang.android.src.testResults.interfaces;


import com.makeus.urirang.android.src.testResults.model.TestResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TestResultsRetrofitInterface {

    // 너희랑 댓글들 가져오기
    @GET("/api/user/getTestResultList")
    Call<TestResultsResponse> tryGetTestResultsList();

}
