package com.tencent.upload.network.b;

import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.c.a;

final class d implements Runnable {
    private /* synthetic */ a a;
    private /* synthetic */ c b;

    d(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public final void run() {
        if (!this.b.i.remove(this.a)) {
            this.a.b();
        } else if (this.b.h == a.AVAILABLE || this.b.h != a.DETECTING) {
            this.a.b();
        } else {
            while (this.b.i.size() > 0) {
                ((a) this.b.i.remove(0)).b();
            }
            if (this.a.h()) {
                this.b.f = this.a;
                this.b.a(a.AVAILABLE);
                com.tencent.upload.common.a.a.b(this.b.f(), "open comepleted! sid=" + this.b.f.hashCode() + " route=" + this.b.f.c());
                return;
            }
            k d = this.a.d();
            if (d != null) {
                a hVar = new h(this.b.e, false, this.b.d.getLooper(), this.b);
                if (hVar.a(d)) {
                    this.b.i.add(hVar);
                    return;
                }
                this.b.f = this.a;
                this.b.a(a.AVAILABLE);
                com.tencent.upload.common.a.a.b(this.b.f(), "open comepleted! sid=" + this.b.f.hashCode() + " route=" + this.b.f.c());
                return;
            }
            this.b.f = this.a;
            this.b.a(a.AVAILABLE);
            com.tencent.upload.common.a.a.b(this.b.f(), "open comepleted! sid=" + this.b.f.hashCode() + " route=" + this.b.f.c());
        }
    }
}
