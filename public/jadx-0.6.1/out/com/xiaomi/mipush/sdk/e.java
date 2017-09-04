package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.y;
import java.util.ArrayList;
import java.util.Iterator;

public class e {

    public static class a {
        private static a a;
        private Context b;
        private String c;
        private Boolean d;
        private a e = new a(this);
        private final ArrayList<com.xiaomi.xmpush.thrift.e> f = new ArrayList();

        public static a a() {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new a();
                    }
                }
            }
            return a;
        }

        private void b(com.xiaomi.xmpush.thrift.e eVar) {
            synchronized (this.f) {
                if (!this.f.contains(eVar)) {
                    this.f.add(eVar);
                    if (this.f.size() > 100) {
                        this.f.remove(0);
                    }
                }
            }
        }

        private boolean b(Context context) {
            if (!al.a(context).c()) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                return packageInfo == null ? false : packageInfo.versionCode >= 108;
            } catch (Exception e) {
                return false;
            }
        }

        private boolean c(Context context) {
            return q.a(context).c() == null && !b(this.b);
        }

        private boolean c(com.xiaomi.xmpush.thrift.e eVar) {
            if (y.a(eVar, false)) {
                return false;
            }
            if (this.d.booleanValue()) {
                c.c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + eVar.toString());
                al.a(this.b).a(eVar);
            } else {
                this.e.a(eVar);
            }
            return true;
        }

        public void a(Context context) {
            if (context == null) {
                c.a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.b = context;
            this.d = Boolean.valueOf(b(context));
            a("com.xiaomi.xmpushsdk.tinydataPending.init");
        }

        public void a(String str) {
            c.c("MiTinyDataClient.processPendingList(" + str + ")");
            ArrayList arrayList = new ArrayList();
            synchronized (this.f) {
                arrayList.addAll(this.f);
                this.f.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a((com.xiaomi.xmpush.thrift.e) it.next());
            }
        }

        public synchronized boolean a(com.xiaomi.xmpush.thrift.e eVar) {
            boolean z = false;
            synchronized (this) {
                if (eVar != null) {
                    if (!y.a(eVar, true)) {
                        boolean z2 = TextUtils.isEmpty(eVar.a()) && TextUtils.isEmpty(this.c);
                        boolean z3 = !b();
                        if (this.b == null || c(this.b)) {
                            z = true;
                        }
                        if (z3 || z2 || z) {
                            if (z2) {
                                c.c("MiTinyDataClient Pending " + eVar + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.channel");
                            } else if (z3) {
                                c.c("MiTinyDataClient Pending " + eVar + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.init");
                            } else if (z) {
                                c.c("MiTinyDataClient Pending " + eVar + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.appId");
                            }
                            b(eVar);
                            z = true;
                        } else {
                            c.c("MiTinyDataClient Send item immediately." + eVar.toString());
                            if (TextUtils.isEmpty(eVar.l())) {
                                eVar.f(c.a());
                            }
                            if (TextUtils.isEmpty(eVar.a())) {
                                eVar.a(this.c);
                            }
                            if (TextUtils.isEmpty(eVar.j())) {
                                eVar.e(this.b.getPackageName());
                            }
                            if (eVar.f() <= 0) {
                                eVar.b(System.currentTimeMillis());
                            }
                            z = c(eVar);
                        }
                    }
                }
            }
            return z;
        }

        public boolean b() {
            return this.b != null;
        }
    }

    public static boolean a(Context context, com.xiaomi.xmpush.thrift.e eVar) {
        c.c("MiTinyDataClient.upload " + eVar.toString());
        if (!a.a().b()) {
            a.a().a(context);
        }
        return a.a().a(eVar);
    }

    public static boolean a(Context context, String str, String str2, long j, String str3) {
        com.xiaomi.xmpush.thrift.e eVar = new com.xiaomi.xmpush.thrift.e();
        eVar.d(str);
        eVar.c(str2);
        eVar.a(j);
        eVar.b(str3);
        eVar.c(true);
        eVar.a("push_sdk_channel");
        return a(context, eVar);
    }
}
