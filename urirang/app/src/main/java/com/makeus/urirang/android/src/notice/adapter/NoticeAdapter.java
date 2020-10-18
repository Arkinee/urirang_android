package com.makeus.urirang.android.src.notice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.notice.models.Notice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Notice> mNoticeList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public NoticeAdapter(Context context, ArrayList<Notice> notices, OnItemClickListener listener) {
        this.mContext = context;
        this.mNoticeList = notices;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNickname;
        TextView tvCreatedAt;

        ImageView ivNoticeMbti;
        ImageView ivNoticeNew;

        ConstraintLayout constraintItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvNickname = itemView.findViewById(R.id.item_notice_tv_nickname);
            tvCreatedAt = itemView.findViewById(R.id.item_notice_tv_created_at);

            ivNoticeNew = itemView.findViewById(R.id.item_notice_iv_new);
            ivNoticeMbti = itemView.findViewById(R.id.item_notice_iv_mbti);

            constraintItemView = itemView.findViewById(R.id.item_notice_constraint);

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
        View view = inflater.inflate(R.layout.item_notice, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice = mNoticeList.get(position);

        if (notice.isAnonymous()) {
            holder.tvNickname.setText("익명");
        } else {
            holder.tvNickname.setText(notice.getUser().getNickname());
        }

        String createdTime = notice.getCreatedAt();
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd HH:mm");

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
            holder.ivNoticeNew.setVisibility(View.VISIBLE);
        } else holder.ivNoticeNew.setVisibility(View.INVISIBLE);

        holder.tvCreatedAt.setText(posted);

        if (notice.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(holder.ivNoticeMbti);
        else if (notice.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(holder.ivNoticeMbti);

        if (!notice.isChecked()) {
            holder.constraintItemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorHotPink3));
        } else {
            holder.constraintItemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        }

    }

    public Notice getItem(int pos){
        return mNoticeList.get(pos);
    }

    @Override
    public int getItemCount() {
        return mNoticeList.size();
    }

}
