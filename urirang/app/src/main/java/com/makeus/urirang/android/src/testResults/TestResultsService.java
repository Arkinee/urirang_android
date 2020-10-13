package com.makeus.urirang.android.src.testResults;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsActivityView;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsRetrofitInterface;
import com.makeus.urirang.android.src.testResults.model.TestResultsResponse;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouActivityView;
import com.makeus.urirang.android.src.withYou.comment.interfaces.WithYouRetrofitInterface;
import com.makeus.urirang.android.src.withYou.comment.models.IsMyCommentResponse;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class TestResultsService {

    private Context mContext;
    private TestResultsActivityView mView;

    public TestResultsService(final TestResultsActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // 테스트 결과 가져오기
    public void tryGetTestResultsList() {
        final TestResultsRetrofitInterface resultsRetrofitInterface = getRetrofit().create(TestResultsRetrofitInterface.class);
        resultsRetrofitInterface.tryGetTestResultsList().enqueue(new Callback<TestResultsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TestResultsResponse> call, @NonNull Response<TestResultsResponse> response) {

                if (response.code() == 200) {
                    final TestResultsResponse testResultsResponse = response.body();
                    mView.tryGetSuccess(testResultsResponse);
                } else {
                    mView.tryGetFailure("테스트 결과 받아오기 실패");
                }

            }

            @Override
            public void onFailure(Call<TestResultsResponse> call, Throwable t) {
                mView.tryGetFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
