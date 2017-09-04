package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.ar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class XMPushService extends Service implements com.xiaomi.smack.d {
    public static int c = 1;
    private static final int h = Process.myPid();
    Messenger a = null;
    final BroadcastReceiver b = new aj(this);
    private com.xiaomi.smack.b d;
    private t e;
    private e f;
    private long g = 0;
    private com.xiaomi.c.f i;
    private com.xiaomi.smack.a j;
    private aa k;
    private PacketSync l = null;
    private av m = null;
    private com.xiaomi.smack.f n = new ab(this);

    public static abstract class h extends com.xiaomi.push.service.av.b {
        public h(int i) {
            super(i);
        }

        public abstract void a();

        public abstract String b();

        public void run() {
            if (!(this.f == 4 || this.f == 8)) {
                com.xiaomi.channel.commonutils.b.c.a("JOB: " + b());
            }
            a();
        }
    }

    class a extends h {
        com.xiaomi.push.service.am.b a = null;
        final /* synthetic */ XMPushService b;

        public a(XMPushService xMPushService, com.xiaomi.push.service.am.b bVar) {
            this.b = xMPushService;
            super(9);
            this.a = bVar;
        }

        public void a() {
            try {
                if (this.b.f()) {
                    com.xiaomi.push.service.am.b b = am.a().b(this.a.h, this.a.b);
                    if (b == null) {
                        com.xiaomi.channel.commonutils.b.c.a("ignore bind because the channel " + this.a.h + " is removed ");
                        return;
                    } else if (b.m == com.xiaomi.push.service.am.c.unbind) {
                        b.a(com.xiaomi.push.service.am.c.binding, 0, 0, null, null);
                        this.b.j.a(b);
                        com.xiaomi.d.h.a(this.b, b);
                        return;
                    } else {
                        com.xiaomi.channel.commonutils.b.c.a("trying duplicate bind, ingore! " + b.m);
                        return;
                    }
                }
                com.xiaomi.channel.commonutils.b.c.d("trying bind while the connection is not created, quit!");
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.b.c.a((Throwable) e);
                this.b.a(10, e);
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.b.c.a(th);
            }
        }

        public String b() {
            return "bind the client. " + this.a.h;
        }
    }

    static class b extends h {
        private final com.xiaomi.push.service.am.b a;

        public b(com.xiaomi.push.service.am.b bVar) {
            super(12);
            this.a = bVar;
        }

        public void a() {
            this.a.a(com.xiaomi.push.service.am.c.unbind, 1, 21, null, null);
        }

        public String b() {
            return "bind time out. chid=" + this.a.h;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof b) ? false : TextUtils.equals(((b) obj).a.h, this.a.h);
        }

        public int hashCode() {
            return this.a.h.hashCode();
        }
    }

    class c extends h {
        final /* synthetic */ XMPushService a;
        private com.xiaomi.c.b b = null;

        public c(XMPushService xMPushService, com.xiaomi.c.b bVar) {
            this.a = xMPushService;
            super(8);
            this.b = bVar;
        }

        public void a() {
            this.a.l.a(this.b);
        }

        public String b() {
            return "receive a message.";
        }
    }

    public class d extends h {
        final /* synthetic */ XMPushService a;

        d(XMPushService xMPushService) {
            this.a = xMPushService;
            super(1);
        }

        public void a() {
            if (this.a.b()) {
                this.a.n();
            } else {
                com.xiaomi.channel.commonutils.b.c.a("should not connect. quit the job.");
            }
        }

        public String b() {
            return "do reconnect..";
        }
    }

    class e extends BroadcastReceiver {
        final /* synthetic */ XMPushService a;

        e(XMPushService xMPushService) {
            this.a = xMPushService;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.onStart(intent, XMPushService.c);
        }
    }

    public class f extends h {
        public int a;
        public Exception b;
        final /* synthetic */ XMPushService c;

        f(XMPushService xMPushService, int i, Exception exception) {
            this.c = xMPushService;
            super(2);
            this.a = i;
            this.b = exception;
        }

        public void a() {
            this.c.a(this.a, this.b);
        }

        public String b() {
            return "disconnect the connection.";
        }
    }

    class g extends h {
        final /* synthetic */ XMPushService a;
        private Intent b = null;

        public g(XMPushService xMPushService, Intent intent) {
            this.a = xMPushService;
            super(15);
            this.b = intent;
        }

        public void a() {
            this.a.c(this.b);
        }

        public String b() {
            return "Handle intent action = " + this.b.getAction();
        }
    }

    class i extends h {
        final /* synthetic */ XMPushService a;

        public i(XMPushService xMPushService) {
            this.a = xMPushService;
            super(5);
        }

        public void a() {
            this.a.m.b();
        }

        public String b() {
            return "ask the job queue to quit";
        }
    }

    class j extends h {
        final /* synthetic */ XMPushService a;
        private com.xiaomi.smack.packet.d b = null;

        public j(XMPushService xMPushService, com.xiaomi.smack.packet.d dVar) {
            this.a = xMPushService;
            super(8);
            this.b = dVar;
        }

        public void a() {
            this.a.l.a(this.b);
        }

        public String b() {
            return "receive a message.";
        }
    }

    class k extends h {
        boolean a;
        final /* synthetic */ XMPushService b;

        public k(XMPushService xMPushService, boolean z) {
            this.b = xMPushService;
            super(4);
            this.a = z;
        }

        public void a() {
            if (this.b.f()) {
                try {
                    if (!this.a) {
                        com.xiaomi.d.h.a();
                    }
                    this.b.j.b(this.a);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.b.c.a((Throwable) e);
                    this.b.a(10, e);
                }
            }
        }

        public String b() {
            return "send ping..";
        }
    }

    class l extends h {
        com.xiaomi.push.service.am.b a = null;
        final /* synthetic */ XMPushService b;

        public l(XMPushService xMPushService, com.xiaomi.push.service.am.b bVar) {
            this.b = xMPushService;
            super(4);
            this.a = bVar;
        }

        public void a() {
            try {
                this.a.a(com.xiaomi.push.service.am.c.unbind, 1, 16, null, null);
                this.b.j.a(this.a.h, this.a.b);
                this.a.a(com.xiaomi.push.service.am.c.binding, 1, 16, null, null);
                this.b.j.a(this.a);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.b.c.a((Throwable) e);
                this.b.a(10, e);
            }
        }

        public String b() {
            return "rebind the client. " + this.a.h;
        }
    }

    class m extends h {
        final /* synthetic */ XMPushService a;

        m(XMPushService xMPushService) {
            this.a = xMPushService;
            super(3);
        }

        public void a() {
            this.a.a(11, null);
            if (this.a.b()) {
                this.a.n();
            }
        }

        public String b() {
            return "reset the connection.";
        }
    }

    class n extends h {
        com.xiaomi.push.service.am.b a = null;
        int b;
        String c;
        String d;
        final /* synthetic */ XMPushService e;

        public n(XMPushService xMPushService, com.xiaomi.push.service.am.b bVar, int i, String str, String str2) {
            this.e = xMPushService;
            super(9);
            this.a = bVar;
            this.b = i;
            this.c = str;
            this.d = str2;
        }

        public void a() {
            if (!(this.a.m == com.xiaomi.push.service.am.c.unbind || this.e.j == null)) {
                try {
                    this.e.j.a(this.a.h, this.a.b);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.b.c.a((Throwable) e);
                    this.e.a(10, e);
                }
            }
            this.a.a(com.xiaomi.push.service.am.c.unbind, this.b, 0, this.d, this.c);
        }

        public String b() {
            return "unbind the channel. " + this.a.h;
        }
    }

    static {
        com.xiaomi.network.f.a("app.chat.xiaomi.net", "app.chat.xiaomi.net");
        com.xiaomi.network.f.a("app.chat.xiaomi.net", "42.62.94.2:443");
        com.xiaomi.network.f.a("app.chat.xiaomi.net", "114.54.23.2");
        com.xiaomi.network.f.a("app.chat.xiaomi.net", "111.13.142.2");
        com.xiaomi.network.f.a("app.chat.xiaomi.net", "111.206.200.2");
    }

    @TargetApi(11)
    public static Notification a(Context context) {
        Intent intent = new Intent(context, XMPushService.class);
        if (VERSION.SDK_INT >= 11) {
            Builder builder = new Builder(context);
            builder.setSmallIcon(context.getApplicationInfo().icon);
            builder.setContentTitle("Push Service");
            builder.setContentText("Push Service");
            builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
            return builder.getNotification();
        }
        Notification notification = new Notification();
        notification.setLatestEventInfo(context, "Push Service", "Push Service", PendingIntent.getService(context, 0, intent, 0));
        return notification;
    }

    private com.xiaomi.smack.packet.c a(com.xiaomi.smack.packet.c cVar, String str) {
        byte[] a = s.a(str, cVar.k());
        com.xiaomi.smack.packet.c cVar2 = new com.xiaomi.smack.packet.c();
        cVar2.n(cVar.n());
        cVar2.m(cVar.m());
        cVar2.k(cVar.k());
        cVar2.l(cVar.l());
        cVar2.b(true);
        String a2 = s.a(a, com.xiaomi.smack.d.d.c(cVar.c()));
        com.xiaomi.smack.packet.a aVar = new com.xiaomi.smack.packet.a("s", null, (String[]) null, (String[]) null);
        aVar.b(a2);
        cVar2.a(aVar);
        return cVar2;
    }

    private com.xiaomi.smack.packet.d a(com.xiaomi.smack.packet.d dVar, String str, String str2, boolean z) {
        am a = am.a();
        List b = a.b(str);
        if (b.isEmpty()) {
            com.xiaomi.channel.commonutils.b.c.a("open channel should be called first before sending a packet, pkg=" + str);
        } else {
            dVar.o(str);
            String l = dVar.l();
            if (TextUtils.isEmpty(l)) {
                l = (String) b.get(0);
                dVar.l(l);
            }
            com.xiaomi.push.service.am.b b2 = a.b(l, dVar.n());
            if (!f()) {
                com.xiaomi.channel.commonutils.b.c.a("drop a packet as the channel is not connected, chid=" + l);
            } else if (b2 == null || b2.m != com.xiaomi.push.service.am.c.binded) {
                com.xiaomi.channel.commonutils.b.c.a("drop a packet as the channel is not opened, chid=" + l);
            } else if (TextUtils.equals(str2, b2.j)) {
                return ((dVar instanceof com.xiaomi.smack.packet.c) && z) ? a((com.xiaomi.smack.packet.c) dVar, b2.i) : dVar;
            } else {
                com.xiaomi.channel.commonutils.b.c.a("invalid session. " + str2);
            }
        }
        return null;
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra(o.y);
        String stringExtra2 = intent.getStringExtra(o.B);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
        com.xiaomi.smack.packet.d dVar = (com.xiaomi.smack.packet.c) a(new com.xiaomi.smack.packet.c(bundleExtra), stringExtra, stringExtra2, false);
        if (dVar != null) {
            com.xiaomi.push.service.am.b b = am.a().b(dVar.l(), dVar.n());
            if (booleanExtra && "3".equals(dVar.l())) {
                com.xiaomi.smack.a h = h();
                if (h != null && h.a()) {
                    c(new u(this, com.xiaomi.c.b.a(dVar, b.i)));
                    return;
                }
            }
            if (booleanExtra) {
                dVar = a((com.xiaomi.smack.packet.c) dVar, b.i);
            }
            if (dVar != null) {
                c(new u(this, dVar));
            }
        }
    }

    private void a(String str, int i) {
        Collection<com.xiaomi.push.service.am.b> c = am.a().c(str);
        if (c != null) {
            for (com.xiaomi.push.service.am.b bVar : c) {
                if (bVar != null) {
                    a(new n(this, bVar, i, null, null));
                }
            }
        }
        am.a().a(str);
    }

    public static boolean a(int i, String str) {
        return (TextUtils.equals(str, "Enter") && i == 1) ? true : TextUtils.equals(str, "Leave") && i == 2;
    }

    private boolean a(String str, Intent intent) {
        com.xiaomi.push.service.am.b b = am.a().b(str, intent.getStringExtra(o.p));
        boolean z = false;
        if (b == null || str == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra(o.B);
        String stringExtra2 = intent.getStringExtra(o.u);
        if (!(TextUtils.isEmpty(b.j) || TextUtils.equals(stringExtra, b.j))) {
            com.xiaomi.channel.commonutils.b.c.a("session changed. old session=" + b.j + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(b.i)) {
            return z;
        }
        com.xiaomi.channel.commonutils.b.c.a("security changed. chid = " + str + " sechash = " + com.xiaomi.channel.commonutils.g.c.a(stringExtra2));
        return true;
    }

    private boolean a(String str, String str2, Context context) {
        if (TextUtils.equals("Leave", str) && !TextUtils.equals("Enter", ar.a(context).c(str2))) {
            return false;
        }
        if (ar.a(context).a(str2, str) != 0) {
            return true;
        }
        com.xiaomi.channel.commonutils.b.c.a("update geofence statue failed geo_id:" + str2);
        return false;
    }

    private com.xiaomi.push.service.am.b b(String str, Intent intent) {
        com.xiaomi.push.service.am.b b = am.a().b(str, intent.getStringExtra(o.p));
        if (b == null) {
            b = new com.xiaomi.push.service.am.b(this);
        }
        b.h = intent.getStringExtra(o.q);
        b.b = intent.getStringExtra(o.p);
        b.c = intent.getStringExtra(o.s);
        b.a = intent.getStringExtra(o.y);
        b.f = intent.getStringExtra(o.w);
        b.g = intent.getStringExtra(o.x);
        b.e = intent.getBooleanExtra(o.v, false);
        b.i = intent.getStringExtra(o.u);
        b.j = intent.getStringExtra(o.B);
        b.d = intent.getStringExtra(o.t);
        b.k = this.k;
        b.l = getApplicationContext();
        am.a().a(b);
        return b;
    }

    private void b(Intent intent) {
        int i = 0;
        String stringExtra = intent.getStringExtra(o.y);
        String stringExtra2 = intent.getStringExtra(o.B);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        com.xiaomi.smack.packet.c[] cVarArr = new com.xiaomi.smack.packet.c[parcelableArrayExtra.length];
        boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
        int i2 = 0;
        while (i2 < parcelableArrayExtra.length) {
            cVarArr[i2] = new com.xiaomi.smack.packet.c((Bundle) parcelableArrayExtra[i2]);
            cVarArr[i2] = (com.xiaomi.smack.packet.c) a(cVarArr[i2], stringExtra, stringExtra2, false);
            if (cVarArr[i2] != null) {
                i2++;
            } else {
                return;
            }
        }
        am a = am.a();
        if (booleanExtra && "3".equals(cVarArr[0].l())) {
            com.xiaomi.smack.a h = h();
            if (h != null && h.a()) {
                com.xiaomi.c.b[] bVarArr = new com.xiaomi.c.b[cVarArr.length];
                while (i < cVarArr.length) {
                    com.xiaomi.smack.packet.d dVar = cVarArr[i];
                    bVarArr[i] = com.xiaomi.c.b.a(dVar, a.b(dVar.l(), dVar.n()).i);
                    i++;
                }
                c(new a(this, bVarArr));
                return;
            }
        }
        while (i < cVarArr.length) {
            cVarArr[i] = booleanExtra ? a(cVarArr[i], a.b(cVarArr[i].l(), cVarArr[i].n()).i) : cVarArr[i];
            i++;
        }
        c(new a(this, cVarArr));
    }

    private void b(boolean z) {
        this.g = System.currentTimeMillis();
        if (!f()) {
            a(true);
        } else if (this.j.o() || this.j.p() || com.xiaomi.channel.commonutils.d.d.f(this)) {
            c(new k(this, z));
        } else {
            c(new f(this, 17, null));
            a(true);
        }
    }

    private void c(Intent intent) {
        com.xiaomi.push.service.am.b bVar = null;
        boolean z = true;
        boolean z2 = false;
        am a = am.a();
        String stringExtra;
        if (o.d.equalsIgnoreCase(intent.getAction()) || o.j.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(o.q);
            if (TextUtils.isEmpty(intent.getStringExtra(o.u))) {
                com.xiaomi.channel.commonutils.b.c.a("security is empty. ignore.");
            } else if (stringExtra != null) {
                boolean a2 = a(stringExtra, intent);
                com.xiaomi.push.service.am.b b = b(stringExtra, intent);
                if (!com.xiaomi.channel.commonutils.d.d.d(this)) {
                    this.k.a(this, b, false, 2, null);
                } else if (!f()) {
                    a(true);
                } else if (b.m == com.xiaomi.push.service.am.c.unbind) {
                    c(new a(this, b));
                } else if (a2) {
                    c(new l(this, b));
                } else if (b.m == com.xiaomi.push.service.am.c.binding) {
                    com.xiaomi.channel.commonutils.b.c.a(String.format("the client is binding. %1$s %2$s.", new Object[]{b.h, b.b}));
                } else if (b.m == com.xiaomi.push.service.am.c.binded) {
                    this.k.a(this, b, true, 0, null);
                }
            } else {
                com.xiaomi.channel.commonutils.b.c.d("channel id is empty, do nothing!");
            }
        } else if (o.i.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(o.y);
            r2 = intent.getStringExtra(o.q);
            Object stringExtra2 = intent.getStringExtra(o.p);
            com.xiaomi.channel.commonutils.b.c.a("Service called closechannel chid = " + r2 + " userId = " + stringExtra2);
            if (TextUtils.isEmpty(r2)) {
                for (String stringExtra3 : a.b(stringExtra3)) {
                    a(stringExtra3, 2);
                }
            } else if (TextUtils.isEmpty(stringExtra2)) {
                a(r2, 2);
            } else {
                a(r2, stringExtra2, 2, null, null);
            }
        } else if (o.e.equalsIgnoreCase(intent.getAction())) {
            a(intent);
        } else if (o.g.equalsIgnoreCase(intent.getAction())) {
            b(intent);
        } else if (o.f.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(o.y);
            r1 = intent.getStringExtra(o.B);
            r4 = new com.xiaomi.smack.packet.b(intent.getBundleExtra("ext_packet"));
            if (a(r4, stringExtra3, r1, false) != null) {
                c(new u(this, r4));
            }
        } else if (o.h.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(o.y);
            r1 = intent.getStringExtra(o.B);
            r4 = new com.xiaomi.smack.packet.f(intent.getBundleExtra("ext_packet"));
            if (a(r4, stringExtra3, r1, false) != null) {
                c(new u(this, r4));
            }
        } else if (o.k.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(o.q);
            r1 = intent.getStringExtra(o.p);
            if (stringExtra3 != null) {
                com.xiaomi.channel.commonutils.b.c.a("request reset connection from chid = " + stringExtra3);
                com.xiaomi.push.service.am.b b2 = am.a().b(stringExtra3, r1);
                if (b2 != null && b2.i.equals(intent.getStringExtra(o.u)) && b2.m == com.xiaomi.push.service.am.c.binded) {
                    com.xiaomi.smack.a h = h();
                    if (h == null || !h.a(System.currentTimeMillis() - com.tencent.qalsdk.base.a.ap)) {
                        c(new m(this));
                    }
                }
            }
        } else if (o.l.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(o.y);
            List b3 = a.b(stringExtra3);
            if (b3.isEmpty()) {
                com.xiaomi.channel.commonutils.b.c.a("open channel should be called first before update info, pkg=" + stringExtra3);
                return;
            }
            stringExtra3 = intent.getStringExtra(o.q);
            Object stringExtra4 = intent.getStringExtra(o.p);
            if (TextUtils.isEmpty(stringExtra3)) {
                stringExtra3 = (String) b3.get(0);
            }
            if (TextUtils.isEmpty(stringExtra4)) {
                Collection c = a.c(stringExtra3);
                if (!(c == null || c.isEmpty())) {
                    bVar = (com.xiaomi.push.service.am.b) c.iterator().next();
                }
            } else {
                bVar = a.b(stringExtra3, stringExtra4);
            }
            if (bVar != null) {
                if (intent.hasExtra(o.w)) {
                    bVar.f = intent.getStringExtra(o.w);
                }
                if (intent.hasExtra(o.x)) {
                    bVar.g = intent.getStringExtra(o.x);
                }
            }
        } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
            if (q.a(getApplicationContext()).a() && q.a(getApplicationContext()).b() == 0) {
                com.xiaomi.channel.commonutils.b.c.a("register without being provisioned. " + intent.getStringExtra("mipush_app_package"));
                return;
            }
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            String stringExtra5 = intent.getStringExtra("mipush_app_package");
            boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
            int intExtra = intent.getIntExtra("mipush_env_type", 1);
            be.a((Context) this).g(stringExtra5);
            if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                a(byteArrayExtra, stringExtra5);
            } else {
                c(new al(this, 14, intExtra, byteArrayExtra, stringExtra5));
            }
        } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("mipush_app_package");
            r1 = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
            if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                be.a((Context) this).d(stringExtra3);
            }
            a(stringExtra3, r1, booleanExtra2);
        } else if (r.a.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("uninstall_pkg_name");
            if (stringExtra3 != null && !TextUtils.isEmpty(stringExtra3.trim())) {
                try {
                    getPackageManager().getPackageInfo(stringExtra3, 0);
                    z = false;
                } catch (NameNotFoundException e) {
                }
                if ("com.xiaomi.channel".equals(stringExtra3) && !am.a().c("1").isEmpty() && r9) {
                    a("1", 0);
                    com.xiaomi.channel.commonutils.b.c.a("close the miliao channel as the app is uninstalled.");
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                r2 = sharedPreferences.getString(stringExtra3, null);
                if (!TextUtils.isEmpty(r2) && r9) {
                    Editor edit = sharedPreferences.edit();
                    edit.remove(stringExtra3);
                    edit.commit();
                    if (f.e(this, stringExtra3)) {
                        f.d(this, stringExtra3);
                    }
                    f.b(this, stringExtra3);
                    if (f() && r2 != null) {
                        try {
                            d.a(this, d.a(stringExtra3, r2));
                            com.xiaomi.channel.commonutils.b.c.a("uninstall " + stringExtra3 + " msg sent");
                        } catch (Exception e2) {
                            com.xiaomi.channel.commonutils.b.c.d("Fail to send Message: " + e2.getMessage());
                            a(10, e2);
                        }
                    }
                }
            }
        } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(o.y);
            r1 = intent.getIntExtra(o.z, -2);
            if (!TextUtils.isEmpty(stringExtra3)) {
                if (r1 >= -1) {
                    f.a((Context) this, stringExtra3, r1);
                } else {
                    f.a((Context) this, stringExtra3, intent.getStringExtra(o.D), intent.getStringExtra(o.E));
                }
            }
        } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
            r2 = intent.getStringExtra(o.y);
            CharSequence stringExtra6 = intent.getStringExtra(o.C);
            CharSequence b4;
            if (intent.hasExtra(o.A)) {
                r1 = intent.getIntExtra(o.A, 0);
                b4 = com.xiaomi.channel.commonutils.g.c.b(r2 + r1);
            } else {
                b4 = com.xiaomi.channel.commonutils.g.c.b(r2);
                r1 = 0;
                z2 = true;
            }
            if (TextUtils.isEmpty(r2) || !TextUtils.equals(stringExtra6, r0)) {
                com.xiaomi.channel.commonutils.b.c.d("invalid notification for " + r2);
            } else if (z2) {
                f.d(this, r2);
            } else {
                f.b((Context) this, r2, r1);
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
            Object stringExtra7 = intent.getStringExtra("mipush_app_package");
            if (!TextUtils.isEmpty(stringExtra7)) {
                be.a((Context) this).e(stringExtra7);
            }
            if (!"com.xiaomi.xmsf".equals(getPackageName())) {
                if (this.f != null) {
                    unregisterReceiver(this.f);
                    this.f = null;
                }
                this.m.c();
                a(new an(this, 2));
                am.a().e();
                am.a().a((Context) this, 0);
                am.a().d();
                v.a().b();
                com.xiaomi.push.service.a.a.a();
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
            r2 = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            String stringExtra8 = intent.getStringExtra("mipush_app_id");
            String stringExtra9 = intent.getStringExtra("mipush_app_token");
            if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                be.a((Context) this).f(r2);
            }
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                be.a((Context) this).h(r2);
                be.a((Context) this).i(r2);
            }
            if (byteArrayExtra2 == null) {
                bg.a(this, r2, byteArrayExtra2, 70000003, "null payload");
                return;
            }
            bg.b(r2, byteArrayExtra2);
            a(new bf(this, r2, stringExtra8, stringExtra9, byteArrayExtra2));
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.f == null) {
                this.f = new e(this);
                registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
        } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("mipush_app_package");
            r1 = intent.getByteArrayExtra("mipush_payload");
            org.apache.thrift.a eVar = new com.xiaomi.xmpush.thrift.e();
            try {
                ar.a(eVar, r1);
                com.xiaomi.e.d.a((Context) this).b(eVar, stringExtra3);
            } catch (Throwable e3) {
                com.xiaomi.channel.commonutils.b.c.a(e3);
            }
        } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
            com.xiaomi.channel.commonutils.b.c.a("Service called on timer");
            if (k()) {
                b(false);
            }
        } else if ("com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
            com.xiaomi.channel.commonutils.b.c.a("Service called on check alive.");
            if (k()) {
                b(false);
            }
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            j();
        }
    }

    private void c(h hVar) {
        this.m.a((com.xiaomi.push.service.av.b) hVar);
    }

    private void c(boolean z) {
        try {
            if (!com.xiaomi.channel.commonutils.android.j.d()) {
                return;
            }
            if (z) {
                sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
            } else {
                sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.b.c.a(e);
        }
    }

    private void j() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.b.c.a(e);
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null) {
            com.xiaomi.channel.commonutils.b.c.a("network changed, " + activeNetworkInfo.toString());
        } else {
            com.xiaomi.channel.commonutils.b.c.a("network changed, no active network");
        }
        if (com.xiaomi.d.f.b() != null) {
            com.xiaomi.d.f.b().b();
        }
        com.xiaomi.smack.d.g.a((Context) this);
        this.i.q();
        if (com.xiaomi.channel.commonutils.d.d.d(this)) {
            if (f() && k()) {
                b(false);
            }
            if (!(f() || g())) {
                this.m.b(1);
                a(new d(this));
            }
            com.xiaomi.push.a.b.a((Context) this).a();
        } else {
            a(new f(this, 2, null));
        }
        m();
        com.xiaomi.e.d.a((Context) this).a("NewWork Changed");
    }

    private boolean k() {
        return System.currentTimeMillis() - this.g < 30000 ? false : com.xiaomi.channel.commonutils.d.d.e(this);
    }

    private boolean l() {
        return "com.xiaomi.xmsf".equals(getPackageName()) || !be.a((Context) this).b(getPackageName());
    }

    private void m() {
        if (!b()) {
            com.xiaomi.push.service.a.a.a();
        } else if (!com.xiaomi.push.service.a.a.b()) {
            com.xiaomi.push.service.a.a.a(true);
        }
    }

    private void n() {
        if (this.j != null && this.j.j()) {
            com.xiaomi.channel.commonutils.b.c.d("try to connect while connecting.");
        } else if (this.j == null || !this.j.k()) {
            this.d.a(com.xiaomi.channel.commonutils.d.d.l(this));
            o();
            if (this.j == null) {
                am.a().a((Context) this);
                c(false);
            }
        } else {
            com.xiaomi.channel.commonutils.b.c.d("try to connect while is connected.");
        }
    }

    private void o() {
        try {
            this.i.a(this.n, new ad(this));
            this.i.s();
            this.j = this.i;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.b.c.a("fail to create Slim connection", e);
            this.i.b(3, e);
        }
    }

    private boolean p() {
        return TextUtils.equals(getPackageName(), "com.xiaomi.xmsf") ? false : k.a((Context) this).a(com.xiaomi.xmpush.thrift.f.ForegroundServiceSwitch.a(), false);
    }

    private void q() {
        if (VERSION.SDK_INT < 18) {
            startForeground(h, new Notification());
        } else {
            bindService(new Intent(this, XMJobService.class), new ae(this), 1);
        }
    }

    void a() {
        if (System.currentTimeMillis() - this.g >= ((long) com.xiaomi.smack.g.c()) && com.xiaomi.channel.commonutils.d.d.e(this)) {
            b(true);
        }
    }

    public void a(int i) {
        this.m.b(i);
    }

    public void a(int i, Exception exception) {
        com.xiaomi.channel.commonutils.b.c.a("disconnect " + hashCode() + ", " + (this.j == null ? null : Integer.valueOf(this.j.hashCode())));
        if (this.j != null) {
            this.j.b(i, exception);
            this.j = null;
        }
        a(7);
        a(4);
        am.a().a((Context) this, i);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.metoknlp.geofencing.state_change");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void a(com.xiaomi.c.b bVar) {
        if (this.j != null) {
            this.j.b(bVar);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(h hVar) {
        a(hVar, 0);
    }

    public void a(h hVar, long j) {
        try {
            this.m.a((com.xiaomi.push.service.av.b) hVar, j);
        } catch (IllegalStateException e) {
        }
    }

    public void a(com.xiaomi.push.service.am.b bVar) {
        if (bVar != null) {
            long a = bVar.a();
            com.xiaomi.channel.commonutils.b.c.a("schedule rebind job in " + (a / 1000));
            a(new a(this, bVar), a);
        }
    }

    public void a(com.xiaomi.smack.a aVar) {
        com.xiaomi.d.f.b().a(aVar);
        c(true);
        this.e.a();
        Iterator it = am.a().b().iterator();
        while (it.hasNext()) {
            a(new a(this, (com.xiaomi.push.service.am.b) it.next()));
        }
    }

    public void a(com.xiaomi.smack.a aVar, int i, Exception exception) {
        com.xiaomi.d.f.b().a(aVar, i, exception);
        a(false);
    }

    public void a(com.xiaomi.smack.a aVar, Exception exception) {
        com.xiaomi.d.f.b().a(aVar, exception);
        c(false);
        a(false);
    }

    public void a(com.xiaomi.smack.packet.d dVar) {
        if (this.j != null) {
            this.j.a(dVar);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        com.xiaomi.push.service.am.b b = am.a().b(str, str2);
        if (b != null) {
            a(new n(this, b, i, str4, str3));
        }
        am.a().a(str, str2);
    }

    void a(String str, byte[] bArr, boolean z) {
        Collection c = am.a().c("5");
        if (c.isEmpty()) {
            if (z) {
                bg.b(str, bArr);
            }
        } else if (((com.xiaomi.push.service.am.b) c.iterator().next()).m == com.xiaomi.push.service.am.c.binded) {
            c(new ao(this, 4, str, bArr));
        } else if (z) {
            bg.b(str, bArr);
        }
    }

    public void a(boolean z) {
        this.e.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            bg.a(this, str, bArr, 70000003, "null payload");
            com.xiaomi.channel.commonutils.b.c.a("register request without payload");
            return;
        }
        org.apache.thrift.a acVar = new ac();
        try {
            ar.a(acVar, bArr);
            if (acVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                org.apache.thrift.a agVar = new ag();
                try {
                    ar.a(agVar, acVar.f());
                    bg.a(acVar.j(), bArr);
                    a(new bf(this, acVar.j(), agVar.d(), agVar.h(), bArr));
                    return;
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.b.c.a(e);
                    bg.a(this, str, bArr, 70000003, " data action error.");
                    return;
                }
            }
            bg.a(this, str, bArr, 70000003, " registration action required.");
            com.xiaomi.channel.commonutils.b.c.a("register request with invalid payload");
        } catch (Throwable e2) {
            com.xiaomi.channel.commonutils.b.c.a(e2);
            bg.a(this, str, bArr, 70000003, " data container error.");
        }
    }

    public void a(com.xiaomi.c.b[] bVarArr) {
        if (this.j != null) {
            this.j.a(bVarArr);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        if (this.j != null) {
            this.j.a(dVarArr);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void b(h hVar) {
        this.m.a(hVar.f, (com.xiaomi.push.service.av.b) hVar);
    }

    public void b(com.xiaomi.smack.a aVar) {
        com.xiaomi.channel.commonutils.b.c.c("begin to connect...");
        com.xiaomi.d.f.b().b(aVar);
    }

    public boolean b() {
        return com.xiaomi.channel.commonutils.d.d.d(this) && am.a().c() > 0 && !c() && l();
    }

    public boolean b(int i) {
        return this.m.a(i);
    }

    public boolean c() {
        try {
            Class cls = Class.forName("miui.os.Build");
            return cls.getField("IS_CM_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CU_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CT_CUSTOMIZATION_TEST").getBoolean(null);
        } catch (Throwable th) {
            return false;
        }
    }

    public aa d() {
        return new aa();
    }

    public aa e() {
        return this.k;
    }

    public boolean f() {
        return this.j != null && this.j.k();
    }

    public boolean g() {
        return this.j != null && this.j.j();
    }

    public com.xiaomi.smack.a h() {
        return this.j;
    }

    public IBinder onBind(Intent intent) {
        return this.a.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        com.xiaomi.channel.commonutils.android.j.a(this);
        bc a = bd.a(this);
        if (a != null) {
            com.xiaomi.channel.commonutils.c.a.a(a.g);
        }
        this.a = new Messenger(new af(this));
        p.a(this);
        this.d = new ag(this, null, 5222, "xiaomi.com", null);
        this.d.a(true);
        this.i = new com.xiaomi.c.f(this, this.d);
        this.k = d();
        try {
            if (com.xiaomi.channel.commonutils.android.j.d()) {
                this.k.a((Context) this);
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.b.c.a(e);
        }
        com.xiaomi.push.service.a.a.a((Context) this);
        this.i.a((com.xiaomi.smack.d) this);
        this.l = new PacketSync(this);
        this.e = new t(this);
        new ap().a();
        com.xiaomi.d.f.a().a(this);
        this.m = new av("Connection Controller Thread");
        if (l()) {
            a(new ah(this, 11));
        }
        am a2 = am.a();
        a2.e();
        a2.a(new ai(this));
        if (l()) {
            this.f = new e(this);
            registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (p()) {
            q();
        }
        com.xiaomi.e.d.a((Context) this).a(new ax(this), "UPLOADER_PUSH_CHANNEL");
        a(this.b);
        com.xiaomi.channel.commonutils.c.g.a((Context) this).a(new aq(this), 86400);
        com.xiaomi.channel.commonutils.b.c.a("XMPushService created pid = " + h);
    }

    public void onDestroy() {
        if (this.f != null) {
            unregisterReceiver(this.f);
        }
        unregisterReceiver(this.b);
        this.m.c();
        a(new ac(this, 2));
        a(new i(this));
        am.a().e();
        am.a().a((Context) this, 15);
        am.a().d();
        this.i.b((com.xiaomi.smack.d) this);
        v.a().b();
        com.xiaomi.push.service.a.a.a();
        super.onDestroy();
        com.xiaomi.channel.commonutils.b.c.a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        if (intent == null) {
            com.xiaomi.channel.commonutils.b.c.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.b.c.c(String.format("onStart() with intent.Action = %s, chid = %s", new Object[]{intent.getAction(), intent.getStringExtra(o.q)}));
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.m.d()) {
                    com.xiaomi.channel.commonutils.b.c.d("ERROR, the job controller is blocked.");
                    am.a().a((Context) this, 14);
                    stopSelf();
                    return;
                }
                a(new g(this, intent));
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                a(new g(this, intent));
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return c;
    }
}
