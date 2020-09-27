package com.makeus.urirang.android.src.main.fragments.board.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.makeus.urirang.android.src.main.fragments.board.fragments.BoardWithAllFragment;
import com.makeus.urirang.android.src.main.fragments.board.fragments.BoardWithYouFragment;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void replaceFragment(Fragment fragment, String title, int index) {
        mFragmentList.set(index, fragment);
        mFragmentTitleList.set(index, title);
    }

    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public SectionAdapter(FragmentManager fm) {
        super(fm);
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
        if (object instanceof BoardWithAllFragment) {
            return 0;
        } else if (object instanceof BoardWithYouFragment) {
            return 1;
        }
        return POSITION_NONE;
        //else return super.getItemPosition(object);
    }

}
