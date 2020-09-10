package com.makeus.urirang.android.src.login.social;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.email.EmailLoginActivity;
import com.makeus.urirang.android.src.login.signup.SignUpActivity;

public class SocialLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);
    }

    public void socialLoginOnclick(View view){
        switch (view.getId()){
            case R.id.social_login_tv_go_email_login:
                Intent intent = new Intent(this, EmailLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.social_login_tv_use_sign_up:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
        }
    }
}