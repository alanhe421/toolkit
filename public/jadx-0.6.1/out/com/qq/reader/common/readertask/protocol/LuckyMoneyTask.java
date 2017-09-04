package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class LuckyMoneyTask extends ReaderProtocolJSONTask {
    public LuckyMoneyTask(c cVar, int i) {
        super(cVar);
        this.mFaiedAutoTryedTime = 4;
        this.mUrl = e.cb + "?m=" + i;
        setFailedType(1);
    }
}
