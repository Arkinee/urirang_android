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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

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

    private boolean mDoubleClickFlag = false;
    private int mTopicId = 0;

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

        mWithYouFirstConstraint = view.findViewById(R.id.with_you_constraint_first_comment);
        mWithYouFirstIvMbti = view.findViewById(R.id.with_you_first_comment_iv_mbti);
        mWithYouFirstTvNickname = view.findViewById(R.id.with_you_first_comment_tv_nickname);
        mWithYouFirstTvCreatedAt = view.findViewById(R.id.with_you_first_comment_tv_created_at);
        mWithYouFirstTvContent = view.findViewById(R.id.with_you_first_comment_tv_content);
        mWithYouFirstTvLikes = view.findViewById(R.id.with_you_first_comment_tv_like);

        mWithYouSecondConstraint = view.findViewById(R.id.with_you_constraint_second_comment);
        mWithYouSecondIvMbti = view.findViewById(R.id.with_you_second_comment_iv_mbti);
        mWithYouSecondTvNickname = view.findViewById(R.id.with_you_second_comment_tv_nickname);
        mWithYouSecondTvCreatedAt = view.findViewById(R.id.with_you_second_comment_tv_created_at);
        mWithYouSecondTvContent = view.findViewById(R.id.with_you_second_comment_tv_content);
        mWithYouSecondTvLikes = view.findViewById(R.id.with_you_second_comment_tv_like);

        mWithYouThirdConstraint = view.findViewById(R.id.with_you_constraint_third_comment);
        mWithYouThirdIvMbti = view.findViewById(R.id.with_you_third_comment_iv_mbti);
        mWithYouThirdTvNickname = view.findViewById(R.id.with_you_third_comment_tv_nickname);
        mWithYouThirdTvCreatedAt = view.findViewById(R.id.with_you_third_comment_tv_created_at);
        mWithYouThirdTvContent = view.findViewById(R.id.with_you_third_comment_tv_content);
        mWithYouThirdTvLikes = view.findViewById(R.id.with_you_third_comment_tv_like);

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

        final BoardService withYouService = new BoardService(this, mContext);
        withYouService.tryGetWithYouInfo(3);
        ((MainActivity) mContext).showProgressDialog();

    }

    @Override
    public void tryGetWithYouSuccess(BoardWithYouData data) {

        mTopicId = data.getTopic().getId();

        // 상단
        Glide.with(mContext).load(data.getTopic().getImages().get(0).getUrl()).into(mWithyouivMain);
        mWithYouTvTopic.setText(data.getTopic().getTitle());
        mWithYouTvNickname.setText(data.getTopic().getUser().getNickname());
        mWithYouTvCommentNum.setText(String.valueOf(data.getTopic().getCommentNum()));

        if (data.getTopic().getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(mWithYouIvMbti);
        else if (data.getTopic().getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(mWithYouIvMbti);


        // 하단
        CommentList first = new CommentList();
        CommentList second = new CommentList();
        CommentList third = new CommentList();


        // first
        if (data.getCommentLists().size() >= 1) {
            first = data.getCommentLists().get(0);
            mWithYouFirstTvNickname.setText(first.getUserNickName());
            mWithYouFirstTvCreatedAt.setText(first.getCreatedAt());
            mWithYouFirstTvLikes.setText(String.valueOf(first.getLike()));
            mWithYouFirstTvContent.setText(first.getContent());

            String createdTime = first.getCreatedAt();
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
                Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(mWithYouFirstIvMbti);
            else if (first.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(mWithYouFirstIvMbti);

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
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
                Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(mWithYouSecondIvMbti);
            else if (second.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(mWithYouSecondIvMbti);

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
            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
                Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("infj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("istj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("istp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("intp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("infp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("isfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("isfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("entj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("enfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("estj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("estp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("entp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("enfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("esfj"))
                Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(mWithYouThirdIvMbti);
            else if (third.getUserMbti().equals("esfp"))
                Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(mWithYouThirdIvMbti);

        } else {
            mWithYouThirdConstraint.setVisibility(View.INVISIBLE);
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
    }
}

