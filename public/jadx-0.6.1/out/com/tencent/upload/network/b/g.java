package com.tencent.upload.network.b;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.upload.common.a.a;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.a.b;
import java.util.ArrayList;
import java.util.List;

final class g implements Runnable {
    private /* synthetic */ a a;
    private /* synthetic */ int b;
    private /* synthetic */ List c;
    private /* synthetic */ f d;

    g(f fVar, a aVar, int i, List list) {
        this.d = fVar;
        this.a = aVar;
        this.b = i;
        this.c = list;
    }

    public final void run() {
        k c = this.a.c();
        List arrayList = new ArrayList();
        for (int i = 0; i < this.b; i++) {
            a hVar = new h(this.d.f, false, this.a.a(), null);
            hVar.a(c);
            arrayList.add(hVar);
        }
        long j = BuglyBroadcastRecevier.UPLOADLIMITED;
        while (j > 0 && arrayList.size() > 0) {
            try {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    a aVar = (a) arrayList.get(size);
                    if (aVar.g() == b.ESTABLISHED) {
                        this.c.add(aVar);
                        arrayList.remove(size);
                        a.c(this.d.d(), "clone session async succeed! sid=" + aVar.hashCode());
                    } else if (aVar.g() == b.NO_CONNECT) {
                        arrayList.remove(size);
                    }
                }
                j -= 20;
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.d.i = false;
    }
}
