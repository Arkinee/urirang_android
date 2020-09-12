package com.makeus.urirang.android.src.splash;

public interface SplashActivityView {

    void tryGetAutoLoginSuccessGoMain(String message);
    void tryGetAutoLoginSuccessGoLogIn(String message);
    void tryGetAutoLoginFailure(String message);

}
