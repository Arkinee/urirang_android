package com.makeus.urirang.android.src.withYou.comment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.RecyclerDecoration;
import com.makeus.urirang.android.src.withYou.comment.WithYouCommentActivity;
import com.makeus.urirang.android.src.withYou.comment.WithYouCommentService;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouCommentByCommentView;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouComment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithYouCommentAdapter extends RecyclerView.Adapter<WithYouCommentAdapter.ViewHolder> implements Filterable, WithYouCommentByCommentView {

    private Context mContext;
    private ArrayList<WithYouComment> mCommentList;
    private ArrayList<WithYouComment> mFilterList;
    private OnItemClickListener mListener = null;
    private WithYouCommentByCommentView mView;

    private CommentByCommentAdapter commentAdapter;
    private int mPosition = -1;
    private int mCommentId = -1;

    public interface OnItemClickListener {
        void onWriteClick(View v, int pos);

        void onLikeClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public WithYouCommentAdapter(Context context, ArrayList<WithYouComment> commentLists, OnItemClickListener listener) {
        this.mContext = context;
        this.mCommentList = commentLists;
        this.mFilterList = commentLists;
        this.mListener = listener;
        mView = this;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCommentMbti;
        ImageView ivNew;
        ImageView ivLike;

        TextView tvCommentNickname;
        TextView tvCommentCreatedAt;
        TextView tvCommentContent;
        TextView tvCommentLike;
        TextView tvCommentWrite;

        LinearLayout linearCommentLike;

        RecyclerView rvComment;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivCommentMbti = itemView.findViewById(R.id.item_with_you_comment_iv_mbti);
            ivNew = itemView.findViewById(R.id.item_with_you_comment_iv_new);
            ivLike = itemView.findViewById(R.id.item_with_you_comment_iv_like);
            tvCommentNickname = itemView.findViewById(R.id.item_with_you_comment_tv_nickname);
            tvCommentCreatedAt = itemView.findViewById(R.id.item_with_you_comment_tv_created_at);
            tvCommentContent = itemView.findViewById(R.id.item_with_you_comment_tv_content);
            tvCommentLike = itemView.findViewById(R.id.item_with_you_comment_tv_like);
            tvCommentWrite = itemView.findViewById(R.id.item_with_you_comment_tv_write);
            rvComment = itemView.findViewById(R.id.item_with_you_comment_rv);
            linearCommentLike = itemView.findViewById(R.id.item_with_you_comment_linear_like);

            rvComment.addItemDecoration(new RecyclerDecoration(mContext, 12));

            tvCommentWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onWriteClick(v, pos);
                        }
                    }
                }
            });

            linearCommentLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onLikeClick(v, pos);
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
        View view = inflater.inflate(R.layout.item_with_you_comment, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final WithYouComment comment = mFilterList.get(position);

        commentAdapter = new CommentByCommentAdapter(mContext, comment.getComments(), new CommentByCommentAdapter.OnItemClickListener() {
            @Override
            public void onLikeClick(View v, int pos) {
                int commentId = comment.getComments().get(pos).getId();
                mCommentId = commentId;
                mPosition = pos;

                final WithYouCommentService isMyCommentService = new WithYouCommentService(mView, mContext);
                isMyCommentService.tryGetIsMyComment(commentId);
                ((WithYouCommentActivity) mContext).showProgressDialog();
            }
        });

        holder.rvComment.setAdapter(commentAdapter);

        holder.tvCommentNickname.setText(comment.getUserNickName());
        holder.tvCommentContent.setText(comment.getContent());

        if (comment.isLiked()) {
            holder.ivLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_true));
            holder.tvCommentLike.setTextColor(mContext.getResources().getColor(R.color.colorHotPink));
        } else {
            holder.ivLike.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_liked_false));
            holder.tvCommentLike.setTextColor(mContext.getResources().getColor(R.color.colorBasicBlack27));
        }

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
            holder.ivNew.setVisibility(View.VISIBLE);
        } else holder.ivNew.setVisibility(View.INVISIBLE);

        holder.tvCommentCreatedAt.setText(posted);
        holder.tvCommentLike.setText(String.valueOf(comment.getLikes()));

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
    }

    @Override
    public void tryPostLikeCommentByCommentSuccess() {
        ((WithYouCommentActivity) mContext).hideProgressDialog();
    }

    @Override
    public void tryPostLikeCommentByCommentFailure(String message) {
        ((WithYouCommentActivity) mContext).hideProgressDialog();
        ((WithYouCommentActivity) mContext).showCustomToastShort(message);

        // UI 업데이트 다시 되돌리기
        WithYouComment comment = mFilterList.get(mPosition);
        comment.getComments().get(mPosition).setLiked(!comment.getComments().get(mPosition).isLiked());
        if (comment.getComments().get(mPosition).isLiked()) {
            comment.getComments().get(mPosition).setLike(comment.getComments().get(mPosition).getLike() + 1);
        } else {
            comment.getComments().get(mPosition).setLike(comment.getComments().get(mPosition).getLike() - 1);
        }
    }

    @Override
    public void tryGetIsMyCommentByCommentSuccess(boolean isMyComment) {

        // 내 댓글
        if (isMyComment) {
            ((WithYouCommentActivity) mContext).hideProgressDialog();
            ((WithYouCommentActivity) mContext).showCustomToastShort("자신의 댓글에 좋아요를 누를 수 없습니다");
        } else {
            // 내 댓글이 아닌 경우 좋아요 누름
            WithYouComment comment = mFilterList.get(mPosition);

            final WithYouCommentService likeService = new WithYouCommentService(mView, mContext);
            likeService.tryPostCommentByCommentLike(mCommentId, comment.getComments().get(mPosition).isLiked());
            ((WithYouCommentActivity) mContext).showProgressDialog();

            // UI 업데이트
            comment.getComments().get(mPosition).setLiked(!comment.getComments().get(mPosition).isLiked());
            if (comment.getComments().get(mPosition).isLiked()) {
                comment.getComments().get(mPosition).setLike(comment.getComments().get(mPosition).getLike() + 1);
            } else {
                comment.getComments().get(mPosition).setLike(comment.getComments().get(mPosition).getLike() - 1);
            }
        }
    }

    @Override
    public void tryGetIsMyCommentByCommentFailure(String message) {
        ((WithYouCommentActivity) mContext).hideProgressDialog();
        ((WithYouCommentActivity) mContext).showCustomToastShort(message);
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    public WithYouComment getItem(int position) {
        return mFilterList.get(position);
    }

    public void like(int pos) {
        mFilterList.get(pos).setLiked(!mFilterList.get(pos).isLiked());
        if (mFilterList.get(pos).isLiked()) {
            mFilterList.get(pos).setLikes(mFilterList.get(pos).getLikes() + 1);
        } else {
            mFilterList.get(pos).setLikes(mFilterList.get(pos).getLikes() - 1);
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFilterList = mCommentList;
                } else {
                    ArrayList<WithYouComment> filtered = new ArrayList<>();
                    for (WithYouComment comment : mFilterList) {

                    }
                    mFilterList = filtered;
                }

                FilterResults results = new FilterResults();
                results.values = mFilterList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilterList = (ArrayList<WithYouComment>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
