package com.qq.reader.common.readertask.protocol;

import android.os.RemoteException;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.web.a.a;
import java.util.HashMap;

public class ProfileNetTask extends ReaderProtocolJSONTask {
    private a iGameAidlInterface;

    public ProfileNetTask(c cVar) {
        super(cVar);
        this.mUrl = e.a + "nativepage/getAcctInfo";
    }

    public ProfileNetTask(c cVar, a aVar) {
        super(cVar);
        this.mUrl = e.a + "nativepage/getAcctInfo";
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
}
