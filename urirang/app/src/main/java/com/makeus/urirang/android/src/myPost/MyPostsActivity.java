package com.makeus.urirang.android.src.myPost;

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
import com.makeus.urirang.android.src.myPost.adapter.MyPostsAdapter;
import com.makeus.urirang.android.src.myPost.interfaces.MyPostsActivityView;
import com.makeus.urirang.android.src.myPost.models.MyPosts;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class MyPostsActivity extends BaseActivity implements MyPostsActivityView {

    private MyPostsAdapter mAdapter;
    private RecyclerView mMyPostsRv;
    private ArrayList<MyPosts> mPostsList;
    private TextView mMyPostsTvCount;

    private boolean mIsEmptyResult = false;
    private boolean mDoubleClickFlag = false;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        mPostsList = new ArrayList<>();
        mMyPostsTvCount = findViewById(R.id.my_posts_tv_num_of_posts);
        mMyPostsRv = findViewById(R.id.my_posts_rv);
        mAdapter = new MyPostsAdapter(this, mPostsList, new MyPostsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                ;
                mDoubleClickFlag = true;

            }
        });

        mMyPostsRv.addItemDecoration(new DividerItemDecoration(mMyPostsRv.getContext(), LinearLayoutManager.VERTICAL));
        mMyPostsRv.setAdapter(mAdapter);

        mMyPostsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mMyPostsRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mMyPostsRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 my posts");
                        getMyPosts(mPage);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    void getMyPosts(int page) {
        final MyPostsService postsService = new MyPostsService(this, this);
        postsService.tryGetMyPosts(page, 20);
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mPage = 1;
        getMyPosts(mPage);
    }

    public void myPostsOnClick(View view) {

        switch (view.getId()) {
            case R.id.my_posts_iv_back_arrow:
                finish();
                break;
        }
    }

    @Override
    public void tryGetMyPostsSuccess(ArrayList<MyPosts> data) {

        mMyPostsTvCount.setText(String.valueOf(data.size()));

        if (data.size() < 20) {
            mIsEmptyResult = true;
        }

        mPostsList.addAll(data);
        mAdapter.notifyDataSetChanged();
        mPage += 1;
        hideProgressDialog();

    }

    @Override
    public void tryGetMyPostsFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}