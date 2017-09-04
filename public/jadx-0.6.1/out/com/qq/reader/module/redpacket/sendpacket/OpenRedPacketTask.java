package com.qq.reader.module.redpacket.sendpacket;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import java.util.HashMap;

public class OpenRedPacketTask extends ReaderProtocolJSONTask {
    public OpenRedPacketTask(long j, long j2, c cVar) {
        super(cVar);
        this.mUrl = a.e + "rid=" + j + "&rtype=" + j2;
    }

    public HashMap<String, String> getBasicHeader() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.mHeaders.put("qrsy", d.f(this.mContext, currentTimeMillis));
        this.mHeaders.put("qrtm", String.valueOf(currentTimeMillis));
        this.mHeaders.put("qrem", ao.p());
        return super.getBasicHeader();
    }
}
