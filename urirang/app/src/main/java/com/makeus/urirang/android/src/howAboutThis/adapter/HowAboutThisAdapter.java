package com.makeus.urirang.android.src.howAboutThis.adapter;

import android.content.Context;
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
import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThis;

import java.util.ArrayList;

public class HowAboutThisAdapter extends RecyclerView.Adapter<HowAboutThisAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<HowAboutThis> mHowList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public HowAboutThisAdapter(Context context, ArrayList<HowAboutThis> howList, OnItemClickListener listener) {
        this.mContext = context;
        this.mHowList = howList;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvNickname;
        TextView tvLikes;

        ImageView ivHowAboutThisThumbnail;
        ImageView ivHowAboutThisMbti;

        ConstraintLayout constraintItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvTitle = itemView.findViewById(R.id.item_how_about_this_tv_title);
            tvNickname = itemView.findViewById(R.id.item_how_about_this_tv_nickname);
            tvLikes = itemView.findViewById(R.id.item_how_about_this_tv_like);

            ivHowAboutThisThumbnail = itemView.findViewById(R.id.item_how_about_this_iv_thumbnail);
            ivHowAboutThisMbti = itemView.findViewById(R.id.item_how_about_this_iv_mbti);

            constraintItemView = itemView.findViewById(R.id.item_how_about_this_constraint);

            constraintItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
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
        View view = inflater.inflate(R.layout.item_how_about_this, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HowAboutThis item = mHowList.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvNickname.setText(item.getUser().getNickname());
        holder.tvLikes.setText(String.valueOf(item.getLikes()));

        if(item.getImages().size() != 0) {
            Glide.with(mContext).load(item.getImages().get(0).getUrl()).into(holder.ivHowAboutThisThumbnail);
        }else{
            holder.ivHowAboutThisThumbnail.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_image));
        }

        if (item.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(holder.ivHowAboutThisMbti);
        else if (item.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(holder.ivHowAboutThisMbti);
    }

    @Override
    public int getItemCount() {
        return mHowList.size();
    }

}
