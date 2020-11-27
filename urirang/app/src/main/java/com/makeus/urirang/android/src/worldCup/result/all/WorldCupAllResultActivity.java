package com.makeus.urirang.android.src.worldCup.result.all;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.LocationTemplate;
import com.kakao.message.template.SocialObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetAllResultMbtiFilterDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.worldCup.WorldCupWriteAndContentService;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupAllResultView;
import com.makeus.urirang.android.src.worldCup.result.all.adapter.AllResultAdapter;
import com.makeus.urirang.android.src.worldCup.result.all.models.MyTypeResult;
import com.makeus.urirang.android.src.worldCup.result.all.models.Result;
import com.makeus.urirang.android.src.worldCup.result.my.models.MyResult;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldCupAllResultActivity extends BaseActivity implements WorldCupAllResultView, BottomSheetAllResultMbtiFilterDialog.BottomSheetListener {

    private Context mContext;
    private ImageView mTopIvMbti;
    private TextView mTopTvTitle;
    private TextView mTopTvNickname;
    private TextView mTopTvRound;
    private TextView mTopTvParticipate;

    private ImageView mIvMySelect;
    private TextView mTvMySelect;
    private TextView mTvMySelectRank;
    private TextView mTvMySelectRatio;

    private ImageView mIvMyMbtiSelect;
    private TextView mTvMyMbtiSelect;
    private TextView mTvMyMbtiSelectRank;
    private TextView mTvMyMbtiSelectRatio;

    private ImageView mIvFirst;
    private TextView mTvFirstTitle;
    private TextView mTvFirstRatio;

    private ImageView mIvSecond;
    private TextView mTvSecondTitle;
    private TextView mTvSecondRatio;

    private ImageView mIvThird;
    private TextView mTvThirdTitle;
    private TextView mTvThirdRatio;

    private AllResultAdapter mResultAdapter;
    private ArrayList<Result> mResultList;

    private boolean mDoubleClick = false;
    private String mSelectedMbti = "";
    private int mWorldCupId;
    private WorldCupContent mContent;
    private MyResult mMyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_cup_all_result);

        mContext = this;
        mResultList = new ArrayList<>();

        mWorldCupId = getIntent().getIntExtra("worldCupId", -1);
        mMyResult = (MyResult) getIntent().getSerializableExtra("myResult");
        mContent = (WorldCupContent) getIntent().getSerializableExtra("worldCupContent");

        // 월드컵 정보
        mTopIvMbti = findViewById(R.id.world_cup_all_result_iv_mbti);
        mTopTvTitle = findViewById(R.id.world_cup_all_result_tv_title);
        mTopTvNickname = findViewById(R.id.world_cup_all_result_tv_nickname);
        mTopTvRound = findViewById(R.id.world_cup_all_result_tv_round);
        mTopTvParticipate = findViewById(R.id.world_cup_all_result_tv_participate);

        mTopTvTitle.setText(mContent.getTitle());
        mTopTvNickname.setText(mContent.getUser().getNickname());

        String round = " " + mContext.getString(R.string.world_cup_middle_dot) + " " + mContent.getRoundNum() + "강 " + mContext.getString(R.string.world_cup_middle_dot);
        mTopTvRound.setText(round);
        mTopTvParticipate.setText(String.valueOf(mContent.getJoinNum()));

        if (mContent.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mTopIvMbti);
        else if (mContent.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mTopIvMbti);

        // 내 결과
        mIvMySelect = findViewById(R.id.world_cup_all_result_iv_result);
        mTvMySelect = findViewById(R.id.world_cup_all_result_tv_result);
        mTvMySelectRank = findViewById(R.id.world_cup_all_result_tv_rank);
        mTvMySelectRatio = findViewById(R.id.world_cup_all_result_tv_ratio);

        Glide.with(this).load(mMyResult.getCandidate().getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mIvMySelect);
        mTvMySelect.setText(mMyResult.getCandidate().getTitle());
        mTvMySelectRank.setText("랭킹 ".concat(String.valueOf(mMyResult.getRank())).concat("위"));
        mTvMySelectRatio.setText("(".concat(String.valueOf(mMyResult.getRatio())).concat("%)"));

        // 나와 같은 유형 결과
        mIvMyMbtiSelect = findViewById(R.id.world_cup_all_result_my_mbti_iv_result);
        mTvMyMbtiSelect = findViewById(R.id.world_cup_all_result_my_mbti_tv_result);
        mTvMyMbtiSelectRank = findViewById(R.id.world_cup_all_result_my_mbti_tv_rank);
        mTvMyMbtiSelectRatio = findViewById(R.id.world_cup_all_result_my_mbti_tv_ratio);

        //1등
        mIvFirst = findViewById(R.id.world_cup_all_result_iv_first);
        mTvFirstTitle = findViewById(R.id.world_cup_all_result_tv_first);
        mTvFirstRatio = findViewById(R.id.world_cup_all_result_tv_first_ratio);

        //2등
        mIvSecond = findViewById(R.id.world_cup_all_result_iv_second);
        mTvSecondTitle = findViewById(R.id.world_cup_all_result_tv_second);
        mTvSecondRatio = findViewById(R.id.world_cup_all_result_tv_second_ratio);

        //3등
        mIvThird = findViewById(R.id.world_cup_all_result_iv_third);
        mTvThirdTitle = findViewById(R.id.world_cup_all_result_tv_third);
        mTvThirdRatio = findViewById(R.id.world_cup_all_result_tv_third_ratio);

        RecyclerView rvResults = findViewById(R.id.world_cup_all_result_rv);
        mResultAdapter = new AllResultAdapter(this, mResultList, (v, pos) -> {

        });
        rvResults.setAdapter(mResultAdapter);

        getMyMbtiResult();
    }

    void getMyMbtiResult() {
        final WorldCupWriteAndContentService resultService = new WorldCupWriteAndContentService(this, this);
        resultService.tryGetWorldCupMyMbtiResult(mWorldCupId);
        showProgressDialog();
    }

    void getAllResult() {
        final WorldCupWriteAndContentService resultService = new WorldCupWriteAndContentService(this, this);
        resultService.tryGetWorldCupAllResult(mWorldCupId, mSelectedMbti);
        showProgressDialog();
    }

    public void allResultClick(View view) {
        switch (view.getId()) {
            case R.id.world_cup_all_result_iv_back:
                Intent goMain = new Intent(this, MainActivity.class);
                goMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goMain);
                finish();
                break;
            case R.id.world_cup_all_result_iv_share:
                if (mDoubleClick) return;
                mDoubleClick = false;

                makeKakaoLinkFeed();
                break;
            case R.id.world_cup_all_result_linear_filter:
                if (mDoubleClick) return;
                mDoubleClick = true;

                BottomSheetAllResultMbtiFilterDialog filterDialog = new BottomSheetAllResultMbtiFilterDialog(mContext);
                filterDialog.setCancelable(false);
                filterDialog.show(getSupportFragmentManager(), "MbtiFilterDialog");
                break;
            case R.id.world_cup_all_result_relative_bottom:
                if (mDoubleClick) return;
                mDoubleClick = true;

                Intent goWithAll = new Intent(this, MainActivity.class);
                goWithAll.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                goWithAll.putExtra("goWithAll", true);
                startActivity(goWithAll);
                finish();
                break;
        }
    }

    public void makeKakaoLinkFeed() {

        String title = mContent.getTitle();

        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("result", mMyResult.getCandidate().getTitle());
        params.put("participate", String.valueOf(mContent.getJoinNum()));
        params.put("image", mMyResult.getCandidate().getImageUrl());

        KakaoLinkService.getInstance().sendCustom(this, "39233", params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
//                Log.d("로그", "카카오 링크 success: " +  result.toString());
            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent goMain = new Intent(this, MainActivity.class);
        goMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goMain);
        finish();
    }

    @Override
    public void tryGetWorldCupMbtiResultSuccess(MyTypeResult result) {

        Glide.with(this).load(result.getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mIvMyMbtiSelect);
        mTvMyMbtiSelect.setText(result.getTitle());
        mTvMyMbtiSelectRank.setText("랭킹 ".concat(String.valueOf(result.getRank())).concat("위"));
        mTvMyMbtiSelectRatio.setText("(".concat(String.valueOf(result.getRatio())).concat("%)"));

        getAllResult();
    }

    @Override
    public void tryGetWorldCupMbtiResultFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryGetWorldCupAllResultSuccess(ArrayList<Result> result) {

        Result first = result.get(0);
        result.remove(0);
        Result second = result.get(0);
        result.remove(0);
        Result third = result.get(0);
        result.remove(0);

        Glide.with(mContext).load(first.getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mIvFirst);
        mTvFirstTitle.setText(first.getTitle());
        mTvFirstRatio.setText(String.valueOf(first.getRatio()).concat("%"));

        Glide.with(mContext).load(second.getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mIvSecond);
        mTvSecondTitle.setText(second.getTitle());
        mTvSecondRatio.setText(String.valueOf(second.getRatio()).concat("%"));

        Glide.with(mContext).load(third.getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mIvThird);
        mTvThirdTitle.setText(third.getTitle());
        mTvThirdRatio.setText(String.valueOf(third.getRatio()).concat("%"));

        mResultList.addAll(result);
        mResultAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void tryGetWorldCupAllResultFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void onFilterApply(String mbti) {
        mResultList.clear();
        mSelectedMbti = mbti;
        getAllResult();
        mDoubleClick = false;
    }
}