package com.makeus.urirang.android.src.splash.interfaces;

public interface SplashActivityView {

    void tryGetAutoLoginSuccessGoMain();
    void tryGetAutoLoginSuccessGoLogIn(String message);
    void tryGetAutoLoginFailure(String message);

}
