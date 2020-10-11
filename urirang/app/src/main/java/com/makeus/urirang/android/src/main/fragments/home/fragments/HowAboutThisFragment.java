package com.makeus.urirang.android.src.main.fragments.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.HowAboutThisActivity;

public class HowAboutThisFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private ImageView mHomeHowAboutThisIvMain1;
    private ImageView mHomeHowAboutThisIvMain2;
    private ImageView mHomeHowAboutThisIvMain3;
    private ImageView mHomeHowAboutThisIvMain4;

    public HowAboutThisFragment() {
    }

    public HowAboutThisFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_how_about_this, container, false);

        mHomeHowAboutThisIvMain1 = view.findViewById(R.id.fragment_home_how_about_this_iv_main_1);
        mHomeHowAboutThisIvMain2 = view.findViewById(R.id.fragment_home_how_about_this_iv_main_2);
        mHomeHowAboutThisIvMain3 = view.findViewById(R.id.fragment_home_how_about_this_iv_main_3);
        mHomeHowAboutThisIvMain4 = view.findViewById(R.id.fragment_home_how_about_this_iv_main_4);

        CardView homeCardHallOfFame = view.findViewById(R.id.fragment_home_how_about_this_card);

        homeCardHallOfFame.setOnClickListener(this);

        return view;
    }

    public void imageSetting(String url1, String url2, String url3, String url4) {

        Glide.with(mContext).load(url1).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHowAboutThisIvMain1);
        Glide.with(mContext).load(url2).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHowAboutThisIvMain2);
        Glide.with(mContext).load(url3).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHowAboutThisIvMain3);
        Glide.with(mContext).load(url4).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHowAboutThisIvMain4);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_how_about_this_card:
                Intent goHowAboutThisIntent = new Intent(mContext, HowAboutThisActivity.class);
                startActivity(goHowAboutThisIntent);
                break;
        }
    }
}
