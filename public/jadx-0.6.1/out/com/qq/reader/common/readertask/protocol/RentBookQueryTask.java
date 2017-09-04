package com.qq.reader.common.readertask.protocol;

import android.util.Log;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class RentBookQueryTask extends ReaderProtocolJSONTask {
    public RentBookQueryTask(c cVar, long j) {
        super(cVar);
        this.mUrl = e.h + "getRentInfo?bid=" + j;
        Log.e("RentBookQueryTask url", this.mUrl);
    }
}
