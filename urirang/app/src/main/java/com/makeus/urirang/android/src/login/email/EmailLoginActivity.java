package com.makeus.urirang.android.src.login.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.find.FindPasswordActivity;

public class EmailLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
    }

    public void emailLoginOnClick(View view) {
        switch (view.getId()) {
            case R.id.email_login_iv_back_arrow:
                finish();
                break;
            case R.id.email_login_tv_find_password:
                Intent intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}