package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class QueryNewTask extends ReaderProtocolJSONTask {
    private String mFollowContent;

    public QueryNewTask(c cVar, String str) {
        super(cVar);
        this.mFollowContent = str;
        this.mUrl = e.aP;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        return this.mFollowContent;
    }
}
