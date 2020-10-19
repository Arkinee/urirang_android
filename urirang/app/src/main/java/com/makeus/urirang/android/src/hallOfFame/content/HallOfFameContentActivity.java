package com.makeus.urirang.android.src.hallOfFame.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetHallOfFameFilterDialog;
import com.makeus.urirang.android.src.hallOfFame.HallOfFameService;
import com.makeus.urirang.android.src.hallOfFame.adapter.HallOfFameAdapter;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameActivityView;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameContentView;
import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameContentComment;
import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameContentData;
import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.board.models.CommentList;
import com.makeus.urirang.android.src.withYou.comment.WithYouCommentActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class HallOfFameContentActivity extends BaseActivity implements HallOfFameContentView {

    private PreviousSubject mContent;
    private boolean mIsEmptyResult = false;
    private int mTopicId;
    private Context mContext;

    private ImageView mFameContentIvMbti;
    private ImageView mFameContentIvMain;

    // 상단
    private TextView mFameContentTvTitle;
    private TextView mFameContentTvNickname;
    private TextView mFameContentTvSelectedAt;
    private TextView mFameContentTvCommentNum;

    //first
    private ConstraintLayout mFameContentFirstConstraint;
    private ImageView mFameContentFirstIvMbti;
    private TextView mFameContentFirstTvNickname;
    private TextView mFameContentFirstTvCreatedAt;
    private TextView mFameContentFirstTvContent;
    private TextView mFameContentFirstTvLikes;

    //second
    private ConstraintLayout mFameContentSecondConstraint;
    private ImageView mFameContentSecondIvMbti;
    private TextView mFameContentSecondTvNickname;
    private TextView mFameContentSecondTvCreatedAt;
    private TextView mFameContentSecondTvContent;
    private TextView mFameContentSecondTvLikes;

    //third
    private ConstraintLayout mFameContentThirdConstraint;
    private ImageView mFameContentThirdIvMbti;
    private TextView mFameContentThirdTvNickname;
    private TextView mFameContentThirdTvCreatedAt;
    private TextView mFameContentThirdTvContent;
    private TextView mFameContentThirdTvLikes;

    //fourth
    private ConstraintLayout mFameContentFourthConstraint;
    private ImageView mFameContentFourthIvMbti;
    private TextView mFameContentFourthTvNickname;
    private TextView mFameContentFourthTvCreatedAt;
    private TextView mFameContentFourthTvContent;
    private TextView mFameContentFourthTvLikes;

    //fifth
    private ConstraintLayout mFameContentFifthConstraint;
    private ImageView mFameContentFifthIvMbti;
    private TextView mFameContentFifthTvNickname;
    private TextView mFameContentFifthTvCreatedAt;
    private TextView mFameContentFifthTvContent;
    private TextView mFameContentFifthTvLikes;

    private boolean mDoubleClickFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame_content);

        mContext = this;
        mTopicId = getIntent().getIntExtra("topicId", -1);
        mFameContentIvMbti = findViewById(R.id.hall_of_fame_content_presenter_iv_mbti);
        mFameContentIvMain = findViewById(R.id.hall_of_fame_content_iv_main);
        mFameContentTvTitle = findViewById(R.id.hall_of_fame_content_tv_topic);
        mFameContentTvNickname = findViewById(R.id.hall_of_fame_content_presenter_tv_nickname);
        mFameContentTvSelectedAt = findViewById(R.id.hall_of_fame_content_tv_selected_at);
        mFameContentTvCommentNum = findViewById(R.id.hall_of_fame_content_tv_comment);

        //1
        mFameContentFirstConstraint = findViewById(R.id.hall_of_fame_content_constraint_first_comment);
        mFameContentFirstIvMbti = findViewById(R.id.hall_of_fame_content_first_comment_iv_mbti);
        mFameContentFirstTvNickname = findViewById(R.id.hall_of_fame_content_first_comment_tv_nickname);
        mFameContentFirstTvCreatedAt = findViewById(R.id.hall_of_fame_content_first_comment_tv_created_at);
        mFameContentFirstTvContent = findViewById(R.id.hall_of_fame_content_first_comment_tv_content);
        mFameContentFirstTvLikes = findViewById(R.id.hall_of_fame_content_first_comment_tv_like);
        //2
        mFameContentSecondConstraint = findViewById(R.id.hall_of_fame_content_constraint_second_comment);
        mFameContentSecondIvMbti = findViewById(R.id.hall_of_fame_content_second_comment_iv_mbti);
        mFameContentSecondTvNickname = findViewById(R.id.hall_of_fame_content_second_comment_tv_nickname);
        mFameContentSecondTvCreatedAt = findViewById(R.id.hall_of_fame_content_second_comment_tv_created_at);
        mFameContentSecondTvContent = findViewById(R.id.hall_of_fame_content_second_comment_tv_content);
        mFameContentSecondTvLikes = findViewById(R.id.hall_of_fame_content_second_comment_tv_like);
        //3
        mFameContentThirdConstraint = findViewById(R.id.hall_of_fame_content_constraint_third_comment);
        mFameContentThirdIvMbti = findViewById(R.id.hall_of_fame_content_third_comment_iv_mbti);
        mFameContentThirdTvNickname = findViewById(R.id.hall_of_fame_content_third_comment_tv_nickname);
        mFameContentThirdTvCreatedAt = findViewById(R.id.hall_of_fame_content_third_comment_tv_created_at);
        mFameContentThirdTvContent = findViewById(R.id.hall_of_fame_content_third_comment_tv_content);
        mFameContentThirdTvLikes = findViewById(R.id.hall_of_fame_content_third_comment_tv_like);
        //4
        mFameContentFourthConstraint = findViewById(R.id.hall_of_fame_content_constraint_fourth_comment);
        mFameContentFourthIvMbti = findViewById(R.id.hall_of_fame_content_fourth_comment_iv_mbti);
        mFameContentFourthTvNickname = findViewById(R.id.hall_of_fame_content_fourth_comment_tv_nickname);
        mFameContentFourthTvCreatedAt = findViewById(R.id.hall_of_fame_content_fourth_comment_tv_created_at);
        mFameContentFourthTvContent = findViewById(R.id.hall_of_fame_content_fourth_comment_tv_content);
        mFameContentFourthTvLikes = findViewById(R.id.hall_of_fame_content_fourth_comment_tv_like);
        //5
        mFameContentFifthConstraint = findViewById(R.id.hall_of_fame_content_constraint_fifth_comment);
        mFameContentFifthIvMbti = findViewById(R.id.hall_of_fame_content_fifth_comment_iv_mbti);
        mFameContentFifthTvNickname = findViewById(R.id.hall_of_fame_content_fifth_comment_tv_nickname);
        mFameContentFifthTvCreatedAt = findViewById(R.id.hall_of_fame_content_fifth_comment_tv_created_at);
        mFameContentFifthTvContent = findViewById(R.id.hall_of_fame_content_fifth_comment_tv_content);
        mFameContentFifthTvLikes = findViewById(R.id.hall_of_fame_content_fifth_comment_tv_like);

    }

    void getHallOfFameContent(int topicId) {
        final HallOfFameService historyService = new HallOfFameService(this, this);
        historyService.tryGetHallOfFameContent(topicId);
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        getHallOfFameContent(mTopicId);
    }

    public void hallContentOnClick(View view) {

        switch (view.getId()) {
            case R.id.hall_of_fame_content_iv_back_arrow:
                finish();
                break;
            case R.id.hall_of_fame_content_iv_go_comment:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent goComment = new Intent(mContext, WithYouCommentActivity.class);
                goComment.putExtra("topicId", mTopicId);
                startActivity(goComment);
                break;
        }
    }

    @Override
    public void tryGetHallOfFameContentSuccess(HallOfFameContentData data) {

        // 상단
        if (data.getTopic().getImages().size() != 0) {
            Glide.with(this).load(data.getTopic().getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mFameContentIvMain);
        } else {
            mFameContentIvMain.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_default_image));
        }

        mFameContentTvTitle.setText(data.getTopic().getTitle());
        mFameContentTvNickname.setText(data.getTopic().getUser().getNickname());
        mFameContentTvCommentNum.setText(String.valueOf(data.getTopic().getCommentNum()));

        String created = data.getTopic().getCreatedAt();
        SimpleDateFormat beforeF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat afterF = new SimpleDateFormat("MM/dd");

        String postedDate = "";
        if (created != null) {
            try {
                Date beforeD = beforeF.parse(created);
                postedDate = afterF.format(beforeD);
            } catch (ParseException e) {
                Log.d(TAG, e.toString());
            }
        }

        mFameContentTvSelectedAt.setText(postedDate);

        if (data.getTopic().getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentIvMbti);


        // 하단
        HallOfFameContentComment first = new HallOfFameContentComment();
        HallOfFameContentComment second = new HallOfFameContentComment();
        HallOfFameContentComment third = new HallOfFameContentComment();
        HallOfFameContentComment fourth = new HallOfFameContentComment();
        HallOfFameContentComment fifth = new HallOfFameContentComment();


        // first
        if (data.getComment().size() >= 1) {
            first = data.getComment().get(0);
            mFameContentFirstTvNickname.setText(first.getUserNickname());
            mFameContentFirstTvCreatedAt.setText(first.getCreatedAt());
            mFameContentFirstTvLikes.setText(String.valueOf(first.getLikes()));
            mFameContentFirstTvContent.setText(first.getContent());

            String createdTime = first.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

            String posted = "";
            if (createdTime != null) {
                try {
                    Date before = beforeFormat.parse(createdTime);
                    posted = afterFormat.format(before);
                } catch (ParseException e) {
                    Log.d(TAG, e.toString());
                }
            }

            mFameContentFirstTvCreatedAt.setText(posted);

            if (first.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentFirstIvMbti);
            else if (first.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentFirstIvMbti);

        } else {
            mFameContentFirstConstraint.setVisibility(View.INVISIBLE);
        }


        // second
        if (data.getComment().size() >= 2) {
            second = data.getComment().get(1);
            mFameContentSecondTvNickname.setText(second.getUserNickname());
            mFameContentSecondTvCreatedAt.setText(second.getCreatedAt());
            mFameContentSecondTvLikes.setText(String.valueOf(second.getLikes()));
            mFameContentSecondTvContent.setText(second.getContent());

            String createdTime = second.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

            String posted = "";
            if (createdTime != null) {
                try {
                    Date before = beforeFormat.parse(createdTime);
                    posted = afterFormat.format(before);
                } catch (ParseException e) {
                    Log.d(TAG, e.toString());
                }
            }

            mFameContentSecondTvCreatedAt.setText(posted);

            if (second.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentSecondIvMbti);
            else if (second.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentSecondIvMbti);

        } else {
            mFameContentSecondConstraint.setVisibility(View.INVISIBLE);
        }


        // third
        if (data.getComment().size() >= 3) {
            third = data.getComment().get(2);
            mFameContentThirdTvNickname.setText(third.getUserNickname());
            mFameContentThirdTvCreatedAt.setText(third.getCreatedAt());
            mFameContentThirdTvLikes.setText(String.valueOf(third.getLikes()));
            mFameContentThirdTvContent.setText(third.getContent());

            String createdTime = third.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

            String posted = "";
            if (createdTime != null) {
                try {
                    Date before = beforeFormat.parse(createdTime);
                    posted = afterFormat.format(before);
                } catch (ParseException e) {
                    Log.d(TAG, e.toString());
                }
            }

            mFameContentThirdTvCreatedAt.setText(posted);

            if (third.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentThirdIvMbti);
            else if (third.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentThirdIvMbti);

        } else {
            mFameContentThirdConstraint.setVisibility(View.INVISIBLE);
        }

        // fourth
        if (data.getComment().size() >= 4) {
            fourth = data.getComment().get(3);
            mFameContentFourthTvNickname.setText(fourth.getUserNickname());
            mFameContentFourthTvCreatedAt.setText(fourth.getCreatedAt());
            mFameContentFourthTvLikes.setText(String.valueOf(fourth.getLikes()));
            mFameContentFourthTvContent.setText(fourth.getContent());

            String createdTime = fourth.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

            String posted = "";
            if (createdTime != null) {
                try {
                    Date before = beforeFormat.parse(createdTime);
                    posted = afterFormat.format(before);
                } catch (ParseException e) {
                    Log.d(TAG, e.toString());
                }
            }

            mFameContentFourthTvCreatedAt.setText(posted);

            if (fourth.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentFourthIvMbti);
            else if (fourth.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentFourthIvMbti);

        } else {
            mFameContentFourthConstraint.setVisibility(View.INVISIBLE);
        }

        // fifth
        if (data.getComment().size() >= 4) {
            fifth = data.getComment().get(4);
            mFameContentFifthTvNickname.setText(fifth.getUserNickname());
            mFameContentFifthTvCreatedAt.setText(fifth.getCreatedAt());
            mFameContentFifthTvLikes.setText(String.valueOf(fifth.getLikes()));
            mFameContentFifthTvContent.setText(fifth.getContent());

            String createdTime = fifth.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

            String posted = "";
            if (createdTime != null) {
                try {
                    Date before = beforeFormat.parse(createdTime);
                    posted = afterFormat.format(before);
                } catch (ParseException e) {
                    Log.d(TAG, e.toString());
                }
            }

            mFameContentFifthTvCreatedAt.setText(posted);

            if (fifth.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mFameContentFifthIvMbti);
            else if (fifth.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mFameContentFifthIvMbti);

        } else {
            mFameContentFifthConstraint.setVisibility(View.INVISIBLE);
        }

        hideProgressDialog();

    }

    @Override
    public void tryGetHallOfFameContentFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}