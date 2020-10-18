package com.makeus.urirang.android.src.notice;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.notice.interfaces.NoticeActivityView;
import com.makeus.urirang.android.src.notice.interfaces.NoticeRetrofitInterface;
import com.makeus.urirang.android.src.notice.models.NoticeResponse;
import com.makeus.urirang.android.src.splash.interfaces.SplashActivityView;
import com.makeus.urirang.android.src.splash.interfaces.SplashRetrofitInterface;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class NoticeService {

    private Context mContext;
    private NoticeActivityView mView;

    public NoticeService(final NoticeActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryGetNoticeList(int page, int limit) {
        final NoticeRetrofitInterface noticeRetrofitInterface = getRetrofit().create(NoticeRetrofitInterface.class);
        noticeRetrofitInterface.tryGetNoticeList(page, limit).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(@NonNull Call<NoticeResponse> call, @NonNull Response<NoticeResponse> response) {

                if (response.code() == 200) {
                    final NoticeResponse noticeResponse = response.body();
                    mView.tryGetNoticeListSuccess(noticeResponse.getData());
                } else {
                    mView.tryGetNoticeListFailure("알림 목록 받아오기 실패!");
                }

            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                mView.tryGetNoticeListFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryPostNoticeCheck(HashMap<String, Object> params) {
        final NoticeRetrofitInterface noticeRetrofitInterface = getRetrofit().create(NoticeRetrofitInterface.class);
        noticeRetrofitInterface.tryPostNoticeCheck(params).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mView.tryPostCheckSuccess();
                } else {
                    mView.tryPostCheckFailure("알림 읽기 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mView.tryGetNoticeListFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
