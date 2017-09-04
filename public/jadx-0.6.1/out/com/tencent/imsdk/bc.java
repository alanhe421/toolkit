package com.tencent.imsdk;

import com.tencent.openqq.IMPushListener;
import com.tencent.openqq.protocol.imsdk.msg_push.ReqBody;

final class bc implements Runnable {
    private /* synthetic */ IMPushListener a;
    private /* synthetic */ ReqBody b;

    bc(bb bbVar, IMPushListener iMPushListener, ReqBody reqBody) {
        this.a = iMPushListener;
        this.b = reqBody;
    }

    public final void run() {
        this.a.onRecv(this.b.msg_msg.msg_msg_body.msg_content.get().toByteArray());
    }
}
