package com.qq.reader.module.redpacket.square.a;

import com.qq.reader.appconfig.a.b;
import com.qq.reader.module.redpacket.square.b.a;
import com.qq.reader.module.redpacket.square.data.d;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: RedPacketSquareController */
public class c extends b {
    private static WeakReferenceHandler a;
    private com.qq.reader.module.redpacket.square.data.c b;
    private d c;
    private com.qq.reader.module.redpacket.square.data.b d;

    public c() {
        b();
    }

    public void b() {
        this.c = d.b();
        this.b = com.qq.reader.module.redpacket.square.data.c.b();
        this.d = com.qq.reader.module.redpacket.square.data.b.b();
    }

    public void a(WeakReferenceHandler weakReferenceHandler) {
        a = weakReferenceHandler;
        this.c.a(weakReferenceHandler);
    }

    public void a() {
        this.c.a();
        this.d.a();
        this.b.a();
    }

    public void a(a aVar) {
        this.c.a(aVar);
    }

    public long c() {
        return this.b.c();
    }

    public void a(int i, long j) {
        this.c.a(i, j);
    }

    public void a(long j) {
        this.b.a(j);
    }

    public long d() {
        return this.c.c();
    }

    public long e() {
        return this.c.d();
    }
}
