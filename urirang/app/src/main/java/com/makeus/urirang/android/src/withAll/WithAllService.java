package com.makeus.urirang.android.src.withAll;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllRetrofitInterface;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllWriteActivityView;
import com.makeus.urirang.android.src.withAll.models.WithAllWriteResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WithAllService {

    private Context mContext;
    private WithAllWriteActivityView mView;

    public WithAllService(final WithAllWriteActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 이건 어때 토픽 게시
    public void tryPostWithAll(Map<String, RequestBody> params) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryPostWithAll(params).enqueue(new Callback<WithAllWriteResponse>() {
            @Override
            public void onResponse(@NonNull Call<WithAllWriteResponse> call, @NonNull Response<WithAllWriteResponse> response) {

                if (response.code() == 201) {
                    mView.tryPostWithAllSuccess();
                } else {
                    mView.tryPostWithAllFailure("모두랑 게시 실패");
                }

            }

            @Override
            public void onFailure(Call<WithAllWriteResponse> call, Throwable t) {
                mView.tryPostWithAllFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
