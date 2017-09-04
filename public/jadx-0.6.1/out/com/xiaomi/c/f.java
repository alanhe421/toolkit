package com.xiaomi.c;

import android.text.TextUtils;
import com.google.protobuf.micro.a;
import com.google.protobuf.micro.d;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.b.b.j;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import com.xiaomi.smack.b;
import com.xiaomi.smack.d.g;
import com.xiaomi.smack.h;
import com.xiaomi.smack.l;

public class f extends h {
    private Thread w;
    private c x;
    private d y;

    public f(XMPushService xMPushService, b bVar) {
        super(xMPushService, bVar);
    }

    private b c(boolean z) {
        b bVar = new b();
        bVar.a("PING", null);
        if (z) {
            bVar.a("1");
        } else {
            bVar.a("0");
        }
        j jVar = new j();
        byte[] a = c().a();
        if (a != null) {
            try {
                jVar.a(com.xiaomi.push.b.b.b.b(a));
            } catch (d e) {
            }
        }
        a = com.xiaomi.d.h.c();
        if (a != null) {
            jVar.a(a.a(a));
        }
        bVar.a(jVar.c(), null);
        return bVar;
    }

    private void w() {
        try {
            this.x = new c(this.q.getInputStream(), this);
            this.y = new d(this.q.getOutputStream(), this);
            this.w = new g(this, "Blob Reader (" + this.l + ")");
            this.w.start();
        } catch (Throwable e) {
            throw new l("Error to init reader and writer", e);
        }
    }

    protected synchronized void a(int i, Exception exception) {
        if (this.x != null) {
            this.x.b();
            this.x = null;
        }
        if (this.y != null) {
            try {
                this.y.b();
            } catch (Throwable e) {
                c.a(e);
            }
            this.y = null;
        }
        super.a(i, exception);
    }

    void a(b bVar) {
        if (bVar != null) {
            if (bVar.d()) {
                c.a("[Slim] RCV blob chid=" + bVar.c() + "; id=" + bVar.h() + "; errCode=" + bVar.e() + "; err=" + bVar.f());
            }
            if (bVar.c() == 0) {
                if ("PING".equals(bVar.a())) {
                    c.a("[Slim] RCV ping id=" + bVar.h());
                    v();
                } else if ("CLOSE".equals(bVar.a())) {
                    c(13, null);
                }
            }
            for (com.xiaomi.smack.a.a a : this.g.values()) {
                a.a(bVar);
            }
        }
    }

    public synchronized void a(am.b bVar) {
        a.a(bVar, r(), this);
    }

    @Deprecated
    public void a(com.xiaomi.smack.packet.d dVar) {
        b(b.a(dVar, null));
    }

    public synchronized void a(String str, String str2) {
        a.a(str, str2, this);
    }

    protected void a(boolean z) {
        if (this.y != null) {
            b c = c(z);
            c.a("[Slim] SND ping id=" + c.h());
            b(c);
            u();
            return;
        }
        throw new l("The BlobWriter is null.");
    }

    public void a(b[] bVarArr) {
        for (b b : bVarArr) {
            b(b);
        }
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        for (com.xiaomi.smack.packet.d a : dVarArr) {
            a(a);
        }
    }

    public boolean a() {
        return true;
    }

    protected synchronized void b() {
        w();
        this.y.a();
    }

    public void b(b bVar) {
        if (this.y != null) {
            try {
                int a = this.y.a(bVar);
                this.o = System.currentTimeMillis();
                Object i = bVar.i();
                if (!TextUtils.isEmpty(i)) {
                    g.a(this.n, i, (long) a, false, System.currentTimeMillis());
                }
                for (com.xiaomi.smack.a.a a2 : this.h.values()) {
                    a2.a(bVar);
                }
                return;
            } catch (Throwable e) {
                throw new l(e);
            }
        }
        throw new l("the writer is null.");
    }

    void b(com.xiaomi.smack.packet.d dVar) {
        if (dVar != null) {
            for (com.xiaomi.smack.a.a a : this.g.values()) {
                a.a(dVar);
            }
        }
    }
}
