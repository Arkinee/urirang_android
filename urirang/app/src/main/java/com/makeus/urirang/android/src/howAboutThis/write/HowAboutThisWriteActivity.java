package com.makeus.urirang.android.src.howAboutThis.write;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisWriteTopicView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HowAboutThisWriteActivity extends BaseActivity implements HowAboutThisWriteTopicView {

    private Context mContext;
    private File mFile;
    private boolean mIsSelected;

    private EditText mWriteTopicEdtTitle;
    private TextView mWriteTopicTvRegister;
    private ImageView mWriteTopicIvMain;
    private ImageView mWriteTopicIvAdd;

    private InputMethodManager mInputMethodManager;

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_this_write);

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

                    Map<String, RequestBody> map = new HashMap<>();
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), mFile);
                    RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), mWriteTopicEdtTitle.getText().toString());
                    RequestBody typeBody = RequestBody.create(MediaType.parse("text/plain"), "topic");
                    RequestBody isAnonymousBody = RequestBody.create(MediaType.parse("text/plain"), "false");

                    map.put("images\"; filename=\"" + mFile.getName(), fileBody);
                    map.put("title", titleBody);
                    map.put("type", typeBody);
                    map.put("isAnonymous", isAnonymousBody);

                    final HowAboutThisWriteService writeService = new HowAboutThisWriteService(this, this);
                    writeService.tryPostHowAboutThisTopic(map);
                    showProgressDialog();

                } else {
                    showCustomToastShort("이미지를 선택해 주세요.");
                }

                break;
            case R.id.how_about_this_write_card:

                int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

                if (permssionCheck != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                        Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                        Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();

                    }
                } else {

                    ImagePicker.Companion.with(this)
                            .crop()
                            .compress(1024)
                            .maxResultSize(1080, 1080)
                            .start();
                }
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
            mFile = file;
//            filePart = MultipartBody.Part.createFormData("images", file.getName(),  RequestBody.create(MediaType.parse("image/*"), file));
            mIsSelected = true;

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "승인이 허가되었습니다", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "승인이 거부되었습니다", Toast.LENGTH_LONG).show();
                }
                return;
            }

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

    /*
    Copyright 2019, The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
     */
}