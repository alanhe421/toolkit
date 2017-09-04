package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.push.service.f;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.l;
import com.xiaomi.push.service.o;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ah;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.am;
import com.xiaomi.xmpush.thrift.ao;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.s;
import com.xiaomi.xmpush.thrift.u;
import com.xiaomi.xmpush.thrift.x;
import com.xiaomi.xmpush.thrift.y;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.apache.thrift.a;
import tencent.tls.platform.SigType;

public class aj {
    private static aj a = null;
    private static Queue<String> c;
    private static Object d = new Object();
    private Context b;

    private aj(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static Intent a(Context context, String str, Map<String, String> map) {
        Intent launchIntentForPackage;
        URISyntaxException e;
        Intent intent;
        MalformedURLException malformedURLException;
        if (map == null || !map.containsKey("notify_effect")) {
            return null;
        }
        String str2 = (String) map.get("notify_effect");
        if (o.a.equals(str2)) {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e2) {
                c.d("Cause: " + e2.getMessage());
                launchIntentForPackage = null;
            }
        } else {
            if (o.b.equals(str2)) {
                if (map.containsKey("intent_uri")) {
                    str2 = (String) map.get("intent_uri");
                    if (str2 != null) {
                        try {
                            launchIntentForPackage = Intent.parseUri(str2, 1);
                            try {
                                launchIntentForPackage.setPackage(str);
                            } catch (URISyntaxException e3) {
                                e = e3;
                                c.d("Cause: " + e.getMessage());
                                if (launchIntentForPackage == null) {
                                    return null;
                                }
                                launchIntentForPackage.addFlags(SigType.TLS);
                                try {
                                    return context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null ? null : launchIntentForPackage;
                                } catch (Exception e22) {
                                    c.d("Cause: " + e22.getMessage());
                                    return null;
                                }
                            }
                        } catch (URISyntaxException e4) {
                            e = e4;
                            launchIntentForPackage = null;
                            c.d("Cause: " + e.getMessage());
                            if (launchIntentForPackage == null) {
                                return null;
                            }
                            launchIntentForPackage.addFlags(SigType.TLS);
                            if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                            }
                        }
                    }
                    launchIntentForPackage = null;
                } else if (map.containsKey("class_name")) {
                    str2 = (String) map.get("class_name");
                    intent = new Intent();
                    intent.setComponent(new ComponentName(str, str2));
                    try {
                        if (map.containsKey("intent_flag")) {
                            intent.setFlags(Integer.parseInt((String) map.get("intent_flag")));
                        }
                    } catch (NumberFormatException e5) {
                        c.d("Cause by intent_flag: " + e5.getMessage());
                    }
                    launchIntentForPackage = intent;
                }
            } else if (o.c.equals(str2)) {
                str2 = (String) map.get("web_uri");
                if (str2 != null) {
                    str2 = str2.trim();
                    String str3 = (str2.startsWith("http://") || str2.startsWith("https://")) ? str2 : "http://" + str2;
                    try {
                        str2 = new URL(str3).getProtocol();
                        if (com.tencent.qalsdk.core.c.d.equals(str2) || "https".equals(str2)) {
                            launchIntentForPackage = new Intent("android.intent.action.VIEW");
                            try {
                                launchIntentForPackage.setData(Uri.parse(str3));
                            } catch (MalformedURLException e6) {
                                MalformedURLException malformedURLException2 = e6;
                                intent = launchIntentForPackage;
                                malformedURLException = malformedURLException2;
                                c.d("Cause: " + malformedURLException.getMessage());
                                launchIntentForPackage = intent;
                                if (launchIntentForPackage == null) {
                                    return null;
                                }
                                launchIntentForPackage.addFlags(SigType.TLS);
                                if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                                }
                            }
                        }
                        launchIntentForPackage = null;
                    } catch (MalformedURLException e7) {
                        malformedURLException = e7;
                        intent = null;
                        c.d("Cause: " + malformedURLException.getMessage());
                        launchIntentForPackage = intent;
                        if (launchIntentForPackage == null) {
                            return null;
                        }
                        launchIntentForPackage.addFlags(SigType.TLS);
                        if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
                        }
                    }
                }
            }
            launchIntentForPackage = null;
        }
        if (launchIntentForPackage == null) {
            return null;
        }
        launchIntentForPackage.addFlags(SigType.TLS);
        if (context.getPackageManager().resolveActivity(launchIntentForPackage, 65536) != null) {
        }
    }

    private a a(ac acVar, boolean z, byte[] bArr) {
        a aVar = null;
        try {
            a a = ah.a(this.b, acVar);
            if (a == null) {
                c.d("receiving an un-recognized message. " + acVar.a);
                return null;
            }
            c.c("receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = acVar.a();
            c.a("processing a message, action=" + a2);
            String b;
            String str;
            List list;
            switch (ak.a[a2.ordinal()]) {
                case 1:
                    if (!q.a(this.b).k() || z) {
                        ak akVar = (ak) a;
                        r l = akVar.l();
                        if (l == null) {
                            c.d("receive an empty message without push content, drop it");
                            return null;
                        }
                        if (z) {
                            if (f.b(acVar)) {
                                c.a(this.b, l.b(), acVar.m(), acVar.f, l.d());
                            } else {
                                c.a(this.b, l.b(), acVar.m(), l.d());
                            }
                        }
                        if (!z) {
                            if (!TextUtils.isEmpty(akVar.j()) && c.i(this.b, akVar.j()) < 0) {
                                c.a(this.b, akVar.j());
                            } else if (!TextUtils.isEmpty(akVar.h()) && c.g(this.b, akVar.h()) < 0) {
                                c.e(this.b, akVar.h());
                            }
                        }
                        CharSequence charSequence = (acVar.h == null || acVar.h.s() == null) ? null : (String) acVar.h.j.get("jobkey");
                        if (TextUtils.isEmpty(charSequence)) {
                            b = l.b();
                        } else {
                            CharSequence charSequence2 = charSequence;
                        }
                        if (z || !a(this.b, b)) {
                            Serializable a3 = f.a(akVar, acVar.m(), z);
                            if (a3.getPassThrough() == 0 && !z && f.a(a3.getExtra())) {
                                f.a(this.b, acVar, bArr);
                                return null;
                            }
                            c.a("receive a message, msgid=" + l.b() + ", jobkey=" + b);
                            if (z && a3.getExtra() != null && a3.getExtra().containsKey("notify_effect")) {
                                Map extra = a3.getExtra();
                                str = (String) extra.get("notify_effect");
                                if (f.b(acVar)) {
                                    Intent a4 = a(this.b, acVar.f, extra);
                                    if (a4 == null) {
                                        c.a("Getting Intent fail from ignore reg message. ");
                                        return null;
                                    }
                                    Object f = l.f();
                                    if (!TextUtils.isEmpty(f)) {
                                        a4.putExtra("payload", f);
                                    }
                                    this.b.startActivity(a4);
                                    return null;
                                }
                                Intent a5 = a(this.b, this.b.getPackageName(), extra);
                                if (a5 == null) {
                                    return null;
                                }
                                if (!str.equals(o.c)) {
                                    a5.putExtra("key_message", a3);
                                }
                                this.b.startActivity(a5);
                                return null;
                            }
                            Serializable serializable = a3;
                        } else {
                            c.a("drop a duplicate message, key=" + b);
                        }
                        if (acVar.m() != null || z) {
                            return aVar;
                        }
                        a(akVar, acVar);
                        return aVar;
                    }
                    c.a("receive a message in pause state. drop it");
                    return null;
                case 2:
                    ah ahVar = (ah) a;
                    if (ahVar.f == 0) {
                        q.a(this.b).b(ahVar.h, ahVar.i);
                    }
                    if (TextUtils.isEmpty(ahVar.h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(ahVar.h);
                    }
                    aVar = f.a("register", list, ahVar.f, ahVar.g, null);
                    al.a(this.b).e();
                    return aVar;
                case 3:
                    if (((ao) a).f == 0) {
                        q.a(this.b).h();
                        c.e(this.b);
                    }
                    PushMessageHandler.a();
                    return null;
                case 4:
                    am amVar = (am) a;
                    if (amVar.f == 0) {
                        c.e(this.b, amVar.h());
                    }
                    if (TextUtils.isEmpty(amVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(amVar.h());
                    }
                    return f.a("subscribe-topic", list, amVar.f, amVar.g, amVar.k());
                case 5:
                    aq aqVar = (aq) a;
                    if (aqVar.f == 0) {
                        c.f(this.b, aqVar.h());
                    }
                    if (TextUtils.isEmpty(aqVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(aqVar.h());
                    }
                    return f.a("unsubscibe-topic", list, aqVar.f, aqVar.g, aqVar.k());
                case 6:
                    ab abVar = (ab) a;
                    Object e = abVar.e();
                    List k = abVar.k();
                    if (abVar.g == 0) {
                        if (TextUtils.equals(e, "accept-time") && k != null && k.size() > 1) {
                            c.g(this.b, (String) k.get(0), (String) k.get(1));
                            if ("00:00".equals(k.get(0)) && "00:00".equals(k.get(1))) {
                                q.a(this.b).a(true);
                            } else {
                                q.a(this.b).a(false);
                            }
                            list = a(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), k);
                            return f.a(e, list, abVar.g, abVar.h, abVar.m());
                        } else if (TextUtils.equals(e, "set-alias") && k != null && k.size() > 0) {
                            c.a(this.b, (String) k.get(0));
                            list = k;
                            return f.a(e, list, abVar.g, abVar.h, abVar.m());
                        } else if (TextUtils.equals(e, "unset-alias") && k != null && k.size() > 0) {
                            c.b(this.b, (String) k.get(0));
                            list = k;
                            return f.a(e, list, abVar.g, abVar.h, abVar.m());
                        } else if (TextUtils.equals(e, "set-account") && k != null && k.size() > 0) {
                            c.c(this.b, (String) k.get(0));
                            list = k;
                            return f.a(e, list, abVar.g, abVar.h, abVar.m());
                        } else if (TextUtils.equals(e, "unset-account") && k != null && k.size() > 0) {
                            c.d(this.b, (String) k.get(0));
                        }
                    }
                    list = k;
                    return f.a(e, list, abVar.g, abVar.h, abVar.m());
                case 7:
                    String c;
                    if (a instanceof y) {
                        y yVar = (y) a;
                        c = yVar.c();
                        if (p.DisablePushMessage.T.equalsIgnoreCase(yVar.e)) {
                            if (yVar.g == 0) {
                                synchronized (ag.class) {
                                    if (ag.a(this.b).e(c)) {
                                        ag.a(this.b).d(c);
                                        if ("disable_syncing".equals(ag.a(this.b).a())) {
                                            ag.a(this.b).f("disable_synced");
                                            c.j(this.b);
                                            c.f(this.b);
                                            PushMessageHandler.a();
                                            al.a(this.b).b();
                                        }
                                    }
                                }
                                return null;
                            } else if ("disable_syncing".equals(ag.a(this.b).a())) {
                                synchronized (ag.class) {
                                    if (ag.a(this.b).e(c)) {
                                        if (ag.a(this.b).c(c) < 10) {
                                            ag.a(this.b).b(c);
                                            al.a(this.b).a(true, c);
                                        } else {
                                            ag.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                ag.a(this.b).d(c);
                                return null;
                            }
                        } else if (!p.EnablePushMessage.T.equalsIgnoreCase(yVar.e)) {
                            return null;
                        } else {
                            if (yVar.g == 0) {
                                synchronized (ag.class) {
                                    if (ag.a(this.b).e(c)) {
                                        ag.a(this.b).d(c);
                                        if ("enable_syncing".equals(ag.a(this.b).a())) {
                                            ag.a(this.b).f("enable_synced");
                                            ag.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else if ("enable_syncing".equals(ag.a(this.b).a())) {
                                synchronized (ag.class) {
                                    if (ag.a(this.b).e(c)) {
                                        if (ag.a(this.b).c(c) < 10) {
                                            ag.a(this.b).b(c);
                                            al.a(this.b).a(false, c);
                                        } else {
                                            ag.a(this.b).d(c);
                                        }
                                    }
                                }
                                return null;
                            } else {
                                ag.a(this.b).d(c);
                                return null;
                            }
                        }
                    } else if (!(a instanceof af)) {
                        return null;
                    } else {
                        af afVar = (af) a;
                        if ("registration id expired".equalsIgnoreCase(afVar.e)) {
                            List<String> b2 = c.b(this.b);
                            List<String> c2 = c.c(this.b);
                            List<String> d = c.d(this.b);
                            String p = c.p(this.b);
                            c.a(this.b, u.RegIdExpired);
                            for (String str2 : b2) {
                                c.b(this.b, str2, null);
                            }
                            for (String str22 : c2) {
                                c.e(this.b, str22, null);
                            }
                            for (String str222 : d) {
                                c.c(this.b, str222, null);
                            }
                            String[] split = p.split(",");
                            if (split.length != 2) {
                                return null;
                            }
                            c.g(this.b, split[0], split[1]);
                            return null;
                        } else if ("client_info_update_ok".equalsIgnoreCase(afVar.e)) {
                            if (afVar.i() == null || !afVar.i().containsKey("app_version")) {
                                return null;
                            }
                            q.a(this.b).a((String) afVar.i().get("app_version"));
                            return null;
                        } else if ("awake_app".equalsIgnoreCase(afVar.e)) {
                            if (afVar.i() == null || !afVar.i().containsKey("packages")) {
                                return null;
                            }
                            c.a(this.b, ((String) afVar.i().get("packages")).split(","));
                            return null;
                        } else if (p.NormalClientConfigUpdate.T.equalsIgnoreCase(afVar.e)) {
                            r1 = new ae();
                            try {
                                ar.a(r1, afVar.m());
                                l.a(k.a(this.b), r1);
                                return null;
                            } catch (Throwable e2) {
                                c.a(e2);
                                return null;
                            }
                        } else if (p.CustomClientConfigUpdate.T.equalsIgnoreCase(afVar.e)) {
                            r1 = new ad();
                            try {
                                ar.a(r1, afVar.m());
                                l.a(k.a(this.b), r1);
                                return null;
                            } catch (Throwable e22) {
                                c.a(e22);
                                return null;
                            }
                        } else if (p.SyncInfoResult.T.equalsIgnoreCase(afVar.e)) {
                            l.a(this.b, afVar);
                            return null;
                        } else if (p.ForceSync.T.equalsIgnoreCase(afVar.e)) {
                            c.a("receive force sync notification");
                            l.a(this.b, false);
                            return null;
                        } else if (p.GeoRegsiter.T.equalsIgnoreCase(afVar.e)) {
                            t.a(this.b).a(afVar);
                            return null;
                        } else if (p.GeoUnregsiter.T.equalsIgnoreCase(afVar.e)) {
                            t.a(this.b).b(afVar);
                            return null;
                        } else if (p.GeoSync.T.equalsIgnoreCase(afVar.e)) {
                            t.a(this.b).c(afVar);
                            return null;
                        } else if (p.CancelPushMessage.T.equals(afVar.e)) {
                            c = "";
                            if (afVar.i() == null) {
                                return null;
                            }
                            int parseInt;
                            if (afVar.i().containsKey(o.H)) {
                                c = (String) afVar.i().get(o.H);
                                if (!TextUtils.isEmpty(c)) {
                                    try {
                                        parseInt = Integer.parseInt(c);
                                    } catch (NumberFormatException e3) {
                                        e3.printStackTrace();
                                        parseInt = -2;
                                    }
                                    if (parseInt < -1) {
                                        c.a(this.b, parseInt);
                                        return null;
                                    }
                                    c = "";
                                    b = "";
                                    if (afVar.i().containsKey(o.F)) {
                                        c = (String) afVar.i().get(o.F);
                                    }
                                    c.f(this.b, c, afVar.i().containsKey(o.G) ? (String) afVar.i().get(o.G) : b);
                                    return null;
                                }
                            }
                            parseInt = -2;
                            if (parseInt < -1) {
                                c = "";
                                b = "";
                                if (afVar.i().containsKey(o.F)) {
                                    c = (String) afVar.i().get(o.F);
                                }
                                if (afVar.i().containsKey(o.G)) {
                                }
                                c.f(this.b, c, afVar.i().containsKey(o.G) ? (String) afVar.i().get(o.G) : b);
                                return null;
                            }
                            c.a(this.b, parseInt);
                            return null;
                        } else if (p.HybridRegisterResult.T.equals(afVar.e)) {
                            try {
                                ah ahVar2 = new ah();
                                ar.a((a) ahVar2, afVar.m());
                                ab.a(this.b, ahVar2);
                                return null;
                            } catch (Throwable e222) {
                                c.a(e222);
                                return null;
                            }
                        } else if (!p.HybridUnregisterResult.T.equals(afVar.e)) {
                            return null;
                        } else {
                            try {
                                ao aoVar = new ao();
                                ar.a((a) aoVar, afVar.m());
                                ab.a(this.b, aoVar);
                                return null;
                            } catch (Throwable e2222) {
                                c.a(e2222);
                                return null;
                            }
                        }
                    }
                default:
                    return null;
            }
        } catch (Throwable e22222) {
            c.a(e22222);
            a(acVar);
            return null;
        } catch (Throwable e222222) {
            c.a(e222222);
            c.d("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private a a(ac acVar, byte[] bArr) {
        String str = null;
        try {
            a a = ah.a(this.b, acVar);
            if (a == null) {
                c.d("message arrived: receiving an un-recognized message. " + acVar.a);
                return null;
            }
            c.c("message arrived: receive a message." + a);
            com.xiaomi.xmpush.thrift.a a2 = acVar.a();
            c.a("message arrived: processing an arrived message, action=" + a2);
            switch (ak.a[a2.ordinal()]) {
                case 1:
                    ak akVar = (ak) a;
                    r l = akVar.l();
                    if (l == null) {
                        c.d("message arrived: receive an empty message without push content, drop it");
                        return null;
                    }
                    if (!(acVar.h == null || acVar.h.s() == null)) {
                        str = (String) acVar.h.j.get("jobkey");
                    }
                    MiPushMessage a3 = f.a(akVar, acVar.m(), false);
                    a3.setArrivedMessage(true);
                    c.a("message arrived: receive a message, msgid=" + l.b() + ", jobkey=" + str);
                    return a3;
                default:
                    return null;
            }
        } catch (Throwable e) {
            c.a(e);
            c.d("message arrived: receive a message but decrypt failed. report when click.");
            return null;
        } catch (Throwable e2) {
            c.a(e2);
            c.d("message arrived: receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    public static aj a(Context context) {
        if (a == null) {
            a = new aj(context);
        }
        return a;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong("last_reinitialize", 0)) > 1800000) {
            c.a(this.b, u.PackageUnregistered);
            sharedPreferences.edit().putLong("last_reinitialize", currentTimeMillis).commit();
        }
    }

    private void a(ac acVar) {
        c.a("receive a message but decrypt failed. report now.");
        a afVar = new af(acVar.m().a, false);
        afVar.c(p.DecryptMessageFail.T);
        afVar.b(acVar.h());
        afVar.d(acVar.f);
        afVar.h = new HashMap();
        afVar.h.put("regid", c.k(this.b));
        al.a(this.b).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
    }

    private void a(ak akVar, ac acVar) {
        s m = acVar.m();
        a xVar = new x();
        xVar.b(akVar.e());
        xVar.a(akVar.c());
        xVar.a(akVar.l().h());
        if (!TextUtils.isEmpty(akVar.h())) {
            xVar.c(akVar.h());
        }
        if (!TextUtils.isEmpty(akVar.j())) {
            xVar.d(akVar.j());
        }
        xVar.a(ar.a(this.b, acVar));
        al.a(this.b).a(xVar, com.xiaomi.xmpush.thrift.a.AckMessage, m);
    }

    private static boolean a(Context context, String str) {
        boolean z = false;
        synchronized (d) {
            q.a(context);
            SharedPreferences b = q.b(context);
            if (c == null) {
                String[] split = b.getString("pref_msg_ids", "").split(",");
                c = new LinkedList();
                for (Object add : split) {
                    c.add(add);
                }
            }
            if (c.contains(str)) {
                z = true;
            } else {
                c.add(str);
                if (c.size() > 25) {
                    c.poll();
                }
                String a = d.a(c, ",");
                Editor edit = b.edit();
                edit.putString("pref_msg_ids", a);
                edit.commit();
            }
        }
        return z;
    }

    private void b(ac acVar) {
        s m = acVar.m();
        a xVar = new x();
        xVar.b(acVar.h());
        xVar.a(m.b());
        xVar.a(m.d());
        if (!TextUtils.isEmpty(m.f())) {
            xVar.c(m.f());
        }
        xVar.a(ar.a(this.b, acVar));
        al.a(this.b).a(xVar, com.xiaomi.xmpush.thrift.a.AckMessage, false, acVar.m());
    }

    public a a(Intent intent) {
        String action = intent.getAction();
        c.a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        byte[] byteArrayExtra;
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                c.d("receiving an empty message, drop");
                return null;
            }
            ac acVar = new ac();
            try {
                ar.a((a) acVar, byteArrayExtra);
                q a = q.a(this.b);
                s m = acVar.m();
                if (!(acVar.a() != com.xiaomi.xmpush.thrift.a.SendMessage || m == null || a.k() || booleanExtra)) {
                    if (m != null) {
                        acVar.m().a("mrt", stringExtra);
                        acVar.m().a("mat", Long.toString(System.currentTimeMillis()));
                    }
                    b(acVar);
                }
                if (acVar.a() == com.xiaomi.xmpush.thrift.a.SendMessage && !acVar.c()) {
                    if (!f.b(acVar)) {
                        action = "drop an un-encrypted messages. %1$s, %2$s";
                        Object[] objArr = new Object[2];
                        objArr[0] = acVar.j();
                        objArr[1] = m != null ? m.b() : "";
                        c.a(String.format(action, objArr));
                        return null;
                    } else if (!(booleanExtra && m.s() != null && m.s().containsKey("notify_effect"))) {
                        c.a(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[]{acVar.j(), m.b()}));
                        return null;
                    }
                }
                if (a.i() || acVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                    if (!a.i() || !a.m()) {
                        return a(acVar, booleanExtra, byteArrayExtra);
                    }
                    if (acVar.a == com.xiaomi.xmpush.thrift.a.UnRegistration) {
                        a.h();
                        c.e(this.b);
                        PushMessageHandler.a();
                    } else {
                        c.g(this.b);
                    }
                } else if (f.b(acVar)) {
                    return a(acVar, booleanExtra, byteArrayExtra);
                } else {
                    c.d("receive message without registration. need re-register!");
                    a();
                }
            } catch (Throwable e) {
                c.a(e);
            } catch (Throwable e2) {
                c.a(e2);
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            a miPushCommandMessage = new MiPushCommandMessage();
            a acVar2 = new ac();
            try {
                byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra != null) {
                    ar.a(acVar2, byteArrayExtra);
                }
            } catch (org.apache.thrift.f e3) {
            }
            miPushCommandMessage.setCommand(String.valueOf(acVar2.a()));
            miPushCommandMessage.setResultCode((long) intent.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
            c.d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra2 == null) {
                c.d("message arrived: receiving an empty message, drop");
                return null;
            }
            ac acVar3 = new ac();
            try {
                ar.a((a) acVar3, byteArrayExtra2);
                q a2 = q.a(this.b);
                if (f.b(acVar3)) {
                    c.d("message arrived: receive ignore reg message, ignore!");
                } else if (!a2.i()) {
                    c.d("message arrived: receive message without registration. need unregister or re-register!");
                } else if (!a2.i() || !a2.m()) {
                    return a(acVar3, byteArrayExtra2);
                } else {
                    c.d("message arrived: app info is invalidated");
                }
            } catch (Throwable e22) {
                c.a(e22);
            } catch (Throwable e222) {
                c.a(e222);
            }
        }
        return null;
    }

    public List<String> a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = (long) (((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60);
        long parseLong = Long.parseLong(((String) list.get(0)).split(":")[0]);
        long parseLong2 = Long.parseLong(((String) list.get(0)).split(":")[1]);
        parseLong = ((((parseLong * 60) + parseLong2) - rawOffset) + 1440) % 1440;
        long parseLong3 = (((Long.parseLong(((String) list.get(1)).split(":")[1]) + (60 * Long.parseLong(((String) list.get(1)).split(":")[0]))) - rawOffset) + 1440) % 1440;
        List arrayList = new ArrayList();
        List list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)}));
        list2 = arrayList;
        list2.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)}));
        return arrayList;
    }
}
