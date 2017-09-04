package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class PluginNetTask extends ReaderProtocolJSONTask {
    public PluginNetTask(c cVar, String str, String str2) {
        super(cVar);
        this.mUrl = e.aF + str.trim() + "&version=" + str2.trim();
    }
}
