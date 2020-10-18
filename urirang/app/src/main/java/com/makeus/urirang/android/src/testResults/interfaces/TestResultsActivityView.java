package com.makeus.urirang.android.src.testResults.interfaces;

import com.makeus.urirang.android.src.testResults.content.model.TestResults;

import java.util.ArrayList;

public interface TestResultsActivityView {

    void tryGetSuccess(ArrayList<TestResults> results);

    void tryGetFailure(String message);

}
