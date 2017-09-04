package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class SignUpTask extends ReaderProtocolJSONTask {
    public SignUpTask(c cVar, int[] iArr, int i) {
        super(cVar);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.a + "checkin?");
        stringBuffer.append("weekday=");
        for (int i2 = 0; i2 < iArr.length; i2++) {
            stringBuffer.append(iArr[i2]);
            if (i2 < iArr.length - 1) {
                stringBuffer.append(',');
            }
        }
        stringBuffer.append("&isresign=").append(i);
        f.a("SignUpTask", stringBuffer.toString());
        this.mUrl = stringBuffer.toString();
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}
