package com.makeus.urirang.android.src.testResults.interfaces;


import com.makeus.urirang.android.src.testResults.content.model.TestResultsResponse;
import com.makeus.urirang.android.src.testResults.write.models.WriteTestResultsResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface TestResultsRetrofitInterface {

    // 테스트 결과 가져오기
    @GET("/api/user/getTestResultList")
    Call<TestResultsResponse> tryGetTestResultsList();

    // 테스트 결과 쓰기
    @Multipart
    @POST("/api/user/createTestResult/{testId}")
    Call<WriteTestResultsResponse> tryPostWriteTestResults(@Path("testId") int testId, @PartMap HashMap<String, RequestBody> params, @Part List<MultipartBody.Part> images);

    // 테스트 결과 수정하기
    @Multipart
    @POST("/api/user/updateTestResult/{testId}")
    Call<WriteTestResultsResponse> tryPostModifyTestResults(@Path("testId") int testId, @PartMap HashMap<String, RequestBody> params, @Part List<MultipartBody.Part> images);

}
