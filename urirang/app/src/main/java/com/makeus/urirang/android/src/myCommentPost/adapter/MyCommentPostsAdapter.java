package com.makeus.urirang.android.src.myCommentPost.adapter;

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

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPosts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class MyCommentPostsAdapter extends RecyclerView.Adapter<MyCommentPostsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MyCommentPosts> mPostList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MyCommentPostsAdapter(Context context, ArrayList<MyCommentPosts> posts, OnItemClickListener listener) {
        this.mContext = context;
        this.mPostList = posts;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPostsType;
        TextView tvPostsTitle;
        TextView tvPostsNumOfComments;
        TextView tvPostsCreatedAt;
        TextView tvPostsLikes;

        ImageView ivPostsHasImages;
        ImageView ivPostsNew;
        ImageView ivPostsLike;

        ConstraintLayout constraintItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvPostsType = itemView.findViewById(R.id.item_my_posts_tv_board_type);
            tvPostsTitle = itemView.findViewById(R.id.item_my_posts_tv_title);
            tvPostsNumOfComments = itemView.findViewById(R.id.item_my_posts_tv_num_of_comments);
            tvPostsCreatedAt = itemView.findViewById(R.id.item_my_posts_tv_created_at);
            tvPostsLikes = itemView.findViewById(R.id.item_my_posts_tv_likes);

            ivPostsHasImages = itemView.findViewById(R.id.item_my_posts_iv_has_image);
            ivPostsNew = itemView.findViewById(R.id.item_my_posts_iv_new);
            ivPostsLike = itemView.findViewById(R.id.item_my_posts_iv_like);

            constraintItemView = itemView.findViewById(R.id.item_my_posts_constraint);

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
        View view = inflater.inflate(R.layout.item_my_posts, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyCommentPosts posts = mPostList.get(position);

        if (posts.getCategory().equals("topic")) {
            holder.tvPostsType.setText("너희랑");
        } else if (posts.getCategory().equals("post")) {
            holder.tvPostsType.setText("모두랑");
        } else if (posts.getCategory().equals("prevTopic")) {
            holder.tvPostsType.setText("명예의 전당");
        }

        holder.tvPostsTitle.setText(posts.getTitle());
        holder.tvPostsNumOfComments.setText("(".concat(String.valueOf(posts.getCommentNum())).concat(")"));

        if (posts.getCategory().equals("topic")) {
            holder.ivPostsLike.setVisibility(View.INVISIBLE);
            holder.tvPostsLikes.setVisibility(View.INVISIBLE);
        } else {
            holder.ivPostsLike.setVisibility(View.VISIBLE);
            holder.tvPostsLikes.setVisibility(View.VISIBLE);
            holder.tvPostsLikes.setText(String.valueOf(posts.getLikes()));
        }

        String createdTime = posts.getCreatedAt();
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
            holder.ivPostsNew.setVisibility(View.VISIBLE);
        } else holder.ivPostsNew.setVisibility(View.INVISIBLE);

        holder.tvPostsCreatedAt.setText(posted);

        if (posts.getImages().size() == 0) {
            holder.ivPostsHasImages.setVisibility(View.INVISIBLE);
        } else {
            holder.ivPostsHasImages.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

}
