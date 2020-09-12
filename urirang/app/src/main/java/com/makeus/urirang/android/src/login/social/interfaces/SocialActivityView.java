package com.makeus.urirang.android.src.login.social.interfaces;

public interface SocialActivityView {

    void tryGetIsMemberSuccessGoMain(String message);
    void tryGetIsMemberSuccessNeedSignUp(String message, String kakaoId);
    void tryGetIsMemberFailure(String message);

}
