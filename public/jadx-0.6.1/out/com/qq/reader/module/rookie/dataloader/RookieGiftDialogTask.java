package com.qq.reader.module.rookie.dataloader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class RookieGiftDialogTask extends ReaderProtocolJSONTask {
    private final String TAG;

    public RookieGiftDialogTask(c cVar) {
        super(cVar);
        this.TAG = "RookieGiftDialog";
        this.mUrl = e.F + "common/newUser/getOneOpenMonthConf";
        com.qq.reader.common.monitor.debug.c.e("RookieGiftDialog", this.mUrl);
    }
}
