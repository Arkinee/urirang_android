package com.makeus.urirang.android.src.hallOfFame.interfaces;

import com.makeus.urirang.android.src.hallOfFame.models.PreviousSubject;

import java.util.ArrayList;

public interface HallOfFameActivityView {

    void tryGetHallOfFameSuccess(ArrayList<PreviousSubject> results);

    void tryGetHallOfFameFailure(String message);

}
