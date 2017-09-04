package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class OpenMonthVipTask extends ReaderProtocolJSONTask {
    public OpenMonthVipTask(int i, boolean z) {
        if (z) {
            this.mUrl = e.bs + "month=" + i + "&autoOpen=1";
        } else {
            this.mUrl = e.bs + "month=" + i + "&autoOpen=0";
        }
    }
}
