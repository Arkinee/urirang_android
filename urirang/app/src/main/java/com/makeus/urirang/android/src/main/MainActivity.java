package com.makeus.urirang.android.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.login.signup.SignUpActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mainClick(View view){
        switch (view.getId()){
        }
    }
}