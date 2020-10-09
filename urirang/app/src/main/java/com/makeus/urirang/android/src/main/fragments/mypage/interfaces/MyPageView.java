package com.makeus.urirang.android.src.main.fragments.mypage.interfaces;

import com.makeus.urirang.android.src.main.fragments.mypage.models.TestResult;

import java.util.ArrayList;

public interface MyPageView {

    void tryGetUserInfoSuccess(String nick, String mbti);

    void tryGetUserInfoFailure(String message);

    void tryGetTestResultSuccess(ArrayList<TestResult> results);

    void tryGetTestResultFailure(String message);

}
