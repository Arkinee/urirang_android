package com.makeus.urirang.android.src.howAboutThis.write;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisWriteTopicView;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HowAboutThisWriteActivity extends BaseActivity implements HowAboutThisWriteTopicView {

    private Context mContext;
    private ArrayList<MultipartBody.Part> imageList;
    private RequestBody requestFile;
    private MultipartBody.Part filePart;
    private boolean mIsSelected;

    private EditText mWriteTopicEdtTitle;
    private TextView mWriteTopicTvRegister;
    private ImageView mWriteTopicIvMain;
    private ImageView mWriteTopicIvAdd;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_this_write);

        imageList = new ArrayList<>();
        mContext = this;
        mIsSelected = false;

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mWriteTopicIvMain = findViewById(R.id.how_about_this_write_iv_main);
        mWriteTopicIvAdd = findViewById(R.id.how_about_this_write_iv_add_image);
        mWriteTopicEdtTitle = findViewById(R.id.how_about_this_write_edt_title);
        mWriteTopicTvRegister = findViewById(R.id.how_about_this_write_tv_register);
        mWriteTopicEdtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mWriteTopicEdtTitle.getText().toString().equals("")) {
                    mWriteTopicTvRegister.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                } else {
                    mWriteTopicTvRegister.setTextColor(mContext.getResources().getColor(R.color.colorModifyCancel));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void howWriteOnClick(View view) {

        switch (view.getId()) {
            case R.id.how_about_this_write_tv_cancel:
                finish();
                break;
            case R.id.how_about_this_write_tv_register:
                if (mWriteTopicEdtTitle.getText().toString().equals("")) {
                    showCustomToastShort("토픽 제목을 입력해 주세요");
                    return;
                }

                if (mIsSelected) {

                    final HowAboutThisWriteService writeService = new HowAboutThisWriteService(this, this);
                    writeService.tryPostHowAboutThisTopic(mWriteTopicEdtTitle.getText().toString(), "topic", false, filePart);
                    showProgressDialog();

                } else {
                    showCustomToastShort("이미지를 선택해 주세요.");
                }

                break;
            case R.id.how_about_this_write_card:
                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            mWriteTopicIvMain.setImageURI(fileUri);
            mWriteTopicIvAdd.setVisibility(View.GONE);

            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);
            filePart = MultipartBody.Part.createFormData("images", file.getName(),  RequestBody.create(MediaType.parse("image/*"), file));
            mIsSelected = true;

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
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

    @Override
    public void tryPostHowAboutThisWriteTopicSuccess() {
        hideProgressDialog();
        finish();
    }

    @Override
    public void tryPostHowAboutThisWriteTopicFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}