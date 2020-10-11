package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

public interface HomeActivityView {

    void tryGetUserInfoSuccess(String nick, String mbti);

    void tryGetUserInfoFailure(String message);

    void tryGetMbtiRelateContentsSuccess(ArrayList<RelateContent> result);

    void tryGetMbtiRelateContentsFailure(String message);

    void tryGetCurrentTopicSuccess(String title, int commentNum, String imageUrl);

    void tryGetCurrentTopicFailure(String message);

    void tryGetTopicHistoryImagesSuccess(ArrayList<String> result);
    void tryGetTopicHistoryImagesFailure(String message);
}
