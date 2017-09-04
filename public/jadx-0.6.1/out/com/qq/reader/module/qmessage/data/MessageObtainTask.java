package com.qq.reader.module.qmessage.data;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class MessageObtainTask extends ReaderProtocolJSONTask {
    private c mMessagePackage;

    public MessageObtainTask(c cVar) {
        this.mMessagePackage = cVar;
        this.mUrl = buildUrl(this.mMessagePackage);
    }

    private String buildUrl(c cVar) {
        long j = 0;
        if (cVar == null) {
            return "";
        }
        String g = cVar.g();
        if (g != null && g.length() > 0) {
            return g;
        }
        StringBuilder stringBuilder = new StringBuilder(e.h);
        stringBuilder.append("nativepage/message/get?");
        if (cVar.c() == 1) {
            stringBuilder.append("endtime=9223372036854775807");
            stringBuilder.append("&starttime=");
            if (cVar.b() - 100 > 0) {
                j = cVar.b() - 100;
            }
            stringBuilder.append(j);
        } else {
            stringBuilder.append("endtime=");
            stringBuilder.append(cVar.b());
        }
        if (cVar.d() == 1) {
            stringBuilder.append("&type=1");
        } else {
            stringBuilder.append("&type=2");
        }
        stringBuilder.append("&pagesize=");
        stringBuilder.append(20);
        return stringBuilder.toString();
    }
}
