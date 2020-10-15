package com.makeus.urirang.android.src.withAll.content;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.withAll.WithAllService;
import com.makeus.urirang.android.src.withAll.content.adapter.WithAllContentCommentAdapter;
import com.makeus.urirang.android.src.withAll.content.models.WithAllComment;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllContentActivityView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithAllContentActivity extends BaseActivity implements WithAllContentActivityView {

    private Context mContext;
    private boolean mDoubleClick = false;

    private RecyclerView mWithAllContentRv;
    private WithAllContentCommentAdapter mCommentAdapter;
    private ArrayList<WithAllComment> mCommentList;
    private TextView mWithAllContentTvNumOfComment;

    private NestedScrollView mScroll;

    // 이미지
    private CardView mWithAllContentCard1;
    private CardView mWithAllContentCard2;
    private CardView mWithAllContentCard3;
    private CardView mWithAllContentCard4;

    private ImageView mWithAllContentIv1;
    private ImageView mWithAllContentIv2;
    private ImageView mWithAllContentIv3;
    private ImageView mWithAllContentIv4;

    // 상단
    private ImageView mWithAllContentIvMbti;
    private ImageView mWithAllContentIvNew;
    private ImageView mWithAllContentIvSendLike;
    private TextView mWithAllContentTvTitle;
    private TextView mWithAllContentTvNickname;
    private TextView mWithAllContentTvCreatedAt;
    private TextView mWithAllContentTvContent;
    private TextView mWithAllContentTvViews;
    private TextView mWithAllContentTvLikes;
    private TextView mWithAllContentTvSendLike;
    private LinearLayout mWithAllLinearLikes;

    private int mPostId = -1;
    private boolean mIsLiked = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private boolean mLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_all_content);

        mContext = this;
        mCommentList = new ArrayList<>();
        mPostId = getIntent().getIntExtra("postId", -1);

        mScroll = findViewById(R.id.with_all_content_nested_scroll);
        mWithAllContentRv = findViewById(R.id.with_all_content_rv_comment);
        mCommentAdapter = new WithAllContentCommentAdapter(mContext, mCommentList, (v, pos) -> {

        });

        mWithAllContentRv.setAdapter(mCommentAdapter);

        mScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

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
                        Log.d(TAG, mPage + "번째 페이지 with all comment");
                        getWithAllCommentList();
                    }

                    mLoading = false;
                }

            }
        });

        mWithAllContentTvNumOfComment = findViewById(R.id.with_all_content_tv_comment_num);

        mWithAllContentCard1 = findViewById(R.id.with_all_content_card_1);
        mWithAllContentCard2 = findViewById(R.id.with_all_content_card_2);
        mWithAllContentCard3 = findViewById(R.id.with_all_content_card_3);
        mWithAllContentCard4 = findViewById(R.id.with_all_content_card_4);

        mWithAllContentIv1 = findViewById(R.id.with_all_content_iv_1);
        mWithAllContentIv2 = findViewById(R.id.with_all_content_iv_2);
        mWithAllContentIv3 = findViewById(R.id.with_all_content_iv_3);
        mWithAllContentIv4 = findViewById(R.id.with_all_content_iv_4);

        mWithAllContentIvMbti = findViewById(R.id.with_all_content_iv_mbti);
        mWithAllContentIvNew = findViewById(R.id.with_all_content_iv_new);
        mWithAllContentIvSendLike = findViewById(R.id.with_all_content_iv_send_like);

        mWithAllContentTvTitle = findViewById(R.id.with_all_content_tv_title);
        mWithAllContentTvNickname = findViewById(R.id.with_all_content_tv_nickname);
        mWithAllContentTvCreatedAt = findViewById(R.id.with_all_content_tv_created_at);
        mWithAllContentTvViews = findViewById(R.id.with_all_content_tv_views);
        mWithAllContentTvLikes = findViewById(R.id.with_all_content_tv_likes);
        mWithAllContentTvSendLike = findViewById(R.id.with_all_content_tv_send_like);
        mWithAllLinearLikes = findViewById(R.id.with_all_content_linear_send_like);
        mWithAllContentTvContent = findViewById(R.id.with_all_content_tv_content);

    }

    void getWithAllContent(int option) {
        final WithAllService getContent = new WithAllService(this, this, option);
        getContent.tryGetWithAllContent(mPostId);
        showProgressDialog();
    }

    void getWithAllCommentList() {
        final WithAllService getComment = new WithAllService(this, this, 1);
        getComment.tryGetWithAllComment(mPostId, mPage, 10);
    }

    public void withAllContentOnClick(View view) {
        switch (view.getId()) {
            case R.id.with_all_content_iv_back_arrow:
                finish();
                break;
            case R.id.with_all_content_linear_send_like:
                if (mDoubleClick) return;
                mDoubleClick = true;

                final WithAllService likeDislike = new WithAllService(this, this, 1);
                if (mIsLiked) {
                    likeDislike.tryPostDisLike(mPostId);
                } else {
                    likeDislike.tryPostLike(mPostId);
                }
                showProgressDialog();

                break;
            case R.id.with_all_content_iv_share:
                if (mDoubleClick) return;
                mDoubleClick = true;

                break;
        }
    }

    void sendLikeOrDisLike(boolean b) {

        if (!b) {

        } else {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClick = false;
        getWithAllContent(1);
    }


    void setWithAllContent(WithAllContentResponse response) {

        mWithAllContentTvNickname.setText(response.getData().getPost().getUser().getNickname());
        String title = response.getData().getPost().getTitle() + " (" + String.valueOf(response.getData().getPost().getCommentNum()) + ")";
        SpannableString spannableString = new SpannableString(title);
        int start = title.indexOf("(");
        int end = title.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ea0071")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.77f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        mWithAllContentTvTitle.setText(spannableString);
        mWithAllContentTvLikes.setText(String.valueOf(response.getData().getPost().getLikes()));
        mWithAllContentTvContent.setText(response.getData().getPost().getContent());

        mWithAllContentTvNumOfComment.setText(String.valueOf(response.getData().getPost().getCommentNum() + "개"));

        // created at
        String createdTime = response.getData().getPost().getCreatedAt();
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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String today = afterFormat.format(date);

        if (posted.substring(0, 5).equals(today.substring(0, 5))) {
            mWithAllContentIvNew.setVisibility(View.VISIBLE);
        } else mWithAllContentIvNew.setVisibility(View.INVISIBLE);

        mWithAllContentTvCreatedAt.setText(posted);
        mWithAllContentTvViews.setText(String.valueOf(response.getData().getPost().getViews()));


        if (response.getData().isLiked()) {
            mIsLiked = true;
            mWithAllLinearLikes.setBackgroundResource(R.drawable.src_how_about_this_like_corner);
            mWithAllContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_true));
            mWithAllContentTvSendLike.setTextColor(getResources().getColor(R.color.colorHotPink));
        } else {
            mIsLiked = false;
            mWithAllLinearLikes.setBackgroundResource(R.drawable.src_how_about_this_dislike_corner);
            mWithAllContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_false));
            mWithAllContentTvSendLike.setTextColor(getResources().getColor(R.color.colorBasicBlack40));
        }

        if (response.getData().getPost().getImages().size() >= 1) {
            mWithAllContentCard1.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(response.getData().getPost().getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllContentIv1);
        }

        if (response.getData().getPost().getImages().size() >= 2) {
            mWithAllContentCard2.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(response.getData().getPost().getImages().get(1).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllContentIv2);
        }

        if (response.getData().getPost().getImages().size() >= 3) {
            mWithAllContentCard3.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(response.getData().getPost().getImages().get(2).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllContentIv3);
        }

        if (response.getData().getPost().getImages().size() >= 4) {
            mWithAllContentCard4.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(response.getData().getPost().getImages().get(3).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mWithAllContentIv4);
        }

        if (response.getData().getPost().getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(mWithAllContentIvMbti);

    }

    @Override
    public void tryGetContentSuccess(WithAllContentResponse response) {
        setWithAllContent(response);
        getWithAllCommentList();
    }

    @Override
    public void tryGetContentOnlySuccess(WithAllContentResponse response) {
        setWithAllContent(response);
        hideProgressDialog();
    }

    @Override
    public void tryGetContentFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryPostLikeSuccess() {
        mIsLiked = true;
        mDoubleClick = false;

//        mWithAllLinearLikes.setBackgroundResource(R.drawable.src_how_about_this_like_corner);
//        mWithAllContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_true));
//        mWithAllContentTvSendLike.setTextColor(getResources().getColor(R.color.colorHotPink));
        getWithAllContent(2);
    }

    @Override
    public void tryPostDislikeSuccess() {
        mIsLiked = false;
        mDoubleClick = false;
//        mWithAllLinearLikes.setBackgroundResource(R.drawable.src_how_about_this_dislike_corner);
//        mWithAllContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_false));
//        mWithAllContentTvSendLike.setTextColor(getResources().getColor(R.color.colorBasicBlack40));
        getWithAllContent(2);
    }

    @Override
    public void tryPostLikeDislikeFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        mDoubleClick = false;
    }

    @Override
    public void tryGetCommentListSuccess(ArrayList<WithAllComment> results) {
        if (results.size() < 10) {
            mIsEmptyResult = true;
        }

        mCommentList.addAll(results);
        mCommentAdapter.notifyDataSetChanged();
        mPage += 1;
        mLoading = true;
        hideProgressDialog();
    }

    @Override
    public void tryGetCommentListFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}