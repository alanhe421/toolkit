package com.tencent.qalsdk.service;

import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.sdk.w;

/* compiled from: MsfServiceRespHandler */
public class f extends Thread {
    private static String c = "MSF.S.RespHandler";
    public volatile boolean a = true;
    j b;

    public f(j jVar) {
        this.b = jVar;
    }

    public void run() {
        while (this.a) {
            try {
                w wVar = (w) this.b.e().take();
                if (wVar != null) {
                    if (wVar.a != null) {
                        c.a(g.b(wVar.a), wVar.a, wVar.b);
                    } else if (wVar.b != null) {
                        c.a(g.a(wVar.b), wVar.a, wVar.b);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
