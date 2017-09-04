package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.util.HashMap;

public class AppUpdateTask extends ReaderProtocolJSONTask {
    public AppUpdateTask(c cVar) {
        super(cVar);
        this.mUrl = e.aE;
    }

    public HashMap<String, String> getBasicHeader() {
        this.mHeaders.put("action", "checkupdate");
        return this.mHeaders;
    }
}
