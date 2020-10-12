package com.makeus.urirang.android.src.howAboutThis.interfaces;

import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThisResponse;
import com.makeus.urirang.android.src.howAboutThis.models.WriteTopicResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HowAboutThisRetrofitInterface {

    // 이건 어때 리스트 가져오기
    @GET("/api/topic/list")
    Call<HowAboutThisResponse> tryGetHowAboutThisList(@Query("page") int page, @Query("limit") int limit, @Query("sort") String sort);

    // 이건 어때 좋아요
    @POST("/api/post/like/{postId}")
    Call<LikeResponse> tryPostHowAboutThisLike(@Path("postId") int postId);

    // 이건 어때 좋아요 풀기
    @POST("/api/post/dislike/{postId}")
    Call<LikeResponse> tryPostHowAboutThisDislike(@Path("postId") int postId);

    //이건 어때 토픽 게시
    @Multipart
    @POST("/api/post")
    Call<WriteTopicResponse> tryPostHowAboutThisTopic(@Part("title") String title, @Part("type") String type, @Part("isAnonymous") boolean isAnonymous, @Part MultipartBody.Part filePart);

}
