package com.makeus.urirang.android.src.testResults.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import com.makeus.urirang.android.src.testResults.write.TestResultsWriteActivity;

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

    private TextView mTvAniagram;
    private TextView mTvEgogram;
    private TextView mTvMgram;
    private TextView mTvBigFive;
    private TextView mTvLoveType;

    private boolean mDoubleClickFlag = false;
    private boolean[] mExist;
    private boolean[] mImageExists;

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
        mExist = new boolean[5];
        mImageExists = new boolean[5];

        mTvAniagram = findViewById(R.id.test_results_tv_aniagram);
        mTvEgogram = findViewById(R.id.test_results_tv_egogram);
        mTvMgram = findViewById(R.id.test_results_tv_mgram);
        mTvBigFive = findViewById(R.id.test_results_tv_big_five);
        mTvLoveType = findViewById(R.id.test_results_tv_love_type);

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

        mAniagramList.clear();
        mEgogramList.clear();
        mLoveTypeList.clear();
        mBigFiveList.clear();
        mMgramList.clear();
        getTestResults();

    }

    public void testOnClick(View view) {

        switch (view.getId()) {
            case R.id.test_results_iv_back:
                finish();
                break;
            case R.id.test_results_tv_aniagram_modify_or_add:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent test1 = new Intent(this, TestResultsWriteActivity.class);

                if (!mExist[0]) {
                    test1.putExtra("type", 1);
                } else {
                    test1.putExtra("type", 2);
                    if (mImageExists[0]) test1.putExtra("imageList", mAniagramList);
                    test1.putExtra("result", mTvAniagram.getText().toString());
                }
                test1.putExtra("testId", 1);
                startActivity(test1);
                break;
            case R.id.test_results_tv_egogram_modify_or_add:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent test2 = new Intent(this, TestResultsWriteActivity.class);

                if (!mExist[1]) {
                    test2.putExtra("type", 1);
                } else {
                    test2.putExtra("type", 2);
                    if (mImageExists[1]) test2.putExtra("imageList", mEgogramList);
                    test2.putExtra("result", mTvEgogram.getText().toString());
                }
                test2.putExtra("testId", 2);
                startActivity(test2);

                break;
            case R.id.test_results_tv_modify_or_add_big_five:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent test3 = new Intent(this, TestResultsWriteActivity.class);

                if (!mExist[2]) {
                    test3.putExtra("type", 1);
                } else {
                    test3.putExtra("type", 2);
                    if (mImageExists[2]) test3.putExtra("imageList", mBigFiveList);
                    test3.putExtra("result", mTvBigFive.getText().toString());
                }
                test3.putExtra("testId", 3);
                startActivity(test3);
                break;
            case R.id.test_results_tv_modify_or_add_love_type:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent test4 = new Intent(this, TestResultsWriteActivity.class);

                if (!mExist[3]) {
                    test4.putExtra("type", 1);
                } else {
                    test4.putExtra("type", 2);
                    if (mImageExists[3]) test4.putExtra("imageList", mLoveTypeList);
                    test4.putExtra("result", mTvLoveType.getText().toString());
                }
                test4.putExtra("testId", 4);
                startActivity(test4);
                break;
            case R.id.test_results_tv_modify_or_add_mgram:
                if (mDoubleClickFlag) return;
                mDoubleClickFlag = true;

                Intent test5 = new Intent(this, TestResultsWriteActivity.class);

                if (!mExist[4]) {
                    test5.putExtra("type", 1);
                } else {
                    test5.putExtra("type", 2);
                    if (mImageExists[4]) test5.putExtra("imageList", mMgramList);
                    test5.putExtra("result", mTvMgram.getText().toString());
                }
                test5.putExtra("testId", 5);
                startActivity(test5);
                break;
        }
    }


    @Override
    public void tryGetSuccess(ArrayList<TestResults> results) {

        // 기본 세팅
        mExist[0] = false;
        mExist[1] = false;
        mExist[2] = false;
        mExist[3] = false;
        mExist[4] = false;

        mAniagramList.add(new Images());
        mEgogramList.add(new Images());
        mBigFiveList.add(new Images());
        mLoveTypeList.add(new Images());
        mMgramList.add(new Images());

        mAniagramAdapter.notifyDataSetChanged();
        mEgogramAdapter.notifyDataSetChanged();
        mBigFiveAdapter.notifyDataSetChanged();
        mLoveTypeAdapter.notifyDataSetChanged();
        mMgramAdapter.notifyDataSetChanged();

        for (TestResults t : results) {

            switch (t.getTestId()) {
                case 1:
                    mTvAniagram.setText(t.getResultText());
                    mExist[0] = true;

                    if (t.getImages().size() != 0) {
                        mAniagramList.clear();
                        mAniagramList.addAll(t.getImages());
                        mImageExists[0] = true;
                    } else {
                        mImageExists[0] = false;
                    }

                    ((TextView) findViewById(R.id.test_results_tv_aniagram_modify_or_add)).setText(getString(R.string.test_results_tv_modify));
                    mAniagramAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    mTvEgogram.setText(t.getResultText());
                    mExist[1] = true;

                    if (t.getImages().size() != 0) {
                        mEgogramList.clear();
                        mEgogramList.addAll(t.getImages());
                        mImageExists[1] = true;
                    } else {
                        mImageExists[1] = false;
                    }

                    ((TextView) findViewById(R.id.test_results_tv_egogram_modify_or_add)).setText(getString(R.string.test_results_tv_modify));
                    mEgogramAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    mTvBigFive.setText(t.getResultText());
                    mExist[2] = true;

                    if (t.getImages().size() != 0) {
                        mBigFiveList.clear();
                        mBigFiveList.addAll(t.getImages());
                        mImageExists[2] = true;
                    } else {
                        mImageExists[2] = false;
                    }

                    ((TextView) findViewById(R.id.test_results_tv_modify_or_add_big_five)).setText(getString(R.string.test_results_tv_modify));
                    mBigFiveAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    mTvLoveType.setText(t.getResultText());
                    mExist[3] = true;

                    if (t.getImages().size() != 0) {
                        mLoveTypeList.clear();
                        mLoveTypeList.addAll(t.getImages());
                        mImageExists[3] = true;
                    } else {
                        mImageExists[3] = false;
                    }

                    ((TextView) findViewById(R.id.test_results_tv_modify_or_add_love_type)).setText(getString(R.string.test_results_tv_modify));
                    mLoveTypeAdapter.notifyDataSetChanged();
                    break;
                case 5:
                    mTvMgram.setText(t.getResultText());
                    mExist[4] = true;

                    if (t.getImages().size() != 0) {
                        mMgramList.clear();
                        mMgramList.addAll(t.getImages());
                        mImageExists[4] = true;
                    } else {
                        mImageExists[4] = false;
                    }

                    ((TextView) findViewById(R.id.test_results_tv_modify_or_add_mgram)).setText(getString(R.string.test_results_tv_modify));
                    mMgramAdapter.notifyDataSetChanged();
                    break;
            }
        }   // for end


        hideProgressDialog();

    }

    @Override
    public void tryGetFailure(String message) {
        hideProgressDialog();
        showCustomToastShort(message);
    }
}