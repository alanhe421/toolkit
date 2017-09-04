package com.tencent.timint;

import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;

final class ac implements TIMValueCallBack<byte[]> {
    private /* synthetic */ int a;
    private /* synthetic */ ab b;

    ac(ab abVar, int i) {
        this.b = abVar;
        this.a = i;
    }

    public final void onError(int i, String str) {
        QLog.d("MSF.C.TIMIntManager", 1, "resend quality_report faild! code: " + i + " desc: " + str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        if (!this.b.a.dbhelper.delete(this.a)) {
            QLog.d("MSF.C.TIMIntManager", 1, "delete row from local failed, id: " + this.a);
        }
    }
}
