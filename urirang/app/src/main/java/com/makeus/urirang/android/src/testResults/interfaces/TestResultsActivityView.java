package com.makeus.urirang.android.src.testResults.interfaces;

import com.makeus.urirang.android.src.testResults.model.TestResultsResponse;
import com.makeus.urirang.android.src.withYou.comment.models.WithYouComment;

import java.util.ArrayList;

public interface TestResultsActivityView {

    void tryGetSuccess(TestResultsResponse response);

    void tryGetFailure(String message);

}
