package com.makeus.urirang.android.src.search;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;

public class SearchActivity extends BaseActivity {

    private EditText mSearchEdtKeyword;
    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchEdtKeyword = findViewById(R.id.search_edt_keyword);
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
}