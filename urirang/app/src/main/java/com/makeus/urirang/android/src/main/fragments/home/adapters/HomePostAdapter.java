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

import java.util.ArrayList;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PreviousSubject> mSubjectList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public HomePostAdapter(Context context, ArrayList<PreviousSubject> subjects, OnItemClickListener listener) {
        this.mContext = context;
        this.mSubjectList = subjects;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHallOfFameTitle;
        TextView tvHallOfFameNickname;
        TextView tvHallOfFameSelectedAt;
        TextView tvHallOfFameComment;

        ImageView ivHallOfFameMain;
        ImageView ivHallOfFameMbti;

        ConstraintLayout constraintItemView;


        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvHallOfFameTitle = itemView.findViewById(R.id.item_hall_of_fame_tv_title);
            tvHallOfFameNickname = itemView.findViewById(R.id.item_hall_of_fame_tv_nick);
            tvHallOfFameSelectedAt = itemView.findViewById(R.id.item_hall_of_fame_tv_selected_at);
            tvHallOfFameComment = itemView.findViewById(R.id.item_hall_of_fame_tv_comment);

            ivHallOfFameMain = itemView.findViewById(R.id.item_hall_of_fame_iv_main);
            ivHallOfFameMbti = itemView.findViewById(R.id.item_hall_of_fame_iv_mbti);

            constraintItemView = itemView.findViewById(R.id.item_hall_of_fame_constraint);

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
        View view = inflater.inflate(R.layout.item_hall_of_fame, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PreviousSubject subject = mSubjectList.get(position);

        holder.tvHallOfFameTitle.setText(subject.getTitle());
        holder.tvHallOfFameNickname.setText(subject.getNickname());
        holder.tvHallOfFameSelectedAt.setText(subject.getSelectedAt());
        holder.tvHallOfFameComment.setText(String.valueOf(subject.getComment()));

        Glide.with(mContext).load(subject.getImageUrl()).into(holder.ivHallOfFameMain);

    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

}
