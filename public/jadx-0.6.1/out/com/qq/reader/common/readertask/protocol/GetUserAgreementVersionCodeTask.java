package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class GetUserAgreementVersionCodeTask extends ReaderProtocolJSONTask {
    public GetUserAgreementVersionCodeTask(c cVar) {
        super(cVar);
        this.mUrl = "https://newcommon.reader.qq.com/common/agreement?needContent=0";
    }
}
