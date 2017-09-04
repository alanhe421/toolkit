package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import com.tencent.mid.api.MidEntity;
import java.util.HashMap;
import java.util.Map;

public class CommonAllTask extends ReaderProtocolJSONTask {
    private boolean hasInit;

    public CommonAllTask(c cVar) {
        super(cVar);
        this.hasInit = false;
        this.mUrl = e.aE;
        setFailedType(1);
    }

    protected boolean interuptNoConn() {
        return true;
    }

    public HashMap<String, String> getBasicHeader() {
        String s = d.s(getContext());
        String valueOf = String.valueOf(d.aA(getContext()));
        String valueOf2 = String.valueOf(d.aB(getContext()));
        this.mHeaders.put("message_type", "3");
        this.mHeaders.put("isgzip", "1");
        this.mHeaders.put(MidEntity.TAG_IMEI, d.i(getContext()));
        this.mHeaders.put("tuid", d.x(getContext()));
        this.mHeaders.put("channel", ao.h(getContext()));
        this.mHeaders.put("safekey", d.y(getContext()));
        if (isFailed()) {
            this.mHeaders.put("action", "getnewslist|getpluginseries|checkupdate|getgroupiplist|querybooknews|usertype");
        } else {
            this.mHeaders.put("action", "report|userstatus|getnewslist|getpluginseries|checkupdate|getgroupiplist|querybooknews|usertype");
        }
        this.mHeaders.put("bnid", valueOf);
        this.mHeaders.put("bnptime", valueOf2);
        this.mHeaders.put("clientSeries", s);
        this.mHeaders.put("cloudversion", "0");
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        if (isFailed()) {
            return null;
        }
        if (!this.hasInit) {
            this.mRequest = j.d();
            this.hasInit = true;
        }
        return this.mRequest;
    }

    public String getApn() {
        return b.b(getContext());
    }

    protected void doConnectionSuccessToRDM(boolean z) {
        i.a("event_net_commonall", true, 0, 0, null, false, true, ReaderApplication.getApplicationImp());
    }

    protected void doConnectionErrorToRDM(boolean z, Exception exception) {
        Map hashMap = new HashMap();
        if (exception != null) {
            hashMap.put("Exception", exception.toString() + " || " + exception.getMessage());
        }
        i.a("event_net_commonall", false, 0, 0, hashMap, true, true, ReaderApplication.getApplicationImp());
    }

    public boolean isRequestGzip() {
        return true;
    }
}
