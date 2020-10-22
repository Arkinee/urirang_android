package com.makeus.urirang.android.src.main.fragments.worldCup.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WorldCupAdapter extends RecyclerView.Adapter<WorldCupAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WorldCup> mWorldCupList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public WorldCupAdapter(Context context, ArrayList<WorldCup> worldCups, OnItemClickListener listener) {
        this.mContext = context;
        this.mWorldCupList = worldCups;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWorldCupTitle;
        TextView tvWorldCupNickname;
        TextView tvWorldCupParticipate;
        TextView tvWorldCupRound;

        ImageView iv1;
        ImageView iv2;
        ImageView iv3;
        ImageView ivMbti;

        ConstraintLayout constraintItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvWorldCupTitle = itemView.findViewById(R.id.item_world_cup_tv_title);
            tvWorldCupNickname = itemView.findViewById(R.id.item_world_cup_tv_nickname);
            tvWorldCupParticipate = itemView.findViewById(R.id.item_world_cup_tv_participate);
            tvWorldCupRound = itemView.findViewById(R.id.item_world_cup_tv_round);

            iv1 = itemView.findViewById(R.id.item_world_cup_iv_1);
            iv2 = itemView.findViewById(R.id.item_world_cup_iv_2);
            iv3 = itemView.findViewById(R.id.item_world_cup_iv_3);
            ivMbti = itemView.findViewById(R.id.item_world_cup_iv_mbti);

            constraintItemView = itemView.findViewById(R.id.item_world_cup_constraint);

            constraintItemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener.onItemClick(v, pos);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_world_cup, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorldCup worldCup = mWorldCupList.get(position);

        holder.tvWorldCupTitle.setText(worldCup.getTitle());
        holder.tvWorldCupNickname.setText(worldCup.getUser().getNickname());

        String round = " " + mContext.getString(R.string.world_cup_middle_dot) + " " + worldCup.getRoundNum() + "강 " + mContext.getString(R.string.world_cup_middle_dot);
        holder.tvWorldCupRound.setText(round);
        holder.tvWorldCupParticipate.setText(String.valueOf(worldCup.getJoinNum()));

        if (worldCup.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(holder.ivMbti);
        else if (worldCup.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(holder.ivMbti);

        Glide.with(mContext).load(worldCup.getWorldCupCandidates().get(0).getImageUrl()).into(holder.iv1);
        Glide.with(mContext).load(worldCup.getWorldCupCandidates().get(1).getImageUrl()).into(holder.iv2);
        Glide.with(mContext).load(worldCup.getWorldCupCandidates().get(2).getImageUrl()).into(holder.iv3);

    }

    @Override
    public int getItemCount() {
        return mWorldCupList.size();
    }

}
