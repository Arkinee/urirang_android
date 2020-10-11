package com.makeus.urirang.android.src.myCommentPost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.myCommentPost.adapter.MyCommentPostsAdapter;
import com.makeus.urirang.android.src.myCommentPost.interfaces.MyCommentPostsActivityView;
import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPosts;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class MyCommentPostsActivity extends BaseActivity implements MyCommentPostsActivityView {

    private MyCommentPostsAdapter mAdapter;
    private ArrayList<MyCommentPosts> mPostsList;
    private RecyclerView rvMyCommentPosts;

    private TextView mMyCommentPostsTvCount;

    private int mPage = 1;
    private boolean mIsEmptyResult = false;
    private boolean mDoubleClickFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment_posts);

        mMyCommentPostsTvCount = findViewById(R.id.my_comment_posts_tv_num_of_posts);
        mPostsList = new ArrayList<>();
        mAdapter = new MyCommentPostsAdapter(this, mPostsList, new MyCommentPostsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;


            }
        });


        rvMyCommentPosts = findViewById(R.id.my_comment_posts_rv);
        rvMyCommentPosts.addItemDecoration(new DividerItemDecoration(rvMyCommentPosts.getContext(), LinearLayoutManager.VERTICAL));
        rvMyCommentPosts.setAdapter(mAdapter);

        rvMyCommentPosts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(rvMyCommentPosts.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = rvMyCommentPosts.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 my comment posts");
                        getMyCommentPosts(mPage);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    void getMyCommentPosts(int page) {
        final MyCommentPostsService postsService = new MyCommentPostsService(this, this);
        postsService.tryGetMyCommentPosts(page, 20);
        showProgressDialog();
    }

    public void commentPostsClick(View view) {

        switch (view.getId()) {
            case R.id.my_comment_posts_iv_back_arrow:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mPage = 1;
        getMyCommentPosts(mPage);
    }

    @Override
    public void tryGetMyCommentPostsSuccess(ArrayList<MyCommentPosts> data) {

        mMyCommentPostsTvCount.setText(String.valueOf(data.size()));

        if (data.size() < 20) {
            mIsEmptyResult = true;
        }

        mPostsList.addAll(data);
        mPage += 1;
        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void tryGetMyCommentPostsFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }


}