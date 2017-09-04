package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class QZoneSynTask extends ReaderProtocolJSONTask {
    public QZoneSynTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.c + "/login?" + "client=1" + "&" + "version=" + "qqreader_6.5.3.0888_android" + "&" + "sid=" + d.c() + "&" + "usid=" + str;
        f.b("QZoneSynTask", " mUrl : " + this.mUrl);
    }
}
