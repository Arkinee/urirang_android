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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.ApplicationClass;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.home.adapters.HomePostAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.HomeTopPagerAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.OtherTestAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.RelateContentAdapter;
import com.makeus.urirang.android.src.main.fragments.home.fragments.HallOfFameFragment;
import com.makeus.urirang.android.src.main.fragments.home.fragments.WithYouFragment;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.models.HomePost;
import com.makeus.urirang.android.src.main.fragments.home.models.OtherTest;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeActivityView {

    private TextView mHomeTvAppBarNick;
    private TextView mHomeTvAppBarMbti;
    private MainActivity mMainActivity;

    private ViewPager mHomeViewPagerTop;
    private RecyclerView mHomeRvPost;
    private RecyclerView mHomeRvOtherTest;
    private ViewPager2 mHomeViewPagerRelateContents;

    private ArrayList<RelateContent> mRelateList;
    private ArrayList<HomePost> mPostList;
    private ArrayList<OtherTest> mTestList;
    private ArrayList<Fragment> mFragmentList;

    private RelateContentAdapter mRelateAdapter;
    private HomeTopPagerAdapter mHomeTopPagerAdapter;
    private HomePostAdapter mHomePostAdapter;
    private OtherTestAdapter mOtherTestAdapter;

    private CircleIndicator3 mIndicator;

    private WithYouFragment mWithYouFragment;
    private HallOfFameFragment mHallOfFameFragment;

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

        ImageView homeIvUrirang = view.findViewById(R.id.home_iv_urirang);
        homeIvUrirang.setOnClickListener(this);

        mHomeViewPagerTop = view.findViewById(R.id.home_viewpager_top);
        mHomeRvPost = view.findViewById(R.id.home_rv_post);
        mHomeRvOtherTest = view.findViewById(R.id.home_rv_other_test);
        mHomeViewPagerRelateContents = view.findViewById(R.id.home_view_pager_relate_contents);

        mIndicator = view.findViewById(R.id.home_indicator);

        mRelateList = new ArrayList<>();
        mPostList = new ArrayList<>();
        mTestList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mWithYouFragment = new WithYouFragment();
        mHallOfFameFragment = new HallOfFameFragment();

        // 상단 viewpager
        mFragmentList.add(mWithYouFragment);
        mFragmentList.add(mHallOfFameFragment);
        mHomeTopPagerAdapter = new HomeTopPagerAdapter(mMainActivity.getSupportFragmentManager(), mFragmentList, 3, mMainActivity);

        mHomeViewPagerTop.setAdapter(mHomeTopPagerAdapter);
        mHomeViewPagerTop.setOffscreenPageLimit(3);
        mHomeViewPagerTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int dpValue = 17;
        int leftMargin = 20;
        int previewValue = 125;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        int lMargin = (int) (leftMargin * d);
        int preview = (int) (previewValue * d);

        mHomeViewPagerTop.setClipToPadding(false);
        mHomeViewPagerTop.setPadding(lMargin, 0, preview, 0);
        mHomeViewPagerTop.setPageMargin(margin);

        // 인기 게시물
        mHomePostAdapter = new HomePostAdapter(mMainActivity, mPostList, new HomePostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        mHomeRvPost.addItemDecoration(new DividerItemDecoration(mHomeRvPost.getContext(), new LinearLayoutManager(getActivity()).getOrientation()));
        mHomeRvPost.setAdapter(mHomePostAdapter);

        // 각종 테스트
        mOtherTestAdapter = new OtherTestAdapter(mMainActivity, mTestList, new OtherTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(mTestList.get(pos).getLink());
                urlIntent.setData(uri);
                startActivity(urlIntent);
                mMainActivity.showProgressDialog();
            }
        });

        mHomeRvOtherTest.setLayoutManager(new LinearLayoutManager(mMainActivity, LinearLayoutManager.HORIZONTAL, false));
        mHomeRvOtherTest.setAdapter(mOtherTestAdapter);
        mHomeRvOtherTest.addItemDecoration(new RvSpaceDecoration(mMainActivity, 7));
        new PagerSnapHelper().attachToRecyclerView(mHomeRvOtherTest);

        // 관련 컨텐츠

        mRelateAdapter = new RelateContentAdapter(getActivity(), 3);
        mHomeViewPagerRelateContents.setAdapter(mRelateAdapter);

        mIndicator.setViewPager(mHomeViewPagerRelateContents);
        mIndicator.createIndicators(3, 0);
        mHomeViewPagerRelateContents.setOffscreenPageLimit(3);

        mHomeViewPagerRelateContents.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % 3);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mHomeViewPagerRelateContents.setCurrentItem(position);
                }
            }
        });

        // 리스너
        homeIvNotice.setOnClickListener(this);
        homeLinearGoWorldCup.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPostList.add(new HomePost("ENTJ", "Logan", "8/25", "몇년 주기로 검사해봐야 할까요?", 88, 156));
        mPostList.add(new HomePost("ENTJ", "Logan", "8/25", "몇년 주기로 검사해봐야 할까요?", 88, 156));
        mPostList.add(new HomePost("ENTJ", "Logan", "8/25", "몇년 주기로 검사해봐야 할까요?", 88, 156));

        mTestList.add(new OtherTest("에고그램", "https://egogramtest.kr/"));
        mTestList.add(new OtherTest("에니어그램", "https://enneagram-app.appspot.com/quest"));
        mTestList.add(new OtherTest("MGRAM", "https://mgram.me/ko/"));
        mTestList.add(new OtherTest("8기능", "http://jung.test.typologycentral.com/"));

        mOtherTestAdapter.notifyDataSetChanged();
        mHomePostAdapter.notifyDataSetChanged();

        getUserInfo();

    }

    public void getUserInfo() {
        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetUserInfo();
        mMainActivity.showProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_iv_urirang:
                ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, "").apply();
                Intent goLoginIntent = new Intent(getActivity(), SocialLoginActivity.class);
                goLoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goLoginIntent);
                ((MainActivity) getActivity()).finish();
                break;
            case R.id.home_iv_notice:
                break;
            case R.id.home_linear_go_world_cup:
                break;
        }
    }

    @Override
    public void tryGetUserInfoSuccess(String nick, String mbti) {
        mHomeTvAppBarNick.setText(nick.concat(","));
        mHomeTvAppBarMbti.setText(mbti.toUpperCase());

        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetMbtiRelateContents();
    }

    @Override
    public void tryGetUserInfoFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void tryGetMbtiRelateContentsSuccess(ArrayList<RelateContent> result) {
        mRelateList.addAll(result);
        mRelateAdapter.notifyDataSetChanged();
        mMainActivity.hideProgressDialog();
    }

    @Override
    public void tryGetMbtiRelateContentsFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mHomeViewPagerTop.setCurrentItem(0);
    }

    @Override
    public void onStop() {
        super.onStop();
        mMainActivity.hideProgressDialog();
    }
}
