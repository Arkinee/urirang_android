package com.makeus.urirang.android.src.main.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;

import java.util.ArrayList;

public class RelateContentAdapter extends RecyclerView.Adapter<RelateContentAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<RelateContent> mRelateList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RelateContentAdapter(Context context, ArrayList<RelateContent> relateContents, OnItemClickListener listener) {
        this.mContext = context;
        this.mRelateList = relateContents;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivContentImage;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivContentImage = itemView.findViewById(R.id.item_home_relate_content_iv);

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
        View view = inflater.inflate(R.layout.item_home_relate_content, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RelateContent content = mRelateList.get(position);

//        Glide.with(mContext).load(content.getImageUrl()).into(holder.ivContentImage);
    }

    @Override
    public int getItemCount() {
        return mRelateList.size();
    }

}
