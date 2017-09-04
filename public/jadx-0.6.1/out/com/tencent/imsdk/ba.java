package com.tencent.imsdk;

import com.tencent.IMFunc;
import com.tencent.qalsdk.QALPushListener;

final class ba implements QALPushListener {
    final /* synthetic */ IMMsfCoreProxy a;

    ba(IMMsfCoreProxy iMMsfCoreProxy) {
        this.a = iMMsfCoreProxy;
    }

    public final void onError(String str, int i, String str2) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "recv push cmd im_open_push.msg_push error, id: " + str + "|code:" + i + "|desc: " + str2);
    }

    public final void onSuccess(String str, byte[] bArr) {
        QLog.d("imsdk.IMMsfCoreProxy", 1, "cmd:im_open_push.msg_push|req:" + IMFunc.byte2hex(bArr));
        this.a.pool.execute(new bb(this, str, bArr));
    }
}
