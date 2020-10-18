package com.makeus.urirang.android.src.notice.interfaces;

import com.makeus.urirang.android.src.notice.models.Notice;

import java.util.ArrayList;

public interface NoticeActivityView {

    void tryGetNoticeListSuccess(ArrayList<Notice> results);

    void tryGetNoticeListFailure(String message);

    void tryPostCheckSuccess();
    void tryPostCheckFailure(String message);

}
