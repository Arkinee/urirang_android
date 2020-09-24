package com.makeus.urirang.android.src.main.fragments.home.adapters;

import android.content.Context;
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

import java.util.ArrayList;

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
        holder.tvPostNickname.setText(post.getNickname());
        holder.tvPostCreatedAt.setText(post.getCreatedAt());
        holder.tvPostLike.setText(String.valueOf(post.getLike()));
        holder.tvPostViews.setText(String.valueOf(post.getViews()));

        if (post.getMbti().equals("")) {
//        Glide.with(mContext).load(post.get()).into(holder.ivHallOfFameMain);
        }

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

}
