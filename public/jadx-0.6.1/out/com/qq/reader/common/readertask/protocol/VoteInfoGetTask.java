package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class VoteInfoGetTask extends ReaderProtocolJSONTask {
    public VoteInfoGetTask(c cVar, String str, String str2) {
        super(cVar);
        this.mUrl = e.h + "nativepage/popinfo/query?bid=" + str + "&qtype=" + str2;
    }
}
