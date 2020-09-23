package com.makeus.urirang.android.src.main.fragments.home.fragments;

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

import com.makeus.urirang.android.R;

public class HallOfFameFragment extends Fragment implements View.OnClickListener {

    private TextView mHomeWithYouTvTitle;
    private TextView mHomeWithYouTvPeopleParticipate;
    private ImageView mHomeWithYouIvMain;

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

        mHomeWithYouTvTitle.setText("가장 손절 잘하는\n유형은 무엇?");
        mHomeWithYouTvPeopleParticipate.setText("115명 참여중");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_with_you_card:
//                Intent goWithYouBoard = new Intent(getActivity(), );
                break;
        }
    }
}
