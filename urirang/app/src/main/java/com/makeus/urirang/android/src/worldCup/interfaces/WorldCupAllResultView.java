package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.worldCup.result.all.models.MyTypeResult;
import com.makeus.urirang.android.src.worldCup.result.all.models.Result;

import java.util.ArrayList;

public interface WorldCupAllResultView {

    void tryGetWorldCupMbtiResultSuccess(MyTypeResult result);

    void tryGetWorldCupMbtiResultFailure(String message);

    void tryGetWorldCupAllResultSuccess(ArrayList<Result> result);

    void tryGetWorldCupAllResultFailure(String message);

}
