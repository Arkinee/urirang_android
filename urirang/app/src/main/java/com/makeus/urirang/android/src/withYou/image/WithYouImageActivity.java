package com.makeus.urirang.android.src.withYou.image;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;

public class WithYouImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiyh_you_image);

        String imageUrl = getIntent().getStringExtra("imageUrl");

        ImageView ivWithYou = findViewById(R.id.with_you_image_iv_main);
        Glide.with(this).load(imageUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.DATA).into(ivWithYou);

    }

    public void imageOnClick(View view) {

        switch (view.getId()) {
            case R.id.with_you_image_iv_close:
                finish();
                break;
        }
    }

}