package com.makeus.urirang.android.src.main.fragments.board.interfaces;

import com.makeus.urirang.android.src.main.fragments.board.models.BoardWithYouData;

public interface BoardWithYouView {

    void tryGetWithYouSuccess(BoardWithYouData data);
    void tryGetWithYouFailure(String message);

}
