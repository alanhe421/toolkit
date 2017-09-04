package com.tencent.imsdk;

import com.tencent.IMErrInfo;
import com.tencent.IMFunc;
import com.tencent.TIMValueCallBack;
import com.tencent.qalsdk.QALValueCallBack;

final class bf implements QALValueCallBack {
    final /* synthetic */ TIMValueCallBack a;
    private /* synthetic */ String b;
    private /* synthetic */ IMMsfCoreProxy c;

    bf(IMMsfCoreProxy iMMsfCoreProxy, TIMValueCallBack tIMValueCallBack, String str) {
        this.c = iMMsfCoreProxy;
        this.a = tIMValueCallBack;
        this.b = str;
    }

    public final void onError(int i, String str) {
        IMErrInfo iMErrInfo = new IMErrInfo(i, str);
        BaseConstants.covertErrorCode(iMErrInfo);
        this.a.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
    }

    public final void onSuccess(byte[] bArr) {
        QLog.d("imsdk.IMMsfCoreProxy", 1, "cmd:" + this.b + "|rsp:" + IMFunc.byte2hex(bArr));
        if (this.b.equals("im_open_msg.msg_sync") || this.b.equals("group_open_svc.group_msg_get") || this.b.equals("Recentcontact.Get_PB")) {
            this.c.pool.execute(new bg(this, bArr));
        } else {
            this.a.onSuccess(bArr);
        }
    }
}
