package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: NotificationCompat */
public class t {
    private static final g a;

    /* compiled from: NotificationCompat */
    public static class a extends android.support.v4.app.w.a {
        public static final android.support.v4.app.w.a.a d = new android.support.v4.app.w.a.a() {
        };
        public int a;
        public CharSequence b;
        public PendingIntent c;
        private final Bundle e;
        private final ac[] f;

        public /* synthetic */ android.support.v4.app.ae.a[] f() {
            return e();
        }

        public int a() {
            return this.a;
        }

        public CharSequence b() {
            return this.b;
        }

        public PendingIntent c() {
            return this.c;
        }

        public Bundle d() {
            return this.e;
        }

        public ac[] e() {
            return this.f;
        }
    }

    /* compiled from: NotificationCompat */
    public static abstract class p {
        d d;
        CharSequence e;
        CharSequence f;
        boolean g = false;

        public void a(d dVar) {
            if (this.d != dVar) {
                this.d = dVar;
                if (this.d != null) {
                    this.d.a(this);
                }
            }
        }
    }

    /* compiled from: NotificationCompat */
    public static class b extends p {
        Bitmap a;
        Bitmap b;
        boolean c;
    }

    /* compiled from: NotificationCompat */
    public static class c extends p {
        CharSequence a;

        public c a(CharSequence charSequence) {
            this.a = d.d(charSequence);
            return this;
        }
    }

    /* compiled from: NotificationCompat */
    public static class d {
        Notification A;
        public Notification B = new Notification();
        public ArrayList<String> C;
        public Context a;
        public CharSequence b;
        public CharSequence c;
        PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        public Bitmap g;
        public CharSequence h;
        public int i;
        int j;
        boolean k = true;
        public boolean l;
        public p m;
        public CharSequence n;
        int o;
        int p;
        boolean q;
        String r;
        boolean s;
        String t;
        public ArrayList<a> u = new ArrayList();
        boolean v = false;
        String w;
        Bundle x;
        int y = 0;
        int z = 0;

        public d(Context context) {
            this.a = context;
            this.B.when = System.currentTimeMillis();
            this.B.audioStreamType = -1;
            this.j = 0;
            this.C = new ArrayList();
        }

        public d a(long j) {
            this.B.when = j;
            return this;
        }

        public d a(int i) {
            this.B.icon = i;
            return this;
        }

        public d a(CharSequence charSequence) {
            this.b = d(charSequence);
            return this;
        }

        public d b(CharSequence charSequence) {
            this.c = d(charSequence);
            return this;
        }

        public d a(RemoteViews remoteViews) {
            this.B.contentView = remoteViews;
            return this;
        }

        public d a(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public d c(CharSequence charSequence) {
            this.B.tickerText = d(charSequence);
            return this;
        }

        public d a(Bitmap bitmap) {
            this.g = bitmap;
            return this;
        }

        public d a(Uri uri) {
            this.B.sound = uri;
            this.B.audioStreamType = -1;
            return this;
        }

        public d a(long[] jArr) {
            this.B.vibrate = jArr;
            return this;
        }

        public d a(boolean z) {
            a(2, z);
            return this;
        }

        public d b(boolean z) {
            a(8, z);
            return this;
        }

        public d c(boolean z) {
            a(16, z);
            return this;
        }

        public d b(int i) {
            this.B.defaults = i;
            if ((i & 4) != 0) {
                Notification notification = this.B;
                notification.flags |= 1;
            }
            return this;
        }

        private void a(int i, boolean z) {
            if (z) {
                Notification notification = this.B;
                notification.flags |= i;
                return;
            }
            notification = this.B;
            notification.flags &= i ^ -1;
        }

        public d c(int i) {
            this.j = i;
            return this;
        }

        public d a(p pVar) {
            if (this.m != pVar) {
                this.m = pVar;
                if (this.m != null) {
                    this.m.a(this);
                }
            }
            return this;
        }

        public Notification a() {
            return t.a.a(this, b());
        }

        protected e b() {
            return new e();
        }

        protected static CharSequence d(CharSequence charSequence) {
            if (charSequence != null && charSequence.length() > 5120) {
                return charSequence.subSequence(0, 5120);
            }
            return charSequence;
        }
    }

    /* compiled from: NotificationCompat */
    protected static class e {
        protected e() {
        }

        public Notification a(d dVar, s sVar) {
            return sVar.b();
        }
    }

    /* compiled from: NotificationCompat */
    public static class f extends p {
        ArrayList<CharSequence> a = new ArrayList();

        public f a(CharSequence charSequence) {
            this.e = d.d(charSequence);
            return this;
        }

        public f b(CharSequence charSequence) {
            this.a.add(d.d(charSequence));
            return this;
        }
    }

    /* compiled from: NotificationCompat */
    interface g {
        Notification a(d dVar, e eVar);
    }

    /* compiled from: NotificationCompat */
    static class j implements g {
        j() {
        }

        public Notification a(d dVar, e eVar) {
            Notification notification = dVar.B;
            notification.setLatestEventInfo(dVar.a, dVar.b, dVar.c, dVar.d);
            if (dVar.j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    /* compiled from: NotificationCompat */
    static class n extends j {
        n() {
        }

        public Notification a(d dVar, e eVar) {
            s aVar = new android.support.v4.app.aa.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.o, dVar.p, dVar.q, dVar.l, dVar.j, dVar.n, dVar.v, dVar.x, dVar.r, dVar.s, dVar.t);
            t.b((r) aVar, dVar.u);
            t.b(aVar, dVar.m);
            return eVar.a(dVar, aVar);
        }
    }

    /* compiled from: NotificationCompat */
    static class o extends n {
        o() {
        }

        public Notification a(d dVar, e eVar) {
            s aVar = new android.support.v4.app.ab.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.o, dVar.p, dVar.q, dVar.k, dVar.l, dVar.j, dVar.n, dVar.v, dVar.C, dVar.x, dVar.r, dVar.s, dVar.t);
            t.b((r) aVar, dVar.u);
            t.b(aVar, dVar.m);
            return eVar.a(dVar, aVar);
        }
    }

    /* compiled from: NotificationCompat */
    static class h extends o {
        h() {
        }

        public Notification a(d dVar, e eVar) {
            s aVar = new android.support.v4.app.u.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.o, dVar.p, dVar.q, dVar.k, dVar.l, dVar.j, dVar.n, dVar.v, dVar.C, dVar.x, dVar.r, dVar.s, dVar.t);
            t.b((r) aVar, dVar.u);
            t.b(aVar, dVar.m);
            return eVar.a(dVar, aVar);
        }
    }

    /* compiled from: NotificationCompat */
    static class i extends h {
        i() {
        }

        public Notification a(d dVar, e eVar) {
            s aVar = new android.support.v4.app.v.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.o, dVar.p, dVar.q, dVar.k, dVar.l, dVar.j, dVar.n, dVar.v, dVar.w, dVar.C, dVar.x, dVar.y, dVar.z, dVar.A, dVar.r, dVar.s, dVar.t);
            t.b((r) aVar, dVar.u);
            t.b(aVar, dVar.m);
            return eVar.a(dVar, aVar);
        }
    }

    /* compiled from: NotificationCompat */
    static class k extends j {
        k() {
        }

        public Notification a(d dVar, e eVar) {
            Notification notification = dVar.B;
            notification.setLatestEventInfo(dVar.a, dVar.b, dVar.c, dVar.d);
            notification = x.a(notification, dVar.a, dVar.b, dVar.c, dVar.d, dVar.e);
            if (dVar.j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    /* compiled from: NotificationCompat */
    static class l extends j {
        l() {
        }

        public Notification a(d dVar, e eVar) {
            return y.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g);
        }
    }

    /* compiled from: NotificationCompat */
    static class m extends j {
        m() {
        }

        public Notification a(d dVar, e eVar) {
            return eVar.a(dVar, new android.support.v4.app.z.a(dVar.a, dVar.B, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.o, dVar.p, dVar.q));
        }
    }

    private static void b(r rVar, ArrayList<a> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            rVar.a((a) it.next());
        }
    }

    private static void b(s sVar, p pVar) {
        if (pVar == null) {
            return;
        }
        if (pVar instanceof c) {
            c cVar = (c) pVar;
            aa.a(sVar, cVar.e, cVar.g, cVar.f, cVar.a);
        } else if (pVar instanceof f) {
            f fVar = (f) pVar;
            aa.a(sVar, fVar.e, fVar.g, fVar.f, fVar.a);
        } else if (pVar instanceof b) {
            b bVar = (b) pVar;
            aa.a(sVar, bVar.e, bVar.g, bVar.f, bVar.a, bVar.b, bVar.c);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new i();
        } else if (VERSION.SDK_INT >= 20) {
            a = new h();
        } else if (VERSION.SDK_INT >= 19) {
            a = new o();
        } else if (VERSION.SDK_INT >= 16) {
            a = new n();
        } else if (VERSION.SDK_INT >= 14) {
            a = new m();
        } else if (VERSION.SDK_INT >= 11) {
            a = new l();
        } else if (VERSION.SDK_INT >= 9) {
            a = new k();
        } else {
            a = new j();
        }
    }
}
