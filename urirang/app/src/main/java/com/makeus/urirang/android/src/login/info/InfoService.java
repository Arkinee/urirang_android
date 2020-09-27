package com.makeus.urirang.android.src.login.info;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.login.info.interfaces.InfoActivityView;
import com.makeus.urirang.android.src.login.info.interfaces.InfoRetrofitInterface;
import com.makeus.urirang.android.src.login.info.models.SignupResponse;

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
        infoRetrofitInterface.tryPostSignUp(params).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {

                if(response.raw().code() == 400){
                    final SignupResponse signupResponse = response.body();
                    mView.signupFailure(signupResponse.getMessage());
                }else if(response.raw().code() == 200){
                    final SignupResponse signupResponse = response.body();
                    mView.signupSuccess(signupResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                mView.signupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
