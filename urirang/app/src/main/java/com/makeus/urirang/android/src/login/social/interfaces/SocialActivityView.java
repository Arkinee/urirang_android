package com.makeus.urirang.android.src.login.social.interfaces;

public interface SocialActivityView {

    void tryGetIsMemberSuccessGoMain(String token);
    void tryGetIsMemberSuccessNeedSignUp(String token);
    void tryGetIsMemberFailure(String message);

}
