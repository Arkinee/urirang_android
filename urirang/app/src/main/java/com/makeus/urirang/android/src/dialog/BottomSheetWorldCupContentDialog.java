package com.makeus.urirang.android.src.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.Candidate;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCup;
import com.makeus.urirang.android.src.worldCup.WorldCupWriteAndContentService;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;

import java.util.ArrayList;
import java.util.Random;

public class BottomSheetWorldCupContentDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private Context mContext;
    private BottomSheetFilterOptionListener mListener;
    private WorldCupContent mWorldCup;

    private TextView tvTitle;
    private TextView tvNickname;
    private TextView tvRound;
    private TextView tvParticipate;
    private ImageView ivMbti;

    private ImageView ivLeft;
    private ImageView ivRight;
    private TextView tvLeft;
    private TextView tvRight;

    private TextView tvRound16;
    private TextView tvRound8;
    private TextView tvRound4;
    private TextView tvRound1;

    private View viewDotRound16;
    private View viewDotRound8;
    private View viewDotRound4;
    private View viewDotRound1;

    private View viewBarRound8;
    private View viewBarRound4;
    private View viewBarRound1;

    private CardView cardLeft;
    private CardView cardRight;

    private int mResidue;
    private boolean mDoubleClick = false;
    private ArrayList<Candidate> mCandidateList;

    private Candidate mCandidateLeft;
    private Candidate mCandidateRight;


    public BottomSheetWorldCupContentDialog() {
    }

    public BottomSheetWorldCupContentDialog(Context context, WorldCupContent worldCup) {
        this.mContext = context;
        this.mWorldCup = worldCup;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener((DialogInterface.OnShowListener) dialog1 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog1;

            FrameLayout bottomSheet = (FrameLayout) d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
            BottomSheetBehavior.from(bottomSheet).setHideable(true);
        });

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_wolrd_cup_content, container, false);

        mCandidateList = new ArrayList<>();
        mCandidateList = mWorldCup.getCandidates();

        cardLeft = view.findViewById(R.id.bottom_sheet_world_cup_card_left);
        cardRight = view.findViewById(R.id.bottom_sheet_world_cup_card_right);
        cardLeft.setOnClickListener(this);
        cardRight.setOnClickListener(this);

        ivLeft = view.findViewById(R.id.bottom_sheet_world_cup_iv_left);
        ivRight = view.findViewById(R.id.bottom_sheet_world_cup_iv_right);
        tvLeft = view.findViewById(R.id.bottom_sheet_world_cup_tv_left);
        tvRight = view.findViewById(R.id.bottom_sheet_world_cup_tv_right);

        tvTitle = view.findViewById(R.id.bottom_sheet_world_cup_tv_title);
        tvNickname = view.findViewById(R.id.bottom_sheet_world_cup_tv_nickname);
        tvRound = view.findViewById(R.id.bottom_sheet_world_cup_tv_round);
        tvParticipate = view.findViewById(R.id.bottom_sheet_world_cup_tv_participate);
        ivMbti = view.findViewById(R.id.bottom_sheet_world_cup_iv_mbti);

        tvRound16 = view.findViewById(R.id.bottom_sheet_world_cup_tv_round_16);
        tvRound8 = view.findViewById(R.id.bottom_sheet_world_cup_tv_round_8);
        tvRound4 = view.findViewById(R.id.bottom_sheet_world_cup_tv_round_4);
        tvRound1 = view.findViewById(R.id.bottom_sheet_world_cup_tv_round_1);

        viewDotRound16 = view.findViewById(R.id.bottom_sheet_world_cup_view_round_16);
        viewDotRound8 = view.findViewById(R.id.bottom_sheet_world_cup_view_round_8);
        viewDotRound4 = view.findViewById(R.id.bottom_sheet_world_cup_view_round_4);
        viewDotRound1 = view.findViewById(R.id.bottom_sheet_world_cup_view_round_1);

        viewBarRound8 = view.findViewById(R.id.bottom_sheet_world_cup_view_bar_8);
        viewBarRound4 = view.findViewById(R.id.bottom_sheet_world_cup_view_bar_4);
        viewBarRound1 = view.findViewById(R.id.bottom_sheet_world_cup_view_bar_1);

        ImageView ivClose = view.findViewById(R.id.bottom_sheet_world_cup_iv_close);
        ivClose.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mResidue = Integer.parseInt(mWorldCup.getRoundNum());

        if (mWorldCup.getRoundNum().equals("8")) {

            tvRound16.setVisibility(View.GONE);
            viewBarRound8.setVisibility(View.GONE);
            viewDotRound16.setVisibility(View.GONE);
            tvRound8.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
            viewDotRound8.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));

        }

        tvTitle.setText(mWorldCup.getTitle());
        tvNickname.setText(mWorldCup.getUser().getNickname());

        String round = " " + mContext.getString(R.string.world_cup_middle_dot) + " " + mWorldCup.getRoundNum() + "강 " + mContext.getString(R.string.world_cup_middle_dot);
        tvRound.setText(round);
        tvParticipate.setText(String.valueOf(mWorldCup.getJoinNum()));

        if (mWorldCup.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(ivMbti);
        else if (mWorldCup.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(ivMbti);


        Random random = new Random();
        int randomNum1 = random.nextInt(mCandidateList.size());

        mCandidateLeft = mCandidateList.get(randomNum1);
        mCandidateList.remove(randomNum1);
        mResidue -= 1;

        int randomNum2 = random.nextInt(mCandidateList.size());

        mCandidateRight = mCandidateList.get(randomNum2);
        mCandidateList.remove(randomNum2);
        mResidue -= 1;

        Glide.with(mContext).load(mCandidateLeft.getImageUrl()).into(ivLeft);
        Glide.with(mContext).load(mCandidateRight.getImageUrl()).into(ivRight);
        tvLeft.setText(mCandidateLeft.getTitle());
        tvRight.setText(mCandidateRight.getTitle());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_sheet_world_cup_iv_close:
                mListener.close();
                dismiss();
                break;
            case R.id.bottom_sheet_world_cup_card_left:
                if (mDoubleClick) return;
                mDoubleClick = true;

                if (mCandidateList.size() == 0) {
                    mListener.finish(mCandidateLeft.getId());
                    dismiss();
                    return;
                }

                mCandidateList.add(mCandidateLeft);
                mResidue += 1;

                Random random = new Random();
                int randomNum1 = random.nextInt(mCandidateList.size());

                Glide.with(mContext)
                        .load(mCandidateList.get(randomNum1).getImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivLeft);

                mCandidateLeft = mCandidateList.get(randomNum1);
                tvLeft.setText(mCandidateLeft.getTitle());
                mCandidateList.remove(randomNum1);
                mResidue -= 1;

                Random random2 = new Random();
                int randomNum2 = random2.nextInt(mCandidateList.size());

                Glide.with(mContext)
                        .load(mCandidateList.get(randomNum2).getImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                mDoubleClick = false;
                                return false;
                            }
                        })
                        .into(ivRight);

                mCandidateRight = mCandidateList.get(randomNum2);
                tvRight.setText(mCandidateRight.getTitle());
                mCandidateList.remove(randomNum2);
                mResidue -= 1;

                if (mResidue == 6) {
                    tvRound8.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound8.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound8.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                } else if (mResidue == 2) {
                    tvRound4.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound4.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound4.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                } else if (mResidue == 0) {
                    tvRound1.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound1.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound1.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                }

                break;
            case R.id.bottom_sheet_world_cup_card_right:
                if (mDoubleClick) return;
                mDoubleClick = true;

                if (mCandidateList.size() == 0) {
                    mListener.finish(mCandidateRight.getId());
                    dismiss();
                    break;
                }

                mCandidateList.add(mCandidateRight);
                mResidue += 1;

                Random random3 = new Random();
                int randomNum3 = random3.nextInt(mCandidateList.size());

                Glide.with(mContext)
                        .load(mCandidateList.get(randomNum3).getImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivLeft);

                mCandidateLeft = mCandidateList.get(randomNum3);
                tvLeft.setText(mCandidateLeft.getTitle());
                mCandidateList.remove(randomNum3);
                mResidue -= 1;

                Random random4 = new Random();
                int randomNum4 = random4.nextInt(mCandidateList.size());

                Glide.with(mContext)
                        .load(mCandidateList.get(randomNum4).getImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                mDoubleClick = false;
                                return false;
                            }
                        })
                        .into(ivRight);

                mCandidateRight = mCandidateList.get(randomNum4);
                tvRight.setText(mCandidateRight.getTitle());
                mCandidateList.remove(randomNum4);
                mResidue -= 1;

                if (mResidue == 6) {
                    tvRound8.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound8.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound8.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                } else if (mResidue == 2) {
                    tvRound4.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound4.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound4.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                } else if (mResidue == 0) {
                    tvRound1.setTextColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                    viewDotRound1.setBackground(ContextCompat.getDrawable(mContext, R.drawable.src_world_cup_view_selected));
                    viewBarRound1.setBackgroundColor(mContext.getResources().getColor(R.color.colorWorldCupRound));
                }
                break;
        }

    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    public interface BottomSheetFilterOptionListener {
        void finish(int candidateId);

        void close();
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
