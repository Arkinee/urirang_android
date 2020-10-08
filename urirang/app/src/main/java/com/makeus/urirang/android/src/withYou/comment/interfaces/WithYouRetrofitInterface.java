package com.makeus.urirang.android.src.withYou.comment.interfaces;


import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WithYouRetrofitInterface {

    // 너희랑 댓글들 가져오기
    @GET("/api/topic/commentList/{topicId}")
    Call<WithYouCommentResponse> tryGetWithYouList(@Path("topicId") int topicId, @Query("mbti") String mbti, @Query("page") int page, @Query("limit") int limit);

    // 댓글 좋아요 누르기
    @POST("/api/comment/like/{commentId}")
    Call<LikeResponse> tryPostCommentLike(@Path("commentId") int commentId);

    // 댓글 좋아요 풀기
    @POST("/api/comment/dislike/{commentId}")
    Call<LikeResponse> tryPostCommentDisLike(@Path("commentId") int commentId);

    // 논제 게시판 댓글 달기
    @POST("/api/comment/toTopic/{topicId}")
    Call<LikeResponse> tryPostWriteComment(@Path("topicId") int topicId, @Query("commentId") String commentId, @Body HashMap<String, Object> params);

}
