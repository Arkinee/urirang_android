package com.makeus.urirang.android.src.withAll.interfaces;

import com.makeus.urirang.android.src.withAll.models.WithAllWriteResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WithAllRetrofitInterface {

    // 모두랑 게시물 게시
    @Multipart
    @POST("/api/post")
    Call<WithAllWriteResponse> tryPostWithAll(@Part("title") RequestBody title, @Part("content") RequestBody content, @Part("type") RequestBody type, @Part("isAnonymous") RequestBody isAnonymous, @Part List<MultipartBody.Part> params);

}
