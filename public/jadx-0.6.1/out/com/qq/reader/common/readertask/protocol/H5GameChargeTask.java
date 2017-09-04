package com.qq.reader.common.readertask.protocol;

import android.os.RemoteException;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.web.a.a;
import java.util.HashMap;

public class H5GameChargeTask extends ReaderProtocolJSONTask {
    public static final int DOCHARGE_V1 = 1001;
    public static final int DOCHARGE_V2 = 1002;
    public static final String GAMEID = "&gameid=";
    public static final String GORDERID = "&gorderid=";
    public static final String ITEMID = "&itemid=";
    public static final String MONEY = "&money=";
    public static final String ORIDERID = "&orderid=";
    public static final String SERVERID = "&serverid=";
    public static final String USERID = "&userid=";
    public int chargeVersion = 1001;
    private a iGameAidlInterface;
    private String webJSCallBack;

    public H5GameChargeTask(String str, String str2, String str3, String str4, String str5, String str6, String str7, a aVar, int i) {
        this.chargeVersion = i;
        switch (i) {
            case 1001:
                this.mUrl = e.bc + USERID + str + GAMEID + str2 + SERVERID + str3 + MONEY + str4;
                break;
            case 1002:
                this.mUrl = e.bd + USERID + str + GAMEID + str2 + SERVERID + str3 + MONEY + str4;
                if (!TextUtils.isEmpty(str7)) {
                    this.mUrl += ORIDERID + str7;
                    break;
                }
                break;
        }
        if (!TextUtils.isEmpty(str6)) {
            this.mUrl += ITEMID + str6;
        }
        if (!TextUtils.isEmpty(str5)) {
            this.mUrl += GORDERID + str5;
        }
        this.iGameAidlInterface = aVar;
        f.a("H5GameChargeHandler", this.mUrl);
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
