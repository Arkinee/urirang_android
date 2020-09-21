package com.makeus.urirang.android.src.main.fragments.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.home.adapters.RelateContentAdapter;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeActivityView {

    private TextView mHomeTvAppBarNick;
    private TextView mHomeTvAppBarMbti;
    private MainActivity mMainActivity;

    private RecyclerView mHomeRvPost;
    private RecyclerView mHomeRvOtherTest;
    private RecyclerView mHomeRvRelateContents;

    private ArrayList<RelateContent> mRelateList;

    private RelateContentAdapter mRelateAdapter;

    private boolean mDoubleClickFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView homeIvNotice = view.findViewById(R.id.home_iv_notice);

        mMainActivity = (MainActivity) getActivity();
        mHomeTvAppBarNick = view.findViewById(R.id.home_tv_appbar_nick);
        mHomeTvAppBarMbti = view.findViewById(R.id.home_tv_appbar_mbti);
        LinearLayout homeLinearGoWorldCup = view.findViewById(R.id.home_linear_go_world_cup);

        mHomeRvPost = view.findViewById(R.id.home_rv_post);
        mHomeRvOtherTest = view.findViewById(R.id.home_rv_other_test);
        mHomeRvRelateContents = view.findViewById(R.id.home_rv_relate_contents);

        mRelateList = new ArrayList<>();
        mRelateAdapter = new RelateContentAdapter(mMainActivity, mRelateList, new RelateContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(mRelateList.get(pos).getLink());
                urlIntent.setData(uri);
                startActivity(urlIntent);
            }
        });

        homeIvNotice.setOnClickListener(this);
        homeLinearGoWorldCup.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHomeRvRelateContents.setAdapter(mRelateAdapter);
        mHomeRvRelateContents.setLayoutManager(new LinearLayoutManager(mMainActivity, LinearLayoutManager.HORIZONTAL, false));
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mHomeRvRelateContents);
        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int activeColor = ContextCompat.getColor(mMainActivity, R.color.colorBlack);
        final int inactiveColor = ContextCompat.getColor(mMainActivity, R.color.colorBasicBlack);
        mHomeRvRelateContents.addItemDecoration(new DotIndicatorDecoration(radius, radius * 4, dotsHeight, inactiveColor, activeColor));
        new PagerSnapHelper().attachToRecyclerView(mHomeRvRelateContents);

        mRelateList.add(new RelateContent("https://comic.naver.com/index.nhn", "https://lh3.googleusercontent.com/proxy/ObJKSCZoWKuKueu_xtvebHFa8oVWdirqMVsybYwostJymtylLjTAd0vvo-noOZ41zAC2kegVe5B8TL9I4Hr_8MfL7qk-rAD58FZ4aKCQoA26788oXbGjzdJWM_t5TmVlh9FiXCiJ075NlqEFYFDIBQ8IvPvsT4KC5b7IJwjOBADaW3Qn_yI"));
        mRelateList.add(new RelateContent("https://comic.naver.com/index.nhn", "https://lh3.googleusercontent.com/proxy/ObJKSCZoWKuKueu_xtvebHFa8oVWdirqMVsybYwostJymtylLjTAd0vvo-noOZ41zAC2kegVe5B8TL9I4Hr_8MfL7qk-rAD58FZ4aKCQoA26788oXbGjzdJWM_t5TmVlh9FiXCiJ075NlqEFYFDIBQ8IvPvsT4KC5b7IJwjOBADaW3Qn_yI"));
        mRelateList.add(new RelateContent("https://comic.naver.com/index.nhn", "https://lh3.googleusercontent.com/proxy/ObJKSCZoWKuKueu_xtvebHFa8oVWdirqMVsybYwostJymtylLjTAd0vvo-noOZ41zAC2kegVe5B8TL9I4Hr_8MfL7qk-rAD58FZ4aKCQoA26788oXbGjzdJWM_t5TmVlh9FiXCiJ075NlqEFYFDIBQ8IvPvsT4KC5b7IJwjOBADaW3Qn_yI"));
        mRelateList.add(new RelateContent("https://comic.naver.com/index.nhn", "https://lh3.googleusercontent.com/proxy/ObJKSCZoWKuKueu_xtvebHFa8oVWdirqMVsybYwostJymtylLjTAd0vvo-noOZ41zAC2kegVe5B8TL9I4Hr_8MfL7qk-rAD58FZ4aKCQoA26788oXbGjzdJWM_t5TmVlh9FiXCiJ075NlqEFYFDIBQ8IvPvsT4KC5b7IJwjOBADaW3Qn_yI"));

        mRelateAdapter.notifyDataSetChanged();

        getUserInfo();

    }

    void getUserInfo() {
        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetUserInfo();
        mMainActivity.showProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_iv_urirang:
                break;
            case R.id.home_iv_notice:
                break;
            case R.id.home_linear_go_world_cup:
                break;
        }
    }

    @Override
    public void tryGetUserInfoSuccess(String nick, String mbti) {
        mMainActivity.hideProgressDialog();
        mHomeTvAppBarNick.setText(nick.concat(","));
        mHomeTvAppBarMbti.setText(mbti.toUpperCase());
    }

    @Override
    public void tryGetUserInfoFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
    }
}
