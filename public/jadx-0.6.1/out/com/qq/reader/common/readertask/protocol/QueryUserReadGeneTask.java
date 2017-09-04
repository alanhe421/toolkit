package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class QueryUserReadGeneTask extends ReaderProtocolJSONTask {
    public QueryUserReadGeneTask(c cVar) {
        super(cVar);
        this.mUrl = e.bM + "?version=v2";
    }

    public boolean isNeedLogin() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }
}
