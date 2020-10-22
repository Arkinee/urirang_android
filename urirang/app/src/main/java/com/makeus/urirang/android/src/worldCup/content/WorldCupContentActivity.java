package com.makeus.urirang.android.src.worldCup.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetWorldCupContentDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetWorldCupFilterDialog;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.worldCup.WorldCupWriteAndContentService;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupContentView;
import com.makeus.urirang.android.src.worldCup.result.my.WorldCupMyResultActivity;

import java.util.HashMap;

public class WorldCupContentActivity extends BaseActivity implements WorldCupContentView, BottomSheetWorldCupContentDialog.BottomSheetFilterOptionListener {

    private Context mContext;
    private ImageView mIvBackground;
    private ImageView mIvMbti;
    private TextView mTvTitle;
    private TextView mTvNickname;
    private TextView mTvRound;
    private TextView mTvParticipate;
    private TextView mTvDescription;

    private WorldCupContent mContent;
    private boolean mDoubleClick = false;
    private int mWorldCupId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_cup_content);

        mContext = this;
        mWorldCupId = getIntent().getIntExtra("worldCupId", -1);

        mIvBackground = findViewById(R.id.world_cup_content_iv_background);
        mIvMbti = findViewById(R.id.world_cup_content_iv_mbti);
        mTvTitle = findViewById(R.id.world_cup_content_tv_title);
        mTvNickname = findViewById(R.id.world_cup_content_tv_nickname);
        mTvRound = findViewById(R.id.world_cup_content_tv_round);
        mTvParticipate = findViewById(R.id.world_cup_content_tv_participate);
        mTvDescription = findViewById(R.id.world_cup_content_tv_description);

    }

    void getContent() {
        final WorldCupWriteAndContentService contentService = new WorldCupWriteAndContentService(this, this);
        contentService.tryGetWorldCupContent(mWorldCupId);
        showProgressDialog();
    }

    public void worldCupContentOnClick(View view) {
        switch (view.getId()) {
            case R.id.world_cup_content_iv_back_arrow:
                finish();
                break;
            case R.id.world_cup_content_tv_start:
                if (mDoubleClick) return;
                mDoubleClick = true;

                BottomSheetWorldCupContentDialog dialog = new BottomSheetWorldCupContentDialog(mContext, mContent);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "WorldCupContent");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClick = false;
        getContent();
    }


    @Override
    public void tryGetWorldCupContentSuccess(WorldCupContent content) {

        if (!content.isFirst())
            ((TextView) findViewById(R.id.world_cup_content_tv_start)).setText(getString(R.string.world_cup_tv_content_restart));

        mContent = content;
        mTvTitle.setText(content.getTitle());
        mTvNickname.setText(content.getUser().getNickname());

        String round = " " + mContext.getString(R.string.world_cup_middle_dot) + " " + content.getRoundNum() + "강 " + mContext.getString(R.string.world_cup_middle_dot);
        mTvRound.setText(round);

        Glide.with(mContext).load(content.getCandidates().get(0).getImageUrl()).into(mIvBackground);
        mTvParticipate.setText(String.valueOf(content.getJoinNum()));
        mTvDescription.setText(content.getDescription());

        if (content.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mIvMbti);
        else if (content.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mIvMbti);
        else if (content.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mIvMbti);

        hideProgressDialog();
    }

    @Override
    public void tryGetWorldCupContentFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryPostWorldCupContentSuccess() {
        hideProgressDialog();
        Intent myResult = new Intent(this, WorldCupMyResultActivity.class);
        myResult.putExtra("worldCupId", mContent.getId());
        myResult.putExtra("worldCupContent", mContent);
        startActivity(myResult);
        finish();
    }

    @Override
    public void tryPostWorldCupContentFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void finish(int candidateId) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("worldCupCandidateId", candidateId);
        params.put("worldCupId", mContent.getId());

        final WorldCupWriteAndContentService save = new WorldCupWriteAndContentService(this, this);
        save.tryPostWorldCupResult(params);
        showProgressDialog();
    }

    @Override
    public void close() {
        mDoubleClick = false;
        getContent();
    }
}