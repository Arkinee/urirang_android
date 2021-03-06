package com.makeus.urirang.android.src.howAboutThis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetHowAboutThisContentDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetHowAboutThisFilterDialog;
import com.makeus.urirang.android.src.howAboutThis.adapter.HowAboutThisAdapter;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisActivityView;
import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThis;
import com.makeus.urirang.android.src.howAboutThis.write.HowAboutThisWriteActivity;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class HowAboutThisActivity extends BaseActivity implements BottomSheetHowAboutThisFilterDialog.BottomSheetFilterOptionListener, HowAboutThisActivityView, BottomSheetHowAboutThisContentDialog.BottomSheetFilterOptionListener {

    private Context mContext;
    private HowAboutThisAdapter mHowAdapter;
    private ArrayList<HowAboutThis> mHowLists;
    private RecyclerView rvHowAboutThis;

    private NestedScrollView mHowAboutThisNestedScroll;

    private ImageView mHowAboutThisIvFirstMedal;
    private ImageView mHowAboutThisIvSecondMedal;
    private ImageView mHowAboutThisIvThirdMedal;

    private ConstraintLayout mHowAboutThisConstraintFirst;
    private ConstraintLayout mHowAboutThisConstraintSecond;
    private ConstraintLayout mHowAboutThisConstraintThird;

    private TextView mHowAboutThisFirstTvTitle;
    private ImageView mHowAboutThisFirstIvImage;
    private ImageView mHowAboutThisFirstIvMbti;
    private TextView mHowAboutThisFirstTvNickname;
    private TextView mHowAboutThisFirstTvlikes;

    private TextView mHowAboutThisSecondTvTitle;
    private ImageView mHowAboutThisSecondIvImage;
    private ImageView mHowAboutThisSecondIvMbti;
    private TextView mHowAboutThisSecondTvNickname;
    private TextView mHowAboutThisSecondTvlikes;

    private TextView mHowAboutThisThirdTvTitle;
    private ImageView mHowAboutThisThirdIvImage;
    private ImageView mHowAboutThisThirdIvMbti;
    private TextView mHowAboutThisThirdTvNickname;
    private TextView mHowAboutThisThirdTvlikes;

    private int mPage = 1;
    private int mOption = 1;
    private boolean mIsEmptyResult = false;
    private boolean mDoubleClickFlag = false;
    private boolean mLoading = false;
    private String mSort = "";

    private HowAboutThis mFirstItem;
    private HowAboutThis mSecondItem;
    private HowAboutThis mThirdItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_this);

        mHowAboutThisNestedScroll = findViewById(R.id.how_about_this_nested_scroll);

        mHowAboutThisIvFirstMedal = findViewById(R.id.how_about_this_1st_medal);
        mHowAboutThisIvSecondMedal = findViewById(R.id.how_about_this_2nd_medal);
        mHowAboutThisIvThirdMedal = findViewById(R.id.how_about_this_3rd_medal);

        mHowAboutThisConstraintFirst = findViewById(R.id.how_about_this_constraint_1st);
        mHowAboutThisConstraintSecond = findViewById(R.id.how_about_this_constraint_2nd);
        mHowAboutThisConstraintThird = findViewById(R.id.how_about_this_constraint_3rd);

        mHowAboutThisFirstTvTitle = findViewById(R.id.how_about_this_1st_tv_title);
        mHowAboutThisFirstIvImage = findViewById(R.id.how_about_this_1st_iv_thumbnail);
        mHowAboutThisFirstIvMbti = findViewById(R.id.how_about_this_1st_iv_mbti);
        mHowAboutThisFirstTvNickname = findViewById(R.id.how_about_this_1st_tv_nickname);
        mHowAboutThisFirstTvlikes = findViewById(R.id.how_about_this_1st_tv_like);

        mHowAboutThisSecondTvTitle = findViewById(R.id.how_about_this_2nd_tv_title);
        mHowAboutThisSecondIvImage = findViewById(R.id.how_about_this_2nd_iv_thumbnail);
        mHowAboutThisSecondIvMbti = findViewById(R.id.how_about_this_2nd_iv_mbti);
        mHowAboutThisSecondTvNickname = findViewById(R.id.how_about_this_2nd_tv_nickname);
        mHowAboutThisSecondTvlikes = findViewById(R.id.how_about_this_2nd_tv_like);

        mHowAboutThisThirdTvTitle = findViewById(R.id.how_about_this_3rd_tv_title);
        mHowAboutThisThirdIvImage = findViewById(R.id.how_about_this_3rd_iv_thumbnail);
        mHowAboutThisThirdIvMbti = findViewById(R.id.how_about_this_3rd_iv_mbti);
        mHowAboutThisThirdTvNickname = findViewById(R.id.how_about_this_3rd_tv_nickname);
        mHowAboutThisThirdTvlikes = findViewById(R.id.how_about_this_3rd_tv_like);

        mContext = this;
        mHowLists = new ArrayList<>();
        mHowAdapter = new HowAboutThisAdapter(this, mHowLists, new HowAboutThisAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                BottomSheetHowAboutThisContentDialog dialog = new BottomSheetHowAboutThisContentDialog(mContext, mHowLists.get(pos), pos);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "HowContentDialog");
            }
        });

        rvHowAboutThis = findViewById(R.id.how_about_this_rv);
        rvHowAboutThis.addItemDecoration(new DividerItemDecoration(rvHowAboutThis.getContext(), LinearLayoutManager.VERTICAL));
        rvHowAboutThis.setAdapter(mHowAdapter);

        mHowAboutThisNestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
                Log.d(TAG, "Scroll DOWN");
            }
            if (scrollY < oldScrollY) {
                Log.d(TAG, "Scroll UP");
            }

            if (scrollY == 0) {
                Log.d(TAG, "TOP SCROLL");
            }

            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                Log.d(TAG, "BOTTOM SCROLL");
                // here where the trick is going

                if (mLoading) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 how about this");
                        getHowAboutThisList(mPage, mSort);
                    }

                    mLoading = false;
                }

            }
        });
    }

    public void howOnClick(View view) {

        switch (view.getId()) {
            case R.id.how_about_this_iv_back_arrow:
                finish();
                break;
            case R.id.how_about_this_tv_filter:
                BottomSheetHowAboutThisFilterDialog dialog = new BottomSheetHowAboutThisFilterDialog(mContext, mOption);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "HowFilterDialog");
                break;
            case R.id.how_about_this_fab_write:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent writeIntent = new Intent(this, HowAboutThisWriteActivity.class);
                startActivity(writeIntent);
                break;
            case R.id.how_about_this_constraint_1st:
                BottomSheetHowAboutThisContentDialog dialog1 = new BottomSheetHowAboutThisContentDialog(mContext, mFirstItem, -1);
                dialog1.setCancelable(false);
                dialog1.show(getSupportFragmentManager(), "HowContentDialog");
                break;
            case R.id.how_about_this_constraint_2nd:
                BottomSheetHowAboutThisContentDialog dialog2 = new BottomSheetHowAboutThisContentDialog(mContext, mSecondItem, -2);
                dialog2.setCancelable(false);
                dialog2.show(getSupportFragmentManager(), "HowContentDialog");
                break;
            case R.id.how_about_this_constraint_3rd:
                BottomSheetHowAboutThisContentDialog dialog3 = new BottomSheetHowAboutThisContentDialog(mContext, mThirdItem, -3);
                dialog3.setCancelable(false);
                dialog3.show(getSupportFragmentManager(), "HowContentDialog");
                break;
        }
    }

    void getHowAboutThisListBest3() {
        final HowAboutThisService howAboutThisService = new HowAboutThisService(this, this);
        howAboutThisService.tryGetHowAboutThisListBest(1, 3);
        showProgressDialog();
    }

    void getHowAboutThisList(int page, String sort) {
        final HowAboutThisService howAboutThisService = new HowAboutThisService(this, this);
        howAboutThisService.tryGetHowAboutThisList(page, 10, sort);
        showProgressDialog();
    }

    @Override
    public void applyFilterCreatedAt(int option) {
        mOption = option;
        mSort = "";
        mHowLists.clear();
        mIsEmptyResult = false;
        mPage = 1;
        getHowAboutThisList(mPage, mSort);
    }

    @Override
    public void applyFilterPopularity(int option) {
        mOption = option;
        mSort = "best";
        mHowLists.clear();
        mIsEmptyResult = false;
        mPage = 1;
        getHowAboutThisList(mPage, mSort);
    }

    @Override
    public void tryGetHowAboutThisBest3Success(ArrayList<HowAboutThis> results) {


        if (results.size() >= 1) {
            mFirstItem = results.get(0);
            mHowAboutThisFirstTvTitle.setText(results.get(0).getTitle());
            Glide.with(mContext).load(results.get(0).getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHowAboutThisFirstIvImage);
            mHowAboutThisFirstTvNickname.setText(results.get(0).getUser().getNickname());
            mHowAboutThisFirstTvlikes.setText(String.valueOf(results.get(0).getLikes()));

            if (results.get(0).getUser().getMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mHowAboutThisFirstIvMbti);
            else if (results.get(0).getUser().getMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mHowAboutThisFirstIvMbti);

        } else {
            mHowAboutThisIvFirstMedal.setVisibility(View.GONE);
            mHowAboutThisConstraintFirst.setVisibility(View.GONE);
        }

        if (results.size() >= 2) {
            mSecondItem = results.get(1);
            mHowAboutThisSecondTvTitle.setText(results.get(1).getTitle());
            Glide.with(mContext).load(results.get(1).getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHowAboutThisSecondIvImage);
            mHowAboutThisSecondTvNickname.setText(results.get(1).getUser().getNickname());
            mHowAboutThisSecondTvlikes.setText(String.valueOf(results.get(1).getLikes()));

            if (results.get(1).getUser().getMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mHowAboutThisSecondIvMbti);
            else if (results.get(1).getUser().getMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mHowAboutThisSecondIvMbti);

        } else {
            mHowAboutThisIvSecondMedal.setVisibility(View.GONE);
            mHowAboutThisConstraintSecond.setVisibility(View.GONE);
        }

        if (results.size() == 3) {
            mThirdItem = results.get(2);
            mHowAboutThisThirdTvTitle.setText(results.get(2).getTitle());
            Glide.with(mContext).load(results.get(2).getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHowAboutThisThirdIvImage);
            mHowAboutThisThirdTvNickname.setText(results.get(2).getUser().getNickname());
            mHowAboutThisThirdTvlikes.setText(String.valueOf(results.get(2).getLikes()));

            if (results.get(2).getUser().getMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mHowAboutThisThirdIvMbti);
            else if (results.get(2).getUser().getMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mHowAboutThisThirdIvMbti);

        } else {
            mHowAboutThisIvThirdMedal.setVisibility(View.GONE);
            mHowAboutThisConstraintThird.setVisibility(View.GONE);
        }

        mHowLists.clear();
        getHowAboutThisList(mPage, mSort);
    }

    @Override
    public void tryGetHowAboutThisBest3Failure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryGetHowAboutThisListSuccess(ArrayList<HowAboutThis> results) {

        if (results.size() < 10) {
            mIsEmptyResult = true;
        }

        mLoading = true;
        mHowLists.addAll(results);
        mHowAdapter.notifyDataSetChanged();
        mPage += 1;
        hideProgressDialog();
    }

    @Override
    public void tryGetHowAboutThisListFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onresume how about this");
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mHowLists.clear();
        mPage = 1;
        mSort = "";
        getHowAboutThisListBest3();
    }

    @Override
    public void sendLike(boolean like, int pos) {
        if (pos == -1 || pos == -2 || pos == -3) {
            mPage = 1;
            mSort = "";
            getHowAboutThisListBest3();
        } else {
            mHowLists.get(pos).setLiked(like);

            if (mHowLists.get(pos).getId() == mFirstItem.getId()) {
                mFirstItem.setLiked(like);
            }

            if (mHowLists.get(pos).getId() == mSecondItem.getId()) {
                mSecondItem.setLiked(like);
            }

            if (mHowLists.get(pos).getId() == mThirdItem.getId()) {
                mThirdItem.setLiked(like);
            }

        }
    }
}