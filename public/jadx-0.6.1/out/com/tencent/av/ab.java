package com.tencent.av;

import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;

final class ab implements TIMValueCallBack<byte[]> {
    ab(PingUtil pingUtil) {
    }

    public final void onError(int i, String str) {
        QLog.e("av.PingUtil", 1, "reportError: code " + i + " desc " + str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        QLog.e("av.PingUtil", 1, "ping report Success");
    }
}
