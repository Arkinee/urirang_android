package com.makeus.urirang.android.src.login.social;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.email.EmailLoginActivity;
import com.makeus.urirang.android.src.login.info.InfoActivity;
import com.makeus.urirang.android.src.login.signup.SignUpActivity;
import com.makeus.urirang.android.src.login.social.interfaces.KakaoLoginView;
import com.makeus.urirang.android.src.login.social.interfaces.SocialActivityView;
import com.makeus.urirang.android.src.main.MainActivity;

import java.util.HashMap;

public class SocialLoginActivity extends BaseActivity implements SocialActivityView, KakaoLoginView {

    private KakaoLoginView mKakaoView;
    private Context mContext;

    private SessionCallback mSessionCallBack;
    private Session session;
    private final int KAKAO_EXTRA_INFO = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

        mKakaoView = this;
        mContext = this;

        mSessionCallBack = new SessionCallback(this, this);
        session = Session.getCurrentSession();
        session.addCallback(mSessionCallBack);
    }

    public void socialLoginOnclick(View view) {
        switch (view.getId()) {
            case R.id.social_login_tv_go_email_login:
                Intent intent = new Intent(this, EmailLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.social_login_tv_use_sign_up:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
            case R.id.social_login_relative_bottom:
                session.open(AuthType.KAKAO_TALK, SocialLoginActivity.this);
                hideProgressDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void tryGetIsMemberSuccessGoMain(String token) {
//        Log.d("로그", "go main");
        final SocialService socialService = new SocialService(mKakaoView, mContext, token);
        HashMap<String, Object> params = new HashMap<>();
        params.put("accessToken", token);
        params.put("nickname", "");
        params.put("mbti", "");
        socialService.tryPostKakaoLogin(params);
        showProgressDialog();
    }

    @Override
    public void tryGetIsMemberSuccessNeedSignUp(String token) {
//        Log.d("로그", "need sign up");
        hideProgressDialog();
        Intent goExtraInfo = new Intent(this, InfoActivity.class);
        goExtraInfo.putExtra("token", token);
        goExtraInfo.putExtra("kakao", true);
        startActivityForResult(goExtraInfo, KAKAO_EXTRA_INFO);
    }

    @Override
    public void tryGetIsMemberFailure(String message) {
//        Log.d("로그", "login failure");
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryPostKakaoLoginSuccess() {
        hideProgressDialog();
        Intent goMainIntent = new Intent(this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }

    @Override
    public void tryPostKakaoLoginFailure() {
        hideProgressDialog();
        showCustomToastShort("로그인 실패");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallBack);
    }
}