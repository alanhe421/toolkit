package com.tencent.imsdk;

import com.tencent.TIMValueCallBack;

final class ag implements TIMValueCallBack<byte[]> {
    ag(IMMsfCoreProxy iMMsfCoreProxy) {
    }

    public final void onError(int i, String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "log report failed, code " + i + "|desc " + str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        QLog.d("imsdk.IMMsfCoreProxy", 1, "log report succ");
    }
}
