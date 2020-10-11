package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.main.fragments.home.models.CurrentTopicResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.HomeHowAboutThisImage;
import com.makeus.urirang.android.src.main.fragments.home.models.HomePostResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;
import com.makeus.urirang.android.src.main.fragments.home.models.TopicHistoryImagesResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeRetrofitInterface {

    @GET("/api/user/current")
    Call<UserInfoResponse> tryGetUserInfo();

    // 관련 컨텐츠 가져오기
    @GET("/api/main/mbtiContentList")
    Call<ArrayList<RelateContent>> tryGetMbtiRelateContents();

    // 너희랑 토픽 가져오기
    @GET("/api/main/currentTopic")
    Call<CurrentTopicResponse> tryGetCurrentTopic();

    // 명예의 전당 사진 4개 가져오기
    @GET("/api/main/topicHistoryImages")
    Call<ArrayList<TopicHistoryImagesResponse>> tryGetTopicHistoryImages();

    // 모두랑 인기 3개 가져오기
    @GET("/api/post/bestTop3list")
    Call<HomePostResponse> tryGetWithAllBest3();

    // 이건 어때 인기순 이미지 4개 가져오기
    @GET("/api/main/topicCandidateImages")
    Call<ArrayList<HomeHowAboutThisImage>> tryGetHowAboutThisImages();

}
