package com.makeus.urirang.android.src.howAboutThis;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisActivityView;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisLikeView;
import com.makeus.urirang.android.src.howAboutThis.interfaces.HowAboutThisRetrofitInterface;
import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThisResponse;
import com.makeus.urirang.android.src.main.interfaces.MainActivityView;
import com.makeus.urirang.android.src.main.interfaces.MainRetrofitInterface;
import com.makeus.urirang.android.src.main.model.FcmResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class HowAboutThisService {

    private Context mContext;
    private HowAboutThisActivityView mView;
    private HowAboutThisLikeView mLikeView;

    public HowAboutThisService(final HowAboutThisActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public HowAboutThisService(final HowAboutThisLikeView view, Context context) {
        this.mLikeView = view;
        this.mContext = context;
    }

    // 이건 어때 베스트 3 가져오기
    public void tryGetHowAboutThisListBest(int page, int limit) {
        final HowAboutThisRetrofitInterface howAboutThisRetrofitInterface = getRetrofit().create(HowAboutThisRetrofitInterface.class);
        howAboutThisRetrofitInterface.tryGetHowAboutThisList(page, limit, "best").enqueue(new Callback<HowAboutThisResponse>() {
            @Override
            public void onResponse(@NonNull Call<HowAboutThisResponse> call, @NonNull Response<HowAboutThisResponse> response) {

                if (response.code() == 200) {
                    final HowAboutThisResponse howAboutThisResponse = response.body();
                    mView.tryGetHowAboutThisBest3Success(howAboutThisResponse.getData());
                } else {
                    mView.tryGetHowAboutThisBest3Failure("베스트3 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<HowAboutThisResponse> call, Throwable t) {
                mView.tryGetHowAboutThisBest3Failure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 이건 어떄 리스트 가져오기
    public void tryGetHowAboutThisList(int page, int limit, String sort) {
        final HowAboutThisRetrofitInterface howAboutThisRetrofitInterface = getRetrofit().create(HowAboutThisRetrofitInterface.class);
        howAboutThisRetrofitInterface.tryGetHowAboutThisList(page, limit, sort).enqueue(new Callback<HowAboutThisResponse>() {
            @Override
            public void onResponse(@NonNull Call<HowAboutThisResponse> call, @NonNull Response<HowAboutThisResponse> response) {

                if (response.code() == 200) {
                    final HowAboutThisResponse howAboutThisResponse = response.body();
                    mView.tryGetHowAboutThisListSuccess(howAboutThisResponse.getData());
                } else {
                    mView.tryGetHowAboutThisListFailure("이건 어때 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<HowAboutThisResponse> call, Throwable t) {
                mView.tryGetHowAboutThisListFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 이건 어때 좋아요 누르기
    public void tryPostHowAboutThisLike(int postId) {
        final HowAboutThisRetrofitInterface howAboutThisRetrofitInterface = getRetrofit().create(HowAboutThisRetrofitInterface.class);
        howAboutThisRetrofitInterface.tryPostHowAboutThisLike(postId).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mLikeView.tryPostHowAboutThisLikeSuccess();
                } else {
                    mLikeView.tryPostHowAboutThisLikeFailure("이건 어때 좋아요 누르기 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mLikeView.tryPostHowAboutThisLikeFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 이건 어때 좋아요 풀기
    public void tryPostHowAboutThisDislike(int postId) {
        final HowAboutThisRetrofitInterface howAboutThisRetrofitInterface = getRetrofit().create(HowAboutThisRetrofitInterface.class);
        howAboutThisRetrofitInterface.tryPostHowAboutThisDislike(postId).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mLikeView.tryPostHowAboutThisLikeSuccess();
                } else {
                    mLikeView.tryPostHowAboutThisLikeFailure("이건 어때 좋아요 풀기 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mLikeView.tryPostHowAboutThisLikeFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
