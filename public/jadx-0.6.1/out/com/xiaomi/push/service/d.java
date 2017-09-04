package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.c.b;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.smack.l;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.v;
import java.nio.ByteBuffer;
import org.apache.thrift.a;

final class d {
    static b a(XMPushService xMPushService, byte[] bArr) {
        ac acVar = new ac();
        try {
            ar.a((a) acVar, bArr);
            return a(bd.a(xMPushService), (Context) xMPushService, acVar);
        } catch (Throwable e) {
            c.a(e);
            return null;
        }
    }

    static b a(bc bcVar, Context context, ac acVar) {
        try {
            b bVar = new b();
            bVar.a(5);
            bVar.c(bcVar.a);
            bVar.b(a(acVar));
            bVar.a("SECMSG", "message");
            String str = bcVar.a;
            acVar.g.b = str.substring(0, str.indexOf("@"));
            acVar.g.d = str.substring(str.indexOf("/") + 1);
            bVar.a(ar.a(acVar), bcVar.c);
            bVar.a((short) 1);
            c.a("try send mi push message. packagename:" + acVar.f + " action:" + acVar.a);
            return bVar;
        } catch (Throwable e) {
            c.a(e);
            return null;
        }
    }

    static ac a(String str, String str2) {
        a afVar = new af();
        afVar.b(str2);
        afVar.c("package uninstalled");
        afVar.a(com.xiaomi.smack.packet.d.j());
        afVar.a(false);
        return a(str, str2, afVar, com.xiaomi.xmpush.thrift.a.Notification);
    }

    static <T extends a<T, ?>> ac a(String str, String str2, T t, com.xiaomi.xmpush.thrift.a aVar) {
        byte[] a = ar.a(t);
        ac acVar = new ac();
        v vVar = new v();
        vVar.a = 5;
        vVar.b = "fakeid";
        acVar.a(vVar);
        acVar.a(ByteBuffer.wrap(a));
        acVar.a(aVar);
        acVar.c(true);
        acVar.b(str);
        acVar.a(false);
        acVar.a(str2);
        return acVar;
    }

    private static String a(ac acVar) {
        if (!(acVar.h == null || acVar.h.k == null)) {
            String str = (String) acVar.h.k.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return acVar.f;
    }

    static void a(XMPushService xMPushService) {
        if (bd.a(xMPushService.getApplicationContext()) != null) {
            am.b a = bd.a(xMPushService.getApplicationContext()).a(xMPushService);
            a(xMPushService, a);
            am.a().a(a);
        }
    }

    static void a(XMPushService xMPushService, am.b bVar) {
        bVar.a(new e(xMPushService));
    }

    static void a(XMPushService xMPushService, ac acVar) {
        com.xiaomi.smack.a h = xMPushService.h();
        if (h == null) {
            throw new l("try send msg while connection is null.");
        } else if (h.a()) {
            b a = a(bd.a(xMPushService), (Context) xMPushService, acVar);
            if (a != null) {
                h.b(a);
            }
        } else {
            throw new l("Don't support XMPP connection.");
        }
    }

    static void a(XMPushService xMPushService, String str, byte[] bArr) {
        com.xiaomi.smack.a h = xMPushService.h();
        if (h == null) {
            throw new l("try send msg while connection is null.");
        } else if (h.a()) {
            b a = a(xMPushService, bArr);
            if (a != null) {
                h.b(a);
            } else {
                bg.a(xMPushService, str, bArr, 70000003, "not a valid message");
            }
        } else {
            throw new l("Don't support XMPP connection.");
        }
    }
}
