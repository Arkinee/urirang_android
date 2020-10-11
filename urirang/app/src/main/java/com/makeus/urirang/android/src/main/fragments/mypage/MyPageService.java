package com.makeus.urirang.android.src.main.fragments.mypage;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;
import com.makeus.urirang.android.src.main.fragments.mypage.interfaces.MyPageRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.mypage.interfaces.MyPageView;
import com.makeus.urirang.android.src.main.fragments.mypage.models.ResultResponse;
import com.makeus.urirang.android.src.main.fragments.mypage.models.TestResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class MyPageService {

    private Context mContext;
    private MyPageView mView;

    public MyPageService(final MyPageView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryGetUserInfo() {
        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.tryGetUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {

                if (response.code() == 200) {
                    final UserInfoResponse userInfoResponse = response.body();
                    mView.tryGetUserInfoSuccess(userInfoResponse.getNickname(), userInfoResponse.getMbti());
                } else {
                    mView.tryGetUserInfoFailure("유저 정보 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mView.tryGetUserInfoFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryGetTestResults() {
        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.tryGetTestResults().enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResultResponse> call, @NonNull Response<ResultResponse> response) {

                if (response.code() == 200) {
                    final ArrayList<TestResult> results = response.body().getResults();
                    mView.tryGetTestResultSuccess(results);
                } else {
                    mView.tryGetTestResultFailure("테스트 결과 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                mView.tryGetUserInfoFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
