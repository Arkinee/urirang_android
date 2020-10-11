package com.makeus.urirang.android.src.main.fragments.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.MainActivity;

public class WithYouFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private TextView mHomeWithYouTvTitle;
    private TextView mHomeWithYouTvPeopleParticipate;
    private ImageView mHomeWithYouIvMain;

    public WithYouFragment() {
    }

    public WithYouFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_with_you, container, false);

        mHomeWithYouTvTitle = view.findViewById(R.id.fragment_home_with_you_tv_title);
        mHomeWithYouTvPeopleParticipate = view.findViewById(R.id.fragment_home_with_you_tv_people_participate);
        mHomeWithYouIvMain = view.findViewById(R.id.fragment_home_with_you_iv_main);
        CardView homeCardWithYou = view.findViewById(R.id.fragment_home_with_you_card);

        homeCardWithYou.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setting(String title, int commentNum, String imageUrl) {
        mHomeWithYouTvTitle.setText(title);
        mHomeWithYouTvPeopleParticipate.setText(String.valueOf(commentNum).concat("명 참여중"));
        Glide.with(mContext).load(imageUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeWithYouIvMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_with_you_card:
                ((MainActivity)mContext).setMainBoardWithYou();
                break;
        }
    }
}
