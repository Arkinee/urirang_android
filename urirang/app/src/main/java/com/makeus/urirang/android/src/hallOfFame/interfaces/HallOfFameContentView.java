package com.makeus.urirang.android.src.hallOfFame.interfaces;

import com.makeus.urirang.android.src.hallOfFame.models.HallOfFameContentData;

public interface HallOfFameContentView {

    void tryGetHallOfFameContentSuccess(HallOfFameContentData data);

    void tryGetHallOfFameContentFailure(String message);

}
