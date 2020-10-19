package com.makeus.urirang.android.src.testResults.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.models.Images;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Images> mImageList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public TestAdapter(Context context, ArrayList<Images> subjects, OnItemClickListener listener) {
        this.mContext = context;
        this.mImageList = subjects;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTestMain;
        ImageView ivTestDefault;
        CardView cardItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivTestMain = itemView.findViewById(R.id.item_test_results_iv_main);
            ivTestDefault = itemView.findViewById(R.id.item_test_results_iv_default);
            cardItemView = itemView.findViewById(R.id.item_test_results_card);

            cardItemView.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.item_test_results, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Images image = mImageList.get(position);

        if (image.getUrl() == null || image.getUrl().equals("")) {
            holder.ivTestMain.setVisibility(View.INVISIBLE);
        } else {
            holder.ivTestMain.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(image.getUrl()).placeholder(R.drawable.ic_default_image).into(holder.ivTestMain);
        }

    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

}
