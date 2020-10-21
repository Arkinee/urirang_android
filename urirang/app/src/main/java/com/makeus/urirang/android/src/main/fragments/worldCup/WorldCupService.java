package com.makeus.urirang.android.src.main.fragments.worldCup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.worldCup.interfaces.WorldCupRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.worldCup.interfaces.WorldCupView;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupBestResponse;
import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCupListResponse;
import com.makeus.urirang.android.src.main.interfaces.MainActivityView;
import com.makeus.urirang.android.src.main.interfaces.MainRetrofitInterface;
import com.makeus.urirang.android.src.main.model.FcmResponse;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupWriteView;
import com.makeus.urirang.android.src.worldCup.write.model.WorldCupWriteResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WorldCupService {

    private Context mContext;
    private WorldCupView mView;
    private WorldCupWriteView mWriteView;

    public WorldCupService(final WorldCupView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public WorldCupService(final WorldCupWriteView view, Context context) {
        this.mWriteView = view;
        this.mContext = context;
    }

    public void tryGetBest() {
        final WorldCupRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetBestWorldCup().enqueue(new Callback<WorldCupBestResponse>() {
            @Override
            public void onResponse(@NonNull Call<WorldCupBestResponse> call, @NonNull Response<WorldCupBestResponse> response) {

                if (response.code() == 200) {
                    final WorldCupBestResponse best = response.body();
                    mView.tryGetBestSuccess(best.getData());
                } else {
                    mView.tryGetWorldCupFailure("인기 월드컵 조회 실패");
                }

            }

            @Override
            public void onFailure(Call<WorldCupBestResponse> call, Throwable t) {
                mView.tryGetWorldCupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryGetList(int page, int limit, String sort) {
        final WorldCupRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetWorldCupList(page, limit, sort).enqueue(new Callback<WorldCupListResponse>() {
            @Override
            public void onResponse(@NonNull Call<WorldCupListResponse> call, @NonNull Response<WorldCupListResponse> response) {

                if (response.code() == 200) {
                    final WorldCupListResponse best = response.body();
                    mView.tryGetListSuccess(best.getData());
                } else {
                    mView.tryGetWorldCupFailure("인기 월드컵 조회 실패");
                }

            }

            @Override
            public void onFailure(Call<WorldCupListResponse> call, Throwable t) {
                mView.tryGetWorldCupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryPostWorldCup(HashMap<String, RequestBody> params, List<MultipartBody.Part> images) {
        final WorldCupRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupRetrofitInterface.class);
        worldCupRetrofitInterface.tryPostWorldCup(params, images).enqueue(new Callback<WorldCupWriteResponse>() {
            @Override
            public void onResponse(@NonNull Call<WorldCupWriteResponse> call, @NonNull Response<WorldCupWriteResponse> response) {

                if (response.code() == 201) {
                    mWriteView.tryPostWorldCupSuccess();
                } else {
                    mWriteView.tryPostWorldCupFailure("월드컵 만들기 실패");
                }

            }

            @Override
            public void onFailure(Call<WorldCupWriteResponse> call, Throwable t) {
                t.printStackTrace();
                mWriteView.tryPostWorldCupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }


}
