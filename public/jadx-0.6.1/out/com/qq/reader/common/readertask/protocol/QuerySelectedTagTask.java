package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class QuerySelectedTagTask extends ReaderProtocolJSONTask {
    public QuerySelectedTagTask(c cVar) {
        super(cVar);
        this.mUrl = e.bL;
    }

    public boolean isNeedLogin() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }
}
