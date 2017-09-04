package com.tencent.timint;

import com.tencent.imsdk.QLog;
import java.util.Map;
import java.util.Map.Entry;

final class ab implements Runnable {
    final /* synthetic */ TIMIntManager a;
    private /* synthetic */ String b;

    ab(TIMIntManager tIMIntManager, String str) {
        this.a = tIMIntManager;
        this.b = str;
    }

    public final void run() {
        Map read = this.a.dbhelper.read();
        if (read == null) {
            QLog.d("MSF.C.TIMIntManager", 1, "failed to get map from db");
            return;
        }
        for (Entry entry : read.entrySet()) {
            int parseInt = Integer.parseInt((String) entry.getKey());
            this.a.request(this.b, (byte[]) entry.getValue(), new ac(this, parseInt));
        }
    }
}
