package com.makeus.urirang.android.src.main.fragments.board.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class WithAllAdapter extends RecyclerView.Adapter<WithAllAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<BoardWithAllData> mWithAllList;
    private ArrayList<BoardWithAllData> mFilterList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public WithAllAdapter(Context context, ArrayList<BoardWithAllData> postLists, OnItemClickListener listener) {
        this.mContext = context;
        this.mWithAllList = postLists;
        this.mFilterList = postLists;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivWithAllThumbnail;
        ImageView ivWithAllNew;

        TextView tvWithAllTitle;
        TextView tvWithAllCommentsNum;
        TextView tvWithAllNickname;
        TextView tvWithAllCreatedAt;
        TextView tvWithAllViews;
        TextView tvWithAllLikes;

        CardView cardWIthAll;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivWithAllThumbnail = itemView.findViewById(R.id.item_with_all_iv_thumbnail);
            ivWithAllNew = itemView.findViewById(R.id.item_with_all_iv_new);
            tvWithAllTitle = itemView.findViewById(R.id.item_with_all_tv_title);
            tvWithAllCommentsNum = itemView.findViewById(R.id.item_with_all_tv_comment);
            tvWithAllNickname = itemView.findViewById(R.id.item_with_all_tv_nickname);
            tvWithAllCreatedAt = itemView.findViewById(R.id.item_with_all_tv_created_at);
            tvWithAllViews = itemView.findViewById(R.id.item_with_all_tv_view);
            tvWithAllLikes = itemView.findViewById(R.id.item_with_all_tv_like);

            cardWIthAll = itemView.findViewById(R.id.item_with_all_card);

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
        View view = inflater.inflate(R.layout.item_board_with_all, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardWithAllData post = mFilterList.get(position);

        holder.tvWithAllTitle.setText(post.getTitle());
        holder.tvWithAllCommentsNum.setText("(".concat(String.valueOf(post.getCommentNum())).concat(")"));
        holder.tvWithAllNickname.setText(post.getUser().getNickname());

        String createdTime = post.getCreatedAt();
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
            holder.ivWithAllNew.setVisibility(View.VISIBLE);
        } else holder.ivWithAllNew.setVisibility(View.INVISIBLE);

        holder.tvWithAllCreatedAt.setText(posted);
        holder.tvWithAllViews.setText(String.valueOf(post.getViews()));
        holder.tvWithAllLikes.setText(String.valueOf(post.getLikes()));


        if(post.getImages().size() != 0) {
            if (!post.getImages().get(0).getUrl().equals("")) {
                holder.cardWIthAll.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(post.getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.ivWithAllThumbnail);
            } else {
                holder.cardWIthAll.setVisibility(View.INVISIBLE);
            }
        }else{
            holder.cardWIthAll.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    public BoardWithAllData getItem(int position) {
        return mFilterList.get(position);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFilterList = mWithAllList;
                } else {
                    ArrayList<BoardWithAllData> filtered = new ArrayList<>();
                    for (BoardWithAllData post : mFilterList) {

                    }
                    mFilterList = filtered;
                }

                FilterResults results = new FilterResults();
                results.values = mFilterList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilterList = (ArrayList<BoardWithAllData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
