package com.makeus.urirang.android.src.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MainViewPager extends ViewPager {

    boolean enable;
    public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        enable = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enable) return super.onInterceptTouchEvent(ev);
        else{
            if (ev.getAction() == MotionEvent.ACTION_MOVE);
            else if(super.onInterceptTouchEvent(ev)) super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (enable) return super.onTouchEvent(ev);
        else return ev.getAction() != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev);
    }

    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if(v != this && v instanceof ViewPager) {
            int currentItem = ((ViewPager) v).getCurrentItem();
            int countItem = ((ViewPager) v).getAdapter().getCount();
            if((currentItem==(countItem-1) && dx<0) || (currentItem==0 && dx>0)){
                return false;
            }
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    public void setScrollEnable(boolean enable){
        this.enable = enable;
    }
}
