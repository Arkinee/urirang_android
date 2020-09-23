package com.makeus.urirang.android.src.main.fragments.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeTopPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private int mFragmentCount;
    private Context mContext;

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void removeFragment(int index) {
        mFragmentList.remove(index);
    }

    public void replaceFragment(int index, Fragment fragment){
        mFragmentList.set(index, fragment);
    }

    public HomeTopPagerAdapter(FragmentManager fm, int fragmentCount, Context context) {
        super(fm);
        this.mFragmentCount = fragmentCount;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
