package com.makeus.urirang.android.src.testResults.write;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.testResults.TestResultsService;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsWriteActivityView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TestResultsWriteActivity extends BaseActivity implements TestResultsWriteActivityView {

    private Context mContext;
    private boolean mDoubleClickFlag = false;
    private int mSelectedImage = 1;

    private ArrayList<File> mFileList;
    private File[] mFileArray;
    private Uri[] mUriArray;
    private boolean[] mFileExist;
    private ArrayList<Uri> mUriList;
    private ArrayList<Images> mImageList;
    private ArrayList<Integer> mRemoveList;

    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;

    private ImageView mIvRemove1;
    private ImageView mIvRemove2;
    private ImageView mIvRemove3;

    private ImageView mIvPlus1;
    private ImageView mIvPlus2;
    private ImageView mIvPlus3;

    private EditText mEdtResult;

    private int mImageCount = 0;
    private int mType = 1;  // 1 : create, 2: update
    private int mTestId = 1;    // 1: 에니어그램, 2: 에고그램, 3: 빅파이브, 4: 성인애착유형, 5:mgram

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results_write);

        mContext = this;
        mFileList = new ArrayList<>();
        mUriList = new ArrayList<>();
        mImageList = new ArrayList<>();
        mRemoveList = new ArrayList<>();

        mFileArray = new File[3];
        mFileExist = new boolean[3];

        mTestId = getIntent().getIntExtra("testId", 1);
        mType = getIntent().getIntExtra("type", 1);

        mEdtResult = findViewById(R.id.test_results_write_edt_test_results);
        mEdtResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdtResult.getText().toString().equals("")) {
                    ((TextView) findViewById(R.id.test_results_write_tv_register)).setTextColor(getResources().getColor(R.color.colorModifyCancel));
                } else {
                    ((TextView) findViewById(R.id.test_results_write_tv_register)).setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIv1 = findViewById(R.id.test_results_write_iv_main_1);
        mIv2 = findViewById(R.id.test_results_write_iv_main_2);
        mIv3 = findViewById(R.id.test_results_write_iv_main_3);

        mIvRemove1 = findViewById(R.id.test_results_write_iv_main_remove_1);
        mIvRemove2 = findViewById(R.id.test_results_write_iv_main_remove_2);
        mIvRemove3 = findViewById(R.id.test_results_write_iv_main_remove_3);

        mIvPlus1 = findViewById(R.id.test_results_write_plus_1);
        mIvPlus2 = findViewById(R.id.test_results_write_plus_2);
        mIvPlus3 = findViewById(R.id.test_results_write_plus_3);

        TextView tvTop = findViewById(R.id.test_results_tv_top);
        switch (mTestId) {
            case 1:
                tvTop.setText("에니어그램");
                break;
            case 2:
                tvTop.setText("에고그램");
                break;
            case 3:
                tvTop.setText("빅파이브");
                break;
            case 4:
                tvTop.setText("성인애착유형");
                break;
            case 5:
                tvTop.setText("MGRAM");
                break;
        }

        if (mType == 2) { // 수정
            mImageList = (ArrayList<Images>) getIntent().getSerializableExtra("imageList");

            int cnt = 1;
            if (mImageList != null) {
                for (Images image : mImageList) {
                    Uri uri = Uri.parse(image.getUrl());
                    mUriList.add(uri);

                    switch (cnt) {
                        case 1:
                            Glide.with(this).load(image.getUrl()).into(mIv1);
                            mIvRemove1.setVisibility(View.VISIBLE);
                            mIvPlus1.setVisibility(View.GONE);
                            break;
                        case 2:
                            Glide.with(this).load(image.getUrl()).into(mIv2);
                            mIvRemove2.setVisibility(View.VISIBLE);
                            mIvPlus2.setVisibility(View.GONE);
                            break;
                        case 3:
                            Glide.with(this).load(image.getUrl()).into(mIv3);
                            mIvRemove3.setVisibility(View.VISIBLE);
                            mIvPlus3.setVisibility(View.GONE);
                            break;
                    }

                    cnt += 1;
                }
                mImageCount = mImageList.size();
            }

            mEdtResult.setText(getIntent().getStringExtra("result"));
        }

    }

    void postCreateTestResult() {
        HashMap<String, RequestBody> map = new HashMap<>();

        List<MultipartBody.Part> images = new ArrayList<>();

        for (File f : mFileList) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), f);
            images.add(MultipartBody.Part.createFormData("images", f.getName(), fileBody));
        }

        RequestBody resultBody = RequestBody.create(MediaType.parse("text/plain"), mEdtResult.getText().toString());

        map.put("resultText", resultBody);

        final TestResultsService createService = new TestResultsService(this, this);
        createService.tryPostWriteTestResults(mTestId, map, images);
        showProgressDialog();
    }

    void postUpdateTestResult() {
        HashMap<String, RequestBody> map = new HashMap<>();

        List<MultipartBody.Part> images = new ArrayList<>();

        for (File f : mFileList) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), f);
            images.add(MultipartBody.Part.createFormData("images", f.getName(), fileBody));
        }

        int cnt = 0;
        for (int i : mRemoveList) {
            RequestBody removeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(i));
            String key = "removeImageIdList[" + cnt + "]";
            cnt += 1;
            map.put(key, removeBody);
        }

        RequestBody resultBody = RequestBody.create(MediaType.parse("text/plain"), mEdtResult.getText().toString());

        map.put("resultText", resultBody);

        final TestResultsService createService = new TestResultsService(this, this);
        createService.tryPostModifyTestResults(mTestId, map, images);
        showProgressDialog();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;

    }

    public void testWriteOnClick(View view) {

        switch (view.getId()) {
            case R.id.test_results_write_tv_cancel:
                finish();
                break;
            case R.id.test_results_write_tv_register:
                if (mEdtResult.getText().toString().equals("")) {
                    showCustomToastShort("결과 유형을 입력해 주세요");
                    return;
                }

                if (mType == 1) {
                    postCreateTestResult();
                } else if (mType == 2) {
                    postUpdateTestResult();
                }

                break;
            case R.id.test_results_write_card_1:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 1;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
                break;
            case R.id.test_results_write_card_2:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 2;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;
            case R.id.test_results_write_card_3:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 3;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;

            case R.id.test_results_write_iv_main_remove_1:
                mImageCount -= 1;

                if (mImageList.size() == 0) {
                    mFileList.remove(0);
                } else {
                    mRemoveList.add(mImageList.get(0).getId());
                    mImageList.remove(0);
                }

                mUriList.remove(0);

                switch (mImageCount) {
                    case 0:
                        mIv1.setImageDrawable(null);
                        mIvRemove1.setVisibility(View.GONE);
                        mIvPlus1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mIvRemove2.setVisibility(View.GONE);
                        mIvPlus2.setVisibility(View.VISIBLE);

                        mIv1.setImageURI(mUriList.get(0));
                        mIv2.setImageDrawable(null);
                        break;
                    case 2:
                        mIvRemove3.setVisibility(View.GONE);
                        mIvPlus3.setVisibility(View.VISIBLE);

                        mIv1.setImageURI(mUriList.get(0));
                        mIv2.setImageURI(mUriList.get(1));
                        mIv3.setImageDrawable(null);
                        break;
                }

                break;
            case R.id.test_results_write_iv_main_remove_2:
                mImageCount -= 1;

                if (mImageList.size() <= 1) {
                    mFileList.remove(1);
                } else {
                    mRemoveList.add(mImageList.get(1).getId());
                    mImageList.remove(1);
                }

                mUriList.remove(1);

                switch (mImageCount) {
                    case 1:
                        mIvRemove2.setVisibility(View.GONE);
                        mIvPlus2.setVisibility(View.VISIBLE);

                        mIv2.setImageDrawable(null);
                        break;
                    case 2:
                        mIvRemove3.setVisibility(View.GONE);
                        mIvPlus3.setVisibility(View.VISIBLE);

                        mIv2.setImageURI(mUriList.get(1));
                        mIv3.setImageDrawable(null);
                        break;
                }

                break;
            case R.id.test_results_write_iv_main_remove_3:
                mImageCount -= 1;

                if (mImageList.size() <= 2) {
                    mFileList.remove(2);
                } else {
                    mRemoveList.add(mImageList.get(2).getId());
                    mImageList.remove(2);
                }

                mUriList.remove(2);

                switch (mImageCount) {
                    case 2:
                        mIvRemove3.setVisibility(View.GONE);
                        mIvPlus3.setVisibility(View.VISIBLE);

                        mIv3.setImageDrawable(null);
                        break;
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
            File file = ImagePicker.Companion.getFile(data);

            if (mImageCount != 3) {

                mFileArray[mImageCount] = file;
                mImageCount += 1;

                switch (mImageCount) {
                    case 1:
                        mIv1.setImageURI(fileUri);
                        mIvRemove1.setVisibility(View.VISIBLE);
                        mIvPlus1.setVisibility(View.GONE);
                        break;
                    case 2:
                        mIv2.setImageURI(fileUri);
                        mIvRemove2.setVisibility(View.VISIBLE);
                        mIvPlus2.setVisibility(View.GONE);
                        break;
                    case 3:
                        mIv3.setImageURI(fileUri);
                        mIvRemove3.setVisibility(View.VISIBLE);
                        mIvPlus3.setVisibility(View.GONE);
                        break;
                }

                mFileList.add(file);
                mUriList.add(fileUri);

            } else {  // 이미 3개가 골라져 있다면
                switch (mSelectedImage) {
                    case 1:
                        mIv1.setImageURI(fileUri);
                        if (mType == 2) {
                            if (mImageList.size() >= 1) {
                                mFileList.add(file);
                                mRemoveList.add(mImageList.get(0).getId());
                                mImageList.remove(0);
                            } else {
                                mFileList.set(0, file);
                            }
                        } else {
                            mFileList.set(0, file);
                        }

                        mUriList.set(0, fileUri);
                        break;
                    case 2:
                        mIv2.setImageURI(fileUri);
                        if (mType == 2) {
                            if (mImageList.size() >= 2) {
                                mFileList.add(file);
                                mRemoveList.add(mImageList.get(1).getId());
                                mImageList.remove(1);
                            } else {
                                mFileList.set(1, file);
                            }
                        } else {
                            mFileList.set(1, file);
                        }
                        mUriList.set(1, fileUri);
                        break;
                    case 3:
                        mIv3.setImageURI(fileUri);
                        if (mType == 2) {
                            if (mImageList.size() == 3) {
                                mFileList.add(file);
                                mRemoveList.add(mImageList.get(2).getId());
                                mImageList.remove(2);
                            } else {
                                mFileList.set(2, file);
                            }
                        } else {
                            mFileList.set(2, file);
                        }
                        mUriList.set(2, fileUri);
                        break;
                }
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void tryPostWriteResultsSuccess() {
        hideProgressDialog();
        showCustomToastShort("결과 등록 완료");
        finish();
    }

    @Override
    public void tryPostWriteResultsFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mDoubleClickFlag = false;
    }
}