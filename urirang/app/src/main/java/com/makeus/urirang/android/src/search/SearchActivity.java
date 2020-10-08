package com.makeus.urirang.android.src.search;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.main.fragments.board.BoardService;
import com.makeus.urirang.android.src.main.fragments.board.adapters.WithAllAdapter;
import com.makeus.urirang.android.src.main.fragments.board.interfaces.BoardWithAllView;
import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllData;

import java.util.ArrayList;
import java.util.Objects;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class SearchActivity extends BaseActivity implements BoardWithAllView {

    private EditText mSearchEdtKeyword;
    private InputMethodManager mInputMethodManager;

    private RecyclerView mSearchRv;
    private ArrayList<BoardWithAllData> mSearchList;
    private WithAllAdapter mAdapter;

    private boolean mIsEmptyResult = false;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchList = new ArrayList<>();
        mAdapter = new WithAllAdapter(this, mSearchList, new WithAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        mSearchRv = findViewById(R.id.search_rv);
        mSearchRv.addItemDecoration(new DividerItemDecoration(mSearchRv.getContext(), LinearLayoutManager.VERTICAL));
        mSearchRv.setAdapter(mAdapter);

        mSearchRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) Objects.requireNonNull(mSearchRv.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = mSearchRv.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {

                    if (!mIsEmptyResult) {
                        Log.d(TAG, mPage + "번째 페이지 with all post");
                        getWithAllListByKeyword(mPage, mSearchEdtKeyword.getText().toString());
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        mSearchEdtKeyword = findViewById(R.id.search_edt_keyword);
        // 완료 누를 시
        mSearchEdtKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mSearchList.clear();
                    mPage = 1;
                    getWithAllListByKeyword(mPage, mSearchEdtKeyword.getText().toString());
                }
                return false;
            }
        });

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void searchOnClick(View view) {

        switch (view.getId()) {
            case R.id.search_iv_back:
                finish();
                break;
            case R.id.search_linear_keyword:
                mSearchEdtKeyword.requestFocus();
                mInputMethodManager.showSoftInput(mSearchEdtKeyword, 0);
                break;
        }
    }

    public void getWithAllListByKeyword(int page, String keyWord) {
        final BoardService withAllService = new BoardService(this, this);
        withAllService.tryGetWithAllList("", keyWord, page, 20);
        showProgressDialog();
    }

    // 키보드 이외의 영역 터치시 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                if (mInputMethodManager != null)
                    mInputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsEmptyResult = false;
        mSearchList.clear();
        mPage = 1;
    }

    @Override
    public void tryGetWithAllSuccess(ArrayList<BoardWithAllData> data) {

        if (data.size() < 20) {
            mIsEmptyResult = true;
        }

        mSearchList.addAll(data);
        mAdapter.notifyDataSetChanged();
        mPage += 1;

        hideProgressDialog();

    }

    @Override
    public void tryGetWithAllFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}