package com.makeus.urirang.android.src.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.HowAboutThisActivity;
import com.makeus.urirang.android.src.howAboutThis.HowAboutThisService;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisLikeView;
import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class BottomSheetHowAboutThisContentDialog extends BottomSheetDialogFragment implements View.OnClickListener, HowAboutThisLikeView {

    private Context mContext;
    private BottomSheetFilterOptionListener mListener;
    private HowAboutThis mHowAboutThis;

    private LinearLayout mHowAboutThisContentLinearSendLike;

    private ImageView mHowAboutThisIvMain;
    private ImageView mHowAboutThisContentIvMbti;
    private ImageView mHowAboutThisContentIvNew;
    private ImageView mHowAboutThisContentIvSendLike;

    private TextView mHowAboutThisContentTvTitle;
    private TextView mHowAboutThisContentTvNickname;
    private TextView mHowAboutThisContentTvCreatedAt;
    private TextView mHowAboutThisContentTvLike;
    private TextView mHowAboutThisContentTvSendLike;

    private boolean mIsLiked;
    private int mPosition;

    public BottomSheetHowAboutThisContentDialog() {
    }

    public BottomSheetHowAboutThisContentDialog(Context context, HowAboutThis howAboutThis, int pos) {
        this.mContext = context;
        this.mHowAboutThis = howAboutThis;
        this.mIsLiked = mHowAboutThis.isLiked();
        this.mPosition = pos;
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
        View view = inflater.inflate(R.layout.bottom_sheet_how_about_this_content, container, false);

        mHowAboutThisContentLinearSendLike = view.findViewById(R.id.bottom_sheet_how_about_this_linear_send_like);

        mHowAboutThisIvMain = view.findViewById(R.id.bottom_sheet_how_about_this_iv_main);
        mHowAboutThisContentIvMbti = view.findViewById(R.id.bottom_sheet_how_about_this_iv_mbti);
        mHowAboutThisContentIvNew = view.findViewById(R.id.bottom_sheet_how_about_this_iv_new);
        mHowAboutThisContentIvSendLike = view.findViewById(R.id.bottom_sheet_how_about_this_iv_send_like);

        mHowAboutThisContentTvTitle = view.findViewById(R.id.bottom_sheet_how_about_this_tv_title);
        mHowAboutThisContentTvNickname = view.findViewById(R.id.bottom_sheet_how_about_this_tv_nickname);
        mHowAboutThisContentTvCreatedAt = view.findViewById(R.id.bottom_sheet_how_about_this_tv_created_at);
        mHowAboutThisContentTvLike = view.findViewById(R.id.bottom_sheet_how_about_this_tv_like);
        mHowAboutThisContentTvSendLike = view.findViewById(R.id.bottom_sheet_how_about_this_tv_send_like);

        ImageView ivClose = view.findViewById(R.id.bottom_sheet_how_about_this_iv_close);

        ivClose.setOnClickListener(this);
        mHowAboutThisContentLinearSendLike.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHowAboutThisContentTvTitle.setText(mHowAboutThis.getTitle());
        mHowAboutThisContentTvNickname.setText(mHowAboutThis.getUser().getNickname());

        if (mHowAboutThis.getImages().size() != 0)
            Glide.with(mContext).load(mHowAboutThis.getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(mHowAboutThisIvMain);
        else
            mHowAboutThisIvMain.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_image));

        String createdTime = mHowAboutThis.getCreatedAt();
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd");

        String posted = "";
        if (createdTime != null) {
            try {
                Date before = beforeFormat.parse(createdTime);
                posted = afterFormat.format(before);
            } catch (ParseException e) {
                Log.d(TAG, e.toString());
            }
        }

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String today = afterFormat.format(date);

        if (posted.substring(0, 5).equals(today.substring(0, 5))) {
            mHowAboutThisContentIvNew.setVisibility(View.VISIBLE);
        } else mHowAboutThisContentIvNew.setVisibility(View.INVISIBLE);

        mHowAboutThisContentTvCreatedAt.setText(posted);
        mHowAboutThisContentTvLike.setText(String.valueOf(mHowAboutThis.getLikes()));

        if (mHowAboutThis.isLiked()) {
            mHowAboutThisContentLinearSendLike.setBackgroundResource(R.drawable.src_how_about_this_like_corner);
            mHowAboutThisContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_true));
            mHowAboutThisContentTvSendLike.setTextColor(mContext.getResources().getColor(R.color.colorHotPink));
        } else {
            mHowAboutThisContentLinearSendLike.setBackgroundResource(R.drawable.src_how_about_this_dislike_corner);
            mHowAboutThisContentIvSendLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_false));
            mHowAboutThisContentTvSendLike.setTextColor(mContext.getResources().getColor(R.color.colorBasicBlack40));
        }

        if (mHowAboutThis.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(mHowAboutThisContentIvMbti);
        else if (mHowAboutThis.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(mHowAboutThisContentIvMbti);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_sheet_how_about_this_linear_send_like:

                if (mHowAboutThis.isLiked()) {
                    final HowAboutThisService likeService = new HowAboutThisService(this, mContext);
                    likeService.tryPostHowAboutThisDislike(mHowAboutThis.getId());
                    ((HowAboutThisActivity) mContext).showProgressDialog();
                } else {
                    final HowAboutThisService likeService = new HowAboutThisService(this, mContext);
                    likeService.tryPostHowAboutThisLike(mHowAboutThis.getId());
                    ((HowAboutThisActivity) mContext).showProgressDialog();
                }
                break;
            case R.id.bottom_sheet_how_about_this_iv_close:
                dismiss();
                break;
        }

    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public void tryPostHowAboutThisLikeSuccess() {
        mIsLiked = !mIsLiked;
        mListener.sendLike(mIsLiked, mPosition);
        ((HowAboutThisActivity) mContext).hideProgressDialog();
        dismiss();
    }

    @Override
    public void tryPostHowAboutThisLikeMyTopic() {
        ((HowAboutThisActivity) mContext).hideProgressDialog();
        ((HowAboutThisActivity) mContext).showCustomToastShort("내 토픽에 좋아요를 할 수 없습니다");
    }

    @Override
    public void tryPostHowAboutThisLikeFailure(String message) {
        ((HowAboutThisActivity) mContext).hideProgressDialog();
        ((HowAboutThisActivity) mContext).showCustomToastShort(message);
    }

    public interface BottomSheetFilterOptionListener {
        void sendLike(boolean like, int pos);
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
