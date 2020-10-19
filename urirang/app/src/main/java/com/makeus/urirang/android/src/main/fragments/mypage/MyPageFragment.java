package com.makeus.urirang.android.src.main.fragments.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.RecyclerHorizontalDecoration;
import com.makeus.urirang.android.src.license.LicenseActivity;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.mypage.adapter.MyPageAdapter;
import com.makeus.urirang.android.src.main.fragments.mypage.interfaces.MyPageView;
import com.makeus.urirang.android.src.main.fragments.mypage.models.TestResult;
import com.makeus.urirang.android.src.modifyProfile.ModifyProfileActivity;
import com.makeus.urirang.android.src.myCommentPost.MyCommentPostsActivity;
import com.makeus.urirang.android.src.myPost.MyPostsActivity;
import com.makeus.urirang.android.src.testResults.content.TestResultsActivity;

import java.util.ArrayList;

public class MyPageFragment extends Fragment implements View.OnClickListener, MyPageView {

    private Context mContext;
    private ImageView mMyPageIvMbti;
    private TextView mMyPageTvCharacteristic;
    private TextView mMyPageTvNickname;
    private TextView mMyTvPageNoResult;
    private RecyclerView mMyPageRvTest;

    private ArrayList<TestResult> mResultList;
    private MyPageAdapter mMyPageAdapter;

    private String mMbti;
    private String mNickname;
    private boolean mDoubleClickFlag = false;

    public MyPageFragment() {

    }

    public MyPageFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        mResultList = new ArrayList<>();
        ImageView ivMyPageSettings = view.findViewById(R.id.my_page_iv_settings);
        TextView tvMyPageModifyProfile = view.findViewById(R.id.my_page_tv_modify_profile);
        TextView tvMyPageEditResults = view.findViewById(R.id.my_page_tv_edit_test_result);

        TextView tvMyPageMyPosts = view.findViewById(R.id.my_page_tv_my_posts);
        TextView tvMyPageMyCommentedPosts = view.findViewById(R.id.my_page_tv_my_commented_posts);
        TextView tvMyPageLicense = view.findViewById(R.id.my_page_tv_license);

        mMyPageIvMbti = view.findViewById(R.id.my_page_iv_mbti);
        mMyPageTvCharacteristic = view.findViewById(R.id.my_page_tv_characteristic);
        mMyPageTvNickname = view.findViewById(R.id.my_page_tv_nickname);
        mMyTvPageNoResult = view.findViewById(R.id.my_page_tv_no_results);

        mMyPageRvTest = view.findViewById(R.id.my_page_rv_tests);
        mMyPageRvTest.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mMyPageRvTest);

        mMyPageAdapter = new MyPageAdapter(mContext, mResultList, (v, pos) -> {

        });

        mMyPageRvTest.addItemDecoration(new RecyclerHorizontalDecoration(mContext, 10));
        mMyPageRvTest.setAdapter(mMyPageAdapter);

        ivMyPageSettings.setOnClickListener(this);
        tvMyPageModifyProfile.setOnClickListener(this);
        tvMyPageEditResults.setOnClickListener(this);
        tvMyPageMyPosts.setOnClickListener(this);
        tvMyPageMyCommentedPosts.setOnClickListener(this);
        tvMyPageLicense.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    void getMyPageInfo() {
        final MyPageService testService = new MyPageService(this, mContext);
        testService.tryGetUserInfo();
        ((MainActivity) mContext).showProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_page_iv_settings:
                break;
            case R.id.my_page_tv_modify_profile:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent modifyIntent = new Intent(mContext, ModifyProfileActivity.class);
                modifyIntent.putExtra("nickname", mNickname);
                modifyIntent.putExtra("mbti", mMbti);
                startActivity(modifyIntent);
                break;
            case R.id.my_page_tv_edit_test_result:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent goTestResults = new Intent(mContext, TestResultsActivity.class);
                startActivity(goTestResults);
                break;
            case R.id.my_page_tv_my_posts:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent myPosts = new Intent(mContext, MyPostsActivity.class);
                startActivity(myPosts);
                break;
            case R.id.my_page_tv_my_commented_posts:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent myCommentPosts = new Intent(mContext, MyCommentPostsActivity.class);
                startActivity(myCommentPosts);
                break;
            case R.id.my_page_tv_license:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent goLicense = new Intent(mContext, LicenseActivity.class);
                startActivity(goLicense);
                break;
        }
    }

    @Override
    public void tryGetUserInfoSuccess(String nick, String mbti) {
        mMbti = mbti.toUpperCase();
        mNickname = nick;

        if (mbti.equals("intj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_intj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("고질적 완벽주의자");
        } else if (mbti.equals("infj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("예수와 히틀러 그 사이쯤");
        } else if (mbti.equals("istj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_istj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("믿음직스러운 모범생");
        } else if (mbti.equals("istp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_istp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("척척박사 만능 재주꾼");
        } else if (mbti.equals("intp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_intp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("생각을 멈출 수 없어");
        } else if (mbti.equals("infp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("중2병 방구석 예술가");
        } else if (mbti.equals("isfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("고질적 완벽주의자");
        } else if (mbti.equals("isfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_isfp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("만사가 귀찮은 거북이");
        }else if (mbti.equals("entj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_entj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("제군들은 나를 따르라");
        }else if (mbti.equals("enfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_enfj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("인싸 오브 파워인싸");
        }else if (mbti.equals("estj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_estj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("워커홀릭 알파고");
        }else if (mbti.equals("estp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_estp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("다같이 복세편살합시다");
        }else if (mbti.equals("entp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_entp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("좀 재수없지만 좀 재미있어");
        }else if (mbti.equals("enfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_enfp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("사는게 너무 좋아! 짜릿해! 새로워!");
        }else if (mbti.equals("esfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_esfj).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("일 벌이는게 제일 좋더라");
        }else if (mbti.equals("esfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_esfp).into(mMyPageIvMbti);
            mMyPageTvCharacteristic.setText("핸들이 고장 난 8t 트럭");
        }

        mMyPageTvNickname.setText(nick);

        mResultList.clear();
        final MyPageService testService = new MyPageService(this, mContext);
        testService.tryGetTestResults();
    }

    @Override
    public void tryGetUserInfoFailure(String message) {
        ((MainActivity) mContext).hideProgressDialog();
        ((MainActivity) mContext).showCustomToastShort(message);
    }

    @Override
    public void tryGetTestResultSuccess(ArrayList<TestResult> results) {
        mResultList.addAll(results);
        mMyPageAdapter.notifyDataSetChanged();

        if (results.size() == 0) {
            mMyTvPageNoResult.setVisibility(View.VISIBLE);
        } else mMyTvPageNoResult.setVisibility(View.GONE);

        ((MainActivity) mContext).hideProgressDialog();
    }

    @Override
    public void tryGetTestResultFailure(String message) {
        ((MainActivity) mContext).hideProgressDialog();
        ((MainActivity) mContext).showCustomToastShort(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        getMyPageInfo();
    }
}
