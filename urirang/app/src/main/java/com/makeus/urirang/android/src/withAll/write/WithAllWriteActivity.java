package com.makeus.urirang.android.src.withAll.write;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.withAll.WithAllService;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllWriteActivityView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WithAllWriteActivity extends BaseActivity implements WithAllWriteActivityView {

    private Context mContext;
    private InputMethodManager mInputMethodManager;
    private ArrayList<File> mFileList;
    private ArrayList<Uri> mUriList;

    private EditText mWithAllWriteEdtTitle;
    private EditText mWithAllWriteEdtContent;
    private TextView mWithAllWriteTvAnonymous;
    private boolean mIsAnonymous = false;

    private boolean mDoubleClick = false;
    private int mImageCount = 0;

    private ImageView mWithAllIv1;
    private ImageView mWithAllIv2;
    private ImageView mWithAllIv3;
    private ImageView mWithAllIv4;

    private ImageView mWithAllIvRemove1;
    private ImageView mWithAllIvRemove2;
    private ImageView mWithAllIvRemove3;
    private ImageView mWithAllIvRemove4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_all_write);

        mContext = this;
        mFileList = new ArrayList<>();
        mUriList = new ArrayList<>();
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mWithAllWriteEdtTitle = findViewById(R.id.with_all_write_edt_title);
        mWithAllWriteEdtContent = findViewById(R.id.with_all_write_edt_content);
        mWithAllWriteTvAnonymous = findViewById(R.id.with_all_write_tv_anonymous);

        mWithAllIv1 = findViewById(R.id.with_all_write_iv_main_1);
        mWithAllIv2 = findViewById(R.id.with_all_write_iv_main_2);
        mWithAllIv3 = findViewById(R.id.with_all_write_iv_main_3);
        mWithAllIv4 = findViewById(R.id.with_all_write_iv_main_4);

        mWithAllIvRemove1 = findViewById(R.id.with_all_write_iv_main_remove_1);
        mWithAllIvRemove2 = findViewById(R.id.with_all_write_iv_main_remove_2);
        mWithAllIvRemove3 = findViewById(R.id.with_all_write_iv_main_remove_3);
        mWithAllIvRemove4 = findViewById(R.id.with_all_write_iv_main_remove_4);

        mWithAllWriteEdtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mWithAllWriteEdtTitle.getText().toString().equals("")) {
                    ((TextView) findViewById(R.id.with_all_write_tv_register)).setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                } else {
                    ((TextView) findViewById(R.id.with_all_write_tv_register)).setTextColor(mContext.getResources().getColor(R.color.colorModifyCancel));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void postWithAll() {

        HashMap<String, RequestBody> map = new HashMap<>();

        List<MultipartBody.Part> images = new ArrayList<>();

        for (File f : mFileList) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), f);
            images.add(MultipartBody.Part.createFormData("images", f.getName(), fileBody));
        }

        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), mWithAllWriteEdtTitle.getText().toString());
        RequestBody contentBody = RequestBody.create(MediaType.parse("text/plain"), mWithAllWriteEdtContent.getText().toString());
        RequestBody typeBody = RequestBody.create(MediaType.parse("text/plain"), "free");

        String isAnony = "";
        if (mIsAnonymous) isAnony = "true";
        else isAnony = "false";
        RequestBody isAnonymousBody = RequestBody.create(MediaType.parse("text/plain"), isAnony);

        map.put("title", titleBody);
        map.put("content", contentBody);
        map.put("type", typeBody);
        map.put("isAnonymous", isAnonymousBody);

        final WithAllService withAllService = new WithAllService(this, mContext);
        withAllService.tryPostWithAll(map, images);
        showProgressDialog();
    }

    public void withAllOnClick(View view) {
        switch (view.getId()) {
            case R.id.with_all_write_tv_cancel:
                finish();
            case R.id.with_all_write_iv_go_gallery:
                if (mDoubleClick) return;
                mDoubleClick = true;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;
            case R.id.with_all_write_iv_main_remove_1:
                if (mUriList.size() < 1) return;

                mImageCount -= 1;
                mUriList.remove(0);
                mFileList.remove(0);
                int i = 1;
                for (Uri uri : mUriList) {

                    if (i == 1) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv1);
                        mWithAllIvRemove1.setVisibility(View.VISIBLE);
                    } else if (i == 2) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv2);
                        mWithAllIvRemove2.setVisibility(View.VISIBLE);
                    } else if (i == 3) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv3);
                        mWithAllIvRemove3.setVisibility(View.VISIBLE);
                    } else if (i == 4) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv4);
                        mWithAllIvRemove4.setVisibility(View.VISIBLE);
                    }

                    i += 1;
                }

                if (mImageCount < 2) {
                    mWithAllIvRemove2.setVisibility(View.INVISIBLE);
                }

                if (mImageCount < 3) {
                    mWithAllIvRemove3.setVisibility(View.INVISIBLE);
                }

                if (mImageCount < 4) {
                    mWithAllIvRemove4.setVisibility(View.INVISIBLE);
                }

                setDefaultImage(i);
                ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setText(String.valueOf(mUriList.size()));
                if (mUriList.size() == 0) {
                    ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.with_all_write_tv_num_of_images_total)).setVisibility(View.GONE);
                    ((ConstraintLayout) findViewById(R.id.with_all_write_constraint_bottom)).setVisibility(View.GONE);
                }
                break;
            case R.id.with_all_write_iv_main_remove_2:
                if (mUriList.size() < 2) return;

                mImageCount -= 1;
                mUriList.remove(1);
                mFileList.remove(1);

                int i2 = 1;
                for (Uri uri : mUriList) {

                    if (i2 == 1) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv1);
                        mWithAllIvRemove1.setVisibility(View.VISIBLE);
                    } else if (i2 == 2) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv2);
                        mWithAllIvRemove2.setVisibility(View.VISIBLE);
                    } else if (i2 == 3) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv3);
                        mWithAllIvRemove3.setVisibility(View.VISIBLE);
                    } else if (i2 == 4) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv4);
                        mWithAllIvRemove4.setVisibility(View.VISIBLE);
                    }

                    i2 += 1;
                }

                if (mImageCount < 2) {
                    mWithAllIvRemove2.setVisibility(View.INVISIBLE);
                }

                if (mImageCount < 3) {
                    mWithAllIvRemove3.setVisibility(View.INVISIBLE);
                }

                if (mImageCount < 4) {
                    mWithAllIvRemove4.setVisibility(View.INVISIBLE);
                }

                ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setText(String.valueOf(mUriList.size()));
                if (mUriList.size() < 2) {
                    mWithAllIvRemove2.setVisibility(View.GONE);
                }
                setDefaultImage(i2);
                break;
            case R.id.with_all_write_iv_main_remove_3:
                if (mUriList.size() < 3) return;

                mImageCount -= 1;
                mUriList.remove(2);
                mFileList.remove(2);

                int i3 = 1;
                for (Uri uri : mUriList) {

                    if (i3 == 1) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv1);
                        mWithAllIvRemove1.setVisibility(View.VISIBLE);
                    } else if (i3 == 2) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv2);
                        mWithAllIvRemove2.setVisibility(View.VISIBLE);
                    } else if (i3 == 3) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv3);
                        mWithAllIvRemove3.setVisibility(View.VISIBLE);
                    } else if (i3 == 4) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv4);
                        mWithAllIvRemove4.setVisibility(View.VISIBLE);
                    }

                    i3 += 1;
                }

                if (mImageCount < 3) {
                    mWithAllIvRemove3.setVisibility(View.INVISIBLE);
                }

                if (mImageCount < 4) {
                    mWithAllIvRemove4.setVisibility(View.INVISIBLE);
                }

                ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setText(String.valueOf(mUriList.size()));
                if (mUriList.size() < 3) {
                    mWithAllIvRemove3.setVisibility(View.GONE);
                }
                setDefaultImage(i3);
                break;
            case R.id.with_all_write_iv_main_remove_4:
                if (mUriList.size() < 4) return;

                mImageCount -= 1;
                mUriList.remove(3);
                mFileList.remove(3);

                int i4 = 1;
                for (Uri uri : mUriList) {

                    if (i4 == 1) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv1);
                        mWithAllIvRemove1.setVisibility(View.VISIBLE);
                    } else if (i4 == 2) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv2);
                        mWithAllIvRemove2.setVisibility(View.VISIBLE);
                    } else if (i4 == 3) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv3);
                        mWithAllIvRemove3.setVisibility(View.VISIBLE);
                    } else if (i4 == 4) {
                        Glide.with(this).load(uri).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllIv4);
                        mWithAllIvRemove4.setVisibility(View.VISIBLE);
                    }

                    i4 += 1;
                }

                if (mImageCount < 4) {
                    mWithAllIvRemove4.setVisibility(View.INVISIBLE);
                }

                ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setText(String.valueOf(mUriList.size()));
                if (mUriList.size() < 4) {
                    mWithAllIvRemove2.setVisibility(View.GONE);
                }
                setDefaultImage(i4);
                break;
            case R.id.with_all_write_tv_register:
                if (mDoubleClick) return;
                mDoubleClick = true;

                if (mWithAllWriteEdtTitle.getText().toString().equals("")) {
                    showCustomToastShort("글 제목을 입력해 주세요");
                    return;
                }

                postWithAll();
                break;
            case R.id.with_all_write_tv_anonymous:
                mIsAnonymous = !mIsAnonymous;
                if (mIsAnonymous) {
                    mWithAllWriteTvAnonymous.setTextColor(getResources().getColor(R.color.colorBlack));
                } else {
                    mWithAllWriteTvAnonymous.setTextColor(getResources().getColor(R.color.colorBasicBlack11));
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

            mImageCount += 1;

            ((ConstraintLayout) findViewById(R.id.with_all_write_constraint_bottom)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.with_all_write_tv_num_of_images_total)).setVisibility(View.VISIBLE);

            mWithAllIvRemove1.setVisibility(View.INVISIBLE);
            mWithAllIvRemove2.setVisibility(View.INVISIBLE);
            mWithAllIvRemove3.setVisibility(View.INVISIBLE);
            mWithAllIvRemove4.setVisibility(View.INVISIBLE);

            if (mImageCount == 1) {
                mWithAllIv1.setImageURI(fileUri);
                mWithAllIvRemove1.setVisibility(View.VISIBLE);
            } else if (mImageCount == 2) {
                mWithAllIv2.setImageURI(fileUri);
                mWithAllIvRemove1.setVisibility(View.VISIBLE);
                mWithAllIvRemove2.setVisibility(View.VISIBLE);
            } else if (mImageCount == 3) {
                mWithAllIv3.setImageURI(fileUri);
                mWithAllIvRemove1.setVisibility(View.VISIBLE);
                mWithAllIvRemove2.setVisibility(View.VISIBLE);
                mWithAllIvRemove3.setVisibility(View.VISIBLE);
            } else if (mImageCount == 4) {
                mWithAllIv4.setImageURI(fileUri);
                mWithAllIvRemove1.setVisibility(View.VISIBLE);
                mWithAllIvRemove2.setVisibility(View.VISIBLE);
                mWithAllIvRemove3.setVisibility(View.VISIBLE);
                mWithAllIvRemove4.setVisibility(View.VISIBLE);
            }

            ((TextView) findViewById(R.id.with_all_write_tv_num_of_images)).setText(String.valueOf(mImageCount));

            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            if (file.exists()) {
                long fileSize = file.length();
                Log.d("BreezeWind", "file size: " + fileSize);
                if (fileSize > 1000000) { // 2 mb
                    // 압축
                    try {
                        file = new Compressor(WithAllWriteActivity.this).compressToFile(file);
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

    void setDefaultImage(int i) {

        if (i == 1) {
            mWithAllIv1.setImageDrawable(null);
            mWithAllIv2.setImageDrawable(null);
            mWithAllIv3.setImageDrawable(null);
            mWithAllIv4.setImageDrawable(null);
        } else if (i == 2) {
            mWithAllIv2.setImageDrawable(null);
            mWithAllIv3.setImageDrawable(null);
            mWithAllIv4.setImageDrawable(null);
        } else if (i == 3) {
            mWithAllIv3.setImageDrawable(null);
            mWithAllIv4.setImageDrawable(null);
        } else if (i == 4) {
            mWithAllIv4.setImageDrawable(null);
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
    public void tryPostWithAllSuccess() {
        hideProgressDialog();
        mDoubleClick = false;
        finish();
    }

    @Override
    public void tryPostWithAllFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        mDoubleClick = false;
    }
}