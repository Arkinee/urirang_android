package com.makeus.urirang.android.src.modifyProfile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.modifyProfile.interfaces.ModifyActivityView;

import java.util.HashMap;

public class ModifyProfileActivity extends BaseActivity implements ModifyActivityView {

    private EditText mModifyEdtNickname;
    private EditText mModifyEdtMbti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        mModifyEdtNickname = findViewById(R.id.modify_profile_edt_nickname);
        mModifyEdtMbti = findViewById(R.id.modify_profile_edt_mbti);

        mModifyEdtNickname.setText(getIntent().getStringExtra("nickname"));
        mModifyEdtMbti.setText(getIntent().getStringExtra("mbti"));

    }

    public void modifyOnClick(View view) {

        switch (view.getId()) {
            case R.id.modify_profile_tv_cancel:
                finish();
                break;
            case R.id.modify_profile_tv_complete:

                if (mModifyEdtNickname.getText().toString().equals("")) {
                    showCustomToastShort("닉네임을 입력하세요");
                    return;
                }

                if (mModifyEdtMbti.getText().toString().equals("")) {
                    showCustomToastShort("MBTI를 입력하세요");
                    return;
                }

                if (!isMbTi(mModifyEdtMbti.getText().toString())) {
                    showCustomToastShort("올바른 대문자 MBTI 유형을 입력하세요");
                    return;
                }

                final ModifyProfileService modifyService = new ModifyProfileService(this, this);
                HashMap<String, Object> params = new HashMap<>();
                params.put("nickname", mModifyEdtNickname.getText().toString());
                params.put("mbti", mModifyEdtMbti.getText().toString().toLowerCase());
                modifyService.tryPutModifyProfile(params);
                showProgressDialog();
                break;
        }
    }

    @Override
    public void tryPutModifyProfileSuccess(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        finish();
    }

    @Override
    public void tryPutModifyProfileFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    boolean isMbTi(String a) {
        return a.equals("INTJ") || a.equals("INFJ") || a.equals("ISTJ") || a.equals("ISTP") ||
                a.equals("INTP") || a.equals("INFP") || a.equals("ISFJ") || a.equals("ISFP") ||
                a.equals("ENTJ") || a.equals("ENFJ") || a.equals("ESTJ") || a.equals("ESTP") ||
                a.equals("ENTP") || a.equals("ENFP") || a.equals("ESFJ") || a.equals("ESFP");
    }
}