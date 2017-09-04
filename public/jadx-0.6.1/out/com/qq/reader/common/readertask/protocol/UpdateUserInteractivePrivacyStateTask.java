package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class UpdateUserInteractivePrivacyStateTask extends ReaderProtocolJSONTask {
    public UpdateUserInteractivePrivacyStateTask(c cVar, int i) {
        super(cVar);
        this.mUrl = e.cF + "?priStatus=" + i;
    }
}
