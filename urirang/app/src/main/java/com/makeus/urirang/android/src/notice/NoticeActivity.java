package com.makeus.urirang.android.src.notice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.notice.adapter.NoticeAdapter;
import com.makeus.urirang.android.src.notice.interfaces.NoticeActivityView;
import com.makeus.urirang.android.src.notice.models.Notice;
import com.makeus.urirang.android.src.withAll.content.WithAllContentActivity;
import com.makeus.urirang.android.src.withYou.comment.WithYouCommentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class NoticeActivity extends BaseActivity implements NoticeActivityView {

    private NoticeActivityView mView;
    private Context mContext;

    private boolean mDoubleClickFlag = false;
    private NoticeAdapter mNoticeAdapter;
    private RecyclerView mNoticeRv;
    private ArrayList<Notice> mNoticeList;

    private int mPage = 1;
    private boolean mIsEmptyResult = false;
    private int mSelectedType = 1; // 1 : 모두랑, 2 : 너희랑
    private int mSelectedId = -1;

    private ConstraintLayout mConstraintEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        mView = this;
        mContext = this;

        mConstraintEmpty = findViewById(R.id.notice_constraint_empty);

        mNoticeList = new ArrayList<>();
        mNoticeRv = findViewById(R.id.notice_rv);
        mNoticeAdapter = new NoticeAdapter(this, mNoticeList, new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                if (mNoticeAdapter.getItem(pos).getPostId() != null) {
                    mSelectedType = 1;
                    mSelectedId = mNoticeAdapter.getItem(pos).getPostId();
                } else if (mNoticeAdapter.getItem(pos).getTopicId() != null) {
                    mSelectedType = 2;
                    mSelectedId = mNoticeAdapter.getItem(pos).getTopicId();
                }

                HashMap<String, Object> params = new HashMap<>();
                params.put("noticeId", mNoticeAdapter.getItem(pos).getId());

                final NoticeService service = new NoticeService(mView, mContext);
                service.tryPostNoticeCheck(params);
                showProgressDialog();
            }
        });

        mNoticeRv.addItemDecoration(new DividerItemDecoration(mNoticeRv.getContext(), LinearLayoutManager.VERTICAL));
        mNoticeRv.setAdapter(mNoticeAdapter);

        mNoticeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mNoticeRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mNoticeRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 notice");
                        getNoticeList(mPage);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    void getNoticeList(int page) {
        final NoticeService noticeService = new NoticeService(this, this);
        noticeService.tryGetNoticeList(page, 10);
        showProgressDialog();
    }

    public void noticeOnClick(View view) {
        switch (view.getId()) {
            case R.id.notice_iv_back_arrow:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mNoticeList.clear();
        mPage = 1;
        getNoticeList(mPage);

    }

    @Override
    public void tryGetNoticeListSuccess(ArrayList<Notice> results) {

        if (results.size() < 10) {
            mIsEmptyResult = true;
        }

        if (mPage == 1 && results.size() == 0) {
            mConstraintEmpty.setVisibility(View.VISIBLE);
        } else {
            mConstraintEmpty.setVisibility(View.GONE);
        }

        mNoticeList.addAll(results);
        mNoticeAdapter.notifyDataSetChanged();
        mPage += 1;
        hideProgressDialog();

    }

    @Override
    public void tryGetNoticeListFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    public void tryPostCheckSuccess() {
        hideProgressDialog();

        if (mSelectedType == 1) {
            Intent go = new Intent(this, WithAllContentActivity.class);
            go.putExtra("postId", mSelectedId);
            startActivity(go);
        } else if (mSelectedId == 2) {
            Intent go = new Intent(this, WithYouCommentActivity.class);
            go.putExtra("topicId", sSharedPreferences.getInt("currentTopicId", 1));
            startActivity(go);
        }

    }

    @Override
    public void tryPostCheckFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}