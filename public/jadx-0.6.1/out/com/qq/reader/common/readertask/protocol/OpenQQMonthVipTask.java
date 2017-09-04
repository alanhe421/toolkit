package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class OpenQQMonthVipTask extends ReaderProtocolJSONTask {
    public OpenQQMonthVipTask(int i, boolean z) {
        if (z) {
            this.mUrl = e.br + "month=" + i + "&autoOpen=1";
        } else {
            this.mUrl = e.br + "month=" + i + "&autoOpen=0";
        }
    }
}
