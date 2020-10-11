package com.makeus.urirang.android.src.main.fragments.home;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginActivityView;
import com.makeus.urirang.android.src.login.email.interfaces.EmailLoginRetrofitInterface;
import com.makeus.urirang.android.src.login.email.models.LoginResponse;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeActivityView;
import com.makeus.urirang.android.src.main.fragments.home.interfaces.HomeRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.home.models.CurrentTopicResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.RelateContent;
import com.makeus.urirang.android.src.main.fragments.home.models.TopicHistoryImagesResponse;
import com.makeus.urirang.android.src.main.fragments.home.models.UserInfoResponse;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class HomeService {

    private Context mContext;
    private HomeActivityView mView;

    public HomeService(final HomeActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 유저 정보 가져오기
    public void tryGetUserInfo() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {

                if (response.code() == 200) {
                    final UserInfoResponse userInfoResponse = response.body();
                    mView.tryGetUserInfoSuccess(userInfoResponse.getNickname(), userInfoResponse.getMbti());
                } else {
                    mView.tryGetUserInfoFailure("사용자 정보 받아오기 실패");
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mView.tryGetUserInfoFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 관련 컨텐츠 조회
    public void tryGetMbtiRelateContents() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetMbtiRelateContents().enqueue(new Callback<ArrayList<RelateContent>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RelateContent>> call, @NonNull Response<ArrayList<RelateContent>> response) {

                if (response.code() == 200) {
                    final ArrayList<RelateContent> result = response.body();
                    mView.tryGetMbtiRelateContentsSuccess(result);
                } else {
                    mView.tryGetMbtiRelateContentsFailure("MBTI 컨텐츠 받기 실패");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<RelateContent>> call, Throwable t) {
                mView.tryGetMbtiRelateContentsFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 현재 너희랑 토픽 가져오기
    public void tryGetCurrentTopic() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetCurrentTopic().enqueue(new Callback<CurrentTopicResponse>() {
            @Override
            public void onResponse(@NonNull Call<CurrentTopicResponse> call, @NonNull Response<CurrentTopicResponse> response) {

                if (response.code() == 200) {
                    final CurrentTopicResponse result = response.body();
                    if (result.getImages().size() != 0)
                        mView.tryGetCurrentTopicSuccess(result.getTitle(), result.getCommentNum(), result.getImages().get(0).getUrl());
                    else
                        mView.tryGetCurrentTopicSuccess(result.getTitle(), result.getCommentNum(), "");
                } else {
                    mView.tryGetCurrentTopicFailure("너희랑 현재 토픽 오류");
                }

            }

            @Override
            public void onFailure(Call<CurrentTopicResponse> call, Throwable t) {
                mView.tryGetCurrentTopicFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 명예의 전당  토픽 가져오기
    public void tryGetTopicHistoryImages() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.tryGetTopicHistoryImages().enqueue(new Callback<ArrayList<TopicHistoryImagesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<TopicHistoryImagesResponse>> call, @NonNull Response<ArrayList<TopicHistoryImagesResponse>> response) {

                if (response.code() == 200) {
                    final ArrayList<TopicHistoryImagesResponse> result = response.body();

                    ArrayList<String> urls = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getImages().size() != 0)
                            urls.add(result.get(i).getImages().get(0).getUrl());
                        else
                            urls.add("");
                    }

                    if(urls.size() < 4){
                        for(int i=0; i< 4 - urls.size(); i++){
                            urls.add("");
                        }
                    }

                    mView.tryGetTopicHistoryImagesSuccess(urls);
                } else {
                    mView.tryGetTopicHistoryImagesFailure("명예의 전당 이미지 오류");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<TopicHistoryImagesResponse>> call, Throwable t) {
                mView.tryGetTopicHistoryImagesFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
