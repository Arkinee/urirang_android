package com.makeus.urirang.android.src.hallOfFame;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameActivityView;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameContentView;
import com.makeus.urirang.android.src.hallOfFame.interfaces.HallOfFameRetrofitInterface;
import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameContentResponse;
import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameResponse;
import com.makeus.urirang.android.src.main.interfaces.MainActivityView;
import com.makeus.urirang.android.src.main.interfaces.MainRetrofitInterface;
import com.makeus.urirang.android.src.main.model.FcmResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class HallOfFameService {

    private Context mContext;
    private HallOfFameActivityView mView;
    private HallOfFameContentView mContentView;

    public HallOfFameService(final HallOfFameActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public HallOfFameService(final HallOfFameContentView view, Context context) {
        this.mContentView = view;
        this.mContext = context;
    }

    // 명예의 전당 리스트 받아오기
    public void tryGetTopicHistoryList(int page, int limit, String sort) {
        final HallOfFameRetrofitInterface hallOfFameRetrofitInterface = getRetrofit().create(HallOfFameRetrofitInterface.class);
        hallOfFameRetrofitInterface.tryGetTopicHistoryList(page, limit, sort).enqueue(new Callback<HallOfFameResponse>() {
            @Override
            public void onResponse(@NonNull Call<HallOfFameResponse> call, @NonNull Response<HallOfFameResponse> response) {

                if (response.code() == 200) {
                    final HallOfFameResponse hallOfFameResponse = response.body();
                    mView.tryGetHallOfFameSuccess(hallOfFameResponse.getData());
                } else {
                    mView.tryGetHallOfFameFailure("명예의 전당 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<HallOfFameResponse> call, Throwable t) {
                mView.tryGetHallOfFameFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 명예의 전당 특정 토픽 컨텐츠 받아오기
    public void tryGetHallOfFameContent(int topicId) {
        final HallOfFameRetrofitInterface hallOfFameRetrofitInterface = getRetrofit().create(HallOfFameRetrofitInterface.class);
        hallOfFameRetrofitInterface.tryGetHallOfFameContent(topicId, 5).enqueue(new Callback<HallOfFameContentResponse>() {
            @Override
            public void onResponse(@NonNull Call<HallOfFameContentResponse> call, @NonNull Response<HallOfFameContentResponse> response) {

                if (response.code() == 200) {
                    final HallOfFameContentResponse hallOfFameResponse = response.body();
                    mContentView.tryGetHallOfFameContentSuccess(hallOfFameResponse.getData());
                } else {
                    mContentView.tryGetHallOfFameContentFailure("명예의 전당 특정 컨텐츠 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<HallOfFameContentResponse> call, Throwable t) {
                mContentView.tryGetHallOfFameContentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
