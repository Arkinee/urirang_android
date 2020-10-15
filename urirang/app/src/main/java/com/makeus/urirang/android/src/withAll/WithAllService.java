package com.makeus.urirang.android.src.withAll;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllContentActivityView;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllRetrofitInterface;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllWriteActivityView;
import com.makeus.urirang.android.src.withAll.write.models.WithAllWriteResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WithAllService {

    private Context mContext;
    private WithAllWriteActivityView mView;
    private WithAllContentActivityView mContentView;

    public WithAllService(final WithAllWriteActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public WithAllService(final WithAllContentActivityView view, Context context) {
        this.mContentView = view;
        this.mContext = context;
    }


    // 모두랑 토픽 게시
    //RequestBody title, RequestBody content, RequestBody type, RequestBody isAnonymous, List<MultipartBody.Part> params
    public void tryPostWithAll(String title, String content, String type, boolean isAnonymous, List<MultipartBody.Part> params) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryPostWithAll(title, content, type, isAnonymous, params).enqueue(new Callback<WithAllWriteResponse>() {
            @Override
            public void onResponse(@NonNull Call<WithAllWriteResponse> call, @NonNull Response<WithAllWriteResponse> response) {

                if (response.code() == 201) {
                    mView.tryPostWithAllSuccess();
                } else {
                    mView.tryPostWithAllFailure("모두랑 게시 실패");
                }

            }

            @Override
            public void onFailure(Call<WithAllWriteResponse> call, Throwable t) {
                mView.tryPostWithAllFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 모두랑 글 읽기 가져오기
    public void tryGetWithAllContent(int postId) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryGetWithAllContent(postId).enqueue(new Callback<WithAllContentResponse>() {
            @Override
            public void onResponse(@NonNull Call<WithAllContentResponse> call, @NonNull Response<WithAllContentResponse> response) {

                if (response.code() == 200) {
                    final WithAllContentResponse contentResponse = response.body();
                    mContentView.tryGetContentSuccess(contentResponse);
                } else {
                    mContentView.tryGetContentFailure("모두랑 글 읽기 실패");
                }

            }

            @Override
            public void onFailure(Call<WithAllContentResponse> call, Throwable t) {
                mContentView.tryGetContentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 모두랑 글 좋아요
    public void tryPostLike(int postId) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryPostWithAllLike(postId).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mContentView.tryPostLikeSuccess();
                } else if (response.code() == 409 || response.code() == 400 || response.code() == 404) {
                    final LikeResponse likeResponse = response.body();
                    mContentView.tryPostLikeDislikeFailure(likeResponse.getMessage());
                } else {
                    mContentView.tryPostLikeDislikeFailure("모두랑 글 좋아요 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mContentView.tryPostLikeDislikeFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 모두랑 글 좋아요 풀기
    public void tryPostDisLike(int postId) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryPostWithAllDislike(postId).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mContentView.tryPostDislikeSuccess();
                } else if (response.code() == 409 || response.code() == 400 || response.code() == 404) {
                    final LikeResponse likeResponse = response.body();
                    mContentView.tryPostLikeDislikeFailure(likeResponse.getMessage());
                } else {
                    mContentView.tryPostLikeDislikeFailure("모두랑 글 좋아요 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mContentView.tryPostLikeDislikeFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
