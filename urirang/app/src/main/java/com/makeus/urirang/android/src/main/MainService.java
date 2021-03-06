package com.makeus.urirang.android.src.main;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.main.interfaces.MainActivityView;
import com.makeus.urirang.android.src.main.interfaces.MainRetrofitInterface;
import com.makeus.urirang.android.src.main.model.FcmResponse;
import com.makeus.urirang.android.src.splash.interfaces.SplashActivityView;
import com.makeus.urirang.android.src.splash.interfaces.SplashRetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class MainService {

    private Context mContext;
    private MainActivityView mView;

    public MainService(final MainActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryPostFcmToken(HashMap<String, Object> params) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.tryPostFcmToken(params).enqueue(new Callback<FcmResponse>() {
            @Override
            public void onResponse(@NonNull Call<FcmResponse> call, @NonNull Response<FcmResponse> response) {

                if (response.code() == 200) {
                    mView.tryPostFcmTokenSuccess();
                } else{
                    mView.tryPostFcmTokenFailure();
                }

            }

            @Override
            public void onFailure(Call<FcmResponse> call, Throwable t) {
                mView.tryPostFcmTokenFailure();
            }
        });
    }
}
