package com.makeus.urirang.android.src.howAboutThis.write;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisRetrofitInterface;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisWriteTopicView;
import com.makeus.urirang.android.src.howAboutThis.models.WriteTopicResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class HowAboutThisWriteService {

    private Context mContext;
    private HowAboutThisWriteTopicView mView;

    public HowAboutThisWriteService(final HowAboutThisWriteTopicView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 이건 어때 토픽 게시
    public void tryPostHowAboutThisTopic(Map<String, RequestBody> params) {
        final HowAboutThisRetrofitInterface howAboutThisRetrofitInterface = getRetrofit().create(HowAboutThisRetrofitInterface.class);
        howAboutThisRetrofitInterface.tryPostHowAboutThisTopic(params).enqueue(new Callback<WriteTopicResponse>() {
            @Override
            public void onResponse(@NonNull Call<WriteTopicResponse> call, @NonNull Response<WriteTopicResponse> response) {

                if (response.code() == 201) {
                    mView.tryPostHowAboutThisWriteTopicSuccess();
                } else {
                    mView.tryPostHowAboutThisWriteTopicFailure("토픽 게시 실패");
                }

            }

            @Override
            public void onFailure(Call<WriteTopicResponse> call, Throwable t) {
                mView.tryPostHowAboutThisWriteTopicFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
