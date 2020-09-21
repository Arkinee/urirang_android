package com.makeus.urirang.android.src.main.fragments.home.interfaces;

public interface HomeActivityView {
    void tryGetUserInfoSuccess(String nick, String mbti);
    void tryGetUserInfoFailure(String message);
}
