package com.makeus.urirang.android.src.main.fragments.mypage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllData;
import com.makeus.urirang.android.src.main.fragments.mypage.models.TestResult;

import java.util.ArrayList;

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<TestResult> mWithAllList;
    private ArrayList<TestResult> mFilterList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MyPageAdapter(Context context, ArrayList<TestResult> postLists, OnItemClickListener listener) {
        this.mContext = context;
        this.mWithAllList = postLists;
        this.mFilterList = postLists;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTestMain;
        ImageView ivDefault;

        TextView tvTestTitle;
        TextView tvTestResult;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            ivTestMain = itemView.findViewById(R.id.item_my_page_test_iv_main);
            ivDefault = itemView.findViewById(R.id.item_my_page_iv_default);
            tvTestTitle = itemView.findViewById(R.id.item_my_page_test_tv_title);
            tvTestResult = itemView.findViewById(R.id.item_my_page_test_tv_result);

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
        View view = inflater.inflate(R.layout.item_my_page_test, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestResult result = mFilterList.get(position);

        holder.tvTestTitle.setText(result.getTest().getTitle());
        holder.tvTestResult.setText(result.getResultText());

        if (result.getImages().size() == 0) {
            holder.ivTestMain.setVisibility(View.GONE);
        } else {
            holder.ivTestMain.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(result.getImages().get(0).getUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.ivTestMain);
        }
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    public TestResult getItem(int position) {
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
                    ArrayList<TestResult> filtered = new ArrayList<>();
                    for (TestResult result : mFilterList) {

                    }
                    mFilterList = filtered;
                }

                FilterResults results = new FilterResults();
                results.values = mFilterList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilterList = (ArrayList<TestResult>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
