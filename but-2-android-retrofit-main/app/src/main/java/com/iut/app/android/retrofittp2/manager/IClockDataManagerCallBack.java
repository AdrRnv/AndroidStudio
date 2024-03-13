package com.iut.app.android.retrofittp2.manager;

import com.iut.app.android.retrofittp2.model.Clock;

public interface IClockDataManagerCallBack {

    void getTimeResponseSuccess(Clock clock);
    void getTimeResponseError(String message);

}
