package com.makeus.urirang.android.src.main.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.models.OtherTest;
import com.makeus.urirang.android.src.main.fragments.home.models.HomePost;

import java.util.ArrayList;

public class OtherTestAdapter extends RecyclerView.Adapter<OtherTestAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OtherTest> mTestList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public OtherTestAdapter(Context context, ArrayList<OtherTest> tests, OnItemClickListener listener) {
        this.mContext = context;
        this.mTestList = tests;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTestTitle;

        ConstraintLayout constraintItemView;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvTestTitle = itemView.findViewById(R.id.item_home_other_test_title);
            constraintItemView = itemView.findViewById(R.id.item_home_other_test_constraint);

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
        View view = inflater.inflate(R.layout.item_home_other_test, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OtherTest test = mTestList.get(position);

        holder.tvTestTitle.setText(test.getTitle());

    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }

}
