package com.makeus.urirang.android.src.myPost;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.myCommentPost.models.MyCommentPostsResponse;
import com.makeus.urirang.android.src.myPost.interfaces.MyPostsActivityView;
import com.makeus.urirang.android.src.myPost.interfaces.MyPostsRetrofitInterface;
import com.makeus.urirang.android.src.myPost.models.MyPostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class MyPostsService {

    private Context mContext;
    private MyPostsActivityView mView;

    public MyPostsService(final MyPostsActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 내가 쓴 글 가져오기
    public void tryGetMyPosts(int page, int limit) {
        final MyPostsRetrofitInterface myPostsRetrofitInterface = getRetrofit().create(MyPostsRetrofitInterface.class);
        myPostsRetrofitInterface.tryGetMyPosts(page, limit).enqueue(new Callback<MyPostsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyPostsResponse> call, @NonNull Response<MyPostsResponse> response) {

                if (response.code() == 200) {
                    final MyPostsResponse postsResponse = response.body();
                    mView.tryGetMyPostsSuccess(postsResponse.getData());
                } else {
                    mView.tryGetMyPostsFailure("내가 쓴 글 가져오기 실패");
                }

            }

            @Override
            public void onFailure(Call<MyPostsResponse> call, Throwable t) {
                mView.tryGetMyPostsFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
