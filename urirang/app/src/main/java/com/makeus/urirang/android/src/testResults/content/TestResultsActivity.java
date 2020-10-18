package com.makeus.urirang.android.src.testResults.content;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.BaseActivity;
import com.makeus.urirang.android.src.howAboutThis.models.Images;
import com.makeus.urirang.android.src.main.fragments.home.RvSpaceDecoration;
import com.makeus.urirang.android.src.testResults.TestResultsService;
import com.makeus.urirang.android.src.testResults.adapter.TestAdapter;
import com.makeus.urirang.android.src.testResults.interfaces.TestResultsActivityView;
import com.makeus.urirang.android.src.testResults.content.model.TestResults;

import java.util.ArrayList;

public class TestResultsActivity extends BaseActivity implements TestResultsActivityView {

    private Context mContext;
    private RecyclerView mTestAniagramRv;
    private RecyclerView mTestEgogramRv;
    private RecyclerView mTestMgramRv;
    private RecyclerView mTestBigFiveRv;
    private RecyclerView mTestLoveTypeRv;

    private ArrayList<Images> mAniagramList;
    private ArrayList<Images> mEgogramList;
    private ArrayList<Images> mMgramList;
    private ArrayList<Images> mBigFiveList;
    private ArrayList<Images> mLoveTypeList;

    private TestAdapter mAniagramAdapter;
    private TestAdapter mEgogramAdapter;
    private TestAdapter mMgramAdapter;
    private TestAdapter mBigFiveAdapter;
    private TestAdapter mLoveTypeAdapter;

    private boolean mDoubleClickFlag = false;
    private boolean mIsEmptyResult = false;
    private int mPage = 1;
    private int mOption = 1;
    private String mSort = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        mContext = this;

        mAniagramList = new ArrayList<>();
        mEgogramList = new ArrayList<>();
        mMgramList = new ArrayList<>();
        mBigFiveList = new ArrayList<>();
        mLoveTypeList = new ArrayList<>();

        // 애니어그램
        mTestAniagramRv = findViewById(R.id.test_results_rv_aniagram);
        mAniagramAdapter = new TestAdapter(this, mAniagramList, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

            }
        });

        mTestAniagramRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTestAniagramRv.setAdapter(mAniagramAdapter);
        mTestAniagramRv.addItemDecoration(new RvSpaceDecoration(this, 7));
        new PagerSnapHelper().attachToRecyclerView(mTestAniagramRv);


        // 에고그램
        mTestEgogramRv = findViewById(R.id.test_results_rv_egogram);
        mEgogramAdapter = new TestAdapter(this, mEgogramList, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

            }
        });

        mTestEgogramRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTestEgogramRv.setAdapter(mEgogramAdapter);
        mTestEgogramRv.addItemDecoration(new RvSpaceDecoration(this, 7));
        new PagerSnapHelper().attachToRecyclerView(mTestEgogramRv);

        // Mgram
        mTestMgramRv = findViewById(R.id.test_results_rv_mgram);
        mMgramAdapter = new TestAdapter(this, mMgramList, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

            }
        });

        mTestMgramRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTestMgramRv.setAdapter(mMgramAdapter);
        mTestMgramRv.addItemDecoration(new RvSpaceDecoration(this, 7));
        new PagerSnapHelper().attachToRecyclerView(mTestMgramRv);

        // big five
        mTestBigFiveRv = findViewById(R.id.test_results_rv_big_five);
        mBigFiveAdapter = new TestAdapter(this, mBigFiveList, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

            }
        });

        mTestBigFiveRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTestBigFiveRv.setAdapter(mBigFiveAdapter);
        mTestBigFiveRv.addItemDecoration(new RvSpaceDecoration(this, 7));
        new PagerSnapHelper().attachToRecyclerView(mTestBigFiveRv);

        // love type
        mTestLoveTypeRv = findViewById(R.id.test_results_rv_love_type);
        mLoveTypeAdapter = new TestAdapter(this, mLoveTypeList, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

            }
        });

        mTestLoveTypeRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTestLoveTypeRv.setAdapter(mLoveTypeAdapter);
        mTestLoveTypeRv.addItemDecoration(new RvSpaceDecoration(this, 7));
        new PagerSnapHelper().attachToRecyclerView(mTestLoveTypeRv);


    }

    void getTestResults() {
        final TestResultsService testService = new TestResultsService(this, this);
        testService.tryGetTestResultsList();
        showProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDoubleClickFlag = false;
        mIsEmptyResult = false;
        getTestResults();

    }

    public void testOnClick(View view) {

        switch (view.getId()) {
            case R.id.test_results__iv_back:
                finish();
                break;
            case R.id.test_results_tv_aniagram_modify_or_add:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;



                break;
            case R.id.test_results_tv_modify_or_add:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                break;
            case R.id.test_results_rv_mgram:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                break;
            case R.id.test_results_tv_modify_or_add_big_five:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                break;
            case R.id.test_results_tv_modify_or_add_love_type:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                break;
        }
    }


    @Override
    public void tryGetSuccess(ArrayList<TestResults> results) {


        hideProgressDialog();
    }

    @Override
    public void tryGetFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}