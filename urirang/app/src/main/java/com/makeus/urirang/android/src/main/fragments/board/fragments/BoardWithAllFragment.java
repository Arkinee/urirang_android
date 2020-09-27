package com.makeus.urirang.android.src.main.fragments.board.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;
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

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.board.adapters.WithAllAdapter;
import com.makeus.urirang.android.src.main.fragments.board.models.WithAllPost;
import com.makeus.urirang.android.src.search.SearchActivity;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class BoardWithAllFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private RecyclerView mWithAllRv;
    private WithAllAdapter mWithAllAdapter;
    private ArrayList<WithAllPost> mWithAllList;

    private boolean mDoubleClickFlag = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;

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
        mWithAllAdapter = new WithAllAdapter(mContext, mWithAllList, new WithAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        mWithAllRv.addItemDecoration(new DividerItemDecoration(mWithAllRv.getContext(), new LinearLayoutManager(getActivity()).getOrientation()));
        mWithAllRv.setAdapter(mWithAllAdapter);

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


                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        mWithAllList.add(new WithAllPost(1, "이번 토픽 재밌는거 같음", "신로건",
                "2020-09-28T10:56:22.000Z", "https://w.namu.la/s/9a853133a80a58349f83bebf0a40985a3c2e36b82290c14b8f8e2268e70023f94b43e4d82c9501cd8a1f6fdab09bb98c9827a3bf9d0bfd70feef9fb687c34b80be2d59bca5883c238ae6f47ddbad0191fa8b626c0332a5b4694df587e21e0a98",
                10, 35, 132));

        mWithAllList.add(new WithAllPost(2, "이번 토픽 재밌는거 같음", "엔티제",
                "2020-09-28T08:56:22.000Z", "https://w.namu.la/s/9a853133a80a58349f83bebf0a40985a3c2e36b82290c14b8f8e2268e70023f94b43e4d82c9501cd8a1f6fdab09bb98c9827a3bf9d0bfd70feef9fb687c34b80be2d59bca5883c238ae6f47ddbad0191fa8b626c0332a5b4694df587e21e0a98",
                10, 35, 110));

        mWithAllList.add(new WithAllPost(3, "이번 토픽 재밌는거 같음", "엔티피",
                "2020-09-28T12:56:22.000Z", "https://w.namu.la/s/9a853133a80a58349f83bebf0a40985a3c2e36b82290c14b8f8e2268e70023f94b43e4d82c9501cd8a1f6fdab09bb98c9827a3bf9d0bfd70feef9fb687c34b80be2d59bca5883c238ae6f47ddbad0191fa8b626c0332a5b4694df587e21e0a98",
                10, 35, 89));

        mWithAllList.add(new WithAllPost(4, "이번 토픽 재밌는거 같음", "인프제",
                "2020-09-27T01:56:22.000Z", "https://w.namu.la/s/9a853133a80a58349f83bebf0a40985a3c2e36b82290c14b8f8e2268e70023f94b43e4d82c9501cd8a1f6fdab09bb98c9827a3bf9d0bfd70feef9fb687c34b80be2d59bca5883c238ae6f47ddbad0191fa8b626c0332a5b4694df587e21e0a98",
                10, 35, 55));

        mWithAllList.add(new WithAllPost(5, "INFP들 머하냐", "인프피",
                "2020-09-26T05:56:22.000Z", "https://w.namu.la/s/9a853133a80a58349f83bebf0a40985a3c2e36b82290c14b8f8e2268e70023f94b43e4d82c9501cd8a1f6fdab09bb98c9827a3bf9d0bfd70feef9fb687c34b80be2d59bca5883c238ae6f47ddbad0191fa8b626c0332a5b4694df587e21e0a98",
                10, 35, 20));

        mWithAllAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_with_all_tv_mbti_filtering:
                BottomSheetMbtiFilterDialog filterDialog = new BottomSheetMbtiFilterDialog(mContext);
                filterDialog.setCancelable(false);
                filterDialog.show(((MainActivity)getActivity()).getSupportFragmentManager(), "MbtiFilterDialog");
                break;
            case R.id.fragment_with_all_linear_search:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent searchIntent = new Intent(mContext, SearchActivity.class);
                startActivity(searchIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        mPage = 1;
    }

}
