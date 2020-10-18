package com.makeus.urirang.android.src.main.fragments.home.fragments;

import android.content.Intent;
import android.net.Uri;
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
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.hallOfFame.HallOfFameActivity;
import com.makeus.urirang.android.src.main.MainActivity;

public class RelateContentFragment extends Fragment implements View.OnClickListener {

    private ImageView mHomeRelateContentIvMain;
    private String link;
    private String imageUrl;
    private boolean mDoubleClick = false;

    public RelateContentFragment() {

    }

    public RelateContentFragment(String link, String iamgeUrl) {
        this.link = link;
        this.imageUrl = iamgeUrl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_relate_content, container, false);

        mHomeRelateContentIvMain = view.findViewById(R.id.fragment_home_relate_content_iv);
        mHomeRelateContentIvMain.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (imageUrl != null && !imageUrl.equals(""))
            Glide.with(this).load(imageUrl).into(mHomeRelateContentIvMain);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_relate_content_iv:
                if (mDoubleClick) return;
                mDoubleClick = true;

                ((MainActivity) getActivity()).showProgressDialog();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(link);
                urlIntent.setData(uri);
                startActivity(urlIntent);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).hideProgressDialog();
    }
}
