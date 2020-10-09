package com.makeus.urirang.android.src.main.fragments.home.interfaces;

import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

public interface HomeActivityView {

    void tryGetUserInfoSuccess(String nick, String mbti);

    void tryGetUserInfoFailure(String message);

    void tryGetMbtiRelateContentsSuccess(ArrayList<RelateContent> result);

    void tryGetMbtiRelateContentsFailure(String message);
}
