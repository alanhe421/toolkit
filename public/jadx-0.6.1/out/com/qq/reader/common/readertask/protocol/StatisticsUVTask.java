package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import java.util.HashMap;
import java.util.Map;

public class StatisticsUVTask extends ReaderProtocolJSONTask {
    public StatisticsUVTask() {
        this.mUrl = e.I + "common/logbase";
        setFailedType(1);
    }

    public HashMap<String, String> getBasicHeader() {
        this.mHeaders.put("safekey", d.y(getContext()));
        return this.mHeaders;
    }

    protected void doConnectionSuccessToRDM(boolean z) {
        i.a("event_stat_uv", true, 0, 0, null, false, false, ReaderApplication.getApplicationImp());
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
        if (z) {
            Map hashMap = new HashMap();
            if (exception != null) {
                hashMap.put("Exception", exception.toString() + " || " + exception.getMessage());
            }
            i.a("event_stat_uv", false, 0, 0, hashMap, true, false, ReaderApplication.getApplicationImp());
        }
    }
}
