package com.makeus.urirang.android.src.main.fragments.worldCup.interfaces;

import com.makeus.urirang.android.src.main.fragments.worldCup.model.WorldCup;

import java.util.ArrayList;

public interface WorldCupView {

    void tryGetBestSuccess(WorldCup worldCup);

    void tryGetListSuccess(ArrayList<WorldCup> results);

    void tryGetWorldCupFailure(String message);
}
