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
import com.makeus.urirang.android.src.hallOfFame.HallOfFameActivity;

public class HallOfFameFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private ImageView mHomeHallOfFameIvMain1;
    private ImageView mHomeHallOfFameIvMain2;
    private ImageView mHomeHallOfFameIvMain3;
    private ImageView mHomeHallOfFameIvMain4;

    public HallOfFameFragment() {
    }

    public HallOfFameFragment(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_hall_of_fame, container, false);

        mHomeHallOfFameIvMain1 = view.findViewById(R.id.fragment_home_hall_of_fame_iv_main_1);
        mHomeHallOfFameIvMain2 = view.findViewById(R.id.fragment_home_hall_of_fame_iv_main_2);
        mHomeHallOfFameIvMain3 = view.findViewById(R.id.fragment_home_hall_of_fame_iv_main_3);
        mHomeHallOfFameIvMain4 = view.findViewById(R.id.fragment_home_hall_of_fame_iv_main_4);

        CardView homeCardHallOfFame = view.findViewById(R.id.fragment_home_hall_of_fame_card);

        homeCardHallOfFame.setOnClickListener(this);

        return view;
    }

   public void imageSetting(String url1, String url2, String url3, String url4) {

        Glide.with(mContext).load(url1).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHallOfFameIvMain1);
        Glide.with(mContext).load(url2).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHallOfFameIvMain2);
        Glide.with(mContext).load(url3).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHallOfFameIvMain3);
        Glide.with(mContext).load(url4).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHomeHallOfFameIvMain4);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_hall_of_fame_card:
                Intent goHallOfFameIntent = new Intent(getActivity(), HallOfFameActivity.class);
                startActivity(goHallOfFameIntent);
                break;
        }
    }
}
