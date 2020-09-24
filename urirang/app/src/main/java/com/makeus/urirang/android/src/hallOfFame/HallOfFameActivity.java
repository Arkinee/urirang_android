package com.makeus.urirang.android.src.hallOfFame;

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

public class HallOfFameActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

    }

    public void hallOnClick(View view) {

        switch (view.getId()) {
            case R.id.hall_of_fame_iv_back_arrow:
                finish();
                break;
            case R.id.hall_of_fame_tv_filter:
                break;
        }
    }
}