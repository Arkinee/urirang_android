package com.makeus.urirang.android.src;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerHorizontalDecoration extends RecyclerView.ItemDecoration {

    private final int divWidth;


    public RecyclerHorizontalDecoration(Context context, int divWidth) {
        this.divWidth = toDP(context, divWidth);
    }

    private int toDP(Context context, int value){
        return (int) TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)

            outRect.right = divWidth;

    }

}
