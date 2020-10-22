package com.makeus.urirang.android.src.worldCup.result.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.worldCup.result.all.models.Result;

import java.util.ArrayList;

public class AllResultAdapter extends RecyclerView.Adapter<AllResultAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Result> mResultList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public AllResultAdapter(Context context, ArrayList<Result> results, OnItemClickListener listener) {
        this.mContext = context;
        this.mResultList = results;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRank;
        TextView tvTitle;
        TextView tvRatio;
        ImageView ivMain;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvRank = itemView.findViewById(R.id.item_world_cup_result_tv_rank);
            tvTitle = itemView.findViewById(R.id.item_world_cup_result_tv_title);
            tvRatio = itemView.findViewById(R.id.item_world_cup_result_tv_ratio);
            ivMain = itemView.findViewById(R.id.item_world_cup_result_iv);

            itemView.setOnClickListener(v -> {
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
        View view = inflater.inflate(R.layout.item_world_cup_result, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = mResultList.get(position);

        holder.tvRank.setText(String.valueOf(position + 4).concat("위"));
        holder.tvTitle.setText(result.getTitle());
        holder.tvRatio.setText(String.valueOf(result.getRatio()).concat("%"));
        Glide.with(mContext).load(result.getImageUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.ivMain);

    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

}
