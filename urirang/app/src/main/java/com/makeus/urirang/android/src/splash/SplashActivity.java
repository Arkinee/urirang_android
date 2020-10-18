package com.makeus.urirang.android.src.splash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.kakao.util.helper.Utility;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.splash.interfaces.SplashActivityView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.makeus.urirang.android.src.ApplicationClass.getFcmToken;

public class SplashActivity extends BaseActivity implements SplashActivityView {

    private Context mContext;
    private SplashActivityView mView;
    private boolean mFromPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;
        mView = this;

        mFromPush = getIntent().getBooleanExtra("fromPush", false);
        Log.d("BreezeWind", "from, splash: " + mFromPush);

        getFcmToken();
        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 750); // 1초 후에 hd handler 실행
    }

    private class splashHandler implements Runnable {
        public void run() {
            final SplashService splashService = new SplashService(mView, mContext);
            splashService.tryGetUserInfo();
        }
    }

    @Override
    public void tryGetAutoLoginSuccessGoMain() {
        Intent goMain = new Intent(SplashActivity.this, MainActivity.class);
        goMain.putExtra("fromPush", mFromPush);
        startActivity(goMain);
        finish();
    }

    @Override
    public void tryGetAutoLoginSuccessGoLogIn(String message) {
        Intent goLogin = new Intent(SplashActivity.this, SocialLoginActivity.class);
        startActivity(goLogin);
        finish();
    }

    @Override
    public void tryGetAutoLoginFailure(String message) {
        Intent goLogin = new Intent(SplashActivity.this, SocialLoginActivity.class);
        startActivity(goLogin);
        finish();
    }

    public String getKeyHash(final Context context) {
        PackageInfo packageInfo = Utility.getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.w("urirang", "디버그 keyHash " + Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("urirang", "디버그 keyHash" + signature, e);
            }
        }

        return null;
    }
}