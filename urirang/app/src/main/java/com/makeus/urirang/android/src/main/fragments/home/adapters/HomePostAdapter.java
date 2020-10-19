package com.makeus.urirang.android.src.main.fragments.home.adapters;

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
import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;
import com.makeus.urirang.android.src.main.fragments.home.models.HomePost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<HomePost> mPostList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public HomePostAdapter(Context context, ArrayList<HomePost> posts, OnItemClickListener listener) {
        this.mContext = context;
        this.mPostList = posts;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPostTitle;
        TextView tvPostNickname;
        TextView tvPostCreatedAt;
        TextView tvPostLike;
        TextView tvPostViews;

        ImageView ivPostMbti;
        ConstraintLayout constraintItemView;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvPostTitle = itemView.findViewById(R.id.item_home_post_tv_title);
            tvPostNickname = itemView.findViewById(R.id.item_home_post_tv_nickname);
            tvPostCreatedAt = itemView.findViewById(R.id.item_home_post_tv_created_at);
            tvPostLike = itemView.findViewById(R.id.item_home_post_tv_like);
            tvPostViews = itemView.findViewById(R.id.item_home_post_tv_views);

            ivPostMbti = itemView.findViewById(R.id.item_home_post_iv_mbti);
            constraintItemView = itemView.findViewById(R.id.item_home_post_constraint);

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
        View view = inflater.inflate(R.layout.item_home_post, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomePost post = mPostList.get(position);

        holder.tvPostTitle.setText(post.getTitle());
        holder.tvPostNickname.setText(post.getUser().getNickname());
        holder.tvPostLike.setText(String.valueOf(post.getLikes()));
        holder.tvPostViews.setText("조회수 ".concat(String.valueOf(post.getViews())));

        String createdTime = post.getCreatedAt();
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

        holder.tvPostCreatedAt.setText(posted);

        if (post.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(holder.ivPostMbti);
        else if (post.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(holder.ivPostMbti);

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

}
