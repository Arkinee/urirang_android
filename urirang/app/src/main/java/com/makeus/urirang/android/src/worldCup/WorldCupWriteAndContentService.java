package com.makeus.urirang.android.src.worldCup;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.withYou.comment.models.LikeResponse;
import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContentResponse;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupAllResultView;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupContentRetrofitInterface;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupContentView;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupMyResultView;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupResultRetrofitInterface;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupWriteRetrofitInterface;
import com.makeus.urirang.android.src.worldCup.interfaces.WorldCupWriteView;
import com.makeus.urirang.android.src.worldCup.result.all.models.AllResultResponse;
import com.makeus.urirang.android.src.worldCup.result.all.models.ResultResponse;
import com.makeus.urirang.android.src.worldCup.result.my.models.MyResultResponse;
import com.makeus.urirang.android.src.worldCup.write.model.WorldCupWriteResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class WorldCupWriteAndContentService {
    private Context mContext;
    private WorldCupWriteView mWriteView;
    private WorldCupContentView mContentView;
    private WorldCupMyResultView myResultView;
    private WorldCupAllResultView mAllView;

    public WorldCupWriteAndContentService(final WorldCupWriteView view, Context context) {
        this.mWriteView = view;
        this.mContext = context;
    }

    public WorldCupWriteAndContentService(final WorldCupContentView view, Context context) {
        this.mContentView = view;
        this.mContext = context;
    }

    public WorldCupWriteAndContentService(final WorldCupMyResultView view, Context context) {
        this.myResultView = view;
        this.mContext = context;
    }

    public WorldCupWriteAndContentService(final WorldCupAllResultView view, Context context) {
        this.mAllView = view;
        this.mContext = context;
    }

    // 월드컵 등록
    public void tryPostWorldCup(HashMap<String, RequestBody> params, List<MultipartBody.Part> images) {
        final WorldCupWriteRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupWriteRetrofitInterface.class);
        worldCupRetrofitInterface.tryPostWorldCupWrite(params, images).enqueue(new Callback<WorldCupWriteResponse>() {
            @Override
            public void onResponse(@NonNull Call<WorldCupWriteResponse> call, @NonNull Response<WorldCupWriteResponse> response) {

                if (response.code() == 201) {
                    mWriteView.tryPostWorldCupSuccess();
                } else {
                    mWriteView.tryPostWorldCupFailure("월드컵 만들기 실패");
                }

            }

            @Override
            public void onFailure(Call<WorldCupWriteResponse> call, Throwable t) {
                t.printStackTrace();
                mWriteView.tryPostWorldCupFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 월드컵 내용 가져오기
    public void tryGetWorldCupContent(int worldCupId) {
        final WorldCupContentRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupContentRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetWorldCupContent(worldCupId).enqueue(new Callback<WorldCupContentResponse>() {
            @Override
            public void onResponse(@NonNull Call<WorldCupContentResponse> call, @NonNull Response<WorldCupContentResponse> response) {

                if (response.code() == 200) {
                    final WorldCupContentResponse content = response.body();
                    mContentView.tryGetWorldCupContentSuccess(content.getData());
                } else {
                    mContentView.tryGetWorldCupContentFailure("월드컵 조회 실패");
                }

            }

            @Override
            public void onFailure(Call<WorldCupContentResponse> call, Throwable t) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.append("error start---");
                t.printStackTrace(pw);
                pw.append("error end---");
                mContentView.tryGetWorldCupContentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 월드컵 결과 저장하기
    public void tryPostWorldCupResult(HashMap<String, Object> params) {
        final WorldCupContentRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupContentRetrofitInterface.class);
        worldCupRetrofitInterface.tryPostWorldCupContent(params).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(@NonNull Call<LikeResponse> call, @NonNull Response<LikeResponse> response) {

                if (response.code() == 201) {
                    mContentView.tryPostWorldCupContentSuccess();
                } else {
                    mContentView.tryPostWorldCupContentFailure("결과 저장 실패");
                }

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.append("error start---");
                t.printStackTrace(pw);
                pw.append("error end---");
                mContentView.tryPostWorldCupContentFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 월드컵 내 결과 보기
    public void tryGetWorldCupMyResult(int worldCupId) {
        final WorldCupContentRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupContentRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetMyResult(worldCupId).enqueue(new Callback<MyResultResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResultResponse> call, @NonNull Response<MyResultResponse> response) {

                if (response.code() == 200) {
                    MyResultResponse resultResponse = response.body();
//                    Log.d("BreezeWind", resultResponse.getData().getCandidate().getTitle());
                    myResultView.tryGetWorldCupMyResultSuccess(resultResponse.getData());
                } else {
                    myResultView.tryGetWorldCupMyResultFailure("내 결과 불러오기 실패");
                }

            }

            @Override
            public void onFailure(Call<MyResultResponse> call, Throwable t) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.append("error start---");
                t.printStackTrace(pw);
                pw.append("error end---");
                myResultView.tryGetWorldCupMyResultFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 월드컵 내 성격유형 결과 보기
    public void tryGetWorldCupMyMbtiResult(int worldCupId) {
        final WorldCupResultRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupResultRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetMyMbtiResult(worldCupId).enqueue(new Callback<AllResultResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllResultResponse> call, @NonNull Response<AllResultResponse> response) {

                if (response.code() == 200) {
                    final AllResultResponse resultResponse = response.body();
                    mAllView.tryGetWorldCupMbtiResultSuccess(resultResponse.getData());
                } else {
                    mAllView.tryGetWorldCupMbtiResultFailure("내 성격유형 결과 불러오기 실패");
                }

            }

            @Override
            public void onFailure(Call<AllResultResponse> call, Throwable t) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.append("error start---");
                t.printStackTrace(pw);
                pw.append("error end---");
                mAllView.tryGetWorldCupMbtiResultFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 월드컵 내 성격유형 결과 보기
    public void tryGetWorldCupAllResult(int worldCupId, String mbti) {
        final WorldCupResultRetrofitInterface worldCupRetrofitInterface = getRetrofit().create(WorldCupResultRetrofitInterface.class);
        worldCupRetrofitInterface.tryGetAllResult(worldCupId, mbti).enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResultResponse> call, @NonNull Response<ResultResponse> response) {

                if (response.code() == 200) {
                    final ResultResponse resultResponse = response.body();
                    mAllView.tryGetWorldCupAllResultSuccess(resultResponse.getData());
                } else {
                    mAllView.tryGetWorldCupAllResultFailure("전체결과 불러오기 실패");
                }

            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.append("error start---");
                t.printStackTrace(pw);
                pw.append("error end---");
                mAllView.tryGetWorldCupAllResultFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }


}
