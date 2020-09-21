package com.makeus.urirang.android.src.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.main.fragments.board.BoardFragment;
import com.makeus.urirang.android.src.main.fragments.home.HomeFragment;
import com.makeus.urirang.android.src.main.fragments.mypage.MyPageFragment;
import com.makeus.urirang.android.src.main.fragments.worldCup.WorldCupFragment;

public class MainActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private FrameLayout mMainFrame;

    private ImageView mMainIvHome;
    private ImageView mMainIvBoard;
    private ImageView mMainIvWorldCup;
    private ImageView mMainIvMyPage;

    private HomeFragment mHomeFragment;
    private BoardFragment mBoardFragment;
    private WorldCupFragment mWorldCupFragment;
    private MyPageFragment mMyPageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    void initialize() {

        mMainFrame = findViewById(R.id.main_frame);
        mMainIvHome = findViewById(R.id.main_iv_home);
        mMainIvBoard = findViewById(R.id.main_iv_board);
        mMainIvWorldCup = findViewById(R.id.main_iv_world_cup);
        mMainIvMyPage = findViewById(R.id.main_iv_mypage);

        mHomeFragment = new HomeFragment();
        mBoardFragment = new BoardFragment();
        mWorldCupFragment = new WorldCupFragment();
        mMyPageFragment = new MyPageFragment();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_frame, mHomeFragment).commitAllowingStateLoss();

    }

    public void mainOnClick(View view) {
        mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.main_iv_home:
                setUnselectedImage();
                mMainIvHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home_selected));
                mFragmentTransaction.replace(R.id.main_frame, mHomeFragment).commitAllowingStateLoss();
                break;
            case R.id.main_iv_board:
                setUnselectedImage();
                mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_board_selected));
                mFragmentTransaction.replace(R.id.main_frame, mBoardFragment).commitAllowingStateLoss();
                break;
            case R.id.main_iv_world_cup:
                setUnselectedImage();
                mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_world_cup_selected));
                mFragmentTransaction.replace(R.id.main_frame, mWorldCupFragment).commitAllowingStateLoss();
                break;
            case R.id.main_iv_mypage:
                setUnselectedImage();
                mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mypage_unselected));
                mFragmentTransaction.replace(R.id.main_frame, mMyPageFragment).commitAllowingStateLoss();
                break;

        }
    }

    void setUnselectedImage() {

        mMainIvHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home_unselected));
        mMainIvBoard.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_board_unselected));
        mMainIvWorldCup.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_world_cup_unselected));
        mMainIvMyPage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_mypage_unselected));

    }
}