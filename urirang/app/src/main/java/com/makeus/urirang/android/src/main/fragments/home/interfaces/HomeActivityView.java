package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.main.fragments.home.models.HomePost;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

public interface HomeActivityView {

    void tryGetUserInfoSuccess(String nick, String mbti, int userId);

    void tryGetUserInfoFailure(String message);

    void tryGetMbtiRelateContentsSuccess(ArrayList<RelateContent> result);

    void tryGetMbtiRelateContentsFailure(String message);

    void tryGetCurrentTopicSuccess(String title, int commentNum, String imageUrl);

    void tryGetCurrentTopicFailure(String message);

    void tryGetTopicHistoryImagesSuccess(ArrayList<String> result);
    void tryGetTopicHistoryImagesFailure(String message);

    void tryGetWithAllBest3Success(ArrayList<HomePost> results);
    void tryGetWithAllBest3Failure(String message);

    void tryGetHowAboutThisImagesSuccess(ArrayList<String> result);
    void tryGetHowAboutThisImagesFailure(String message);
}
