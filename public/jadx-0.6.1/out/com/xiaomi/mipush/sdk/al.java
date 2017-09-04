package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.o;
import com.xiaomi.push.service.q;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.b;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class al {
    private static al b;
    private static boolean f = false;
    private static final ArrayList<a> g = new ArrayList();
    private boolean a = false;
    private Context c;
    private String d;
    private Messenger e;
    private Handler h = null;
    private List<Message> i = new ArrayList();
    private boolean j = false;
    private Intent k = null;
    private Integer l = null;

    static class a<T extends org.apache.thrift.a<T, ?>> {
        T a;
        com.xiaomi.xmpush.thrift.a b;
        boolean c;

        a() {
        }
    }

    private al(Context context) {
        this.c = context.getApplicationContext();
        this.d = null;
        this.a = i();
        a(j());
        f = n();
        this.h = new h(this, Looper.getMainLooper());
    }

    public static al a(Context context) {
        if (b == null) {
            b = new al(context);
        }
        return b;
    }

    private void a(Intent intent) {
        try {
            this.c.startService(intent);
        } catch (Throwable e) {
            c.a(e);
        }
    }

    private final void a(String str, boolean z) {
        int i = 0;
        if (q.a(this.c).b() && d.d(this.c)) {
            Object a;
            Object afVar;
            af afVar2 = new af();
            Intent j = j();
            if (TextUtils.isEmpty(str)) {
                a = c.a();
                afVar2.a((String) a);
                afVar = new af(a, true);
                synchronized (ag.class) {
                    ag.a(this.c).a((String) a);
                }
            } else {
                afVar2.a(str);
                afVar = new af(str, true);
            }
            if (z) {
                afVar2.c(p.DisablePushMessage.T);
                afVar.c(p.DisablePushMessage.T);
                j.setAction("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE");
            } else {
                afVar2.c(p.EnablePushMessage.T);
                afVar.c(p.EnablePushMessage.T);
                j.setAction("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE");
            }
            afVar2.b(q.a(this.c).c());
            afVar2.d(this.c.getPackageName());
            a(afVar2, com.xiaomi.xmpush.thrift.a.Notification, false, null);
            afVar.b(q.a(this.c).c());
            afVar.d(this.c.getPackageName());
            byte[] a2 = ar.a(ah.a(this.c, afVar, com.xiaomi.xmpush.thrift.a.Notification, false, this.c.getPackageName(), q.a(this.c).c()));
            if (a2 != null) {
                j.putExtra("mipush_payload", a2);
                j.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                j.putExtra("mipush_app_id", q.a(this.c).c());
                j.putExtra("mipush_app_token", q.a(this.c).d());
                b(j);
            }
            Message obtain = Message.obtain();
            if (z) {
                i = 1;
            }
            obtain.obj = a;
            obtain.arg1 = i;
            this.h.sendMessageDelayed(obtain, 5000);
        }
    }

    private void b(Intent intent) {
        int a = k.a(this.c).a(f.ServiceBootMode.a(), b.START.a());
        int h = h();
        Object obj = (a == b.BIND.a() && f) ? 1 : null;
        a = obj != null ? b.BIND.a() : b.START.a();
        if (a != h) {
            b(a);
        }
        if (obj != null) {
            c(intent);
        } else {
            a(intent);
        }
    }

    private synchronized void c(int i) {
        this.c.getSharedPreferences("mipush_extra", 0).edit().putInt("service_boot_mode", i).commit();
    }

    private synchronized void c(Intent intent) {
        if (this.j) {
            Message d = d(intent);
            if (this.i.size() >= 50) {
                this.i.remove(0);
            }
            this.i.add(d);
        } else if (this.e == null) {
            Context context = this.c;
            ServiceConnection kVar = new k(this);
            Context context2 = this.c;
            context.bindService(intent, kVar, 1);
            this.j = true;
            this.i.clear();
            this.i.add(d(intent));
        } else {
            try {
                this.e.send(d(intent));
            } catch (Throwable e) {
                c.a(e);
            }
        }
    }

    private Message d(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    private synchronized int h() {
        return this.c.getSharedPreferences("mipush_extra", 0).getInt("service_boot_mode", -1);
    }

    private boolean i() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Throwable th) {
            return false;
        }
    }

    private Intent j() {
        Intent intent = new Intent();
        String packageName = this.c.getPackageName();
        if (!c() || "com.xiaomi.xmsf".equals(packageName)) {
            m();
            intent.setComponent(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"));
            intent.putExtra("mipush_app_package", packageName);
        } else {
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", k());
            intent.putExtra("mipush_app_package", packageName);
            l();
        }
        return intent;
    }

    private String k() {
        try {
            if (this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception e) {
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    private void l() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), 2, 1);
        } catch (Throwable th) {
        }
    }

    private void m() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), 1, 1);
        } catch (Throwable th) {
        }
    }

    private boolean n() {
        if (!c()) {
            return true;
        }
        try {
            return this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
        } catch (Exception e) {
            return true;
        }
    }

    private boolean o() {
        String packageName = this.c.getPackageName();
        return packageName.contains("miui") || packageName.contains("xiaomi") || (this.c.getApplicationInfo().flags & 1) != 0;
    }

    public void a() {
        a(j());
    }

    public void a(int i) {
        Intent j = j();
        j.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        j.putExtra(o.y, this.c.getPackageName());
        j.putExtra(o.z, i);
        b(j);
    }

    public final void a(ag agVar, boolean z) {
        this.k = null;
        Intent j = j();
        byte[] a = ar.a(ah.a(this.c, agVar, com.xiaomi.xmpush.thrift.a.Registration));
        if (a == null) {
            c.a("register fail, because msgBytes is null.");
            return;
        }
        j.setAction("com.xiaomi.mipush.REGISTER_APP");
        j.putExtra("mipush_app_id", q.a(this.c).c());
        j.putExtra("mipush_payload", a);
        j.putExtra("mipush_session", this.d);
        j.putExtra("mipush_env_chanage", z);
        j.putExtra("mipush_env_type", q.a(this.c).l());
        if (d.d(this.c) && g()) {
            b(j);
        } else {
            this.k = j;
        }
    }

    public final void a(an anVar) {
        byte[] a = ar.a(ah.a(this.c, anVar, com.xiaomi.xmpush.thrift.a.UnRegistration));
        if (a == null) {
            c.a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent j = j();
        j.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        j.putExtra("mipush_app_id", q.a(this.c).c());
        j.putExtra("mipush_payload", a);
        b(j);
    }

    public final void a(e eVar) {
        Intent j = j();
        byte[] a = ar.a(eVar);
        if (a == null) {
            c.a("send tinydata failed, because tinyDataBytes is null.");
            return;
        }
        j.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        j.putExtra("mipush_payload", a);
        a(j);
    }

    public void a(String str, String str2) {
        Intent j = j();
        j.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        j.putExtra(o.y, this.c.getPackageName());
        j.putExtra(o.D, str);
        j.putExtra(o.E, str2);
        b(j);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, s sVar) {
        a(t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), sVar);
    }

    public <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        a aVar2 = new a();
        aVar2.a = t;
        aVar2.b = aVar;
        aVar2.c = z;
        synchronized (g) {
            g.add(aVar2);
            if (g.size() > 10) {
                g.remove(0);
            }
        }
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, s sVar) {
        a(t, aVar, z, true, sVar, true);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, s sVar, boolean z2) {
        a(t, aVar, z, true, sVar, z2);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, s sVar, boolean z3) {
        a(t, aVar, z, z2, sVar, z3, this.c.getPackageName(), q.a(this.c).c());
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, s sVar, boolean z3, String str, String str2) {
        if (q.a(this.c).i()) {
            org.apache.thrift.a a = ah.a(this.c, t, aVar, z, str, str2);
            if (sVar != null) {
                a.a(sVar);
            }
            byte[] a2 = ar.a(a);
            if (a2 == null) {
                c.a("send message fail, because msgBytes is null.");
                return;
            }
            Intent j = j();
            j.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            j.putExtra("mipush_payload", a2);
            j.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
            b(j);
        } else if (z2) {
            a((org.apache.thrift.a) t, aVar, z);
        } else {
            c.a("drop the message before initialization.");
        }
    }

    public final void a(boolean z) {
        a(z, null);
    }

    public final void a(boolean z, String str) {
        if (z) {
            ag.a(this.c).f("disable_syncing");
        } else {
            ag.a(this.c).f("enable_syncing");
        }
        a(str, z);
    }

    public final void b() {
        Intent j = j();
        j.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        b(j);
    }

    public boolean b(int i) {
        if (!q.a(this.c).b()) {
            return false;
        }
        c(i);
        org.apache.thrift.a afVar = new af();
        afVar.a(c.a());
        afVar.b(q.a(this.c).c());
        afVar.d(this.c.getPackageName());
        afVar.c(p.ClientABTest.T);
        afVar.h = new HashMap();
        afVar.h.put("boot_mode", i + "");
        a(this.c).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
        return true;
    }

    public boolean c() {
        return this.a && 1 == q.a(this.c).l();
    }

    public void d() {
        if (this.k != null) {
            b(this.k);
            this.k = null;
        }
    }

    public void e() {
        synchronized (g) {
            Iterator it = g.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                a(aVar.a, aVar.b, aVar.c, false, null, true);
            }
            g.clear();
        }
    }

    public void f() {
        Intent j = j();
        j.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        j.putExtra(o.y, this.c.getPackageName());
        j.putExtra(o.C, com.xiaomi.channel.commonutils.g.c.b(this.c.getPackageName()));
        b(j);
    }

    public boolean g() {
        if (!c() || !o()) {
            return true;
        }
        if (this.l == null) {
            this.l = Integer.valueOf(q.a(this.c).b());
            if (this.l.intValue() == 0) {
                this.c.getContentResolver().registerContentObserver(q.a(this.c).c(), false, new j(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.l.intValue() != 0;
    }
}
