package com.qq.reader.plugin.audiobook.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.t.d;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookPlayerActivity;
import com.qq.reader.plugin.audiobook.core.f.a;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.HashMap;
import java.util.Map;
import tencent.tls.platform.SigType;

public class QQPlayerService extends Service implements k {
    private static long a;
    private static final Class[] k = new Class[]{Integer.TYPE, Notification.class};
    private int b;
    private int c;
    private int d;
    private boolean e;
    private d f;
    private j g;
    private BroadcastReceiver h;
    private Bundle i;
    private NotificationManager j;
    private Object[] l;
    private final a m;
    private Handler n;
    private Handler o;
    private boolean p;
    private PhoneStateListener q;
    private boolean r;
    private final Handler s;
    private BroadcastReceiver t;
    private BroadcastReceiver u;
    private int v;
    private float w;
    private Handler x;
    private c y;
    private h z;

    static /* synthetic */ float b(QQPlayerService qQPlayerService, float f) {
        float f2 = qQPlayerService.w + f;
        qQPlayerService.w = f2;
        return f2;
    }

    static /* synthetic */ float c(QQPlayerService qQPlayerService, float f) {
        float f2 = qQPlayerService.w - f;
        qQPlayerService.w = f2;
        return f2;
    }

    public QQPlayerService() {
        this.b = -1;
        this.c = 0;
        this.d = -1;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = new Object[2];
        this.m = new a(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void d(int i) {
                this.a.a(i);
            }

            public boolean a() {
                return this.a.o();
            }

            public void b() {
                this.a.n();
            }

            public void c() {
                this.a.r();
            }

            public void d() {
                this.a.t();
                this.a.e(false);
            }

            public void e() {
                this.a.t();
                this.a.a(true);
            }

            public void f() {
                this.a.t();
                this.a.a(false);
            }

            public void g() throws RemoteException {
                this.a.v();
            }

            public long j() {
                return this.a.l();
            }

            public long i() {
                return this.a.k();
            }

            public long a(long j) {
                return this.a.a(j);
            }

            public SongInfo o() {
                return this.a.j();
            }

            public void a(SongInfo[] songInfoArr, Bundle bundle) throws RemoteException {
                this.a.a(songInfoArr, bundle, null);
            }

            public void a(SongInfo[] songInfoArr, Bundle bundle, SongInfo songInfo) throws RemoteException {
                this.a.a(songInfoArr, bundle, songInfo);
            }

            public SongInfo[] p() {
                return this.a.e();
            }

            public void a(int i) {
                this.a.b(i);
            }

            public int n() {
                return this.a.a();
            }

            public int q() {
                return this.a.h();
            }

            public int r() {
                return this.a.b();
            }

            public void b(int i) {
                this.a.c(i);
            }

            public void a(SongInfo[] songInfoArr, int i) {
                this.a.a(songInfoArr, i);
            }

            public void c(int i) {
                this.a.d(i);
            }

            public void a(SongInfo songInfo) {
                this.a.a(songInfo);
            }

            public int k() throws RemoteException {
                return this.a.d();
            }

            public long l() throws RemoteException {
                return this.a.f();
            }

            public long m() throws RemoteException {
                return this.a.g();
            }

            public int s() throws RemoteException {
                return this.a.m();
            }

            public void t() {
                this.a.u();
            }

            public void h() throws RemoteException {
                this.a.q();
            }
        };
        this.n = new Handler(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.f(false);
                if (this.a.f != null && this.a.f.h() == 0) {
                    this.a.n.sendEmptyMessageDelayed(12345021, TracerConfig.LOG_FLUSH_DURATION);
                }
            }
        };
        this.o = new Handler(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                try {
                    if (!this.a.o() && this.a.d() != 6 && !this.a.p && !this.a.e) {
                        this.a.stopSelf(this.a.d);
                    }
                } catch (Exception e) {
                }
            }
        };
        this.p = false;
        this.q = new PhoneStateListener(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void onCallStateChanged(int i, String str) {
                boolean z = true;
                switch (i) {
                    case 0:
                        if (this.a.p) {
                            this.a.w();
                            this.a.p = false;
                            return;
                        }
                        return;
                    case 1:
                        if (((AudioManager) this.a.getSystemService("audio")).getStreamVolume(2) > 0) {
                            boolean z2;
                            QQPlayerService qQPlayerService = this.a;
                            if ((this.a.o() || this.a.p) && this.a.g.f()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            qQPlayerService.p = z2;
                            this.a.p();
                            return;
                        }
                        return;
                    case 2:
                        QQPlayerService qQPlayerService2 = this.a;
                        if (!((this.a.o() || this.a.p) && this.a.g.f())) {
                            z = false;
                        }
                        qQPlayerService2.p = z;
                        this.a.p();
                        return;
                    default:
                        return;
                }
            }
        };
        this.r = true;
        this.s = new Handler(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                try {
                    this.a.r = true;
                } catch (Exception e) {
                }
            }
        };
        this.t = new BroadcastReceiver(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (e.n.equals(action) || e.t.equals(action) || e.r.equals(action) || e.p.equals(action) || e.q.equals(action)) {
                    this.a.d(false);
                }
            }
        };
        this.u = new BroadcastReceiver(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!Boolean.valueOf(this.a.b(action, intent.getStringExtra(e.y))).booleanValue() && e.b.equalsIgnoreCase(action)) {
                    this.a.u();
                }
            }
        };
        this.v = 0;
        this.w = 1.0f;
        this.x = new Handler(this) {
            final /* synthetic */ QQPlayerService a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                synchronized (QQPlayerService.class) {
                    switch (message.what) {
                        case 11:
                            if (this.a.v == 2) {
                                if (this.a.w < 0.0f) {
                                    this.a.w = 0.0f;
                                }
                                if (this.a.w >= 0.85f) {
                                    if (this.a.v == 2) {
                                        this.a.w = 1.0f;
                                        this.a.f.a(this.a.w);
                                        this.a.v = 0;
                                        break;
                                    }
                                }
                                QQPlayerService.b(this.a, 0.15f);
                                this.a.f.a(this.a.w);
                                this.a.x.sendEmptyMessageDelayed(11, 100);
                                break;
                            }
                            break;
                        case 12:
                            if (this.a.v == 1) {
                                if (this.a.w > 1.0f) {
                                    this.a.w = 1.0f;
                                }
                                if (this.a.w <= 0.15f) {
                                    if (this.a.v == 1) {
                                        this.a.w = 1.0f;
                                        if (message.arg1 == 0) {
                                            this.a.p();
                                        } else if (message.arg1 == -1) {
                                            this.a.c(true);
                                        } else if (message.arg1 == 1) {
                                            this.a.c(false);
                                        } else if (message.arg1 == 2) {
                                            this.a.E();
                                        }
                                        this.a.v = 0;
                                        break;
                                    }
                                }
                                QQPlayerService.c(this.a, 0.15f);
                                this.a.f.a(this.a.w);
                                Message obtain = Message.obtain();
                                obtain.what = message.what;
                                obtain.arg1 = message.arg1;
                                this.a.x.sendMessageDelayed(obtain, 100);
                                break;
                            }
                            break;
                    }
                }
            }
        };
        this.g = new j();
    }

    public void onCreate() {
        super.onCreate();
        b(n.a().a(12));
        ((TelephonyManager) getSystemService("phone")).listen(this.q, 32);
        s();
        this.f = new d(this);
        this.g.a((k) this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e.b);
        intentFilter.addAction(e.e);
        intentFilter.addAction(e.d);
        intentFilter.addAction(e.c);
        intentFilter.addAction(e.f);
        registerReceiver(this.u, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction(e.r);
        intentFilter.addAction(e.n);
        intentFilter.addAction(e.t);
        intentFilter.addAction(e.p);
        intentFilter.addAction(e.q);
        registerReceiver(this.t, intentFilter);
        J();
        I();
        K();
        C();
        t();
        this.j = (NotificationManager) getSystemService("notification");
    }

    public void a(int i, int i2, Object obj) {
        switch (i) {
            case 1:
                c();
                return;
            case 2:
                if (i2 == 1) {
                    b(e.t);
                    return;
                } else if (i2 == 2) {
                    n();
                    return;
                } else {
                    c();
                    return;
                }
            case 4:
                b(e.j);
                return;
            case 8:
                if (h() == 0) {
                    n();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.d = i2;
        if (intent != null) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(e.y);
            if (e.c.equals(action) && intent.getBooleanExtra("fromService", false)) {
                i.a("event_C194", null, this);
            }
            if (e.A.equals(stringExtra) && intent.getBooleanExtra("fromService", false)) {
                i.a("event_C195", null, this);
            }
            b(action, stringExtra);
        }
        return 3;
    }

    private void d(boolean z) {
        if (j() != null) {
            ComponentName componentName = new ComponentName(this, QQPlayerService.class);
            final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.player_notification_view);
            Intent intent = new Intent(e.c);
            intent.setComponent(componentName);
            remoteViews.setTextViewText(R.id.trackname, j().h());
            remoteViews.setTextViewText(R.id.artistalbum, j().i());
            remoteViews.setImageViewResource(R.id.icon, R.drawable.default_ting_book_bg);
            if (z) {
                remoteViews.setImageViewResource(R.id.play_pause_btn, R.drawable.notification_pause);
            } else {
                remoteViews.setImageViewResource(R.id.play_pause_btn, R.drawable.notification_play);
            }
            intent.putExtra("fromService", true);
            remoteViews.setOnClickPendingIntent(R.id.play_pause_btn, PendingIntent.getService(this, 0, intent, SigType.TLS));
            intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("filepath", j().a.getBookId() + "");
            intent.putExtras(bundle);
            intent.setClass(this, NativeAudioBookPlayerActivity.class);
            intent.setFlags(335544320);
            PendingIntent activity = PendingIntent.getActivity(this, 0, intent, SigType.TLS);
            Intent intent2 = new Intent();
            intent2.setComponent(componentName);
            intent2.putExtra("fromService", true);
            intent2.setAction(e.g);
            intent2.putExtra(e.y, e.A);
            remoteViews.setOnClickPendingIntent(R.id.close_btn, PendingIntent.getService(this, 0, intent2, SigType.TLS));
            final d y = ao.y((Context) this);
            y.a(remoteViews);
            y.a(activity);
            y.c(1);
            try {
                c.a((Context) this).a(ao.a(j().a.getBookId(), false, (int) Opcodes.OR_INT), new g(this) {
                    final /* synthetic */ QQPlayerService c;

                    public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                        if (obj != null && (obj instanceof j)) {
                            remoteViews.setImageViewBitmap(R.id.icon, ((j) obj).b());
                            this.c.startForeground(Opcodes.NEG_INT, y.a());
                        }
                    }

                    public void a(Exception exception, Drawable drawable) {
                        super.a(exception, drawable);
                        this.c.startForeground(Opcodes.NEG_INT, y.a());
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public void a(int i) {
        synchronized (QQPlayerService.class) {
            if (this.g != null) {
                this.g.b(i);
            }
        }
    }

    public void onDestroy() {
        try {
            F();
            if (o() || d() == 6) {
                n();
                L();
                unregisterReceiver(this.u);
                unregisterReceiver(this.t);
            } else {
                n();
                L();
                unregisterReceiver(this.u);
                unregisterReceiver(this.t);
            }
            if (this.h != null) {
                unregisterReceiver(this.h);
                this.h = null;
            }
            N();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public int a() {
        return this.b;
    }

    public int b() {
        int b;
        synchronized (QQPlayerService.class) {
            b = this.g.b();
        }
        return b;
    }

    private void v() {
        synchronized (QQPlayerService.class) {
            if (d() == 6) {
                this.f.f();
            } else {
                w();
            }
            x();
        }
    }

    private void w() {
        a = System.currentTimeMillis();
        f(true);
        this.n.sendEmptyMessageDelayed(12345021, TracerConfig.LOG_FLUSH_DURATION);
        if (this.f != null) {
            d(true);
            M();
            this.f.g();
            b(e.u);
            SongInfo i = i();
            if (i != null) {
                ao.a(2, i.e(), true, i.i());
            }
        }
        b(e.j);
    }

    private void x() {
        H();
    }

    private void y() {
        d(false);
        this.f.d();
        b(e.j);
        e(0);
    }

    private void z() {
        e(2);
    }

    public void c() {
        synchronized (QQPlayerService.class) {
            B();
        }
    }

    private void A() {
        n();
        b(e.l);
    }

    private void b(String str) {
        a(str, null);
    }

    private void a(String str, String str2) {
        Intent intent = new Intent(str);
        if (this.g.f()) {
            intent.putExtra(e.v, this.g.e());
        }
        intent.putExtra(e.w, d());
        if (str2 != null) {
            intent.putExtra(e.x, str2);
        }
        sendBroadcast(intent);
    }

    public void a(SongInfo[] songInfoArr, Bundle bundle, SongInfo songInfo) {
        int i;
        int i2 = 0;
        this.i = bundle;
        this.g.a(songInfoArr);
        if (!(songInfo == null || songInfoArr == null)) {
            i = 0;
            while (i < songInfoArr.length) {
                if (songInfoArr[i].equals(songInfo)) {
                    break;
                }
                i++;
            }
        }
        i = -1;
        if (i != -1) {
            i2 = i;
        } else if (this.g.a()) {
            i2 = -1;
        }
        this.g.a(i2);
    }

    private void B() {
        this.g.d(true);
        if (this.g.f()) {
            E();
        } else {
            A();
        }
    }

    public int d() {
        if (this.f != null) {
            return this.f.h();
        }
        return 2;
    }

    public SongInfo[] e() {
        return this.g.h();
    }

    public long f() {
        if (this.f != null) {
            return this.f.m();
        }
        return 0;
    }

    public long g() {
        if (this.f != null) {
            return this.f.n();
        }
        return 0;
    }

    public void a(SongInfo[] songInfoArr, int i) {
        this.g.a(songInfoArr, i);
    }

    public void b(int i) {
        if (this.b != i) {
            this.b = i;
            switch (i) {
                case 10:
                    this.g.c(true);
                    this.g.a(false);
                    this.g.b(false);
                    return;
                case 11:
                    this.g.c(true);
                    this.g.a(true);
                    this.g.b(false);
                    return;
                case 12:
                    this.g.c(false);
                    this.g.a(false);
                    this.g.b(false);
                    return;
                case 13:
                    this.g.c(false);
                    this.g.a(true);
                    this.g.b(false);
                    return;
                case 14:
                    this.g.c(false);
                    this.g.a(false);
                    this.g.b(true);
                    return;
                case 15:
                    this.g.c(false);
                    this.g.a(true);
                    this.g.b(true);
                    return;
                default:
                    return;
            }
        }
    }

    public void c(int i) {
        t();
        synchronized (QQPlayerService.class) {
            this.g.a(i);
            if (!(G() || d() == 6)) {
                if (!this.g.f()) {
                    A();
                } else if (o()) {
                    z();
                } else {
                    E();
                }
            }
        }
    }

    private void e(boolean z) {
        synchronized (QQPlayerService.class) {
            E();
        }
    }

    public int h() {
        if (this.g != null) {
            return this.g.c();
        }
        return 0;
    }

    public SongInfo i() {
        SongInfo d;
        synchronized (QQPlayerService.class) {
            d = this.g.d();
        }
        return d;
    }

    public SongInfo j() {
        SongInfo e;
        synchronized (QQPlayerService.class) {
            e = this.g.e();
        }
        return e;
    }

    public long k() {
        if (this.f != null) {
            return this.f.j();
        }
        return 0;
    }

    public long l() {
        if (this.f != null) {
            return this.f.k();
        }
        return 0;
    }

    public int m() {
        if (this.f != null) {
            return this.f.l();
        }
        return 0;
    }

    public long a(long j) {
        if (this.f == null) {
            return 0;
        }
        if (j < 0) {
            j = 0;
        }
        if (j > this.f.j()) {
            j = this.f.j();
        }
        return this.f.a((int) j);
    }

    public void n() {
        f(true);
        O();
        this.n.removeCallbacksAndMessages(null);
        long j = this.f.j();
        this.f.c();
        a(e.j, "" + j);
        SongInfo i = i();
        if (i != null) {
            ao.a(2, i.e(), false, i.i());
        }
        this.j.cancel(Opcodes.NEG_INT);
        C();
    }

    private void C() {
        this.o.removeCallbacksAndMessages(null);
        this.o.sendMessageDelayed(this.o.obtainMessage(), BuglyBroadcastRecevier.UPLOADLIMITED);
    }

    private void D() {
        this.o.removeCallbacksAndMessages(null);
    }

    public boolean o() {
        return this.f.i();
    }

    public void p() {
        f(true);
        O();
        this.n.removeCallbacksAndMessages(null);
        this.f.e();
        b(e.j);
        SongInfo i = i();
        if (i != null) {
            ao.a(2, i.e(), false, i.i());
        }
        d(false);
    }

    public void q() {
        synchronized (QQPlayerService.class) {
            if (d() == 0) {
                r();
            }
        }
    }

    public void r() {
        synchronized (QQPlayerService.class) {
            if (d() != 5) {
                y();
            } else {
                p();
            }
        }
    }

    public void a(boolean z) {
        synchronized (QQPlayerService.class) {
            c(z);
        }
    }

    public void b(boolean z) {
        if (z) {
            this.g.g();
        } else {
            this.g.d(false);
        }
        if (this.g.f()) {
            E();
        } else {
            A();
        }
    }

    public IBinder onBind(Intent intent) {
        this.e = true;
        return this.m;
    }

    public void onRebind(Intent intent) {
        D();
        this.e = true;
    }

    public boolean onUnbind(Intent intent) {
        this.e = false;
        if (!(o() || d() == 6 || this.p)) {
            if (h() > 0) {
                C();
            } else {
                stopSelf(this.d);
            }
        }
        return true;
    }

    public void a(String str) {
        n();
    }

    public void s() {
        if (this.h == null) {
            this.h = new BroadcastReceiver(this) {
                final /* synthetic */ QQPlayerService a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action.equals("android.intent.action.MEDIA_EJECT")) {
                        this.a.a(intent.getData().getPath());
                    } else if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                        this.a.b(e.i);
                        this.a.b(e.h);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addDataScheme("file");
            registerReceiver(this.h, intentFilter);
        }
    }

    public void c(boolean z) {
        if (this.r) {
            this.r = false;
            this.s.sendEmptyMessageDelayed(0, 500);
            b(z);
        }
    }

    private synchronized void E() {
        try {
            if (this.g.f()) {
                M();
                Object e = this.g.e();
                a = System.currentTimeMillis();
                f(true);
                this.n.sendEmptyMessageDelayed(12345021, TracerConfig.LOG_FLUSH_DURATION);
                this.f.a();
                if (this.f.a(getApplicationContext(), e, 0)) {
                    com.qq.reader.common.monitor.debug.c.e("ting", "try play directly");
                    t();
                    this.f.b();
                    e.a(0);
                    b(e.u);
                    SongInfo i = i();
                    if (i != null) {
                        ao.a(2, i.e(), true, i.i());
                    }
                    d(true);
                    Map hashMap = new HashMap();
                    hashMap.put("bid", e.a.getBookId() + "");
                    hashMap.put("cid", e.a.getChapterId() + "");
                    i.a("event_C231", hashMap, this);
                } else {
                    a(2, 2, e);
                }
            }
            b(e.h);
        } catch (Throwable th) {
            b(e.h);
        }
    }

    protected void t() {
        this.c = 0;
    }

    private boolean b(String str, String str2) {
        if (str2 == null && str == null) {
            return false;
        }
        if (e.D.equals(str2) || e.e.equals(str)) {
            a(false);
            return true;
        } else if (e.C.equals(str2) || e.d.equals(str)) {
            a(true);
            return true;
        } else if (e.z.equals(str2) || e.c.equals(str)) {
            if (o()) {
                r();
            } else if (d() == 1 || d() == 6) {
                v();
            } else {
                e(false);
            }
            return true;
        } else if (e.B.equals(str2) || e.f.equals(str)) {
            p();
            return true;
        } else if (e.A.equals(str2)) {
            n();
            stopForeground(true);
            return true;
        } else if (!e.b.equalsIgnoreCase(str)) {
            return false;
        } else {
            u();
            return true;
        }
    }

    public void u() {
        this.j.cancel(Opcodes.NEG_INT);
        n();
        stopSelf(this.d);
    }

    private void F() {
        n.a().b(this.b);
        SongInfo j = j();
        if (j != null && this.f != null) {
            n.a().a(j, this.f.k(), this.f.j());
        }
    }

    public void a(SongInfo songInfo) {
        d(this.g.a(songInfo));
    }

    public void d(int i) {
        if (this.g.c(i)) {
            n();
            b(e.h);
        }
    }

    private boolean G() {
        return this.v != 0;
    }

    private void H() {
        switch (this.v) {
            case 0:
                this.w = 0.0f;
                this.f.a(this.w);
                this.v = 2;
                this.x.sendEmptyMessage(11);
                return;
            case 1:
                this.v = 2;
                this.x.sendEmptyMessage(11);
                return;
            default:
                return;
        }
    }

    private void e(int i) {
        Message obtainMessage;
        switch (this.v) {
            case 0:
                this.w = 1.0f;
                this.f.a(this.w);
                this.v = 1;
                obtainMessage = this.x.obtainMessage(12);
                obtainMessage.arg1 = i;
                this.x.sendMessage(obtainMessage);
                return;
            case 2:
                this.v = 1;
                obtainMessage = this.x.obtainMessage(12);
                obtainMessage.arg1 = i;
                this.x.sendMessage(obtainMessage);
                return;
            default:
                return;
        }
    }

    private void I() {
        this.y = new c(getApplicationContext(), this.m);
    }

    private void J() {
        this.z = new h(getApplicationContext());
    }

    private void K() {
        if (this.z != null) {
            this.z.a();
        }
    }

    private void L() {
        if (this.z != null) {
            this.z.b();
        }
    }

    private void M() {
        if (this.y != null) {
            this.y.a();
        }
    }

    private void N() {
        if (this.y != null) {
            this.y.b();
        }
    }

    private void O() {
        SongInfo i = i();
        if (i != null && this.f != null) {
            try {
                ao.a(i.e(), i.g(), this.f.k() / 1000);
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
        }
    }

    private void f(boolean z) {
        long currentTimeMillis = System.currentTimeMillis() - a;
        SongInfo i;
        if (z) {
            try {
                i = i();
                if (i == null || this.f == null) {
                    ao.a(0, 0, currentTimeMillis, i.b);
                } else {
                    ao.a(i.e(), i.g(), currentTimeMillis, i.b);
                }
                a = System.currentTimeMillis();
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
        } else if (currentTimeMillis >= 5000) {
            i = i();
            if (i == null || this.f == null) {
                ao.a(0, 0, currentTimeMillis, i.b);
            } else {
                ao.a(i.e(), i.g(), currentTimeMillis, i.b);
            }
            a = System.currentTimeMillis();
        }
    }
}
