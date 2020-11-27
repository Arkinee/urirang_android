package com.makeus.urirang.android.src.main.fragments.home;

import android.content.Context;
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

import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.ApplicationClass;
import com.makeus.urirang.android.src.login.social.SocialLoginActivity;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.home.adapters.HomePostAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.HomeTopPagerAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.OtherTestAdapter;
import com.makeus.urirang.android.src.main.fragments.home.adapters.RelateContentAdapter;
import com.makeus.urirang.android.src.main.fragments.home.fragments.HallOfFameFragment;
import com.makeus.urirang.android.src.main.fragments.home.fragments.HowAboutThisFragment;
import com.makeus.urirang.android.src.main.fragments.home.fragments.WithYouFragment;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.models.HomePost;
import com.makeus.urirang.android.src.main.fragments.home.models.OtherTest;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;
import com.makeus.urirang.android.src.notice.NoticeActivity;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeActivityView {

    private TextView mHomeTvAppBarNick;
    private ImageView mHomeIvAppBarMbti;
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
    private HowAboutThisFragment mHowAboutThisFragment;

    private Context mContext;
    private boolean mDoubleClickFlag = false;

    public HomeFragment() {
    }

    public HomeFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView homeIvNotice = view.findViewById(R.id.home_iv_notice);

        mMainActivity = (MainActivity) getActivity();
        mHomeTvAppBarNick = view.findViewById(R.id.home_tv_appbar_nick);
        mHomeIvAppBarMbti = view.findViewById(R.id.home_iv_appbar_mbti);
        LinearLayout homeLinearGoWorldCup = view.findViewById(R.id.home_linear_go_world_cup);

        ImageView homeIvUrirang = view.findViewById(R.id.home_iv_urirang);
        homeIvUrirang.setOnClickListener(this);

        ImageView homeIvGoWithAll = view.findViewById(R.id.home_iv_go_with_all);
        homeIvGoWithAll.setOnClickListener(this);

        mHomeViewPagerTop = view.findViewById(R.id.home_viewpager_top);
        mHomeRvPost = view.findViewById(R.id.home_rv_post);
        mHomeRvOtherTest = view.findViewById(R.id.home_rv_other_test);
        mHomeViewPagerRelateContents = view.findViewById(R.id.home_view_pager_relate_contents);

        mIndicator = view.findViewById(R.id.home_indicator);

        mRelateList = new ArrayList<>();
        mPostList = new ArrayList<>();
        mTestList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mWithYouFragment = new WithYouFragment(mMainActivity);
        mHallOfFameFragment = new HallOfFameFragment(mMainActivity);
        mHowAboutThisFragment = new HowAboutThisFragment(mMainActivity);

        // 상단 viewpager
        mFragmentList.add(mWithYouFragment);
        mFragmentList.add(mHallOfFameFragment);
        mFragmentList.add(mHowAboutThisFragment);
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

        mHomeRvPost.addItemDecoration(new DividerItemDecoration(mHomeRvPost.getContext(), new LinearLayoutManager(mMainActivity).getOrientation()));
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
        mRelateAdapter = new RelateContentAdapter(mMainActivity, 3);
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

        mTestList.add(new OtherTest("에고그램", "https://egogramtest.kr/"));
        mTestList.add(new OtherTest("에니어그램", "https://enneagram-app.appspot.com/quest"));
        mTestList.add(new OtherTest("MGRAM", "https://mgram.me/ko/"));
        mTestList.add(new OtherTest("8기능", "http://jung.test.typologycentral.com/"));

        mOtherTestAdapter.notifyDataSetChanged();

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
//                ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, "").apply();
//                Intent goLoginIntent = new Intent(mContext, SocialLoginActivity.class);
//                goLoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(goLoginIntent);
//                ((MainActivity) mContext).finish();
                break;
            case R.id.home_iv_notice:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent notice = new Intent(mMainActivity, NoticeActivity.class);
                startActivity(notice);
                break;
            case R.id.home_linear_go_world_cup:
                ((MainActivity) mContext).setMainWorldCup();
                break;
            case R.id.home_iv_go_with_all:
                ((MainActivity) mContext).setMainBoardWithALl();
                break;
        }
    }

    @Override
    public void tryGetUserInfoSuccess(String nick, String mbti, int userId) {
        mHomeTvAppBarNick.setText(nick.concat(","));

        if (mbti.equals("intj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_intj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("infj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("istj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_istj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("istp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_istp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("intp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_intp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("infp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("isfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_infp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("isfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_isfp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("entj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_entj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("enfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_enfj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("estj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_estj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("estp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_estp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("entp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_entp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("enfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_enfp).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("esfj")) {
            Glide.with(mContext).load(R.drawable.ic_wording_esfj).into(mHomeIvAppBarMbti);
        } else if (mbti.equals("esfp")) {
            Glide.with(mContext).load(R.drawable.ic_wording_esfp).into(mHomeIvAppBarMbti);
        }

        sSharedPreferences.edit().putInt("userId", userId).apply();

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

        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetCurrentTopic();
    }

    @Override
    public void tryGetMbtiRelateContentsFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void tryGetCurrentTopicSuccess(String title, int commentNum, String imageUrl) {
        mWithYouFragment.setting(title, commentNum, imageUrl);

        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetTopicHistoryImages();
    }

    @Override
    public void tryGetCurrentTopicFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void tryGetTopicHistoryImagesSuccess(ArrayList<String> result) {
        mHallOfFameFragment.imageSetting(result.get(0), result.get(1), result.get(2), result.get(3));

        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetHowAboutThisImages();
    }

    @Override
    public void tryGetTopicHistoryImagesFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void tryGetWithAllBest3Success(ArrayList<HomePost> results) {
        mPostList.addAll(results);
        mHomePostAdapter.notifyDataSetChanged();
    }

    @Override
    public void tryGetWithAllBest3Failure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void tryGetHowAboutThisImagesSuccess(ArrayList<String> result) {
        mHowAboutThisFragment.imageSetting(result.get(0), result.get(1), result.get(2), result.get(3));

        mPostList.clear();
        final HomeService homeService = new HomeService(this, mMainActivity);
        homeService.tryGetWithAllBest3();
    }

    @Override
    public void tryGetHowAboutThisImagesFailure(String message) {
        mMainActivity.hideProgressDialog();
        mMainActivity.showCustomToastShort(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mHomeViewPagerTop.setCurrentItem(0);
        getUserInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMainActivity.hideProgressDialog();
    }
}
