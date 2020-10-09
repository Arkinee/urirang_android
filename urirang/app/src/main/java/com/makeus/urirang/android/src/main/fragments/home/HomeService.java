package com.makeus.urirang.android.src.main.fragments.home;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginActivityView;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginRetrofitInterface;
import com.makeus.urirang.android.src.login.email.models.LoginResponse;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class HomeService {

    private Context mContext;
    private HomeActivityView mView;

    public HomeService(final HomeActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryGetUserInfo() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {

                if (response.code() == 200) {
                    final UserInfoResponse userInfoResponse = response.body();
                    mView.tryGetUserInfoSuccess(userInfoResponse.getNickname(), userInfoResponse.getMbti());
                } else {
                    mView.tryGetUserInfoFailure("사용자 정보 받아오기 실패");
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mView.tryGetUserInfoFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryGetMbtiRelateContents() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetMbtiRelateContents().enqueue(new Callback<ArrayList<RelateContent>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RelateContent>> call, @NonNull Response<ArrayList<RelateContent>> response) {

                if (response.code() == 200) {
                    final ArrayList<RelateContent> result = response.body();
                    mView.tryGetMbtiRelateContentsSuccess(result);
                } else {
                    mView.tryGetMbtiRelateContentsFailure("MBTI 컨텐츠 받기 실패");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<RelateContent>> call, Throwable t) {
                mView.tryGetMbtiRelateContentsFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
