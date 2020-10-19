package com.makeus.urirang.android.src.main.fragments.board.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.board.BoardService;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardWithYouView;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithYouData;
import com.makeus.urirang.android.src.main.fragments.board.models.CommentList;
import com.makeus.urirang.android.src.withYou.comment.WithYouCommentActivity;
import com.makeus.urirang.android.src.withYou.image.WithYouImageActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class BoardWithYouFragment extends Fragment implements BoardWithYouView {

    private Context mContext;

    private ImageView mWithyouivMain;
    private TextView mWithYouTvTopic;
    private TextView mWithYouTvNickname;
    private TextView mWithYouTvCommentNum;
    private ImageView mWithYouIvMbti;

    //first
    private ConstraintLayout mWithYouFirstConstraint;
    private ImageView mWithYouFirstIvMbti;
    private TextView mWithYouFirstTvNickname;
    private TextView mWithYouFirstTvCreatedAt;
    private TextView mWithYouFirstTvContent;
    private TextView mWithYouFirstTvLikes;

    //second
    private ConstraintLayout mWithYouSecondConstraint;
    private ImageView mWithYouSecondIvMbti;
    private TextView mWithYouSecondTvNickname;
    private TextView mWithYouSecondTvCreatedAt;
    private TextView mWithYouSecondTvContent;
    private TextView mWithYouSecondTvLikes;

    //third
    private ConstraintLayout mWithYouThirdConstraint;
    private ImageView mWithYouThirdIvMbti;
    private TextView mWithYouThirdTvNickname;
    private TextView mWithYouThirdTvCreatedAt;
    private TextView mWithYouThirdTvContent;
    private TextView mWithYouThirdTvLikes;

    //fourth
    private ConstraintLayout mWithYouFourthConstraint;
    private ImageView mWithYouFourthIvMbti;
    private TextView mWithYouFourthTvNickname;
    private TextView mWithYouFourthTvCreatedAt;
    private TextView mWithYouFourthTvContent;
    private TextView mWithYouFourthTvLikes;

    //fifth
    private ConstraintLayout mWithYouFifthConstraint;
    private ImageView mWithYouFifthIvMbti;
    private TextView mWithYouFifthTvNickname;
    private TextView mWithYouFifthTvCreatedAt;
    private TextView mWithYouFifthTvContent;
    private TextView mWithYouFifthTvLikes;

    private boolean mDoubleClickFlag = false;
    private int mTopicId = 0;
    private String mImageUrl = "";

    public BoardWithYouFragment() {
    }

    public BoardWithYouFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_with_you, container, false);

        mWithyouivMain = view.findViewById(R.id.with_you_iv_main);
        mWithYouTvTopic = view.findViewById(R.id.with_you_tv_topic);
        mWithYouTvNickname = view.findViewById(R.id.with_you_presenter_tv_nickname);
        mWithYouTvCommentNum = view.findViewById(R.id.with_you_tv_comment);
        mWithYouIvMbti = view.findViewById(R.id.with_you_presenter_iv_mbti);

        mWithyouivMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent goImage = new Intent(mContext, WithYouImageActivity.class);
                goImage.putExtra("imageUrl", mImageUrl);
                startActivity(goImage);
            }
        });

        //1
        mWithYouFirstConstraint = view.findViewById(R.id.with_you_constraint_first_comment);
        mWithYouFirstIvMbti = view.findViewById(R.id.with_you_first_comment_iv_mbti);
        mWithYouFirstTvNickname = view.findViewById(R.id.with_you_first_comment_tv_nickname);
        mWithYouFirstTvCreatedAt = view.findViewById(R.id.with_you_first_comment_tv_created_at);
        mWithYouFirstTvContent = view.findViewById(R.id.with_you_first_comment_tv_content);
        mWithYouFirstTvLikes = view.findViewById(R.id.with_you_first_comment_tv_like);
        //2
        mWithYouSecondConstraint = view.findViewById(R.id.with_you_constraint_second_comment);
        mWithYouSecondIvMbti = view.findViewById(R.id.with_you_second_comment_iv_mbti);
        mWithYouSecondTvNickname = view.findViewById(R.id.with_you_second_comment_tv_nickname);
        mWithYouSecondTvCreatedAt = view.findViewById(R.id.with_you_second_comment_tv_created_at);
        mWithYouSecondTvContent = view.findViewById(R.id.with_you_second_comment_tv_content);
        mWithYouSecondTvLikes = view.findViewById(R.id.with_you_second_comment_tv_like);
        //3
        mWithYouThirdConstraint = view.findViewById(R.id.with_you_constraint_third_comment);
        mWithYouThirdIvMbti = view.findViewById(R.id.with_you_third_comment_iv_mbti);
        mWithYouThirdTvNickname = view.findViewById(R.id.with_you_third_comment_tv_nickname);
        mWithYouThirdTvCreatedAt = view.findViewById(R.id.with_you_third_comment_tv_created_at);
        mWithYouThirdTvContent = view.findViewById(R.id.with_you_third_comment_tv_content);
        mWithYouThirdTvLikes = view.findViewById(R.id.with_you_third_comment_tv_like);
        //4
        mWithYouFourthConstraint = view.findViewById(R.id.with_you_constraint_fourth_comment);
        mWithYouFourthIvMbti = view.findViewById(R.id.with_you_fourth_comment_iv_mbti);
        mWithYouFourthTvNickname = view.findViewById(R.id.with_you_fourth_comment_tv_nickname);
        mWithYouFourthTvCreatedAt = view.findViewById(R.id.with_you_fourth_comment_tv_created_at);
        mWithYouFourthTvContent = view.findViewById(R.id.with_you_fourth_comment_tv_content);
        mWithYouFourthTvLikes = view.findViewById(R.id.with_you_fourth_comment_tv_like);
        //5
        mWithYouFifthConstraint = view.findViewById(R.id.with_you_constraint_fifth_comment);
        mWithYouFifthIvMbti = view.findViewById(R.id.with_you_fifth_comment_iv_mbti);
        mWithYouFifthTvNickname = view.findViewById(R.id.with_you_fifth_comment_tv_nickname);
        mWithYouFifthTvCreatedAt = view.findViewById(R.id.with_you_fifth_comment_tv_created_at);
        mWithYouFifthTvContent = view.findViewById(R.id.with_you_fifth_comment_tv_content);
        mWithYouFifthTvLikes = view.findViewById(R.id.with_you_fifth_comment_tv_like);

        // 모든 코멘트 보기로 가기
        ImageView ivGoComment = view.findViewById(R.id.with_you_iv_go_comment);
        ivGoComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent goComment = new Intent(mContext, WithYouCommentActivity.class);
                goComment.putExtra("topicId", mTopicId);
                startActivity(goComment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void tryGetWithYouSuccess(BoardWithYouData data) {

        mTopicId = data.getTopic().getId();
        sSharedPreferences.edit().putInt("currentTopicId", mTopicId).apply();
        mImageUrl = data.getTopic().getImages().get(0).getUrl();

        // 상단
        Glide.with(mContext).load(mImageUrl).into(mWithyouivMain);
        mWithYouTvTopic.setText(data.getTopic().getTitle());
        mWithYouTvNickname.setText(data.getTopic().getUser().getNickname());
        mWithYouTvCommentNum.setText(String.valueOf(data.getTopic().getCommentNum()));

        if (data.getTopic().getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouIvMbti);


        // 하단
        CommentList first = new CommentList();
        CommentList second = new CommentList();
        CommentList third = new CommentList();
        CommentList fourth = new CommentList();
        CommentList fifth = new CommentList();


        // first
        if (data.getCommentLists().size() >= 1) {
            first = data.getCommentLists().get(0);
            mWithYouFirstTvNickname.setText(first.getUserNickName());
            mWithYouFirstTvCreatedAt.setText(first.getCreatedAt());
            mWithYouFirstTvLikes.setText(String.valueOf(first.getLike()));
            mWithYouFirstTvContent.setText(first.getContent());

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

            mWithYouFirstTvCreatedAt.setText(posted);

            if (first.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouFirstIvMbti);

        } else {
            mWithYouFirstConstraint.setVisibility(View.INVISIBLE);
        }


        // second
        if (data.getCommentLists().size() >= 2) {
            second = data.getCommentLists().get(1);
            mWithYouSecondTvNickname.setText(second.getUserNickName());
            mWithYouSecondTvCreatedAt.setText(second.getCreatedAt());
            mWithYouSecondTvLikes.setText(String.valueOf(second.getLike()));
            mWithYouSecondTvContent.setText(second.getContent());

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

            mWithYouSecondTvCreatedAt.setText(posted);

            if (second.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouSecondIvMbti);

        } else {
            mWithYouSecondConstraint.setVisibility(View.INVISIBLE);
        }


        // third
        if (data.getCommentLists().size() >= 3) {
            third = data.getCommentLists().get(2);
            mWithYouThirdTvNickname.setText(third.getUserNickName());
            mWithYouThirdTvCreatedAt.setText(third.getCreatedAt());
            mWithYouThirdTvLikes.setText(String.valueOf(third.getLike()));
            mWithYouThirdTvContent.setText(third.getContent());

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

            mWithYouThirdTvCreatedAt.setText(posted);

            if (third.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouThirdIvMbti);

        } else {
            mWithYouThirdConstraint.setVisibility(View.INVISIBLE);
        }

        // fourth
        if (data.getCommentLists().size() >= 4) {
            fourth = data.getCommentLists().get(3);
            mWithYouFourthTvNickname.setText(fourth.getUserNickName());
            mWithYouFourthTvCreatedAt.setText(fourth.getCreatedAt());
            mWithYouFourthTvLikes.setText(String.valueOf(fourth.getLike()));
            mWithYouFourthTvContent.setText(fourth.getContent());

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

            mWithYouFourthTvCreatedAt.setText(posted);

            if (fourth.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouFourthIvMbti);
            else if (fourth.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouFourthIvMbti);

        } else {
            mWithYouFourthConstraint.setVisibility(View.INVISIBLE);
        }

        // fifth
        if (data.getCommentLists().size() >= 5) {
            fifth = data.getCommentLists().get(4);
            mWithYouFifthTvNickname.setText(fifth.getUserNickName());
            mWithYouFifthTvCreatedAt.setText(fifth.getCreatedAt());
            mWithYouFifthTvLikes.setText(String.valueOf(fifth.getLike()));
            mWithYouFifthTvContent.setText(fifth.getContent());

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

            mWithYouFifthTvCreatedAt.setText(posted);

            if (fifth.getUserMbti().equals("intj"))
                Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithYouFifthIvMbti);
            else if (fifth.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithYouFifthIvMbti);

        } else {
            mWithYouFifthConstraint.setVisibility(View.INVISIBLE);
        }

        ((MainActivity) mContext).hideProgressDialog();
    }

    @Override
    public void tryGetWithYouFailure(String message) {
        ((MainActivity) mContext).hideProgressDialog();
        ((MainActivity) mContext).showCustomToastShort(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;

        final BoardService withYouService = new BoardService(this, mContext);
        withYouService.tryGetWithYouInfo(5);
        ((MainActivity) mContext).showProgressDialog();
    }
}

