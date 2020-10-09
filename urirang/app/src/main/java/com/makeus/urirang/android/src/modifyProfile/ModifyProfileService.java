package com.makeus.urirang.android.src.modifyProfile;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.modifyProfile.interfaces.ModifyActivityView;
import com.makeus.urirang.android.src.modifyProfile.interfaces.ModifyRetrofitInterface;
import com.makeus.urirang.android.src.modifyProfile.model.ModifyResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class ModifyProfileService {

    private Context mContext;
    private ModifyActivityView mView;

    public ModifyProfileService(final ModifyActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public void tryPutModifyProfile(HashMap<String, Object> params) {
        final ModifyRetrofitInterface modifyRetrofitInterface = getRetrofit().create(ModifyRetrofitInterface.class);
        modifyRetrofitInterface.tryPutModifyProfile(params).enqueue(new Callback<ModifyResponse>() {
            @Override
            public void onResponse(@NonNull Call<ModifyResponse> call, @NonNull Response<ModifyResponse> response) {

                if (response.code() == 201) {
                    final ModifyResponse modifyResponse = response.body();
                    mView.tryPutModifyProfileSuccess(modifyResponse.getMessage());
                } else {
                    mView.tryPutModifyProfileFailure("프로필 수정 실패");
                }

            }

            @Override
            public void onFailure(Call<ModifyResponse> call, Throwable t) {
                mView.tryPutModifyProfileFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
