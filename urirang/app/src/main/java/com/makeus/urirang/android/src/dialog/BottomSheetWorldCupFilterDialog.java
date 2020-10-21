package com.makeus.urirang.android.src.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeus.urirang.android.R;

public class BottomSheetWorldCupFilterDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private Context mContext;
    private BottomSheetFilterOptionListener mListener;
    private int mOption = 1;

    private ImageView ivCreatedAtCheck;
    private ImageView ivPopularityCheck;

    public BottomSheetWorldCupFilterDialog() {
    }

    public BottomSheetWorldCupFilterDialog(Context context, int option) {
        this.mContext = context;
        this.mOption = option;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_how_about_this_filter, container, false);

        ivCreatedAtCheck = view.findViewById(R.id.bottom_sheet_how_about_this_filter_created_at_iv_check);
        ivPopularityCheck = view.findViewById(R.id.bottom_sheet_how_about_this_filter_popularity_iv_check);

        LinearLayout linearTop = view.findViewById(R.id.bottom_sheet_how_about_this_linear_top);
        LinearLayout linearBottom = view.findViewById(R.id.bottom_sheet_how_about_this_linear_bottom);

        if (mOption == 1) {
            ivCreatedAtCheck.setVisibility(View.VISIBLE);
        } else if (mOption == 2) {
            ivPopularityCheck.setVisibility(View.VISIBLE);
        }

        linearTop.setOnClickListener(this);
        linearBottom.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_sheet_how_about_this_linear_top:
                mOption = 1;
                mListener.applyFilterCreatedAt(mOption);
                ivCreatedAtCheck.setVisibility(View.VISIBLE);
                dismiss();
                break;
            case R.id.bottom_sheet_how_about_this_linear_bottom:
                mOption = 2;
                mListener.applyFilterPopularity(mOption);
                ivPopularityCheck.setVisibility(View.VISIBLE);
                dismiss();
                break;
        }

    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    public interface BottomSheetFilterOptionListener {
        void applyFilterCreatedAt(int option);

        void applyFilterPopularity(int option);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetFilterOptionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString().concat(" must implement BottomSheetListener"));
        }

    }

}
