package com.makeus.urirang.android.src.main.fragments.worldCup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.RecyclerDecoration;
import com.makeus.urirang.android.src.dialog.BottomSheetHallOfFameFilterDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetWorldCupFilterDialog;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.worldCup.adapter.WorldCupAdapter;
import com.makeus.urirang.android.src.main.fragments.worldCup.interfaces.WorldCupView;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCup;
import com.makeus.urirang.android.src.worldCup.content.WorldCupContentActivity;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;
import com.makeus.urirang.android.src.worldCup.write.WorldCupWriteActivity;

import java.util.ArrayList;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WorldCupFragment extends Fragment implements WorldCupView, View.OnClickListener {

    private Context mContext;
    private NestedScrollView mNestedScroll;
    private WorldCupAdapter mWorldCupAdapter;
    private ArrayList<WorldCup> mWorldCupList;

    private TextView mWorldCupTvPopularTitle;
    private TextView mWorldCupTvPopularNickname;
    private TextView mWorldCupTvPopularRound;
    private TextView mWorldCupTvPopularParticipate;
    private TextView mWorldCupTvPopularDesc;

    private TextView mWorldCupTvOrderBy;

    private ImageView mWorldCupIvPopularMain;
    private ImageView mWorldCupIvPopularMbti;

    private int mPage = 1;
    private int mOption = 1;
    private boolean mDoubleClick = false;
    private boolean mIsEmptyResult = false;
    private boolean mLoading = true;
    private String mSort = "";
    private int mPopularId = -1;

    public WorldCupFragment() {
    }

    public WorldCupFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world_cup, container, false);

        mWorldCupTvPopularTitle = view.findViewById(R.id.world_cup_popular_tv_title);
        mWorldCupTvPopularNickname = view.findViewById(R.id.world_cup_popular_tv_nickname);
        mWorldCupTvPopularRound = view.findViewById(R.id.world_cup_popular_tv_round);
        mWorldCupTvPopularParticipate = view.findViewById(R.id.world_cup_popular_tv_participate);
        mWorldCupIvPopularMbti = view.findViewById(R.id.world_cup_popular_iv_mbti);
        mWorldCupIvPopularMain = view.findViewById(R.id.world_cup_iv_first_main);
        mWorldCupTvPopularDesc = view.findViewById(R.id.world_cup_tv_popular_desc);
        mWorldCupTvOrderBy = view.findViewById(R.id.world_cup_tv_order_by);

        mWorldCupList = new ArrayList<>();
        RecyclerView worldCupRv = view.findViewById(R.id.world_cup_rv);
        mWorldCupAdapter = new WorldCupAdapter(mContext, mWorldCupList, (v, pos) -> {
            if (mDoubleClick) return;
            mDoubleClick = true;

            Intent worldCup = new Intent(mContext, WorldCupContentActivity.class);
            worldCup.putExtra("worldCupId", mWorldCupList.get(pos).getId());
            startActivity(worldCup);

        });
        worldCupRv.addItemDecoration(new RecyclerDecoration(mContext, 11));
        worldCupRv.setAdapter(mWorldCupAdapter);

        mNestedScroll = view.findViewById(R.id.world_cup_nested_scroll);
        mNestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
//                Log.d(TAG, "Scroll DOWN");
            }
            if (scrollY < oldScrollY) {
//                Log.d(TAG, "Scroll UP");
            }

            if (scrollY == 0) {
//                Log.d(TAG, "TOP SCROLL");
            }

            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
//                Log.d(TAG, "BOTTOM SCROLL");
                // here where the trick is going
                if (mLoading) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 world cup");
                        getList();
                    }

                    mLoading = false;
                }

            }
        });

        FloatingActionButton fb = view.findViewById(R.id.world_cup_fab_write);
        fb.setOnClickListener(this);

        ConstraintLayout constraintPopular = view.findViewById(R.id.world_cup_popular_constraint);
        constraintPopular.setOnClickListener(this);

        LinearLayout linearOrderby = view.findViewById(R.id.world_cup_linear_order_by);
        linearOrderby.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    void getBest() {
        final WorldCupService bestService = new WorldCupService(this, mContext);
        bestService.tryGetBest();
        ((MainActivity) mContext).showProgressDialog();
    }

    void getList() {
        final WorldCupService listService = new WorldCupService(this, mContext);
        listService.tryGetList(mPage, 10, mSort);
        ((MainActivity) mContext).showProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWorldCupList.clear();
        mDoubleClick = false;
        mIsEmptyResult = false;
        mLoading = true;
        mSort = "";
        mPage = 1;
        getBest();
    }

    @Override
    public void tryGetBestSuccess(WorldCup worldCup) {

        mPopularId = worldCup.getId();
        mWorldCupTvPopularTitle.setText(worldCup.getTitle());
        mWorldCupTvPopularNickname.setText(worldCup.getUser().getNickname());

        String round = " " + mContext.getString(R.string.world_cup_middle_dot) + " " + worldCup.getRoundNum() + "강 " + mContext.getString(R.string.world_cup_middle_dot);
        mWorldCupTvPopularRound.setText(round);

        Glide.with(mContext).load(worldCup.getWorldCupCandidates().get(0).getImageUrl()).into(mWorldCupIvPopularMain);
        mWorldCupTvPopularDesc.setText(String.format("1위 %s", worldCup.getWorldCupCandidates().get(0).getTitle()));
        mWorldCupTvPopularParticipate.setText(String.valueOf(worldCup.getJoinNum()));

        if (worldCup.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mWorldCupIvPopularMbti);
        else if (worldCup.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mWorldCupIvPopularMbti);

        getList();
    }

    @Override
    public void tryGetListSuccess(ArrayList<WorldCup> results) {

        if (results.size() < 10) {
            mIsEmptyResult = true;
        }

        mWorldCupList.addAll(results);
        mWorldCupAdapter.notifyDataSetChanged();
        mPage += 1;
        ((MainActivity) mContext).hideProgressDialog();
        mLoading = true;
    }

    @Override
    public void tryGetWorldCupFailure(String message) {
        ((MainActivity) mContext).hideProgressDialog();
        ((MainActivity) mContext).showCustomToastShort(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.world_cup_fab_write:
                if (mDoubleClick) return;
                mDoubleClick = true;

                Intent write = new Intent(mContext, WorldCupWriteActivity.class);
                startActivity(write);
                break;
            case R.id.world_cup_popular_constraint:
                if (mDoubleClick) return;
                mDoubleClick = true;

                Intent start = new Intent(mContext, WorldCupContentActivity.class);
                if (mPopularId != -1)
                    start.putExtra("worldCupId", mPopularId);
                else {
                    ((MainActivity) mContext).showCustomToastShort("월드컵 불러오기 실패");
                    return;
                }
                startActivity(start);

                break;
            case R.id.world_cup_linear_order_by:
                if (mDoubleClick) return;
                mDoubleClick = true;

                BottomSheetWorldCupFilterDialog dialog = new BottomSheetWorldCupFilterDialog(mContext, mOption);
                dialog.setCancelable(false);
                dialog.show(((MainActivity) mContext).getSupportFragmentManager(), "WorldCup");
                break;
        }
    }

    public void applyCreatedAt(int option) {
        mWorldCupList.clear();
        mSort = "";
        mPage = 1;
        mIsEmptyResult = false;
        mDoubleClick = false;
        mLoading = true;
        mOption = 1;
        mWorldCupTvOrderBy.setText(mContext.getResources().getString(R.string.hall_of_fame_tv_filter_by_created));
        getList();
    }

    public void applyPopularity(int option) {
        mWorldCupList.clear();
        mSort = "best";
        mPage = 1;
        mIsEmptyResult = false;
        mDoubleClick = false;
        mLoading = true;
        mOption = 2;
        mWorldCupTvOrderBy.setText(mContext.getResources().getString(R.string.hall_of_fame_tv_filter_by_popularity));
        getList();
    }

}
