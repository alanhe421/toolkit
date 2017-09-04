package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.upload.impl.TaskManager;
import com.tencent.util.TimeFormatterUtils;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.g;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.push.service.au;
import com.xiaomi.push.service.az;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.al;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.s;
import com.xiaomi.xmpush.thrift.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class c {
    private static Context a;
    private static long b = System.currentTimeMillis();
    private static n c;

    @Deprecated
    public static abstract class a {
        private String a;

        protected String a() {
            return this.a;
        }

        public void a(long j, String str, String str2) {
        }

        public void a(MiPushMessage miPushMessage) {
        }

        public void a(String str, long j, String str2, List<String> list) {
        }

        public void a(String str, String str2, String str3, boolean z) {
        }

        public void b(long j, String str, String str2) {
        }

        public void c(long j, String str, String str2) {
        }
    }

    protected static synchronized String a() {
        String str;
        synchronized (c.class) {
            str = d.a(4) + b;
            b++;
        }
        return str;
    }

    public static void a(Context context, int i) {
        al.a(context).a(i);
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        s sVar = new s();
        sVar.a(miPushMessage.getMessageId());
        sVar.b(miPushMessage.getTopic());
        sVar.d(miPushMessage.getDescription());
        sVar.c(miPushMessage.getTitle());
        sVar.c(miPushMessage.getNotifyId());
        sVar.a(miPushMessage.getNotifyType());
        sVar.b(miPushMessage.getPassThrough());
        sVar.a(miPushMessage.getExtra());
        a(context, miPushMessage.getMessageId(), sVar, null);
    }

    static void a(Context context, u uVar) {
        if (q.a(context).i()) {
            String a = d.a(6);
            String c = q.a(context).c();
            String d = q.a(context).d();
            q.a(context).h();
            q.a(context).a(c, d, a);
            ag agVar = new ag();
            agVar.a(a());
            agVar.b(c);
            agVar.e(d);
            agVar.f(a);
            agVar.d(context.getPackageName());
            agVar.c(b.a(context, context.getPackageName()));
            agVar.a(uVar);
            al.a(context).a(agVar, false);
        }
    }

    static synchronized void a(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    static void a(Context context, String str, s sVar, String str2) {
        Object afVar = new af();
        if (!TextUtils.isEmpty(str2)) {
            afVar.b(str2);
        } else if (q.a(context).b()) {
            afVar.b(q.a(context).c());
        } else {
            com.xiaomi.channel.commonutils.b.c.d("do not report clicked message");
            return;
        }
        afVar.c("bar:click");
        afVar.a(str);
        afVar.a(false);
        al.a(context).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, sVar);
    }

    static void a(Context context, String str, s sVar, String str2, String str3) {
        org.apache.thrift.a afVar = new af();
        if (TextUtils.isEmpty(str3)) {
            com.xiaomi.channel.commonutils.b.c.d("do not report clicked message");
            return;
        }
        afVar.b(str3);
        afVar.c("bar:click");
        afVar.a(str);
        afVar.a(false);
        al.a(context).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, true, sVar, true, str2, str3);
    }

    public static void a(Context context, String str, String str2) {
        if (!NetworkStatusReceiver.a()) {
            q(context);
        }
        g.a();
        new Thread(new x(context, str, str2)).start();
    }

    @Deprecated
    public static void a(Context context, String str, String str2, a aVar) {
        boolean z = false;
        a((Object) context, "context");
        a((Object) str, "appID");
        a((Object) str2, "appToken");
        try {
            a = context.getApplicationContext();
            if (a == null) {
                a = context;
            }
            if (aVar != null) {
                PushMessageHandler.a(aVar);
            }
            if (j.b(context)) {
                i.a(context);
            }
            if (q.a(a).l() != a.a()) {
                z = true;
            }
            if (z || u(a)) {
                if (z || !q.a(a).a(str, str2) || q.a(a).m()) {
                    String a = d.a(6);
                    q.a(a).h();
                    q.a(a).a(a.a());
                    q.a(a).a(str, str2, a);
                    com.xiaomi.mipush.sdk.e.a.a().a("com.xiaomi.xmpushsdk.tinydataPending.appId");
                    e(a);
                    ag agVar = new ag();
                    agVar.a(a());
                    agVar.b(str);
                    agVar.e(str2);
                    agVar.d(context.getPackageName());
                    agVar.f(a);
                    agVar.c(b.a(context, context.getPackageName()));
                    agVar.b(b.b(context, context.getPackageName()));
                    agVar.g("3_4_3");
                    agVar.a(30403);
                    agVar.h(e.b(a));
                    agVar.a(u.Init);
                    a = e.d(a);
                    Object f = e.f(a);
                    if (!TextUtils.isEmpty(a)) {
                        if (g.b()) {
                            if (!TextUtils.isEmpty(f)) {
                                a = a + "," + f;
                            }
                            agVar.i(a);
                        }
                        agVar.k(d.a(a) + "," + e.g(a));
                    }
                    agVar.j(e.a());
                    int b = e.b();
                    if (b >= 0) {
                        agVar.c(b);
                    }
                    al.a(a).a(agVar, z);
                    g.a(context);
                } else {
                    if (1 == f.a(context)) {
                        a((Object) aVar, "callback");
                        aVar.a(0, null, q.a(context).e());
                    } else {
                        List arrayList = new ArrayList();
                        arrayList.add(q.a(context).e());
                        f.a(a, f.a("register", arrayList, 0, null, null));
                    }
                    al.a(context).a();
                    if (q.a(a).a()) {
                        org.apache.thrift.a afVar = new af();
                        afVar.b(q.a(context).c());
                        afVar.c("client_info_update");
                        afVar.a(a());
                        afVar.h = new HashMap();
                        afVar.h.put("app_version", b.a(a, a.getPackageName()));
                        afVar.h.put("app_version_code", Integer.toString(b.b(a, a.getPackageName())));
                        afVar.h.put("push_sdk_vn", "3_4_3");
                        afVar.h.put("push_sdk_vc", Integer.toString(30403));
                        CharSequence g = q.a(a).g();
                        if (!TextUtils.isEmpty(g)) {
                            afVar.h.put("deviceid", g);
                        }
                        al.a(context).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
                        g.a(context);
                    }
                    if (!h.a(a, "update_devId", false)) {
                        g();
                        h.b(a, "update_devId", true);
                    }
                    if (a(a) && s(a)) {
                        org.apache.thrift.a afVar2 = new af();
                        afVar2.b(q.a(a).c());
                        afVar2.c("pull");
                        afVar2.a(a());
                        afVar2.a(false);
                        al.a(a).a(afVar2, com.xiaomi.xmpush.thrift.a.Notification, false, null, false);
                        r(a);
                    }
                }
                t(a);
                c();
                d();
                e();
                l.a(a);
                if (c == null) {
                    c = new n(a);
                }
                c.a(a);
                if ("disable_syncing".equals(ag.a(a).a())) {
                    h(a);
                }
                if ("enable_syncing".equals(ag.a(a).a())) {
                    i(a);
                }
                if (u.a(a)) {
                    u.b(a);
                    return;
                }
                return;
            }
            al.a(context).a();
            com.xiaomi.channel.commonutils.b.c.a("Could not send  register message within 5s repeatly .");
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.b.c.d(e.toString());
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.b.c.a(th);
        }
    }

    protected static void a(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!"set-alias".equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - i(context, str2)) >= 3600000) {
            if ("unset-alias".equalsIgnoreCase(str) && i(context, str2) < 0) {
                com.xiaomi.channel.commonutils.b.c.a("Don't cancel alias for " + arrayList + " is unseted");
            } else if (!"set-account".equalsIgnoreCase(str) || Math.abs(System.currentTimeMillis() - h(context, str2)) >= 3600000) {
                if (!"unset-account".equalsIgnoreCase(str) || h(context, str2) >= 0) {
                    a(context, str, arrayList, str3);
                } else {
                    com.xiaomi.channel.commonutils.b.c.a("Don't cancel account for " + arrayList + " is unseted");
                }
            } else if (1 == f.a(context)) {
                PushMessageHandler.a(context, str3, str, 0, null, arrayList);
            } else {
                f.a(context, f.a("set-account", arrayList, 0, null, null));
            }
        } else if (1 == f.a(context)) {
            PushMessageHandler.a(context, str3, str, 0, null, arrayList);
        } else {
            f.a(context, f.a("set-alias", arrayList, 0, null, null));
        }
    }

    protected static void a(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(q.a(context).c())) {
            org.apache.thrift.a aaVar = new aa();
            aaVar.a(a());
            aaVar.b(q.a(context).c());
            aaVar.c(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                aaVar.d((String) it.next());
            }
            aaVar.f(str2);
            aaVar.e(context.getPackageName());
            al.a(context).a(aaVar, com.xiaomi.xmpush.thrift.a.Command, null);
        }
    }

    public static void a(Context context, String[] strArr) {
        new Thread(new aa(strArr, context)).start();
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    public static boolean a(Context context) {
        return al.a(context).c();
    }

    public static List<String> b(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    private static void b(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        intent.putExtra("waker_pkgname", context.getPackageName());
                        context.startService(intent);
                        return;
                    } catch (Throwable th) {
                        return;
                    }
                }
            }
        }
    }

    static synchronized void b(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
        }
    }

    public static void b(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a(context, "set-alias", str, str2);
        }
    }

    public static List<String> c(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    private static void c() {
        com.xiaomi.channel.commonutils.c.g.a(a).a(new af(a), k.a(a).a(f.OcVersionCheckFrequency.a(), 86400), 5);
    }

    static synchronized void c(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void c(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a(context, "set-account", str, str2);
        }
    }

    public static List<String> d(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring("account_".length()));
            }
        }
        return arrayList;
    }

    private static void d() {
        if (au.e(a) && !TextUtils.equals("com.xiaomi.xmsf", a.getPackageName()) && k.a(a).a(f.UploadGeoAppLocSwitch.a(), true) && !j.e()) {
            r.a(a, true);
            int max = Math.max(60, k.a(a).a(f.UploadWIFIGeoLocFrequency.a(), (int) com.qq.reader.common.download.task.f.SUCCESS));
            com.xiaomi.channel.commonutils.c.g.a(a).a(new r(a, max), max, max);
        }
    }

    static synchronized void d(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + str).commit();
        }
    }

    public static void d(Context context, String str, String str2) {
        a(context, "unset-account", str, str2);
    }

    private static void e() {
        if (k.a(a).a(f.DataCollectionSwitch.a(), f())) {
            com.xiaomi.channel.commonutils.c.g.a(a).a(new y(), 10);
        }
    }

    protected static void e(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    static synchronized void e(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static void e(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(q.a(context).c()) && !TextUtils.isEmpty(str)) {
            if (Math.abs(System.currentTimeMillis() - g(context, str)) > TimeFormatterUtils.ONE_DAY) {
                org.apache.thrift.a alVar = new al();
                alVar.a(a());
                alVar.b(q.a(context).c());
                alVar.c(str);
                alVar.d(context.getPackageName());
                alVar.e(str2);
                al.a(context).a(alVar, com.xiaomi.xmpush.thrift.a.Subscription, null);
            } else if (1 == f.a(context)) {
                PushMessageHandler.a(context, str2, 0, null, str);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(str);
                f.a(context, f.a("subscribe-topic", arrayList, 0, null, null));
            }
        }
    }

    public static void f(Context context) {
        al.a(context).f();
    }

    static synchronized void f(Context context, String str) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
        }
    }

    public static void f(Context context, String str, String str2) {
        al.a(context).a(str, str2);
    }

    private static boolean f() {
        return g.b();
    }

    public static long g(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1);
    }

    private static void g() {
        new Thread(new z()).start();
    }

    public static void g(Context context) {
        if (q.a(context).b()) {
            an anVar = new an();
            anVar.a(a());
            anVar.b(q.a(context).c());
            anVar.c(q.a(context).e());
            anVar.e(q.a(context).d());
            anVar.d(context.getPackageName());
            al.a(context).a(anVar);
            PushMessageHandler.a();
            q.a(context).j();
            e(context);
            f(context);
            j(context);
            if (c != null) {
                az.a(context).b(c);
            }
        }
    }

    static synchronized void g(Context context, String str, String str2) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putString(MessageKey.MSG_ACCEPT_TIME, str + "," + str2).commit();
        }
    }

    public static long h(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + str, -1);
    }

    public static void h(Context context) {
        al.a(context).a(true);
    }

    public static long i(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1);
    }

    public static void i(Context context) {
        al.a(context).a(false);
    }

    public static void j(Context context) {
        al.a(context).a(-1);
    }

    public static String k(Context context) {
        return q.a(context).i() ? q.a(context).e() : null;
    }

    static synchronized void l(Context context) {
        synchronized (c.class) {
            for (String b : b(context)) {
                b(context, b);
            }
        }
    }

    static synchronized void m(Context context) {
        synchronized (c.class) {
            for (String d : d(context)) {
                d(context, d);
            }
        }
    }

    static synchronized void n(Context context) {
        synchronized (c.class) {
            for (String f : c(context)) {
                f(context, f);
            }
        }
    }

    static synchronized void o(Context context) {
        synchronized (c.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove(MessageKey.MSG_ACCEPT_TIME).commit();
        }
    }

    protected static String p(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString(MessageKey.MSG_ACCEPT_TIME, "00:00-23:59");
    }

    private static void q(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            context.getApplicationContext().registerReceiver(new NetworkStatusReceiver(null), intentFilter);
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.b.c.a(th);
        }
    }

    private static void r(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_pull_notification", System.currentTimeMillis()).commit();
    }

    private static boolean s(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1)) > TaskManager.IDLE_PROTECT_TIME;
    }

    private static void t(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_reg_request", System.currentTimeMillis()).commit();
    }

    private static boolean u(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1)) > 5000;
    }
}
