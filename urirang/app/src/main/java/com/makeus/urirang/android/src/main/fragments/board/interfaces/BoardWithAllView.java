package com.makeus.urirang.android.src.main.fragments.board.interfaces;

import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithAllData;

import java.util.ArrayList;

public interface BoardWithAllView {

    void tryGetWithAllSuccess(ArrayList<BoardWithAllData> data);
    void tryGetWithAllFailure(String message);

}
