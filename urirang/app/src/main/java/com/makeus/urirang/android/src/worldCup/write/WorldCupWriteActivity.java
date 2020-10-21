package com.makeus.urirang.android.src.worldCup.write;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.main.fragments.worldCup.WorldCupService;
import com.makeus.urirang.android.src.main.fragments.worldCup.interfaces.WorldCupView;
import com.makeus.urirang.android.src.withAll.WithAllService;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllWriteActivityView;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupWriteView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WorldCupWriteActivity extends BaseActivity implements WorldCupWriteView {

    private Context mContext;
    private InputMethodManager mInputMethodManager;
    private ArrayList<File> mFileList;
    private ArrayList<Uri> mUriList;
    private ArrayList<String> mCandidateList;

    private ConstraintLayout mConstraintInformation;
    private ScrollView mScrollCandidates;

    private EditText mEdtTitle;
    private EditText mEdtDescription;

    private ImageView mIvRound8;
    private ImageView mIvRound16;
    private TextView mTvRound8;
    private TextView mTvRound16;

    private boolean mDoubleClick = false;
    private int mImageCount = 0;
    private int mRoundNum = 0;
    private int mScreenNum = 1;
    private boolean mIsTitleExist = false;
    private boolean mIsDescriptionExist = false;

    private CardView mCardCandidate9;
    private CardView mCardCandidate10;
    private CardView mCardCandidate11;
    private CardView mCardCandidate12;
    private CardView mCardCandidate13;
    private CardView mCardCandidate14;
    private CardView mCardCandidate15;
    private CardView mCardCandidate16;

    private ImageView mIvCandidate1;
    private ImageView mIvCandidate2;
    private ImageView mIvCandidate3;
    private ImageView mIvCandidate4;
    private ImageView mIvCandidate5;
    private ImageView mIvCandidate6;
    private ImageView mIvCandidate7;
    private ImageView mIvCandidate8;
    private ImageView mIvCandidate9;
    private ImageView mIvCandidate10;
    private ImageView mIvCandidate11;
    private ImageView mIvCandidate12;
    private ImageView mIvCandidate13;
    private ImageView mIvCandidate14;
    private ImageView mIvCandidate15;
    private ImageView mIvCandidate16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_cup_write);

        mContext = this;
        mCandidateList = new ArrayList<>();
        mFileList = new ArrayList<>();
        mUriList = new ArrayList<>();
        mCandidateList = new ArrayList<>();

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mConstraintInformation = findViewById(R.id.world_cup_write_constraint_information);
        mScrollCandidates = findViewById(R.id.world_cup_write_scroll_candidates);

        mEdtTitle = findViewById(R.id.world_cup_write_edt_title);
        mEdtDescription = findViewById(R.id.world_cup_write_edt_description);

        mTvRound8 = findViewById(R.id.world_cup_write_tv_round_8);
        mTvRound16 = findViewById(R.id.world_cup_write_tv_round_16);
        mIvRound8 = findViewById(R.id.world_cup_write_iv_round_8);
        mIvRound16 = findViewById(R.id.world_cup_write_iv_round_16);

        initialize();

        mEdtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdtTitle.getText().toString().equals("")) {
                    mIsTitleExist = false;
                    ((TextView) findViewById(R.id.world_cup_write_tv_cancel)).setTextColor(getResources().getColor(R.color.colorModifyCancel));
                    ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setTextColor(getResources().getColor(R.color.colorModifyCancel));
                } else {
                    mIsTitleExist = true;

                    if (mIsDescriptionExist && mRoundNum != 0) {
                        ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setTextColor(getResources().getColor(R.color.colorBlack));
                        ((TextView) findViewById(R.id.world_cup_write_tv_cancel)).setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdtDescription.getText().toString().equals("")) {
                    mIsDescriptionExist = false;
                    ((TextView) findViewById(R.id.world_cup_write_tv_cancel)).setTextColor(getResources().getColor(R.color.colorModifyCancel));
                    ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setTextColor(getResources().getColor(R.color.colorModifyCancel));
                } else {
                    mIsDescriptionExist = true;

                    if (mIsTitleExist && mRoundNum != 0) {
                        ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setTextColor(getResources().getColor(R.color.colorBlack));
                        ((TextView) findViewById(R.id.world_cup_write_tv_cancel)).setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void initialize() {

        mCardCandidate9 = findViewById(R.id.candidate_9_card);
        mCardCandidate10 = findViewById(R.id.candidate_10_card);
        mCardCandidate11 = findViewById(R.id.candidate_11_card);
        mCardCandidate12 = findViewById(R.id.candidate_12_card);
        mCardCandidate13 = findViewById(R.id.candidate_13_card);
        mCardCandidate14 = findViewById(R.id.candidate_14_card);
        mCardCandidate15 = findViewById(R.id.candidate_15_card);
        mCardCandidate16 = findViewById(R.id.candidate_16_card);

        mIvCandidate1 = findViewById(R.id.candidate_1_iv);
        mIvCandidate2 = findViewById(R.id.candidate_2_iv);
        mIvCandidate3 = findViewById(R.id.candidate_3_iv);
        mIvCandidate4 = findViewById(R.id.candidate_4_iv);
        mIvCandidate5 = findViewById(R.id.candidate_5_iv);
        mIvCandidate6 = findViewById(R.id.candidate_6_iv);
        mIvCandidate7 = findViewById(R.id.candidate_7_iv);
        mIvCandidate8 = findViewById(R.id.candidate_8_iv);
        mIvCandidate9 = findViewById(R.id.candidate_9_iv);
        mIvCandidate10 = findViewById(R.id.candidate_10_iv);
        mIvCandidate11 = findViewById(R.id.candidate_11_iv);
        mIvCandidate12 = findViewById(R.id.candidate_12_iv);
        mIvCandidate13 = findViewById(R.id.candidate_13_iv);
        mIvCandidate14 = findViewById(R.id.candidate_14_iv);
        mIvCandidate15 = findViewById(R.id.candidate_15_iv);
        mIvCandidate16 = findViewById(R.id.candidate_16_iv);

    }

    void postWorldCup() {

        HashMap<String, RequestBody> map = new HashMap<>();

        List<MultipartBody.Part> images = new ArrayList<>();

        for (File f : mFileList) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), f);
            images.add(MultipartBody.Part.createFormData("images", f.getName(), fileBody));
        }

        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), mEdtTitle.getText().toString());
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), mEdtDescription.getText().toString());
        RequestBody roundNumBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mRoundNum));

        map.put("title", titleBody);
        map.put("description", descriptionBody);
        map.put("roundNum", roundNumBody);

        final WorldCupService worldCupWriteService = new WorldCupService(this, mContext);
        worldCupWriteService.tryPostWorldCup(map, images);
        showProgressDialog();
    }

    public void worldCupWriteOnClick(View view) {
        switch (view.getId()) {
            case R.id.world_cup_write_linear_round_8:
                mRoundNum = 8;
                mIvRound8.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_selected));
                mTvRound8.setTextColor(getResources().getColor(R.color.colorBasicBlack38));
                mIvRound16.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_default));
                mTvRound16.setTextColor(getResources().getColor(R.color.colorSearchHint));
                break;
            case R.id.world_cup_write_linear_round_16:
                mRoundNum = 16;
                mTvRound8.setTextColor(getResources().getColor(R.color.colorSearchHint));
                mIvRound8.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_default));
                mTvRound16.setTextColor(getResources().getColor(R.color.colorBasicBlack38));
                mIvRound16.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_selected));
                break;
            case R.id.with_all_write_tv_cancel:
                if (mScreenNum == 1) {
                    finish();
                } else if (mScreenNum == 2) {
                    mScreenNum -= 1;

                    ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setText(getString(R.string.signup_tv_next));

                    mConstraintInformation.animate().alpha(0f).setDuration(750).withEndAction(
                            () -> {
                                mConstraintInformation.setAlpha(1f);
                            }
                    ).start();

                    mScrollCandidates.animate().alpha(1f).setDuration(750).withEndAction(
                            () -> {
                                mScrollCandidates.setAlpha(0f);
                            }
                    ).start();

                }
            case R.id.world_cup_write_tv_next_and_register:
                if (mDoubleClick) return;
                mDoubleClick = true;

                if (mScreenNum == 1) {

                    if (mRoundNum == 0) {
                        showCustomToastShort("몇 강으로 만들까요?");
                        return;
                    }

                    if (mEdtTitle.getText().toString().equals("")) {
                        showCustomToastShort("월드컵 제목을 입력해 주세요");
                        return;
                    }

                    if (mEdtDescription.getText().toString().equals("")) {
                        showCustomToastShort("간단한 설명을 입력해 주세요");
                        return;
                    }

                    mScreenNum += 1;
                    ((TextView) findViewById(R.id.world_cup_write_tv_next_and_register)).setText(getString(R.string.how_about_this_write_tv_register));

                    mConstraintInformation.animate().alpha(1f).setDuration(750).withEndAction(
                            () -> {
                                mConstraintInformation.setAlpha(0f);
                            }
                    ).start();

                    mScrollCandidates.animate().alpha(0f).setDuration(750).withEndAction(
                            () -> {
                                mScrollCandidates.setAlpha(1f);
                            }
                    ).start();

                } else if (mScreenNum == 2) {
                    mScreenNum -= 1;

                    postWorldCup();

                }
                break;

        }
    }

    // 이미지 선택 후
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            mUriList.add(fileUri);

            if (mImageCount != mRoundNum) {

                mImageCount += 1;

                if (mImageCount == 1) {
                    mIvCandidate1.setImageURI(fileUri);
                } else if (mImageCount == 2) {
                    mIvCandidate2.setImageURI(fileUri);
                } else if (mImageCount == 3) {
                    mIvCandidate3.setImageURI(fileUri);
                } else if (mImageCount == 4) {
                    mIvCandidate4.setImageURI(fileUri);
                } else if (mImageCount == 5) {
                    mIvCandidate5.setImageURI(fileUri);
                } else if (mImageCount == 6) {
                    mIvCandidate6.setImageURI(fileUri);
                } else if (mImageCount == 7) {
                    mIvCandidate7.setImageURI(fileUri);
                } else if (mImageCount == 8) {
                    mIvCandidate8.setImageURI(fileUri);
                } else if (mImageCount == 9) {
                    mIvCandidate9.setImageURI(fileUri);
                } else if (mImageCount == 10) {
                    mIvCandidate10.setImageURI(fileUri);
                } else if (mImageCount == 11) {
                    mIvCandidate11.setImageURI(fileUri);
                } else if (mImageCount == 12) {
                    mIvCandidate12.setImageURI(fileUri);
                } else if (mImageCount == 13) {
                    mIvCandidate13.setImageURI(fileUri);
                } else if (mImageCount == 14) {
                    mIvCandidate14.setImageURI(fileUri);
                } else if (mImageCount == 15) {
                    mIvCandidate15.setImageURI(fileUri);
                } else if (mImageCount == 16) {
                    mIvCandidate16.setImageURI(fileUri);
                }

            } else {


            }

            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            if (file.exists()) {
                long fileSize = file.length();
                Log.d("BreezeWind", "file size: " + fileSize);
                if (fileSize > 1000000) { // 2 mb
                    // 압축
                    try {
                        file = new Compressor(WorldCupWriteActivity.this).compressToFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long afterFileSize = file.length();
                    Log.d("BreezeWind", "after file size: " + afterFileSize);
                }
            }

            mFileList.add(file);

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
    protected void onResume() {
        super.onResume();
        mDoubleClick = false;
    }

    @Override
    public void tryPostWorldCupSuccess() {
        hideProgressDialog();
        mDoubleClick = false;
        finish();
    }

    @Override
    public void tryPostWorldCupFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        mDoubleClick = false;
    }
}