package com.makeus.urirang.android.src.login.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginActivityView;
import com.makeus.urirang.android.src.login.find.FindPasswordActivity;
import com.makeus.urirang.android.src.main.MainActivity;

import java.util.HashMap;

public class EmailLoginActivity extends BaseActivity implements EmailLoginActivityView {

    private boolean mDoubleClickFlag = false;
    private EditText mEmailLoginEdtEmail;
    private EditText mEmailLoginEdtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        mEmailLoginEdtEmail = findViewById(R.id.email_login_edt_email);
        mEmailLoginEdtPassword = findViewById(R.id.email_login_edt_password);

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
            case R.id.email_login_tv_login:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                HashMap<String, Object> params = new HashMap<>();
                params.put("email", mEmailLoginEdtEmail.getText().toString());
                params.put("password", mEmailLoginEdtPassword.getText().toString());

                EmailLoginService loginService = new EmailLoginService(this, this);
                loginService.tryPostLogin(params);
                showProgressDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;

    }

    @Override
    public void tryPostLoginSuccess() {
        hideProgressDialog();
        Intent goMainIntent = new Intent(this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }

    @Override
    public void tryPostLoginFailure(String message) {
        hideProgressDialog();
        showCustomToastShort("로그인 실패");
        mDoubleClickFlag = false;
    }

}