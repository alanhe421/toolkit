package com.qq.reader.module.rookie.dataloader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class RookieGiftTask extends ReaderProtocolJSONTask {
    private final String TAG;

    public RookieGiftTask(c cVar) {
        super(cVar);
        this.TAG = "RookieGift";
        this.mUrl = e.F + "common/newUser/giftListV2";
        com.qq.reader.common.monitor.debug.c.e("RookieGift", this.mUrl);
    }
}
