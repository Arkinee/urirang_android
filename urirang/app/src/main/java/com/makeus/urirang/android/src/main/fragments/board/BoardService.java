package com.makeus.urirang.android.src.main.fragments.board;

import android.content.Context;

import androidx.annotation.NonNull;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardRetrofitInterface;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardWithAllView;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardWithYouView;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllResponse;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithYouResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.urirang.android.src.ApplicationClass.getRetrofit;

public class BoardService {

    private Context mContext;
    private BoardWithAllView mWithAllView;
    private BoardWithYouView mWithYouView;

    public BoardService(final BoardWithAllView view, Context context) {
        this.mWithAllView = view;
        this.mContext = context;
    }

    public BoardService(final BoardWithYouView view, Context context) {
        this.mWithYouView = view;
        this.mContext = context;
    }

    // 모두랑 리스트 가져오기
    public void tryGetWithAllList(String mbti, String keyword, int page, int limit) {
        final BoardRetrofitInterface boardRetrofitInterface = getRetrofit().create(BoardRetrofitInterface.class);
        boardRetrofitInterface.tryGetWithAllList(mbti, keyword, page, limit).enqueue(new Callback<BoardWithAllResponse>() {
            @Override
            public void onResponse(@NonNull Call<BoardWithAllResponse> call, @NonNull Response<BoardWithAllResponse> response) {

                if (response.code() == 200) {
                    final BoardWithAllResponse boardWithAllResponse = response.body();
                    mWithAllView.tryGetWithAllSuccess(boardWithAllResponse.getData());
                } else {
                    mWithAllView.tryGetWithAllFailure("모두랑 조회 실패");
                }

            }

            @Override
            public void onFailure(Call<BoardWithAllResponse> call, Throwable t) {
                mWithAllView.tryGetWithAllFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }

    // 너희랑 메인 정보 조회
    public void tryGetWithYouInfo(int limit) {
        final BoardRetrofitInterface boardRetrofitInterface = getRetrofit().create(BoardRetrofitInterface.class);
        boardRetrofitInterface.tryGetWithYouInfo(limit).enqueue(new Callback<BoardWithYouResponse>() {
            @Override
            public void onResponse(@NonNull Call<BoardWithYouResponse> call, @NonNull Response<BoardWithYouResponse> response) {

                if (response.code() == 200) {
                    final BoardWithYouResponse boardWithYouResponse = response.body();
                    assert boardWithYouResponse != null;
                    mWithYouView.tryGetWithYouSuccess(boardWithYouResponse.getData());
                } else {
                    mWithYouView.tryGetWithYouFailure("너희랑 정보조회 실패");
                }

            }

            @Override
            public void onFailure(Call<BoardWithYouResponse> call, Throwable t) {
                mWithYouView.tryGetWithYouFailure(mContext.getString(R.string.network_connect_failure));
            }
        });
    }
}
