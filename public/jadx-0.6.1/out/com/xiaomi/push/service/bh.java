package com.xiaomi.push.service;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.e.d;
import com.xiaomi.smack.d.g;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.s;
import com.xiaomi.xmpush.thrift.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.thrift.a;

public class bh {
    public static Intent a(byte[] bArr, long j) {
        ac a = a(bArr);
        if (a == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(a.f);
        return intent;
    }

    public static ac a(Context context, ac acVar) {
        return a(context, acVar, false, false, false);
    }

    public static ac a(Context context, ac acVar, boolean z, boolean z2, boolean z3) {
        a xVar = new x();
        xVar.b(acVar.h());
        s m = acVar.m();
        if (m != null) {
            xVar.a(m.b());
            xVar.a(m.d());
            if (!TextUtils.isEmpty(m.f())) {
                xVar.c(m.f());
            }
        }
        xVar.a(ar.a(context, acVar));
        xVar.b(ar.a(z, z2, z3));
        ac a = d.a(acVar.j(), acVar.h(), xVar, com.xiaomi.xmpush.thrift.a.f);
        m = acVar.m().a();
        m.a("mat", Long.toString(System.currentTimeMillis()));
        a.a(m);
        return a;
    }

    public static ac a(byte[] bArr) {
        Object acVar = new ac();
        try {
            ar.a(acVar, bArr);
            return acVar;
        } catch (Throwable th) {
            c.a(th);
            return null;
        }
    }

    private static void a(XMPushService xMPushService, ac acVar) {
        xMPushService.a(new bi(4, xMPushService, acVar));
    }

    private static void a(XMPushService xMPushService, ac acVar, String str) {
        xMPushService.a(new bm(4, xMPushService, acVar, str));
    }

    private static void a(XMPushService xMPushService, ac acVar, String str, String str2) {
        xMPushService.a(new b(4, xMPushService, acVar, str, str2));
    }

    public static void a(XMPushService xMPushService, ac acVar, boolean z, boolean z2, boolean z3) {
        xMPushService.a(new c(4, xMPushService, acVar, z, z2, z3));
    }

    public static void a(XMPushService xMPushService, String str, byte[] bArr, Intent intent, boolean z) {
        ac a = a(bArr);
        s m = a.m();
        if (c(a) && a((Context) xMPushService, str)) {
            d(xMPushService, a);
        } else if (a(a) && !a((Context) xMPushService, str) && !b(a)) {
            e(xMPushService, a);
        } else if ((f.b(a) && b.g(xMPushService, a.f)) || a((Context) xMPushService, intent)) {
            String j;
            if (com.xiaomi.xmpush.thrift.a.a == a.a()) {
                j = a.j();
                Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                edit.putString(j, a.e);
                edit.commit();
                d.a(xMPushService).a("Register Success, package name is " + j);
            }
            if (m != null && !TextUtils.isEmpty(m.h()) && !TextUtils.isEmpty(m.j()) && m.h != 1 && (f.a(m.s()) || !f.a(xMPushService, a.f))) {
                boolean z2 = false;
                j = null;
                if (m != null) {
                    if (m.j != null) {
                        j = (String) m.j.get("jobkey");
                    }
                    if (TextUtils.isEmpty(j)) {
                        j = m.b();
                    }
                    z2 = g.a(xMPushService, a.f, j);
                }
                if (z2) {
                    c.a("drop a duplicate message, key=" + j);
                } else {
                    f$b a2 = f.a(xMPushService, a, bArr);
                    if (a2.b > 0 && !TextUtils.isEmpty(a2.a)) {
                        g.a(xMPushService, a2.a, a2.b, true, System.currentTimeMillis());
                    }
                    if (!f.b(a)) {
                        Intent intent2 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                        intent2.putExtra("mipush_payload", bArr);
                        intent2.setPackage(a.f);
                        try {
                            List queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent2, 0);
                            if (!(queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty())) {
                                xMPushService.sendBroadcast(intent2, aa.a(a.f));
                            }
                        } catch (Exception e) {
                            xMPushService.sendBroadcast(intent2, aa.a(a.f));
                        }
                    }
                }
                if (z) {
                    a(xMPushService, a, false, true, false);
                } else {
                    c(xMPushService, a);
                }
            } else if ("com.xiaomi.xmsf".contains(a.f) && !a.c() && m != null && m.s() != null && m.s().containsKey("ab")) {
                c(xMPushService, a);
                c.c("receive abtest message. ack it." + m.b());
            } else if (a(xMPushService, str, a, m)) {
                xMPushService.sendBroadcast(intent, aa.a(a.f));
            }
            if (a.a() == com.xiaomi.xmpush.thrift.a.b && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                xMPushService.stopSelf();
            }
        } else if (b.g(xMPushService, a.f)) {
            c.a("receive a mipush message, we can see the app, but we can't see the receiver.");
        } else {
            a(xMPushService, a);
        }
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) {
        ac a = a(bArr);
        if (a != null) {
            if (TextUtils.isEmpty(a.f)) {
                c.a("receive a mipush message without package name");
                return;
            }
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            Intent a2 = a(bArr, valueOf.longValue());
            String a3 = f.a(a);
            g.a(xMPushService, a3, j, true, System.currentTimeMillis());
            s m = a.m();
            if (m != null) {
                m.a("mrt", Long.toString(valueOf.longValue()));
            }
            String str;
            if (com.xiaomi.xmpush.thrift.a.e == a.a() && be.a(xMPushService).a(a.f) && !f.b(a)) {
                str = "";
                if (m != null) {
                    str = m.b();
                }
                c.a("Drop a message for unregistered, msgid=" + str);
                a(xMPushService, a, a.f);
            } else if (com.xiaomi.xmpush.thrift.a.e == a.a() && be.a(xMPushService).c(a.f) && !f.b(a)) {
                str = "";
                if (m != null) {
                    str = m.b();
                }
                c.a("Drop a message for push closed, msgid=" + str);
                a(xMPushService, a, a.f);
            } else if (com.xiaomi.xmpush.thrift.a.e != a.a() || TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") || TextUtils.equals(xMPushService.getPackageName(), a.f)) {
                if (!(m == null || m.b() == null)) {
                    c.a(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[]{a.h(), m.b()}));
                }
                if (m != null) {
                    Map s = m.s();
                    if (s != null && s.containsKey("hide") && "true".equalsIgnoreCase((String) s.get("hide"))) {
                        c(xMPushService, a);
                        return;
                    }
                }
                if (!(m == null || m.s() == null || !m.s().containsKey("__miid"))) {
                    str = (String) m.s().get("__miid");
                    Account a4 = f.a(xMPushService);
                    if (((a4 == null ? 1 : 0) | (!TextUtils.equals(str, a4.name) ? 1 : 0)) != 0) {
                        c.a(new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        a(xMPushService, a, "miid already logout or anther already login", new StringBuilder().append(str).append(" should be login, but got ").append(a4).toString() == null ? "nothing" : a4.name);
                        return;
                    }
                }
                boolean z = m != null && a(m.s());
                if (z) {
                    if (au.e(xMPushService)) {
                        boolean z2 = a(m) && au.d(xMPushService);
                        if (!z2) {
                            z2 = true;
                        } else if (b(xMPushService, a)) {
                            z2 = a(xMPushService, m, bArr);
                        } else {
                            return;
                        }
                        a(xMPushService, a, true, false, false);
                        if (!z2) {
                            return;
                        }
                    }
                    return;
                }
                a(xMPushService, a3, bArr, a2, z);
            } else {
                c.a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + a.f);
                a(xMPushService, a, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + a.f);
            }
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    private static boolean a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            return (packageManager.queryBroadcastReceivers(intent2, 32).isEmpty() && packageManager.queryIntentServices(intent, 32).isEmpty()) ? false : true;
        } catch (Throwable e) {
            c.a(e);
            return false;
        }
    }

    private static boolean a(XMPushService xMPushService, s sVar, byte[] bArr) {
        Map s = sVar.s();
        String[] split = ((String) s.get("__geo_ids")).split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            if (ar.a(xMPushService).a(str) != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("geo_id", str);
                contentValues.put("message_id", sVar.b());
                int parseInt = Integer.parseInt((String) s.get("__geo_action"));
                contentValues.put("action", Integer.valueOf(parseInt));
                contentValues.put(MessageKey.MSG_CONTENT, bArr);
                contentValues.put("deadline", Long.valueOf(Long.parseLong((String) s.get("__geo_deadline"))));
                if (TextUtils.equals(ar.a(xMPushService).c(str), "Enter") && parseInt == 1) {
                    return true;
                }
                arrayList.add(contentValues);
            }
        }
        if (!at.a((Context) xMPushService).a(arrayList)) {
            c.c("geofence added some new geofence message failed messagi_id:" + sVar.b());
        }
        return false;
    }

    private static boolean a(XMPushService xMPushService, String str, ac acVar, s sVar) {
        if (sVar == null || sVar.s() == null || !sVar.s().containsKey("__check_alive") || !sVar.s().containsKey("__awake")) {
            return true;
        }
        a afVar = new af();
        afVar.b(acVar.h());
        afVar.d(str);
        afVar.c(p.O.T);
        afVar.a(sVar.b());
        afVar.h = new HashMap();
        boolean f = b.f(xMPushService.getApplicationContext(), str);
        afVar.h.put("app_running", Boolean.toString(f));
        if (!f) {
            f = Boolean.parseBoolean((String) sVar.s().get("__awake"));
            afVar.h.put("awaked", Boolean.toString(f));
            if (!f) {
                f = false;
                d.a(xMPushService, d.a(acVar.j(), acVar.h(), afVar, com.xiaomi.xmpush.thrift.a.i));
                return f;
            }
        }
        f = true;
        try {
            d.a(xMPushService, d.a(acVar.j(), acVar.h(), afVar, com.xiaomi.xmpush.thrift.a.i));
            return f;
        } catch (Throwable e) {
            c.a(e);
            return f;
        }
    }

    private static boolean a(ac acVar) {
        return "com.xiaomi.xmsf".equals(acVar.f) && acVar.m() != null && acVar.m().s() != null && acVar.m().s().containsKey("miui_package_name");
    }

    public static boolean a(s sVar) {
        if (sVar == null) {
            return false;
        }
        Map s = sVar.s();
        return s != null ? TextUtils.equals("1", (CharSequence) s.get("__geo_local_check")) : false;
    }

    private static boolean a(Map<String, String> map) {
        return map != null && map.containsKey("__geo_ids");
    }

    private static boolean b(XMPushService xMPushService, ac acVar) {
        if (!au.a(xMPushService)) {
            return false;
        }
        if (!au.c(xMPushService)) {
            return false;
        }
        if (b.g(xMPushService, acVar.f)) {
            Map s = acVar.m().s();
            return s == null ? false : !Constants.VIA_REPORT_TYPE_SET_AVATAR.contains((CharSequence) s.get("__geo_action")) ? false : !TextUtils.isEmpty((CharSequence) s.get("__geo_ids"));
        } else {
            a(xMPushService, acVar);
            return false;
        }
    }

    private static boolean b(ac acVar) {
        Map s = acVar.m().s();
        return s != null && s.containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, ac acVar) {
        xMPushService.a(new bj(4, xMPushService, acVar));
    }

    private static boolean c(ac acVar) {
        return (acVar.m() == null || acVar.m().s() == null) ? false : "1".equals(acVar.m().s().get("obslete_ads_message"));
    }

    private static void d(XMPushService xMPushService, ac acVar) {
        xMPushService.a(new bk(4, xMPushService, acVar));
    }

    private static void e(XMPushService xMPushService, ac acVar) {
        xMPushService.a(new bl(4, xMPushService, acVar));
    }

    public void a(Context context, am.b bVar, boolean z, int i, String str) {
        if (!z) {
            bc a = bd.a(context);
            if (a != null && "token-expired".equals(str)) {
                try {
                    bd.a(context, a.d, a.e, a.f);
                } catch (Throwable e) {
                    c.a(e);
                } catch (Throwable e2) {
                    c.a(e2);
                }
            }
        }
    }

    public void a(XMPushService xMPushService, com.xiaomi.c.b bVar, am.b bVar2) {
        try {
            a(xMPushService, bVar.d(bVar2.i), (long) bVar.l());
        } catch (Throwable e) {
            c.a(e);
        }
    }

    public void a(XMPushService xMPushService, com.xiaomi.smack.packet.d dVar, am.b bVar) {
        if (dVar instanceof com.xiaomi.smack.packet.c) {
            com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) dVar;
            com.xiaomi.smack.packet.a p = cVar.p("s");
            if (p != null) {
                try {
                    a(xMPushService, s.b(s.a(bVar.i, cVar.k()), p.c()), (long) g.a(dVar.c()));
                    return;
                } catch (Throwable e) {
                    c.a(e);
                    return;
                }
            }
            return;
        }
        c.a("not a mipush message");
    }
}
