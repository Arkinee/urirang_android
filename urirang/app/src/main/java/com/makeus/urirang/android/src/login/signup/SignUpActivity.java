package com.makeus.urirang.android.src.login.signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.info.InfoActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity {

    private EditText mSignUpEdtEmail;
    private EditText mSignUpEdtPass;
    private EditText mSignUpEdtPassCheck;
    private TextView mSignUpTvEmailValidity;
    private TextView mSignUpTvPasswordValidity;
    private TextView mSignUpTvNext;

    private InputMethodManager mInputMethodManager;
    private boolean mFlagEmail = false;
    private boolean mFlagPassword = false;
    private boolean mFlagPasswordCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initialize();
        addListener();
    }

    void initialize() {

        mSignUpEdtEmail = findViewById(R.id.signup_edt_email);
        mSignUpTvEmailValidity = findViewById(R.id.signup_tv_email_validity);
        mSignUpEdtPass = findViewById(R.id.signup_edt_password);
        mSignUpTvPasswordValidity = findViewById(R.id.signup_tv_password_validity);
        mSignUpEdtPassCheck = findViewById(R.id.signup_edt_password_check);
        mSignUpTvNext = findViewById(R.id.signup_tv_next);

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    void addListener() {

        mSignUpEdtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSignUpEdtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mSignUpEdtEmail.getText().toString().length() != 0) {
                        mFlagEmail = true;
                    } else {
                        mFlagEmail = false;
                    }
                }

                if (mFlagEmail && mFlagPassword && mFlagPasswordCheck)
                    mSignUpTvNext.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        mSignUpEdtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mSignUpEdtPass.getText().toString().length() != 0) {
                        mFlagPassword = true;
                    } else {
                        mFlagPassword = false;
                    }
                }

                if (mFlagEmail && mFlagPassword && mFlagPasswordCheck)
                    mSignUpTvNext.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        mSignUpEdtPassCheck.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mSignUpEdtPassCheck.getText().toString().length() != 0) {
                        mFlagPasswordCheck = true;
                    } else {
                        mFlagPasswordCheck = false;
                    }
                }

                if (mFlagEmail && mFlagPassword && mFlagPasswordCheck)
                    mSignUpTvNext.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

    }

    public void signUpOnClick(View view) {
        switch (view.getId()) {
            case R.id.signup_tv_next:

                //이메일 형식 체크
                Pattern pattern = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
                Matcher m = pattern.matcher(mSignUpEdtEmail.getText().toString());

                if (!m.find()) {
                    showCustomToastShort(getString(R.string.signup_toast_email_reg));
                    return;
                }

                Pattern pattern2 = Pattern.compile("^(?=.[a-zA-Z])(?=.[!@#$%^+=-])(?=.[0-9]).{6,16}");
                Matcher m2 = pattern.matcher(mSignUpEdtPass.getText().toString());

//                if (!m.find()) {
//                    showCustomToastShort(getString(R.string.signup_toast_pass_invalid_format));
//                    return;
//                }

                //비밀번호 체크
                if (!mSignUpEdtPass.getText().toString().equals(mSignUpEdtPassCheck.getText().toString())) {
                    showCustomToastShort(getString(R.string.signup_toast_pass_check));
                    return;
                }

                Intent goExtra = new Intent(this, InfoActivity.class);
                goExtra.putExtra("email", mSignUpEdtEmail.getText().toString());
                goExtra.putExtra("password", mSignUpEdtPass.getText().toString());
                startActivity(goExtra);
                break;
            case R.id.signup_iv_back_arrow:
                finish();
                break;
        }
    }

    // 키보드 이외의 영역 터치시 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                if (mInputMethodManager != null)
                    mInputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}