package com.makeus.urirang.android.src.main.fragments.board;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.MainActivity;
import com.makeus.urirang.android.src.main.fragments.board.adapters.SectionAdapter;
import com.makeus.urirang.android.src.main.fragments.board.fragments.BoardWithAllFragment;
import com.makeus.urirang.android.src.main.fragments.board.fragments.BoardWithYouFragment;

public class BoardFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private TabLayout mBoardTab;
    private SectionAdapter mSectionAdapter;
    private ViewPager mBoardViewPager;

    private BoardWithAllFragment mWithAllFragment;
    private BoardWithYouFragment mWithYouFragment;

    private Context mContext;

    public BoardFragment(){}

    public BoardFragment(Context context){
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        mBoardTab = view.findViewById(R.id.fragment_board_tab);
        mBoardViewPager = view.findViewById(R.id.fragment_board_view_pager);
        mSectionAdapter = new SectionAdapter(((MainActivity)mContext).getSupportFragmentManager());

        mWithAllFragment = new BoardWithAllFragment(mContext);
        mWithYouFragment = new BoardWithYouFragment(mContext);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSectionAdapter.addFragment(mWithAllFragment, "모두랑");
        mSectionAdapter.addFragment(mWithYouFragment, "너희랑");

        mBoardViewPager.setAdapter(mSectionAdapter);
        mBoardTab.setupWithViewPager(mBoardViewPager);
        mBoardViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
