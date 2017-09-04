package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class GetVoucherDetailTask extends ReaderProtocolJSONTask {
    public GetVoucherDetailTask() {
        this.mUrl = e.h + "usersign/giftlist";
    }
}
