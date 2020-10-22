package com.makeus.urirang.android.src.worldCup.interfaces;

import com.makeus.urirang.android.src.worldCup.content.models.WorldCupContent;

public interface WorldCupContentView {

    void tryGetWorldCupContentSuccess(WorldCupContent content);

    void tryGetWorldCupContentFailure(String message);

    void tryPostWorldCupContentSuccess();

    void tryPostWorldCupContentFailure(String message);

}
