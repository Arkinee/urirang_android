package com.makeus.urirang.android.src.login.social;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginActivityView;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginRetrofitInterface;
import com.makeus.urirang.android.src.login.email.models.LoginResponse;
import com.makeus.urirang.android.src.login.social.interfaces.KakaoLoginView;
import com.makeus.urirang.android.src.login.social.interfaces.SocialActivityView;
import com.makeus.urirang.android.src.login.social.interfaces.SocialRetrofitInterface;
import com.makeus.urirang.android.src.login.social.models.KakaoLoginResponse;
import com.makeus.urirang.android.src.login.social.models.SocialResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofitForKakao;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class SocialService {

    private Context mContext;
    private SocialActivityView mView;
    private KakaoLoginView mKakaoView;
    private String mToken;

    public SocialService(final SocialActivityView view, Context context, String token) {
        this.mView = view;
        this.mContext = context;
        this.mToken = token;
    }

    public SocialService(final KakaoLoginView view, Context context, String token) {
        this.mKakaoView = view;
        this.mContext = context;
        this.mToken = token;
    }

    public void tryGetIsMember() {
        final SocialRetrofitInterface socialRetrofitInterface = getRetrofitForKakao().create(SocialRetrofitInterface.class);
        socialRetrofitInterface.tryGetIsMember(mToken).enqueue(new Callback<SocialResponse>() {
            @Override
            public void onResponse(@NonNull Call<SocialResponse> call, @NonNull Response<SocialResponse> response) {

                Log.d("로그", String.valueOf(response.raw().code()));

                if (response.raw().code() == 200) {
                    mView.tryGetIsMemberSuccessGoMain(mToken);
                } else if (response.raw().code() == 404) {
                    mView.tryGetIsMemberSuccessNeedSignUp(mToken);
                } else if (response.raw().code() == 401) {
                    mView.tryGetIsMemberFailure("카카오 로그인 실패");
                }

            }

            @Override
            public void onFailure(Call<SocialResponse> call, Throwable t) {
                mView.tryGetIsMemberFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryPostKakaoSignUp(HashMap<String, Object> params) {
        final SocialRetrofitInterface socialRetrofitInterface = getRetrofitForKakao().create(SocialRetrofitInterface.class);
        socialRetrofitInterface.tryPostKakaoSignUp(params).enqueue(new Callback<KakaoLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<KakaoLoginResponse> call, @NonNull Response<KakaoLoginResponse> response) {

                if (response.raw().code() == 200) {
                    final KakaoLoginResponse kakaoLoginResponse = response.body();
                    sSharedPreferences.edit().putString(X_ACCESS_TOKEN, kakaoLoginResponse.getToken()).apply();
                    mKakaoView.tryPostKakaoLoginSuccess();
                } else if (response.raw().code() == 401) {
                    mKakaoView.tryPostKakaoLoginFailure();
                }

            }

            @Override
            public void onFailure(Call<KakaoLoginResponse> call, Throwable t) {
                mView.tryGetIsMemberFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    public void tryPostKakaoLogin() {
        final SocialRetrofitInterface socialRetrofitInterface = getRetrofitForKakao().create(SocialRetrofitInterface.class);
        socialRetrofitInterface.tryPostKakaoLogin(mToken).enqueue(new Callback<KakaoLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<KakaoLoginResponse> call, @NonNull Response<KakaoLoginResponse> response) {

                if (response.raw().code() == 200) {
                    final KakaoLoginResponse kakaoLoginResponse = response.body();
                    sSharedPreferences.edit().putString(X_ACCESS_TOKEN, kakaoLoginResponse.getToken()).apply();
                    mKakaoView.tryPostKakaoLoginSuccess();
                } else if (response.raw().code() == 401) {
                    mKakaoView.tryPostKakaoLoginFailure();
                } else if(response.raw().code() == 500){
                    mKakaoView.tryPostKakaoLoginFailure();
                }

            }

            @Override
            public void onFailure(Call<KakaoLoginResponse> call, Throwable t) {
                mView.tryGetIsMemberFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

}
