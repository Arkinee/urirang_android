package com.makeus.urirang.android.src.main.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.fragments.RelateContentFragment;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;
import java.util.List;

public class RelateContentAdapter extends FragmentStateAdapter {

    private Context mContext;
    private List<Fragment> mRelateList;
    private int mCount;

    public RelateContentAdapter(FragmentActivity fa, int count){
        super(fa);
        mCount = count;
    }

    public void addFragment(Fragment fragment){
        mRelateList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index == 0) return new RelateContentFragment("https://www.youtube.com/watch?v=eAZG7BuXnOo&ab_channel=OTR", "https://woorirang-dev.s3.ap-northeast-2.amazonaws.com/mbtiContents/%EF%BF%BD%08%EF%BF%BD%20%18%EF%BF%BDD%20L%20%EF%BF%BD%EF%BF%BD_woorirang_1602032348265.png");
        else if(index == 1) return new RelateContentFragment("https://m.blog.naver.com/94cs/221810776769", "https://woorirang-dev.s3.ap-northeast-2.amazonaws.com/mbtiContents/%EF%BF%BDLX%208%20H%EF%BF%BD%1C_woorirang_1602032382314.png");
        else if (index == 2) return new RelateContentFragment("https://comic.naver.com/webtoon/list.nhn?titleId=751013&no=24&weekday=wed", "https://woorirang-dev.s3.ap-northeast-2.amazonaws.com/mbtiContents/%60%EF%BF%BDX%20mbti_woorirang_1602032407223.png");
        else return null;
    }

    public int getRealPosition(int position) {return position % mCount;}
}
