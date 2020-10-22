package com.makeus.urirang.android.src.worldCup.result.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.worldCup.WorldCupWriteAndContentService;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupMyResultView;
import com.makeus.urirang.android.src.worldCup.result.all.WorldCupAllResultActivity;
import com.makeus.urirang.android.src.worldCup.result.my.models.MyResult;

public class WorldCupMyResultActivity extends BaseActivity implements WorldCupMyResultView {

    private Context mContext;
    private WorldCupMyResultView mView;

    private ImageView mIvSelected;
    private TextView mTvSelected;
    private TextView mTvRanking;
    private TextView mTvRatio;

    private MyResult mResult;
    private WorldCupContent mContent;

    private boolean mDoubleClick = false;
    private int mWorldCupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_cup_my_result);

        mContext = this;
        mView = this;
        mWorldCupId = getIntent().getIntExtra("worldCupId", -1);
        mContent = (WorldCupContent) getIntent().getSerializableExtra("worldCupContent");

        mIvSelected = findViewById(R.id.world_cup_my_result_iv_result);
        mTvSelected = findViewById(R.id.world_cup_my_result_tv_result);
        mTvRanking = findViewById(R.id.world_cup_my_result_tv_rank);
        mTvRatio = findViewById(R.id.world_cup_my_result_tv_percent);

        TextView goAll = findViewById(R.id.world_cup_my_result_tv_go_all);
        goAll.setOnClickListener(v -> {
            if (mDoubleClick) return;
            mDoubleClick = true;

            Intent allResult = new Intent(this, WorldCupAllResultActivity.class);
            allResult.putExtra("worldCupId", mWorldCupId);
            allResult.putExtra("myResult", mResult);
            allResult.putExtra("worldCupContent", mContent);
            startActivity(allResult);
            finish();
        });

        getMyResult();

    }

    void getMyResult() {
        final WorldCupWriteAndContentService resultService = new WorldCupWriteAndContentService(mView, this);
        resultService.tryGetWorldCupMyResult(mWorldCupId);
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClick = false;
    }

    @Override
    public void tryGetWorldCupMyResultSuccess(MyResult result) {
        mResult = result;

        mTvSelected.setText(result.getCandidate().getTitle());
        Glide.with(mContext).load(result.getCandidate().getImageUrl()).into(mIvSelected);

        mTvRanking.setText(String.format("랭킹 %s위", result.getRank()));
        mTvRatio.setText(String.valueOf(result.getRatio()).concat("%"));
        hideProgressDialog();
    }

    @Override
    public void tryGetWorldCupMyResultFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}