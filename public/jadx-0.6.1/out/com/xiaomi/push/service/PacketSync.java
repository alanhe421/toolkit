package com.xiaomi.push.service;

import android.os.Parcelable;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.d.h;
import com.xiaomi.network.b;
import com.xiaomi.network.f;
import com.xiaomi.push.b.b.i;
import com.xiaomi.push.b.b.j;
import com.xiaomi.push.b.b.k;
import com.xiaomi.smack.d.g;
import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.packet.d;
import java.util.Date;

public class PacketSync {
    private XMPushService a;

    public interface PacketReceiveHandler extends Parcelable {
    }

    PacketSync(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private void a(a aVar) {
        Object c = aVar.c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
            b a = f.a().a(com.xiaomi.smack.b.b(), false);
            if (a != null && split.length > 0) {
                a.a(split);
                this.a.a(20, null);
                this.a.a(true);
            }
        }
    }

    private void b(d dVar) {
        Object m = dVar.m();
        Object l = dVar.l();
        if (!TextUtils.isEmpty(m) && !TextUtils.isEmpty(l)) {
            am.b b = am.a().b(l, m);
            if (b != null) {
                g.a(this.a, b.a, (long) g.a(dVar.c()), true, System.currentTimeMillis());
            }
        }
    }

    private void c(com.xiaomi.c.b bVar) {
        Object j = bVar.j();
        Object num = Integer.toString(bVar.c());
        if (!TextUtils.isEmpty(j) && !TextUtils.isEmpty(num)) {
            am.b b = am.a().b(num, j);
            if (b != null) {
                g.a(this.a, b.a, (long) bVar.l(), true, System.currentTimeMillis());
            }
        }
    }

    public void a(com.xiaomi.c.b bVar) {
        if (5 != bVar.c()) {
            c(bVar);
        }
        try {
            b(bVar);
        } catch (Throwable e) {
            c.a("handle Blob chid = " + bVar.c() + " cmd = " + bVar.a() + " packetid = " + bVar.h() + " failure ", e);
        }
    }

    public void a(d dVar) {
        if (!"5".equals(dVar.l())) {
            b(dVar);
        }
        String l = dVar.l();
        if (TextUtils.isEmpty(l)) {
            l = "1";
            dVar.l(l);
        }
        if (l.equals("0")) {
            c.a("Received wrong packet with chid = 0 : " + dVar.c());
        }
        a p;
        if (dVar instanceof com.xiaomi.smack.packet.b) {
            p = dVar.p("kick");
            if (p != null) {
                String m = dVar.m();
                String a = p.a("type");
                String a2 = p.a("reason");
                c.a("kicked by server, chid=" + l + " userid=" + m + " type=" + a + " reason=" + a2);
                if ("wait".equals(a)) {
                    am.b b = am.a().b(l, m);
                    if (b != null) {
                        this.a.a(b);
                        b.a(am.c.unbind, 3, 0, a2, a);
                        return;
                    }
                    return;
                }
                this.a.a(l, m, 3, a2, a);
                am.a().a(l, m);
                return;
            }
        } else if (dVar instanceof com.xiaomi.smack.packet.c) {
            com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) dVar;
            if ("redir".equals(cVar.a())) {
                p = cVar.p("hosts");
                if (p != null) {
                    a(p);
                    return;
                }
                return;
            }
        }
        this.a.e().a(this.a, l, dVar);
    }

    public void b(com.xiaomi.c.b bVar) {
        String a = bVar.a();
        switch (bVar.c()) {
            case 0:
                if ("PING".equals(a)) {
                    byte[] k = bVar.k();
                    if (k != null && k.length > 0) {
                        j b = j.b(k);
                        if (b.f()) {
                            v.a().a(b.g());
                        }
                    }
                    if ("1".equals(bVar.h())) {
                        this.a.a();
                        return;
                    } else {
                        h.b();
                        return;
                    }
                } else if ("SYNC".equals(a)) {
                    if ("CONF".equals(bVar.b())) {
                        v.a().a(com.xiaomi.push.b.b.b.b(bVar.k()));
                        return;
                    } else if (TextUtils.equals("U", bVar.b())) {
                        k b2 = k.b(bVar.k());
                        com.xiaomi.push.a.b.a(this.a).a(b2.d(), b2.f(), new Date(b2.h()), new Date(b2.j()), b2.n() * 1024, b2.l());
                        com.xiaomi.c.b bVar2 = new com.xiaomi.c.b();
                        bVar2.a(0);
                        bVar2.a(bVar.a(), "UCA");
                        bVar2.a(bVar.h());
                        this.a.a(new u(this.a, bVar2));
                        return;
                    } else if (TextUtils.equals("P", bVar.b())) {
                        i b3 = i.b(bVar.k());
                        com.xiaomi.c.b bVar3 = new com.xiaomi.c.b();
                        bVar3.a(0);
                        bVar3.a(bVar.a(), "PCA");
                        bVar3.a(bVar.h());
                        i iVar = new i();
                        if (b3.e()) {
                            iVar.a(b3.d());
                        }
                        bVar3.a(iVar.c(), null);
                        this.a.a(new u(this.a, bVar3));
                        c.a("ACK msgP: id = " + bVar.h());
                        return;
                    } else {
                        return;
                    }
                } else if ("NOTIFY".equals(bVar.a())) {
                    com.xiaomi.push.b.b.h b4 = com.xiaomi.push.b.b.h.b(bVar.k());
                    c.a("notify by server err = " + b4.d() + " desc = " + b4.f());
                    return;
                } else {
                    return;
                }
            default:
                String num = Integer.toString(bVar.c());
                if ("SECMSG".equals(bVar.a())) {
                    if (bVar.d()) {
                        c.a("Recv SECMSG errCode = " + bVar.e() + " errStr = " + bVar.f());
                        return;
                    } else {
                        this.a.e().a(this.a, num, bVar);
                        return;
                    }
                } else if ("BIND".equals(a)) {
                    com.xiaomi.push.b.b.d b5 = com.xiaomi.push.b.b.d.b(bVar.k());
                    String j = bVar.j();
                    r0 = am.a().b(num, j);
                    if (r0 == null) {
                        return;
                    }
                    if (b5.d()) {
                        c.a("SMACK: channel bind succeeded, chid=" + bVar.c());
                        r0.a(am.c.binded, 1, 0, null, null);
                        return;
                    }
                    r5 = b5.f();
                    if ("auth".equals(r5)) {
                        if ("invalid-sig".equals(b5.h())) {
                            c.a("SMACK: bind error invalid-sig token = " + r0.c + " sec = " + r0.i);
                            h.a(0, com.xiaomi.push.thrift.a.BIND_INVALID_SIG.a(), 1, null, 0);
                        }
                        r0.a(am.c.unbind, 1, 5, b5.h(), r5);
                        am.a().a(num, j);
                    } else if ("cancel".equals(r5)) {
                        r0.a(am.c.unbind, 1, 7, b5.h(), r5);
                        am.a().a(num, j);
                    } else if ("wait".equals(r5)) {
                        this.a.a(r0);
                        r0.a(am.c.unbind, 1, 7, b5.h(), r5);
                    }
                    c.a("SMACK: channel bind failed, chid=" + num + " reason=" + b5.h());
                    return;
                } else if ("KICK".equals(a)) {
                    com.xiaomi.push.b.b.g b6 = com.xiaomi.push.b.b.g.b(bVar.k());
                    String j2 = bVar.j();
                    r5 = b6.d();
                    String f = b6.f();
                    c.a("kicked by server, chid=" + num + " userid=" + j2 + " type=" + r5 + " reason=" + f);
                    if ("wait".equals(r5)) {
                        r0 = am.a().b(num, j2);
                        if (r0 != null) {
                            this.a.a(r0);
                            r0.a(am.c.unbind, 3, 0, f, r5);
                            return;
                        }
                        return;
                    }
                    this.a.a(num, j2, 3, f, r5);
                    am.a().a(num, j2);
                    return;
                } else {
                    return;
                }
        }
    }
}
