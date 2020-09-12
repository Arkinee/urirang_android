package com.makeus.urirang.android.src.login.info;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.login.info.interfaces.InfoActivityView;
import com.makeus.urirang.android.src.login.info.interfaces.InfoRetrofitInterface;
import com.makeus.urirang.android.src.login.info.models.signupResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.*;

public class InfoService {

    private Context mContext;
    private InfoActivityView mView;

    public InfoService(final InfoActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryPostSignup(HashMap<String, Object> params) {
        final InfoRetrofitInterface infoRetrofitInterface = getRetrofit().create(InfoRetrofitInterface.class);
        infoRetrofitInterface.tryPostSignUp(params).enqueue(new Callback<signupResponse>() {
            @Override
            public void onResponse(@NonNull Call<signupResponse> call, @NonNull Response<signupResponse> response) {

                final signupResponse signupResponse = response.body();
                if (signupResponse == null) {
                    mView.signupFailure(mContext.getString(R.string.response_empty_body));
                    return;
                }

                if (signupResponse.getMessage().equals("가입에 성공하였습니다.")) {
                    mView.signupSuccess(signupResponse.getMessage());
                } else {
                    mView.signupFailure(signupResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<signupResponse> call, Throwable t) {
                mView.signupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
