package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.util.QLog;

/* compiled from: CoreWrapper */
class g extends Thread {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public void run() {
        while (true) {
            try {
                w wVar = (w) e.e.j().k();
                if (wVar != null) {
                    try {
                        if (wVar.a == null) {
                            e.K.post(new h(this, wVar));
                        } else if (!this.a.a(wVar.a, wVar.b)) {
                            QLog.e("CoreWrapper", 1, "unknown resp to" + wVar.a + " from:" + wVar.b);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
