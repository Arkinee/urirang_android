package com.makeus.urirang.android.src.myCommentPost;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.myCommentPost.interfaces.MyCommentPostsActivityView;
import com.makeus.urirang.android.src.myCommentPost.interfaces.MyCommentPostsRetrofitInterface;
import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class MyCommentPostsService {

    private Context mContext;
    private MyCommentPostsActivityView mView;

    public MyCommentPostsService(final MyCommentPostsActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 댓글 단 글 가져오기
    public void tryGetMyCommentPosts(int page, int limit) {
        final MyCommentPostsRetrofitInterface commentPostsRetrofitInterface = getRetrofit().create(MyCommentPostsRetrofitInterface.class);
        commentPostsRetrofitInterface.tryGetMyCommentPosts(page, limit).enqueue(new Callback<MyCommentPostsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyCommentPostsResponse> call, @NonNull Response<MyCommentPostsResponse> response) {

                if (response.code() == 200) {
                    final MyCommentPostsResponse postsResponse = response.body();
                    mView.tryGetMyCommentPostsSuccess(postsResponse.getData());
                } else {
                    mView.tryGetMyCommentPostsFailure("댓글 단 글 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<MyCommentPostsResponse> call, Throwable t) {
                mView.tryGetMyCommentPostsFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
