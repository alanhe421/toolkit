package com.tencent.imsdk;

import com.tencent.TIMValueCallBack;
import com.tencent.openqq.protocol.imsdk.msg_push.ReqBody;

final class bd implements Runnable {
    private /* synthetic */ TIMValueCallBack a;
    private /* synthetic */ ReqBody b;

    bd(bb bbVar, TIMValueCallBack tIMValueCallBack, ReqBody reqBody) {
        this.a = tIMValueCallBack;
        this.b = reqBody;
    }

    public final void run() {
        this.a.onSuccess(this.b.msg_msg.msg_msg_body.msg_content.get().toByteArray());
    }
}
