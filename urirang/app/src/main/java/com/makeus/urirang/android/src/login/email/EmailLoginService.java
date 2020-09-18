package com.makeus.urirang.android.src.login.email;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginActivityView;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginRetrofitInterface;
import com.makeus.urirang.android.src.login.email.models.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class EmailLoginService {

    private Context mContext;
    private EmailLoginActivityView mView;

    public EmailLoginService(final EmailLoginActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryPostLogin(HashMap<String, Object> params) {
        final EmailLoginRetrofitInterface emailLoginRetrofitInterface = getRetrofit().create(EmailLoginRetrofitInterface.class);
        emailLoginRetrofitInterface.tryPostLogin(params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mView.tryPostLoginFailure(mContext.getString(R.string.response_empty_body));
                    return;
                }

                if (response.code() == 200) {
                    sSharedPreferences.edit().putString(X_ACCESS_TOKEN, loginResponse.getToken()).apply();
                    mView.tryPostLoginSuccess();
                } else if (response.code() == 401) {
                    mView.tryPostLoginFailure("이메일과 비밀번호가 일치하지 않습니다");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mView.tryPostLoginFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
