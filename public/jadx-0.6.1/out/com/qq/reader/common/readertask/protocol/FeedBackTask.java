package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class FeedBackTask extends ReaderProtocolJSONTask {
    public FeedBackTask(c cVar) {
        super(cVar);
        this.mUrl = e.aJ;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        return "qqNo=" + a.c.e + "&mobileNo=" + a.c.f + "&content=" + a.c.g;
    }

    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }
}
