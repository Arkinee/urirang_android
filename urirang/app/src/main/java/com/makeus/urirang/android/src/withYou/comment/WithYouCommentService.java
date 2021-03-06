package com.makeus.urirang.android.src.withYou.comment;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouActivityView;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouCommentByCommentView;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouRetrofitInterface;
import com.makeus.urirang.android.src.withYou.comment.models.IsMyCommentResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WithYouCommentService {

    private Context mContext;
    private WithYouActivityView mView;
    private WithYouCommentByCommentView mCommentView;

    public WithYouCommentService(final WithYouActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public WithYouCommentService(final WithYouCommentByCommentView view, Context context) {
        this.mCommentView = view;
        this.mContext = context;
    }

    // 원댓글 불러오기
    public void tryGetWithYouList(int topicId, String mbti, int page, int limit) {
        final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
        withYouRetrofitInterface.tryGetWithYouList(topicId, mbti, page, limit).enqueue(new Callback<WithYouCommentResponse>() {
            @Override
            public void onResponse(@NonNull Call<WithYouCommentResponse> call, @NonNull Response<WithYouCommentResponse> response) {

                if (response.code() == 200) {
                    final WithYouCommentResponse withYouCommentResponse = response.body();
                    mView.tryGetWithYouListSuccess(withYouCommentResponse.getData());
                } else {
                    mView.tryGetWithYouListFailure("사용자 정보 받아오기 실패");
                }

            }

            @Override
            public void onFailure(Call<WithYouCommentResponse> call, Throwable t) {
                mView.tryGetWithYouListFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 원 댓글 좋아요
    public void tryPostCommentLike(int commentId, boolean isLike) {
        if(!isLike) {
            final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
            withYouRetrofitInterface.tryPostCommentLike(commentId).enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                    if (response.code() == 201) {
                        final LikeResponse likeResponse = response.body();
                        mView.tryPostLikeCommentSuccess();
                    }else if(response.code() == 409){
                        mView.tryPostLikeCommentFailure("자신의 글은 좋아요를 누를 수 없습니다");
                    } else {
                        mView.tryPostLikeCommentFailure("실패");
                    }

                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    mView.tryPostLikeCommentFailure(mContext.getString(R.string.network_connect_failure));
                }
            });
        }else{
            final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
            withYouRetrofitInterface.tryPostCommentDisLike(commentId).enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                    if (response.code() == 201) {
                        final LikeResponse likeResponse = response.body();
                        mView.tryPostLikeCommentSuccess();
                    } else {
                        mView.tryPostLikeCommentFailure("실패");
                    }

                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    mView.tryPostLikeCommentFailure(mContext.getString(R.string.network_connect_failure));
                }
            });
        }
    }

    // 대댓글 좋아요
    public void tryPostCommentByCommentLike(int commentId, boolean isLike) {
        if(!isLike) {
            final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
            withYouRetrofitInterface.tryPostCommentLike(commentId).enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                    if (response.code() == 201) {
                        final LikeResponse likeResponse = response.body();
                        mCommentView.tryPostLikeCommentByCommentSuccess();
                    } else {
                        mCommentView.tryPostLikeCommentByCommentFailure("실패");
                    }
                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    mCommentView.tryPostLikeCommentByCommentFailure(mContext.getString(R.string.network_connect_failure));
                }
            });
        }else{
            final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
            withYouRetrofitInterface.tryPostCommentDisLike(commentId).enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                    if (response.code() == 201) {
                        final LikeResponse likeResponse = response.body();
                        mCommentView.tryPostLikeCommentByCommentSuccess();
                    } else {
                        mCommentView.tryPostLikeCommentByCommentFailure("실패");
                    }
                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    mCommentView.tryPostLikeCommentByCommentFailure(mContext.getString(R.string.network_connect_failure));
                }
            });
        }
    }

    // 댓글 달기
    public void tryPostWriteComment(int topicId, String commentId, HashMap<String, Object> params) {
        final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
        withYouRetrofitInterface.tryPostWriteComment(topicId, commentId, params).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201 || response.code() == 500) {
                    final LikeResponse writeResponse = response.body();
                    mView.tryPostWriteCommentSuccess();
                } else {
                    mView.tryPostWriteCommentFailure("글쓰기 실패");
                }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                mView.tryPostWriteCommentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 내 댓글인지 확인
    public void tryGetIsMyComment(int commentId) {
        final WithYouRetrofitInterface withYouRetrofitInterface = getRetrofit().create(WithYouRetrofitInterface.class);
        withYouRetrofitInterface.tryGetIsMyComment(commentId).enqueue(new Callback<IsMyCommentResponse>() {
            @Override
            public void onResponse(@NonNull Call<IsMyCommentResponse> call, @NonNull Response<IsMyCommentResponse> response) {

                if (response.code() == 200) {
                    final IsMyCommentResponse commentResponse = response.body();
                    mCommentView.tryGetIsMyCommentByCommentSuccess(commentResponse.isMyComment());
                } else {
                    mCommentView.tryGetIsMyCommentByCommentFailure("글쓴이 파악 실패");
                }
            }

            @Override
            public void onFailure(Call<IsMyCommentResponse> call, Throwable t) {
                mCommentView.tryGetIsMyCommentByCommentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
