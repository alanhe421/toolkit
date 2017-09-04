package com.tencent.timint;

import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.quality_report.ReqBody;

final class aa implements Runnable {
    private /* synthetic */ ReqBody a;
    private /* synthetic */ TIMIntManager b;

    aa(TIMIntManager tIMIntManager, ReqBody reqBody) {
        this.b = tIMIntManager;
        this.a = reqBody;
    }

    public final void run() {
        if (!this.b.dbhelper.insert(this.a.toByteArray())) {
            QLog.d("MSF.C.TIMIntManager", 1, "Failed to store reqbody to local, uin: " + this.a.uint64_from_uin.get() + " timestamp: " + this.a.uint32_timestamp.get() + " seq: " + this.a.uint32_seq);
        }
    }
}
