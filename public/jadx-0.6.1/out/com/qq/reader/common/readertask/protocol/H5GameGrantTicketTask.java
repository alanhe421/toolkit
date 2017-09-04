package com.qq.reader.common.readertask.protocol;

import android.os.RemoteException;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.web.a.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class H5GameGrantTicketTask extends ReaderProtocolJSONTask {
    public static final String COIN_CLIENTORDERID = "&clientOrderId=";
    public static final String COIN_MEMO = "&memo=";
    public static final String COMMON_BATCHID = "&batchid=";
    public static final String COMMON_COUNT = "&count=";
    public static final int GRANT_BOOK_TICKET = 110;
    public static final int GRANT_GAME_COIN = 120;
    private a iGameAidlInterface;
    public int mGrantType;
    private String webJSCallBack;

    public H5GameGrantTicketTask(Map<String, String> map, a aVar, int i) {
        switch (i) {
            case 110:
                this.mUrl = fillPara(e.bf, map);
                break;
            case 120:
                this.mUrl = fillPara(e.bg, map);
                break;
        }
        this.mGrantType = i;
        this.iGameAidlInterface = aVar;
        f.d("H5GameGrantTicketTask", this.mUrl);
    }

    private String fillPara(String str, Map<String, String> map) {
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                str = str + ((String) entry.getKey()) + ((String) entry.getValue());
            }
        }
        return str;
    }

    protected void refreshHeader(HashMap<String, String> hashMap) {
        super.refreshHeader(hashMap);
        if (this.iGameAidlInterface != null) {
            try {
                if (this.iGameAidlInterface.i() == 1) {
                    String b = this.iGameAidlInterface.b();
                    hashMap.put("skey", b);
                    hashMap.put("cookie", "skey=" + b);
                    hashMap.put("ckey", d.a(b));
                    hashMap.put("qqnum", this.iGameAidlInterface.c());
                } else {
                    hashMap.put("usid", this.iGameAidlInterface.b());
                    hashMap.put("uid", this.iGameAidlInterface.c());
                    hashMap.put("qqnum", this.iGameAidlInterface.c());
                }
                hashMap.put("safekey", d.i(this.mContext, this.iGameAidlInterface.c()));
                hashMap.put("timi", this.iGameAidlInterface.c());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setListener(c cVar) {
        if (cVar instanceof com.qq.reader.module.dicovery.a.d) {
            ((com.qq.reader.module.dicovery.a.d) cVar).c = this.mGrantType;
        }
        this.mListener = cVar;
    }

    public void setWebJSCallBack(String str) {
        this.webJSCallBack = str;
    }

    public String getWebJSCallBack() {
        return this.webJSCallBack;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
