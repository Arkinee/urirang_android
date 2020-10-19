package com.makeus.urirang.android.src.withAll.content;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
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
import java.util.HashMap;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithAllContentActivity extends BaseActivity implements WithAllContentActivityView {

    private Context mContext;
    private boolean mDoubleClick = false;

    private RecyclerView mWithAllContentRv;
    private WithAllContentCommentAdapter mCommentAdapter;
    private ArrayList<WithAllComment> mCommentList;
    private TextView mWithAllContentTvNumOfComment;

    private NestedScrollView mScroll;
    private TextView mWithAllContentToComment;

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

    // 하단
    private EditText mWithAllContentEdtComment;
    private TextView mWithAllContentTvAnonymous;
    private ImageView mWithAllContentIvSend;

    private int mPostId = -1;
    private boolean mIsLiked = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private boolean mLoading = true;
    private boolean mIsAnonymous = false;
    private String mSelectedCommentId = "";
    private int mPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_all_content);

        mContext = this;
        mCommentList = new ArrayList<>();
        mPostId = getIntent().getIntExtra("postId", -1);

        mWithAllContentEdtComment = findViewById(R.id.with_all_content_edt_comment);
        mWithAllContentTvAnonymous = findViewById(R.id.with_all_content_tv_anonymous);
        mWithAllContentIvSend = findViewById(R.id.with_all_content_iv_send);

        mWithAllContentToComment = findViewById(R.id.with_all_content_tv_toComment);
        mScroll = findViewById(R.id.with_all_content_nested_scroll);
        mWithAllContentRv = findViewById(R.id.with_all_content_rv_comment);
        mCommentAdapter = new WithAllContentCommentAdapter(mContext, mCommentList, (v, pos) -> {

            mSelectedCommentId = String.valueOf(mCommentAdapter.getItem(pos).getId());
            mPosition = pos;

            if (!mCommentAdapter.getItem(pos).isAnonymous())
                mWithAllContentToComment.setText("@".concat(mCommentAdapter.getItem(pos).getUserNickName()).concat("에게 댓글 남기는 중"));
            else mWithAllContentToComment.setText("@".concat("익명").concat("에게 댓글 남기는 중"));

            mWithAllContentToComment.setVisibility(View.VISIBLE);
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

        mWithAllContentEdtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mWithAllContentEdtComment.getText().toString().equals("")) {
                    mWithAllContentIvSend.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_comment_send));
                } else {
                    mWithAllContentIvSend.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_comment_send_default));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
            case R.id.with_all_content_tv_anonymous:
                mIsAnonymous = !mIsAnonymous;

                if (mIsAnonymous) {
                    mWithAllContentTvAnonymous.setTextColor(getResources().getColor(R.color.colorBlack));
                } else {
                    mWithAllContentTvAnonymous.setTextColor(getResources().getColor(R.color.colorBasicBlack11));
                }
                break;
            case R.id.with_all_content_iv_send:
                if (mWithAllContentEdtComment.getText().toString().equals("")) {
                    showCustomToastShort("의견을 입력해 주세요");
                    return;
                }

                sendComment();
                break;
            case R.id.with_all_content_tv_toComment:
                mSelectedCommentId = "";
                mWithAllContentToComment.setVisibility(View.GONE);
                break;
        }
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

    void sendComment() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("content", mWithAllContentEdtComment.getText().toString());
        params.put("isAnonymous", mIsAnonymous);

        final WithAllService commentService = new WithAllService(this, this, 1);
        commentService.tryPostWithAllCommentWrite(mPostId, mSelectedCommentId, params);
        showProgressDialog();
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
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWithAllContentIvMbti);
        else if (response.getData().getPost().getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWithAllContentIvMbti);

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

    @Override
    public void tryPostCommentWriteSuccess(String message) {
        mIsAnonymous = false;
        mWithAllContentTvAnonymous.setTextColor(getResources().getColor(R.color.colorBasicBlack11));
        mWithAllContentEdtComment.setText("");
        mWithAllContentIvSend.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_comment_send_default));
        mWithAllContentToComment.setVisibility(View.GONE);

        mPage = 1;
        mCommentList.clear();
        getWithAllContent(1);

        mSelectedCommentId = "";
        mDoubleClick = false;
        hideProgressDialog();
    }

    @Override
    public void tryPostCommentWriteFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
        mDoubleClick = false;
    }
}