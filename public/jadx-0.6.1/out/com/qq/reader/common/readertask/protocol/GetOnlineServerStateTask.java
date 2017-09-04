package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.tencent.mid.api.MidEntity;
import java.util.HashMap;

public class GetOnlineServerStateTask extends ReaderProtocolJSONTask {
    public GetOnlineServerStateTask(c cVar) {
        super(cVar);
        this.mUrl = e.aE;
    }

    public HashMap<String, String> getBasicHeader() {
        String s = d.s(getContext());
        this.mHeaders.put(MidEntity.TAG_IMEI, d.i(getContext()));
        this.mHeaders.put("tuid", d.x(getContext()));
        this.mHeaders.put("channel", ao.h(getContext()));
        this.mHeaders.put("action", "getgroupiplist");
        this.mHeaders.put("clientSeries", s);
        this.mHeaders.put("cloudversion", "0");
        return this.mHeaders;
    }

    public String getApn() {
        return b.b(getContext());
    }
}
