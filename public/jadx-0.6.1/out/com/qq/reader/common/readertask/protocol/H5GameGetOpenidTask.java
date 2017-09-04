package com.qq.reader.common.readertask.protocol;

import android.os.RemoteException;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.web.a.a;
import java.util.HashMap;

public class H5GameGetOpenidTask extends ReaderProtocolJSONTask {
    private a iGameAidlInterface;
    private String webJSCallBack;

    public H5GameGetOpenidTask() {
        this.mUrl = e.be;
    }

    public H5GameGetOpenidTask(a aVar) {
        this.mUrl = e.be;
        this.iGameAidlInterface = aVar;
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
