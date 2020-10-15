package com.makeus.urirang.android.src.withAll.content.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.withAll.content.models.WithAllComment;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithAllContentCommentAdapter extends RecyclerView.Adapter<WithAllContentCommentAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WithAllComment> mCommentList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public WithAllContentCommentAdapter(Context context, ArrayList<WithAllComment> comments, OnItemClickListener listener) {
        this.mContext = context;
        this.mCommentList = comments;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCommentMbti;
        ImageView ivCommentNew;
        TextView tvCommentNickname;
        TextView tvCommentCreatedAt;
        TextView tvCommentContent;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivCommentMbti = itemView.findViewById(R.id.item_with_all_content_comment_iv_mbti);
            ivCommentNew = itemView.findViewById(R.id.item_with_all_content_comment_iv_new);
            tvCommentNickname = itemView.findViewById(R.id.item_with_all_content_comment_tv_nickname);
            tvCommentCreatedAt = itemView.findViewById(R.id.item_with_all_content_comment_tv_created_at);
            tvCommentContent = itemView.findViewById(R.id.item_with_you_content_comment_tv_content);

            itemView.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.item_with_all_content_comment, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WithAllComment comment = mCommentList.get(position);

        holder.tvCommentNickname.setText(comment.getUserNickName());

        if (comment.getUserMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_1_intj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_2_infj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_3_istj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_4_istp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_5_intp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_6_infp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_7_isfj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_8_isfp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_9_entj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_10_enfj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_11_estj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_12_estp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_13_entp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_14_enfp_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_mbti_15_esfj_selected).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_mbti_16_esfp_selected).into(holder.ivCommentMbti);


        String createdTime = comment.getCreatedAt();
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
            holder.ivCommentNew.setVisibility(View.VISIBLE);
        } else holder.ivCommentNew.setVisibility(View.INVISIBLE);

        holder.tvCommentCreatedAt.setText(posted);
        holder.tvCommentContent.setText(comment.getContent());

    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

}
