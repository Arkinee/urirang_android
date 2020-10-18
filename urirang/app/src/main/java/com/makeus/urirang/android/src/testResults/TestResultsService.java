package com.makeus.urirang.android.src.testResults;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsActivityView;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsRetrofitInterface;
import com.makeus.urirang.android.src.testResults.content.model.TestResultsResponse;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsWriteActivityView;
import com.makeus.urirang.android.src.testResults.write.models.WriteTestResultsResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class TestResultsService {

    private Context mContext;
    private TestResultsActivityView mView;
    private TestResultsWriteActivityView mWriteView;

    public TestResultsService(final TestResultsActivityView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    public TestResultsService(final TestResultsWriteActivityView view, Context context) {
        this.mWriteView = view;
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
                    mView.tryGetSuccess(testResultsResponse.getResult());
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

    // 테스트 결과 추가
    public void tryPostWriteTestResults(int testId, HashMap<String, RequestBody> params, List<MultipartBody.Part> images) {
        final TestResultsRetrofitInterface resultsRetrofitInterface = getRetrofit().create(TestResultsRetrofitInterface.class);
        resultsRetrofitInterface.tryPostWriteTestResults(testId, params, images).enqueue(new Callback<WriteTestResultsResponse>() {
            @Override
            public void onResponse(@NonNull Call<WriteTestResultsResponse> call, @NonNull Response<WriteTestResultsResponse> response) {

                if (response.code() == 200) {
                    mWriteView.tryPostWriteResultsSuccess();
                } else {
                    mWriteView.tryPostWriteResultsFailure("결과 전송 실패");
                }

            }

            @Override
            public void onFailure(Call<WriteTestResultsResponse> call, Throwable t) {
                mView.tryGetFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 테스트 결과 추가
    public void tryPostModifyTestResults(int testId, HashMap<String, RequestBody> params, List<MultipartBody.Part> images) {
        final TestResultsRetrofitInterface resultsRetrofitInterface = getRetrofit().create(TestResultsRetrofitInterface.class);
        resultsRetrofitInterface.tryPostModifyTestResults(testId, params, images).enqueue(new Callback<WriteTestResultsResponse>() {
            @Override
            public void onResponse(@NonNull Call<WriteTestResultsResponse> call, @NonNull Response<WriteTestResultsResponse> response) {

                if (response.code() == 200) {
                    mWriteView.tryPostWriteResultsSuccess();
                } else {
                    mWriteView.tryPostWriteResultsFailure("결과 전송 실패");
                }

            }

            @Override
            public void onFailure(Call<WriteTestResultsResponse> call, Throwable t) {
                mView.tryGetFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
