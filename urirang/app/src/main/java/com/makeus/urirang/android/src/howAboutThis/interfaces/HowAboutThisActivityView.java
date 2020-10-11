package com.makeus.urirang.android.src.howAboutThis.interfaces;

import com.makeus.urirang.android.src.howAboutThis.models.HowAboutThis;

import java.util.ArrayList;

public interface HowAboutThisActivityView {

    void tryGetHowAboutThisBest3Success(ArrayList<HowAboutThis> results);

    void tryGetHowAboutThisBest3Failure(String message);

    void tryGetHowAboutThisListSuccess(ArrayList<HowAboutThis> results);

    void tryGetHowAboutThisListFailure(String message);

}
