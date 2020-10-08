package com.makeus.urirang.android.src.withYou.comment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.RecyclerDecoration;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.withYou.comment.adapter.WithYouCommentAdapter;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouActivityView;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouComment;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithYouCommentActivity extends BaseActivity implements WithYouActivityView, BottomSheetMbtiFilterDialog.BottomSheetListener {

    private Context mContext;
    private WithYouActivityView mView;

    private TextView mWithYouCommentTvAnonymous;
    private TextView mWithYouCommentTvToComment;

    private RecyclerView mWithYouCommentRv;
    private WithYouCommentAdapter mWithYouCommentAdatper;
    private ArrayList<WithYouComment> mWithYouList;

    private InputMethodManager mInputMethodManager;
    private boolean mIsAnonymous = false;
    private int mSelectedCommentId = -1;
    private int mTopicId = 0;
    private int mPosition = -1;

    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private String mSelectedMbti = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiyh_you_comment);

        mView = this;
        mContext = this;

        mTopicId = getIntent().getIntExtra("topicId", 0);
        mWithYouList = new ArrayList<>();
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mWithYouCommentTvAnonymous = findViewById(R.id.with_you_comment_tv_anonymous);
        mWithYouCommentTvToComment = findViewById(R.id.with_you_comment_tv_toComment);
        mWithYouCommentRv = findViewById(R.id.with_you_comment_rv);
        mWithYouCommentAdatper = new WithYouCommentAdapter(this, mWithYouList, new WithYouCommentAdapter.OnItemClickListener() {
            @Override
            public void onWriteClick(View v, int pos) {
                mSelectedCommentId = mWithYouCommentAdatper.getItem(pos).getTopicId();
                Log.d("로그", "id " + mSelectedCommentId);
            }

            @Override
            public void onLikeClick(View v, int pos) {
                mPosition = pos;
                final WithYouCommentService likeService = new WithYouCommentService(mView, mContext);
                likeService.tryPostCommentLike(mWithYouCommentAdatper.getItem(pos).getId());
                showProgressDialog();
            }
        });
        mWithYouCommentRv.addItemDecoration(new RecyclerDecoration(this, 13));
        mWithYouCommentRv.setAdapter(mWithYouCommentAdatper);

        mWithYouCommentRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mWithYouCommentRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mWithYouCommentRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 with all post");
                        getWithYouList(mSelectedMbti, mPage);

                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

    void getWithYouList(String mbti, int page) {
        final WithYouCommentService listService = new WithYouCommentService(mView, mContext);
        listService.tryGetWithYouList(mTopicId, mbti, page, 15);
        showProgressDialog();
    }

    public void commentOnClick(View view) {

        switch (view.getId()) {
            case R.id.wiyh_you_comment_iv_back_arrow:
                finish();
                break;
            case R.id.wiyh_you_comment_tv_mbti_filtering:
                BottomSheetMbtiFilterDialog filterDialog = new BottomSheetMbtiFilterDialog(mContext);
                filterDialog.setCancelable(false);
                filterDialog.show(getSupportFragmentManager(), "MbtiFilterDialog");
                break;
            case R.id.with_you_comment_tv_anonymous:
                if (mIsAnonymous) {
                    mWithYouCommentTvAnonymous.setTextColor(getResources().getColor(R.color.colorBasicBlack11));
                } else {
                    mWithYouCommentTvAnonymous.setTextColor(getResources().getColor(R.color.colorBlack));
                }
                mIsAnonymous = !mIsAnonymous;
                break;
            case R.id.with_you_comment_iv_write:

                break;
        }
    }

    // 너희랑 리스트 조회 성공
    @Override
    public void tryGetWithYouListSuccess(ArrayList<WithYouComment> data) {

        if (data.size() < 15) {
            mIsEmptyResult = true;
        }

        mWithYouList.addAll(data);
        mWithYouCommentAdatper.notifyDataSetChanged();
        mPage += 1;
        hideProgressDialog();

    }

    // 너희랑 리스트 조회 실패
    @Override
    public void tryGetWithYouListFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    // 좋아요 성공
    @Override
    public void tryPostLikeCommentSuccess() {
        mWithYouCommentAdatper.like(mPosition);
        hideProgressDialog();
    }

    // 좋아요 실패
    @Override
    public void tryPostLikeCommentFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWithYouList.clear();
        mIsEmptyResult = false;
        mPage = 1;
        mSelectedMbti = "";
        getWithYouList(mSelectedMbti, mPage);
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

    // 필터링 적용
    @Override
    public void onFilterApply(String mbti) {
        mWithYouList.clear();
        mPage = 1;
        mSelectedMbti = mbti;
        getWithYouList(mSelectedMbti, mPage);
    }
}