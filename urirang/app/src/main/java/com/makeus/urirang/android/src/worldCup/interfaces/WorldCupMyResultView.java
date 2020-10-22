package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.worldCup.result.my.models.MyResult;

public interface WorldCupMyResultView {

    void tryGetWorldCupMyResultSuccess(MyResult result);

    void tryGetWorldCupMyResultFailure(String message);


}
