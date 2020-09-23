package com.makeus.urirang.android.src.splash;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.splash.interfaces.SplashActivityView;
import com.makeus.urirang.android.src.splash.interfaces.SplashRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class SplashService {

    private Context mContext;
    private SplashActivityView mView;

    public SplashService(final SplashActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryGetUserInfo() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.tryGetUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {

                final UserInfoResponse userInfoResponse = response.body();
                if (userInfoResponse == null) {
                    mView.tryGetAutoLoginFailure(mContext.getString(R.string.response_empty_body));
                    return;
                }

                if (response.code() == 200) {
                    mView.tryGetAutoLoginSuccessGoMain();
                } else{
                    mView.tryGetAutoLoginSuccessGoLogIn("사용자 정보 받아오기 실패");
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mView.tryGetAutoLoginFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
