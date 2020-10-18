package com.makeus.urirang.android.src.testResults.write;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.main.fragments.home.RvSpaceDecoration;
import com.makeus.urirang.android.src.testResults.TestResultsService;
import com.makeus.urirang.android.src.testResults.adapter.TestAdapter;
import com.makeus.urirang.android.src.testResults.content.model.TestResults;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsActivityView;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsWriteActivityView;

import java.util.ArrayList;

public class TestResultsWriteActivity extends BaseActivity implements TestResultsWriteActivityView {

    private Context mContext;
    private boolean mDoubleClickFlag = false;
    private int mSelectedImage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results_write);

        mContext = this;

    }

    void postTestResults() {
        final TestResultsService testService = new TestResultsService(this, this);
        testService.tryGetTestResultsList();
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;

    }

    public void testWriteOnClick(View view) {

        switch (view.getId()) {
            case R.id.test_results__iv_back:
                finish();
                break;
            case R.id.test_results_write_card_1:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 1;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;
            case R.id.test_results_write_card_2:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 2;

                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;
            case R.id.test_results_write_card_3:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                mSelectedImage = 3;
                
                ImagePicker.Companion.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                break;
        }
    }


    @Override
    public void tryPostWriteResultsSuccess() {
        hideProgressDialog();
    }

    @Override
    public void tryPostWriteResultsFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mDoubleClickFlag = false;
    }
}