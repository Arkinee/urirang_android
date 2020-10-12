package com.makeus.urirang.android.src.hallOfFame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetHallOfFameFilterDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetHowAboutThisFilterDialog;
import com.makeus.urirang.android.src.hallOfFame.adapter.HallOfFameAdapter;
import com.makeus.urirang.android.src.hallOfFame.content.HallOfFameContentActivity;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameActivityView;
import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;
import com.makeus.urirang.android.src.main.fragments.board.BoardFragment;
import com.makeus.urirang.android.src.main.fragments.home.HomeFragment;
import com.makeus.urirang.android.src.main.fragments.mypage.MyPageFragment;
import com.makeus.urirang.android.src.main.fragments.worldCup.WorldCupFragment;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class HallOfFameActivity extends BaseActivity implements BottomSheetHallOfFameFilterDialog.BottomSheetFilterOptionListener, HallOfFameActivityView {

    private Context mContext;
    private RecyclerView mHallOfFameRv;
    private HallOfFameAdapter mHallAdapter;
    private ArrayList<PreviousSubject> mHallList;

    private boolean mDoubleClickFlag = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private int mOption = 1;
    private String mSort = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        mContext = this;
        mHallList = new ArrayList<>();
        mHallOfFameRv = findViewById(R.id.hall_of_fame_rv);
        mHallAdapter = new HallOfFameAdapter(this, mHallList, new HallOfFameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent fameContent = new Intent(mContext, HallOfFameContentActivity.class);
                fameContent.putExtra("topicId", mHallList.get(pos).getId());
                startActivity(fameContent);
            }
        });

        mHallOfFameRv.addItemDecoration(new DividerItemDecoration(mHallOfFameRv.getContext(), LinearLayoutManager.VERTICAL));
        mHallOfFameRv.setAdapter(mHallAdapter);

        mHallOfFameRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mHallOfFameRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mHallOfFameRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 topic history");
                        getTopicHistoryList(mPage, mSort);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

    void getTopicHistoryList(int page, String sort) {
        final HallOfFameService historyService = new HallOfFameService(this, this);
        historyService.tryGetTopicHistoryList(page, 10, sort);
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHallList.clear();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mPage = 1;
        getTopicHistoryList(mPage, mSort);

    }

    public void hallOnClick(View view) {

        switch (view.getId()) {
            case R.id.hall_of_fame_iv_back_arrow:
                finish();
                break;
            case R.id.hall_of_fame_tv_filter:
                BottomSheetHallOfFameFilterDialog dialog = new BottomSheetHallOfFameFilterDialog(this, mOption);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "HallOfFameDialog");
                break;
        }
    }

    @Override
    public void applyFilterCreatedAt(int option) {
        mOption = option;
        mIsEmptyResult = false;
        mHallList.clear();
        mPage = 1;
        mSort = "";
        getTopicHistoryList(mPage, mSort);
    }

    @Override
    public void applyFilterPopularity(int option) {
        mOption = option;
        mIsEmptyResult = false;
        mHallList.clear();
        mPage = 1;
        mSort = "best";
        getTopicHistoryList(mPage, mSort);
    }

    @Override
    public void tryGetHallOfFameSuccess(ArrayList<PreviousSubject> results) {

        if (results.size() < 10) {
            mIsEmptyResult = true;
        }

        mHallList.addAll(results);
        mHallAdapter.notifyDataSetChanged();
        mPage += 1;
        hideProgressDialog();

    }

    @Override
    public void tryGetHallOfFameFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}