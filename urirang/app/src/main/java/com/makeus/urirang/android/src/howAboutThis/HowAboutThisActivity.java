package com.makeus.urirang.android.src.howAboutThis;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.dialog.BottomSheetHowAboutThisFilterDialog;
import com.makeus.urirang.android.src.dialog.BottomSheetMbtiFilterDialog;
import com.makeus.urirang.android.src.howAboutThis.adapter.HowAboutThisAdapter;
import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThis;
import com.makeus.urirang.android.src.main.MainActivity;

import java.util.ArrayList;

public class HowAboutThisActivity extends BaseActivity implements BottomSheetHowAboutThisFilterDialog.BottomSheetFilterOptionListener{

    private Context mContext;
    private HowAboutThisAdapter mHowAdapter;
    private ArrayList<HowAboutThis> mHowLists;

    private int mOption = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_this);

        mContext = this;
        mHowLists = new ArrayList<>();
        mHowAdapter = new HowAboutThisAdapter(this, mHowLists, new HowAboutThisAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        RecyclerView rvHowAboutThis = findViewById(R.id.how_about_this_rv);
        rvHowAboutThis.addItemDecoration(new DividerItemDecoration(rvHowAboutThis.getContext(), LinearLayoutManager.VERTICAL));
        rvHowAboutThis.setAdapter(mHowAdapter);
    }

    public void howOnClick(View view) {

        switch (view.getId()) {
            case R.id.how_about_this_iv_back_arrow:
                finish();
                break;
            case R.id.how_about_this_tv_filter:
                BottomSheetHowAboutThisFilterDialog dialog = new BottomSheetHowAboutThisFilterDialog(mContext, mOption);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "HowFilterDialog");
                break;
            case R.id.how_about_this_fab_write:

                break;
        }
    }

    @Override
    public void applyFilterCreatedAt(int option) {

        mOption = option;
    }

    @Override
    public void applyFilterPopularity(int option) {
        mOption = option;
    }
}