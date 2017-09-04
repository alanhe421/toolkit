package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class MonthVipGiftTask extends ReaderProtocolJSONTask {
    public MonthVipGiftTask(int i) {
        setFailedType(1);
        setPriority(3);
        this.mUrl = e.bu + i;
    }
}
