package com.makeus.urirang.android.src.login.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;

public class FindPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
    }



    public void findPasswordOnClick(View view) {
            switch (view.getId()){
                case R.id.find_password_tv_check:
                    Intent goComplete = new Intent(this, FindPasswordCompleteActivity.class);
                    startActivity(goComplete);
                    finish();
                    break;
            }
    }
}