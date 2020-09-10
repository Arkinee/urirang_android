package com.makeus.urirang.android.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 1000); // 1초 후에 hd handler 실행
    }

    private class splashHandler implements Runnable {
        public void run() {

            Intent goLogin = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(goLogin);
            finish();
        }
    }
}