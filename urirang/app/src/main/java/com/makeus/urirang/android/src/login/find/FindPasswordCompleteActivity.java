package com.makeus.urirang.android.src.login.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;

public class FindPasswordCompleteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_complete);
    }

    public void findPasswordCompleteOnClick(View view) {
        switch (view.getId()){
            case R.id.find_password_complete_tv_go_init:
                Intent goSocialIntent = new Intent(this, SocialLoginActivity.class);
                startActivity(goSocialIntent);
                finish();
                break;
        }
    }

}