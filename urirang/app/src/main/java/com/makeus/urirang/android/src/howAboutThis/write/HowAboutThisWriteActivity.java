package com.makeus.urirang.android.src.howAboutThis.write;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;

public class HowAboutThisWriteActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_this_write);

        mContext = this;

    }

    public void howWriteOnClick(View view) {

        switch (view.getId()) {
        }
    }


}