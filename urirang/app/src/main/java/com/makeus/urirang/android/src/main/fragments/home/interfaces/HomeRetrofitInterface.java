package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.main.fragments.home.models.CurrentTopicResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;
import com.makeus.urirang.android.src.main.fragments.home.models.TopicHistoryImagesResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeRetrofitInterface {

    @GET("/api/user/current")
    Call<UserInfoResponse> tryGetUserInfo();

    @GET("/api/main/mbtiContentList")
    Call<ArrayList<RelateContent>> tryGetMbtiRelateContents();

    @GET("/api/main/currentTopic")
    Call<CurrentTopicResponse> tryGetCurrentTopic();

    @GET("/api/main/topicHistoryImages")
    Call<ArrayList<TopicHistoryImagesResponse>> tryGetTopicHistoryImages();

}
