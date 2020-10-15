package com.makeus.urirang.android.src.withAll.interfaces;

import com.makeus.urirang.android.src.withAll.content.models.WithAllCommentResponse;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;
import com.makeus.urirang.android.src.withAll.write.models.WithAllWriteResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WithAllRetrofitInterface {

    // 모두랑 게시물 게시
    @Multipart
    @POST("/api/post")
    Call<WithAllWriteResponse> tryPostWithAll(@PartMap HashMap<String, RequestBody> params, @Part List<MultipartBody.Part> images);

    // 특정 게시물 조회
    @GET("/api/post/{postId}")
    Call<WithAllContentResponse> tryGetWithAllContent(@Path("postId") int postId);

    // 특정 게시물 댓글 조회
    @GET("/api/post/commentList/{postId}")
    Call<WithAllCommentResponse> tryGetWithAllComment(@Path("postId") int postId, @Query("page") int page, @Query("limit") int limit);

    // 모두랑 좋아요
    @POST("/api/post/like/{postId}")
    Call<LikeResponse> tryPostWithAllLike(@Path("postId") int postId);

    // 모두랑 좋아요 해제
    @POST("/api/post/dislike/{postId}")
    Call<LikeResponse> tryPostWithAllDislike(@Path("postId") int postId);
}
