package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class QueryReadGeneListTask extends ReaderProtocolJSONTask {
    public QueryReadGeneListTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.bM + "/taglist?version=" + str;
    }

    public boolean isNeedLogin() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }
}
