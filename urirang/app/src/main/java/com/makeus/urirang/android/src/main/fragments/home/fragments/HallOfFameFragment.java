package com.makeus.urirang.android.src.main.fragments.home.fragments;

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

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.hallOfFame.HallOfFameActivity;

public class HallOfFameFragment extends Fragment implements View.OnClickListener {

    private ImageView mHomeHallOfFameIvMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_with_you, container, false);

        mHomeHallOfFameIvMain = view.findViewById(R.id.fragment_home_hall_of_fame_iv_main);
        CardView homeCardHallOfFame = view.findViewById(R.id.fragment_home_hall_of_fame_card);

        homeCardHallOfFame.setOnClickListener(this);

        return view;
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
