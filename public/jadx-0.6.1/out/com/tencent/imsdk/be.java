package com.tencent.imsdk;

import com.tencent.av.TIMAvManager;
import com.tencent.openqq.protocol.imsdk.msg_push.ReqBody;

final class be implements Runnable {
    private /* synthetic */ ReqBody a;

    be(bb bbVar, ReqBody reqBody) {
        this.a = reqBody;
    }

    public final void run() {
        TIMAvManager.getInstance().MsgNotify(this.a.msg_msg.msg_msg_body.msg_content.get().toByteArray());
    }
}
