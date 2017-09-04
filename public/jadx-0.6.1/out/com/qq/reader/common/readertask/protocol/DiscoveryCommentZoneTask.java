package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class DiscoveryCommentZoneTask extends ReaderProtocolJSONTask {
    public DiscoveryCommentZoneTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.bP;
        f.b("SearchHotWordsTask", " mUrl : " + this.mUrl);
        if (str != null) {
            this.mRequest = str;
        }
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public boolean isRequestGzip() {
        return true;
    }
}
