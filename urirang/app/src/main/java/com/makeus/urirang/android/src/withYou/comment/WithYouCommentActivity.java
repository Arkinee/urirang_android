package com.makeus.urirang.android.src.withYou.comment;

import android.os.Bundle;
import android.view.View;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;

public class WithYouCommentActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiyh_you_image);


    }

    public void imageOnClick(View view) {

        switch (view.getId()) {
            case R.id.with_you_image_iv_close:
                finish();
                break;
        }
    }

}