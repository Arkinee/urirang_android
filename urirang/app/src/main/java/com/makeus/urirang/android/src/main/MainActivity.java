package com.makeus.urirang.android.src.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.main.adapter.MainFragmentPagerAdapter;
import com.makeus.urirang.android.src.main.fragments.board.BoardFragment;
import com.makeus.urirang.android.src.main.fragments.home.HomeFragment;
import com.makeus.urirang.android.src.main.fragments.mypage.MyPageFragment;
import com.makeus.urirang.android.src.main.fragments.worldCup.WorldCupFragment;
import com.makeus.urirang.android.src.main.interfaces.MainActivityView;

import java.util.HashMap;

import static com.makeus.urirang.android.src.ApplicationClass.FCM_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView, BottomSheetMbtiFilterDialog.BottomSheetListener {

    private MainFragmentPagerAdapter mMainPagerAdapter;
    private MainViewPager mMainViewPager;

    private ImageView mMainIvHome;
    private ImageView mMainIvBoard;
    private ImageView mMainIvWorldCup;
    private ImageView mMainIvMyPage;

    private HomeFragment mHomeFragment;
    private BoardFragment mBoardFragment;
    private WorldCupFragment mWorldCupFragment;
    private MyPageFragment mMyPageFragment;

    private long mOnBackClickTime = 0;
    private int mFragmentFlag = 1;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        refreshFcmToken();

    }

    void refreshFcmToken() {
        final MainService fcmService = new MainService(this, this);
        HashMap<String, Object> params = new HashMap<>();
        params.put("fcmToken", sSharedPreferences.getString(FCM_TOKEN, ""));
        fcmService.tryPostFcmToken(params);
    }

    void initialize() {

        mContext = this;
        mMainIvHome = findViewById(R.id.main_iv_home);
        mMainIvBoard = findViewById(R.id.main_iv_board);
        mMainIvWorldCup = findViewById(R.id.main_iv_world_cup);
        mMainIvMyPage = findViewById(R.id.main_iv_mypage);

        mHomeFragment = new HomeFragment();
        mBoardFragment = new BoardFragment(this);
        mWorldCupFragment = new WorldCupFragment();
        mMyPageFragment = new MyPageFragment(this);

        mMainViewPager = findViewById(R.id.main_view_pager);
        mMainPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), 4, this);

        mMainPagerAdapter.addFragment(mHomeFragment);
        mMainPagerAdapter.addFragment(mBoardFragment);
        mMainPagerAdapter.addFragment(mWorldCupFragment);
        mMainPagerAdapter.addFragment(mMyPageFragment);

        mMainViewPager.setAdapter(mMainPagerAdapter);
        setViewPagerListener();

    }

    public void mainOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_iv_home:
                if (mFragmentFlag == 1) break;
                setUnselectedImage();
                mMainViewPager.setCurrentItem(0);
                mFragmentFlag = 1;
                mMainIvHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home_selected));
                break;
            case R.id.main_iv_board:
                if (mFragmentFlag == 2) break;
                setUnselectedImage();
                mMainViewPager.setCurrentItem(1);
                mFragmentFlag = 2;
                mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_board_selected));
                break;
            case R.id.main_iv_world_cup:
                if (mFragmentFlag == 3) break;
                setUnselectedImage();
                mMainViewPager.setCurrentItem(2);
                mFragmentFlag = 3;
                mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_world_cup_selected));
                break;
            case R.id.main_iv_mypage:
                if (mFragmentFlag == 4) break;
                setUnselectedImage();
                mMainViewPager.setCurrentItem(3);
                mFragmentFlag = 4;
                mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mypage_selected));
                break;

        }
    }

    public void setMainBoardWithYou(){

        setUnselectedImage();
        mMainViewPager.setCurrentItem(1);
        mFragmentFlag = 2;
        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_board_selected));
        mBoardFragment.setItemWithYou();


    }

    public void setViewPagerListener() {

        mMainViewPager.setOffscreenPageLimit(4);
        mMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mFragmentFlag = 1;
                        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home_selected));
                        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_board_unselected));
                        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_world_cup_unselected));
                        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mypage_unselected));
                        mMainViewPager.setScrollEnable(true);
                        break;
                    case 1:
                        mFragmentFlag = 2;
                        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home_unselected));
                        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_board_selected));
                        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_world_cup_unselected));
                        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mypage_unselected));
                        mMainViewPager.setScrollEnable(true);
                        break;
                    case 2:
                        mFragmentFlag = 3;
                        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home_unselected));
                        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_board_unselected));
                        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_world_cup_selected));
                        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mypage_unselected));
                        mMainViewPager.setScrollEnable(true);
                        break;
                    case 3:
                        mFragmentFlag = 4;
                        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home_unselected));
                        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_board_unselected));
                        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_world_cup_unselected));
                        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mypage_selected));
                        mMainViewPager.setScrollEnable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    void setUnselectedImage() {

        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home_unselected));
        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_board_unselected));
        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_world_cup_unselected));
        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mypage_unselected));

    }

    @Override
    public void onBackPressed() {

        long curTime = System.currentTimeMillis();
        long gapTime = curTime - mOnBackClickTime;

        if (0 <= gapTime && 2000 >= gapTime) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            mOnBackClickTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void tryPostFcmTokenSuccess() {

    }

    @Override
    public void tryPostFcmTokenFailure() {

    }

    @Override
    public void onFilterApply(String mbti) {
        mBoardFragment.filterWithAll(mbti);
    }
}