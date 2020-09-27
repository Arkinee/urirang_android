package com.makeus.urirang.android.src.login.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.info.interfaces.InfoActivityView;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;
import com.makeus.urirang.android.src.login.social.SocialService;
import com.makeus.urirang.android.src.login.social.interfaces.KakaoLoginView;
import com.makeus.urirang.android.src.main.MainActivity;

import java.util.HashMap;

public class InfoActivity extends BaseActivity implements InfoActivityView, KakaoLoginView {

    private String mSelectedMbti = "";
    private EditText mInfoEdtNickname;
    private InputMethodManager mInputMethodManager;

    private boolean mDoubleClickFlag = false;
    private boolean mKakaoSignUp;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_info);

        mKakaoSignUp = getIntent().getBooleanExtra("kakao", false);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInfoEdtNickname = findViewById(R.id.info_edt_nickname);

        if(mKakaoSignUp) mToken = getIntent().getStringExtra("token");
    }

    public void infoOnClick(View view) {
        switch (view.getId()) {
            case R.id.info_iv_back_arrow:
                finish();
                break;
            case R.id.info_tv_complete:

                if(mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                if(mInfoEdtNickname.getText().toString().equals("")){
                    showCustomToastShort(getString(R.string.info_toast_nickname));
                    mDoubleClickFlag = false;
                    return;
                }

                if(mSelectedMbti.equals("")){
                    showCustomToastShort(getString(R.string.info_toast_mbti));
                    mDoubleClickFlag = false;
                    return;
                }

                if(!mKakaoSignUp) {
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("email", getIntent().getStringExtra("email"));
                    params.put("password", getIntent().getStringExtra("password"));
                    params.put("nickname", mInfoEdtNickname.getText().toString());
                    params.put("mbti", mSelectedMbti);

                    final InfoService signupService = new InfoService(this, this);
                    signupService.tryPostSignup(params);
                    showProgressDialog();
                }else{
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("accessToken", mToken);
                    params.put("nickname", mInfoEdtNickname.getText().toString());
                    params.put("mbti", mSelectedMbti);

                    final SocialService socialService = new SocialService(this, this, mToken);
                    socialService.tryPostKakaoSignUp(params);
                    showProgressDialog();
                }

                break;
            case R.id.info_constraint_go_test:
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC");
                urlIntent.setData(uri);
                startActivity(urlIntent);
                break;
            // 1번째 줄
            case R.id.info_intj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_intj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_intj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_1_intj_selected));
                mSelectedMbti = "intj";
                break;
            case R.id.info_infj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_infj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_infj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_2_infj_selected));
                mSelectedMbti = "infj";
                break;
            case R.id.info_istj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_istj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_istj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_3_istj_selected));
                mSelectedMbti = "istj";
                break;
            case R.id.info_istp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_istp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_istp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_4_istp_selected));
                mSelectedMbti = "istp";
                break;

            // 2번째 줄
            case R.id.info_intp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_intp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_intp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_5_intp_selected));
                mSelectedMbti = "intp";
                break;
            case R.id.info_infp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_infp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_infp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_6_infp_selected));
                mSelectedMbti = "infp";
                break;
            case R.id.info_isfj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_isfj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_isfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_7_isfj_selected));
                mSelectedMbti = "isfj";
                break;
            case R.id.info_isfp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_isfp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_isfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_8_isfp_selected));
                mSelectedMbti = "isfp";
                break;

            // 3번째 줄
            case R.id.info_entj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_entj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_entj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_9_entj_selected));
                mSelectedMbti = "entj";
                break;
            case R.id.info_enfj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_enfj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_enfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_10_enfj_selected));
                mSelectedMbti = "enfj";
                break;
            case R.id.info_estj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_estj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_estj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_11_estj_selected));
                mSelectedMbti = "estj";
                break;
            case R.id.info_estp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_estp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_estp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_12_estp_selected));
                mSelectedMbti = "estp";
                break;

            // 4번째 줄
            case R.id.info_entp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_entp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_entp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_13_entp_selected));
                mSelectedMbti = "entp";
                break;
            case R.id.info_enfp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_enfp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_enfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_14_enfp_selected));
                mSelectedMbti = "enfp";
                break;
            case R.id.info_esfj:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_esfj_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_esfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_15_esfj_selected));
                mSelectedMbti = "esfj";
                break;
            case R.id.info_esfp:
                uncheckAll();
                ((ImageView)findViewById(R.id.info_iv_esfp_checked)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.info_esfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_16_esfp_selected));
                mSelectedMbti = "esfp";
                break;
        }
    }

    void uncheckAll(){

        ((ImageView)findViewById(R.id.info_iv_intj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_infj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_istj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_istp_checked)).setVisibility(View.INVISIBLE);

        ((ImageView)findViewById(R.id.info_iv_intp_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_infp_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_isfj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_isfp_checked)).setVisibility(View.INVISIBLE);

        ((ImageView)findViewById(R.id.info_iv_entj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_enfj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_estj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_estp_checked)).setVisibility(View.INVISIBLE);

        ((ImageView)findViewById(R.id.info_iv_entp_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_enfp_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_esfj_checked)).setVisibility(View.INVISIBLE);
        ((ImageView)findViewById(R.id.info_iv_esfp_checked)).setVisibility(View.INVISIBLE);

        ((ImageView)findViewById(R.id.info_intj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_1_intj_unselected));
        ((ImageView)findViewById(R.id.info_infj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_2_infj_unselected));
        ((ImageView)findViewById(R.id.info_istj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_3_istj_unselected));
        ((ImageView)findViewById(R.id.info_istp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_4_istp_unselected));

        ((ImageView)findViewById(R.id.info_intp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_5_intp_unselected));
        ((ImageView)findViewById(R.id.info_infp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_6_infp_unselected));
        ((ImageView)findViewById(R.id.info_isfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_7_isfj_unselected));
        ((ImageView)findViewById(R.id.info_isfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_8_isfp_unselected));

        ((ImageView)findViewById(R.id.info_entj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_9_entj_unselected));
        ((ImageView)findViewById(R.id.info_enfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_10_enfj_unselected));
        ((ImageView)findViewById(R.id.info_estj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_11_estj_unselected));
        ((ImageView)findViewById(R.id.info_estp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_12_estp_unselected));

        ((ImageView)findViewById(R.id.info_entp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_13_entp_unselected));
        ((ImageView)findViewById(R.id.info_enfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_14_enfp_unselected));
        ((ImageView)findViewById(R.id.info_esfj)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_15_esfj_unselected));
        ((ImageView)findViewById(R.id.info_esfp)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mbti_16_esfp_unselected));

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


    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
    }

    @Override
    public void signupSuccess(String message) {
        hideProgressDialog();
        showCustomToastShort(message);

        Intent goMain = new Intent(this, SocialLoginActivity.class);
        goMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goMain);
        finish();
    }

    @Override
    public void signupFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        mDoubleClickFlag = false;
    }

    @Override
    public void tryPostKakaoLoginSuccess() {
        hideProgressDialog();
        showCustomToastShort("회원가입 성공");

        Intent goMainIntent = new Intent(this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }

    @Override
    public void tryPostKakaoLoginFailure() {
        hideProgressDialog();
        showCustomToastShort("회원가입 실패");
    }
}