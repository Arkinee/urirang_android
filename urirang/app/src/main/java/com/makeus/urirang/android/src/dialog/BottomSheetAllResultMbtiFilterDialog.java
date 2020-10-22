package com.makeus.urirang.android.src.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.MainActivity;

public class BottomSheetAllResultMbtiFilterDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private Context mContext;
    private BottomSheetListener mListener;
    private String mSelectedMbti = "";

    private TextView tvApply;

    private ImageView ivIntj;
    private ImageView ivInfj;
    private ImageView ivIstj;
    private ImageView ivIstp;

    private ImageView ivIntp;
    private ImageView ivInfp;
    private ImageView ivIsfj;
    private ImageView ivIsfp;

    private ImageView ivEntj;
    private ImageView ivEnfj;
    private ImageView ivEstj;
    private ImageView ivEstp;

    private ImageView ivEntp;
    private ImageView ivEnfp;
    private ImageView ivEsfj;
    private ImageView ivEsfp;

    private ImageView ivClose;

    public BottomSheetAllResultMbtiFilterDialog() {
    }

    public BottomSheetAllResultMbtiFilterDialog(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;

                FrameLayout bottomSheet = (FrameLayout) d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
                BottomSheetBehavior.from(bottomSheet).setHideable(true);
            }
        });

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_mbti_filter, container, false);

        ivClose = view.findViewById(R.id.bottom_sheet_mbti_filter_iv_close);
        tvApply = view.findViewById(R.id.bottom_sheet_mbti_filter_tv_apply);
        TextView tvApply = view.findViewById(R.id.bottom_sheet_mbti_filter_tv_apply);
        tvApply.setOnClickListener(this);

        // 1줄
        ivIntj = view.findViewById(R.id.bottom_sheet_mbti_filter_intj);
        ivInfj = view.findViewById(R.id.bottom_sheet_mbti_filter_infj);
        ivIstj = view.findViewById(R.id.bottom_sheet_mbti_filter_istj);
        ivIstp = view.findViewById(R.id.bottom_sheet_mbti_filter_istp);

        // 2줄
        ivIntp = view.findViewById(R.id.bottom_sheet_mbti_filter_intp);
        ivInfp = view.findViewById(R.id.bottom_sheet_mbti_filter_infp);
        ivIsfj = view.findViewById(R.id.bottom_sheet_mbti_filter_isfj);
        ivIsfp = view.findViewById(R.id.bottom_sheet_mbti_filter_isfp);

        // 3줄
        ivEntj = view.findViewById(R.id.bottom_sheet_mbti_filter_entj);
        ivEnfj = view.findViewById(R.id.bottom_sheet_mbti_filter_enfj);
        ivEstj = view.findViewById(R.id.bottom_sheet_mbti_filter_estj);
        ivEstp = view.findViewById(R.id.bottom_sheet_mbti_filter_estp);

        // 4줄
        ivEntp = view.findViewById(R.id.bottom_sheet_mbti_filter_entp);
        ivEnfp = view.findViewById(R.id.bottom_sheet_mbti_filter_enfp);
        ivEsfj = view.findViewById(R.id.bottom_sheet_mbti_filter_esfj);
        ivEsfp = view.findViewById(R.id.bottom_sheet_mbti_filter_esfp);

        ivClose.setOnClickListener(this);

        ivIntj.setOnClickListener(this);
        ivInfj.setOnClickListener(this);
        ivIstj.setOnClickListener(this);
        ivIstp.setOnClickListener(this);

        ivIntp.setOnClickListener(this);
        ivInfp.setOnClickListener(this);
        ivIsfj.setOnClickListener(this);
        ivIsfp.setOnClickListener(this);

        ivEntj.setOnClickListener(this);
        ivEnfj.setOnClickListener(this);
        ivEstj.setOnClickListener(this);
        ivEstp.setOnClickListener(this);

        ivEntp.setOnClickListener(this);
        ivEnfp.setOnClickListener(this);
        ivEsfj.setOnClickListener(this);
        ivEsfp.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_sheet_mbti_filter_iv_close:
                dismiss();
                break;
            case R.id.bottom_sheet_mbti_filter_tv_apply:
                if(mSelectedMbti.equals("")){
                    Toast.makeText(mContext, "성격유형을 선택해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                mListener.onFilterApply(mSelectedMbti);
                dismiss();
                break;
            // 1번째 줄
            case R.id.bottom_sheet_mbti_filter_intj:
                uncheckAll();
                ivIntj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_1_intj_selected));
                mSelectedMbti = "intj";
                break;
            case R.id.bottom_sheet_mbti_filter_infj:
                uncheckAll();
                ivInfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_2_infj_selected));
                mSelectedMbti = "infj";
                break;
            case R.id.bottom_sheet_mbti_filter_istj:
                uncheckAll();
                ivIstj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_3_istj_selected));
                mSelectedMbti = "istj";
                break;
            case R.id.bottom_sheet_mbti_filter_istp:
                uncheckAll();
                ivIstp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_4_istp_selected));
                mSelectedMbti = "istp";
                break;

            // 2번째 줄
            case R.id.bottom_sheet_mbti_filter_intp:
                uncheckAll();
                ivIntp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_5_intp_selected));
                mSelectedMbti = "intp";
                break;
            case R.id.bottom_sheet_mbti_filter_infp:
                uncheckAll();
                ivInfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_6_infp_selected));
                mSelectedMbti = "infp";
                break;
            case R.id.bottom_sheet_mbti_filter_isfj:
                uncheckAll();
                ivIsfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_7_isfj_selected));
                mSelectedMbti = "isfj";
                break;
            case R.id.bottom_sheet_mbti_filter_isfp:
                uncheckAll();
                ivIsfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_8_isfp_selected));
                mSelectedMbti = "isfp";
                break;

            // 3번째 줄
            case R.id.bottom_sheet_mbti_filter_entj:
                uncheckAll();
                ivEntj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_9_entj_selected));
                mSelectedMbti = "entj";
                break;
            case R.id.bottom_sheet_mbti_filter_enfj:
                uncheckAll();
                ivEnfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_10_enfj_selected));
                mSelectedMbti = "enfj";
                break;
            case R.id.bottom_sheet_mbti_filter_estj:
                uncheckAll();
                ivEstj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_11_estj_selected));
                mSelectedMbti = "estj";
                break;
            case R.id.bottom_sheet_mbti_filter_estp:
                uncheckAll();
                ivEstp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_12_estp_selected));
                mSelectedMbti = "estp";
                break;

            // 4번째 줄
            case R.id.bottom_sheet_mbti_filter_entp:
                uncheckAll();
                ivEntp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_13_entp_selected));
                mSelectedMbti = "entp";
                break;
            case R.id.bottom_sheet_mbti_filter_enfp:
                uncheckAll();
                ivEnfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_14_enfp_selected));
                mSelectedMbti = "enfp";
                break;
            case R.id.bottom_sheet_mbti_filter_esfj:
                uncheckAll();
                ivEsfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_15_esfj_selected));
                mSelectedMbti = "esfj";
                break;
            case R.id.bottom_sheet_mbti_filter_esfp:
                uncheckAll();
                ivEsfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_16_esfp_selected));
                mSelectedMbti = "esfp";
                break;
        }
    }

    void uncheckAll() {
        ivIntj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_1_intj_unselected));
        ivInfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_2_infj_unselected));
        ivIstj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_3_istj_unselected));
        ivIstp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_4_istp_unselected));

        ivIntp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_5_intp_unselected));
        ivInfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_6_infp_unselected));
        ivIsfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_7_isfj_unselected));
        ivIsfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_8_isfp_unselected));

        ivEntj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_9_entj_unselected));
        ivEnfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_10_enfj_unselected));
        ivEstj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_11_estj_unselected));
        ivEstp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_12_estp_unselected));

        ivEntp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_13_entp_unselected));
        ivEnfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_14_enfp_unselected));
        ivEsfj.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_15_esfj_unselected));
        ivEsfp.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_mbti_16_esfp_unselected));
    }

    public interface BottomSheetListener {
        void onFilterApply(String mbti);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString().concat(" must implement BottomSheetListener"));
        }

    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        ((MainActivity) getActivity()).setBoardDoubleClick(false);
    }
}
