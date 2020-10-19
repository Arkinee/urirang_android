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
import com.makeus.urirang.android.src.RecyclerDecoration;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.withAll.content.models.WithAllComment;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContent;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouComment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class WithAllContentCommentAdapter extends RecyclerView.Adapter<WithAllContentCommentAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WithAllComment> mCommentList;
    private OnItemClickListener mListener = null;
    private WithAllCommentByCommentAdapter adapter;

    public interface OnItemClickListener {
        void onWriteCommentClick(View v, int pos);
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
        TextView tvCommentWriteComment;
        RecyclerView rvComment;
        View viewComment;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivCommentMbti = itemView.findViewById(R.id.item_with_all_content_comment_iv_mbti);
            ivCommentNew = itemView.findViewById(R.id.item_with_all_content_comment_iv_new);
            tvCommentNickname = itemView.findViewById(R.id.item_with_all_content_comment_tv_nickname);
            tvCommentCreatedAt = itemView.findViewById(R.id.item_with_all_content_comment_tv_created_at);
            tvCommentContent = itemView.findViewById(R.id.item_with_all_content_comment_tv_content);
            tvCommentWriteComment = itemView.findViewById(R.id.item_with_all_content_comment_tv_write_comment);
            rvComment = itemView.findViewById(R.id.item_with_all_content_comment_rv);
//            rvComment.addItemDecoration(new RecyclerDecoration(mContext, 12));
            viewComment = itemView.findViewById(R.id.item_with_all_content_comment_view_background);

            tvCommentWriteComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onWriteCommentClick(v, pos);
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

        if(comment.getUserId() == sSharedPreferences.getInt("userId", -1))
            holder.viewComment.setBackgroundColor(mContext.getResources().getColor(R.color.colorHotPink3));
        else holder.viewComment.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));

        adapter = new WithAllCommentByCommentAdapter(mContext, comment.getComments(), new WithAllCommentByCommentAdapter.OnItemClickListener() {
            @Override
            public void onLikeClick(View v, int pos) {

            }
        });

        holder.rvComment.setAdapter(adapter);

        if (!comment.isAnonymous()) {
            holder.tvCommentNickname.setText(comment.getUserNickName());
        } else {
            holder.tvCommentNickname.setText("익명");
        }

        if (comment.getUserMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(holder.ivCommentMbti);
        else if (comment.getUserMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(holder.ivCommentMbti);


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

    public WithAllComment getItem(int position) {
        return mCommentList.get(position);
    }

    public WithAllCommentByCommentAdapter getCommentByCommentAdapter() {
        return adapter;
    }
}
