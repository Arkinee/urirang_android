package com.makeus.urirang.android.src.hallOfFame.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class HallOfFameAdapter extends RecyclerView.Adapter<HallOfFameAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PreviousSubject> mSubjectList;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public HallOfFameAdapter(Context context, ArrayList<PreviousSubject> subjects, OnItemClickListener listener) {
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
        holder.tvHallOfFameNickname.setText(subject.getUser().getNickname());

        String createdTime = subject.getCreatedAt();
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat afterFormat = new SimpleDateFormat("MM/dd");

        String posted = "";
        if (createdTime != null) {
            try {
                Date before = beforeFormat.parse(createdTime);
                posted = afterFormat.format(before);
            } catch (ParseException e) {
                Log.d(TAG, e.toString());
            }
        }

        holder.tvHallOfFameSelectedAt.setText(posted);
        holder.tvHallOfFameComment.setText(String.valueOf(subject.getCommentNum()));

        if (subject.getImages().size() != 0)
            Glide.with(mContext).load(subject.getImages().get(0).getUrl()).into(holder.ivHallOfFameMain);
        else
            holder.ivHallOfFameMain.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_image));

        if (subject.getUser().getMbti().equals("intj"))
            Glide.with(mContext).load(R.drawable.ic_profile_intj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("infj"))
            Glide.with(mContext).load(R.drawable.ic_profile_infj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("istj"))
            Glide.with(mContext).load(R.drawable.ic_profile_istj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("istp"))
            Glide.with(mContext).load(R.drawable.ic_profile_istp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("intp"))
            Glide.with(mContext).load(R.drawable.ic_profile_intp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("infp"))
            Glide.with(mContext).load(R.drawable.ic_profile_infp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("isfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("isfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_isfp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("entj"))
            Glide.with(mContext).load(R.drawable.ic_profile_entj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("enfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("estj"))
            Glide.with(mContext).load(R.drawable.ic_profile_estj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("estp"))
            Glide.with(mContext).load(R.drawable.ic_profile_estp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("entp"))
            Glide.with(mContext).load(R.drawable.ic_profile_entp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("enfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_enfp).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("esfj"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfj).into(holder.ivHallOfFameMbti);
        else if (subject.getUser().getMbti().equals("esfp"))
            Glide.with(mContext).load(R.drawable.ic_profile_esfp).into(holder.ivHallOfFameMbti);

    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

}
