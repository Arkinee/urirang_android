package com.makeus.urirang.android.src.withAll;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.withAll.content.models.WithAllCommentResponse;
import com.makeus.urirang.android.src.withAll.content.models.WithAllContentResponse;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllContentActivityView;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllRetrofitInterface;
import com.makeus.urirang.android.src.withAll.interfaces.WithAllWriteActivityView;
import com.makeus.urirang.android.src.withAll.write.models.WithAllWriteResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WithAllService {

    private Context mContext;
    private WithAllWriteActivityView mView;
    private WithAllContentActivityView mContentView;
    private int mOption = 0;

    public WithAllService(final WithAllWriteActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public WithAllService(final WithAllContentActivityView view, Context context, int option) {
        this.mContentView = view;
        this.mContext = context;
        this.mOption = option;
    }

    // 모두랑 토픽 게시
    public void tryPostWithAll(HashMap<String, RequestBody> params, List<MultipartBody.Part> images) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryPostWithAll(params, images).enqueue(new Callback<WithAllWriteResponse>() {
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
                t.printStackTrace();
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

                    if (mOption == 1) { // 모두랑 게시물 정보 받아오고 댓글 리스트까지 조회
                        mContentView.tryGetContentSuccess(contentResponse);
                    } else if (mOption == 2) {  // 모두랑 게시물 정보만 조회
                        mContentView.tryGetContentOnlySuccess(contentResponse);
                    }
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

    // 모두랑 댓글 가져오기
    public void tryGetWithAllComment(int postId, int page, int limit) {
        final WithAllRetrofitInterface withAllRetrofitInterface = getRetrofit().create(WithAllRetrofitInterface.class);
        withAllRetrofitInterface.tryGetWithAllComment(postId, page, limit).enqueue(new Callback<WithAllCommentResponse>() {
            @Override
            public void onResponse(@NonNull Call<WithAllCommentResponse> call, @NonNull Response<WithAllCommentResponse> response) {

                if (response.code() == 200) {
                    final WithAllCommentResponse contentResponse = response.body();
                    mContentView.tryGetCommentListSuccess(contentResponse.getData());
                } else {
                    mContentView.tryGetCommentListFailure("댓글 가져오기 읽기 실패");
                }

            }

            @Override
            public void onFailure(Call<WithAllCommentResponse> call, Throwable t) {
                mContentView.tryGetCommentListFailure(mContext.getString(R.string.network_connect_failure));
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
                } else if (response.code() == 409) {
                    mContentView.tryPostLikeDislikeFailure("내 게시물은 좋아요를 누를 수 없습니다");
                } else if (response.code() == 400) {
                    mContentView.tryPostLikeDislikeFailure("이미 좋아요를 누른 게시물입니다");
                } else if (response.code() == 404) {
                    mContentView.tryPostLikeDislikeFailure("해당 게시물이 존재하지 않습니다");
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
