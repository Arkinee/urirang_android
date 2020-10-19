package com.makeus.urirang.android.src.main.fragments.board.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.board.BoardService;
import com.makeus.urirang.android.src.main.fragments.board.adapters.WithAllAdapter;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardWithAllView;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllData;
import com.makeus.urirang.android.src.search.SearchActivity;
import com.makeus.urirang.android.src.withAll.content.WithAllContentActivity;
import com.makeus.urirang.android.src.withAll.write.WithAllWriteActivity;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class BoardWithAllFragment extends Fragment implements View.OnClickListener, BoardWithAllView, BottomSheetMbtiFilterDialog.BottomSheetListener {

    private Context mContext;
    private RecyclerView mWithAllRv;
    private WithAllAdapter mWithAllAdapter;
    private ArrayList<BoardWithAllData> mWithAllList;

    private boolean mDoubleClickFlag = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private String mSelectedMbti = "";

    public BoardWithAllFragment() {
    }

    public BoardWithAllFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_with_all, container, false);

        mWithAllList = new ArrayList<>();
        LinearLayout withAllLinearSearch = view.findViewById(R.id.fragment_with_all_linear_search);
        TextView withAllTvFilter = view.findViewById(R.id.fragment_with_all_tv_mbti_filtering);
        mWithAllRv = view.findViewById(R.id.fragment_with_all_rv);
        mWithAllAdapter = new WithAllAdapter(mContext, mWithAllList, (v, pos) -> {
            if (mDoubleClickFlag) return;
            mDoubleClickFlag = true;

            Intent goContent = new Intent((MainActivity) mContext, WithAllContentActivity.class);
            goContent.putExtra("postId", mWithAllAdapter.getItem(pos).getId());
            startActivity(goContent);
        });

        mWithAllRv.addItemDecoration(new DividerItemDecoration(mWithAllRv.getContext(), new LinearLayoutManager(getActivity()).getOrientation()));
        mWithAllRv.setAdapter(mWithAllAdapter);

        FloatingActionButton fbutton = view.findViewById(R.id.fragment_with_all_fab_write);

        fbutton.setOnClickListener(this);
        withAllTvFilter.setOnClickListener(this);
        withAllLinearSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWithAllRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mWithAllRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mWithAllRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 with all post");
                        getWithAllList(mSelectedMbti, mPage);

                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void getWithAllList(String mbti, int page) {

        final BoardService withAllService = new BoardService(this, mContext);
        withAllService.tryGetWithAllList(mbti, "", page, 20);
        ((MainActivity) mContext).showProgressDialog();

    }

    public void setClickFlag(boolean b) {
        mDoubleClickFlag = b;
    }

    public void filter(String mbti) {
        mWithAllList.clear();
        mPage = 1;
        getWithAllList(mbti, mPage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_with_all_tv_mbti_filtering:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                BottomSheetMbtiFilterDialog filterDialog = new BottomSheetMbtiFilterDialog(mContext);
                filterDialog.setCancelable(false);
                filterDialog.show(((MainActivity) mContext).getSupportFragmentManager(), "MbtiFilterDialog");
                break;
            case R.id.fragment_with_all_linear_search:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent searchIntent = new Intent(mContext, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.fragment_with_all_fab_write:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent withAllWrite = new Intent(mContext, WithAllWriteActivity.class);
                startActivity(withAllWrite);
                break;
        }
    }

    @Override
    public void onFilterApply(String mbti) {
        mWithAllList.clear();
        mPage = 1;
        getWithAllList(mbti, mPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        mWithAllList.clear();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mPage = 1;
        mSelectedMbti = "";
        getWithAllList(mSelectedMbti, mPage);
    }

    @Override
    public void tryGetWithAllSuccess(ArrayList<BoardWithAllData> data) {

        if (data.size() < 20) {
            mIsEmptyResult = true;
        }

        mWithAllList.addAll(data);
        mWithAllAdapter.notifyDataSetChanged();
        mPage += 1;
        ((MainActivity) mContext).hideProgressDialog();
    }

    @Override
    public void tryGetWithAllFailure(String message) {
        ((MainActivity) mContext).hideProgressDialog();
        ((MainActivity) mContext).showCustomToastShort(message);
    }
}
