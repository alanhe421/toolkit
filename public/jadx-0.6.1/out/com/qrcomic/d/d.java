package com.qrcomic.d;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import com.dynamicload.Lib.DLConstants;
import com.qrcomic.util.g;

/* compiled from: MqrMessageQueue */
public class d implements Callback, IdleHandler {
    private static boolean f = false;
    private static d g;
    c a;
    Handler b;
    Printer c;
    long d = 0;
    long e = 0;
    private volatile boolean h = false;
    private volatile boolean i = false;

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (g == null) {
                g = new d(Looper.getMainLooper());
            }
            dVar = g;
        }
        return dVar;
    }

    private d(Looper looper) {
        this.b = new Handler(this, looper, this) {
            final /* synthetic */ d a;

            public String toString() {
                return "MessageQueueHandler";
            }
        };
    }

    boolean a(c cVar, long j) {
        if (f && g.a()) {
            g.b("MqrMessage.Queue", g.d, "enqueueMessage: " + cVar.toString());
        }
        synchronized (this) {
            cVar.a = j;
            c cVar2 = this.a;
            if (cVar2 == null || j == 0 || j < cVar2.a) {
                cVar.d = cVar2;
                this.a = cVar;
            } else {
                c cVar3 = null;
                while (cVar2 != null && cVar2.a <= j) {
                    c cVar4 = cVar2;
                    cVar2 = cVar2.d;
                    cVar3 = cVar4;
                }
                cVar.d = cVar3.d;
                cVar3.d = cVar;
            }
            d();
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final com.qrcomic.d.c b() {
        /*
        r7 = this;
        r1 = 0;
        monitor-enter(r7);
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0035 }
        r0 = r7.a;	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0032;
    L_0x000a:
        r4 = r0.a;	 Catch:{ all -> 0x0035 }
        r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r6 < 0) goto L_0x0019;
    L_0x0010:
        r1 = r0.d;	 Catch:{ all -> 0x0035 }
        r7.a = r1;	 Catch:{ all -> 0x0035 }
        r1 = 0;
        r0.d = r1;	 Catch:{ all -> 0x0035 }
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
    L_0x0018:
        return r0;
    L_0x0019:
        r2 = r4 - r2;
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r2 = java.lang.Math.min(r2, r4);	 Catch:{ all -> 0x0035 }
        r0 = (int) r2;	 Catch:{ all -> 0x0035 }
        r2 = r7.b;	 Catch:{ all -> 0x0035 }
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2.removeMessages(r3);	 Catch:{ all -> 0x0035 }
        r2 = r7.b;	 Catch:{ all -> 0x0035 }
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = (long) r0;	 Catch:{ all -> 0x0035 }
        r2.sendEmptyMessageDelayed(r3, r4);	 Catch:{ all -> 0x0035 }
    L_0x0032:
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
        r0 = r1;
        goto L_0x0018;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.d.d.b():com.qrcomic.d.c");
    }

    private void a(final Throwable th) {
        new Thread(this) {
            final /* synthetic */ d b;

            public void run() {
                throw new RuntimeException("queueIdle encounter business crash. " + Log.getStackTraceString(th));
            }
        }.start();
    }

    public boolean queueIdle() {
        this.b.removeMessages(1001);
        boolean a = a(true);
        if (a) {
            this.b.sendEmptyMessage(1000);
        } else {
            this.i = false;
        }
        return a;
    }

    private final void c() {
        if (a(false)) {
            this.b.sendEmptyMessage(1001);
        }
    }

    private boolean a(boolean z) {
        if (f && g.a()) {
            g.b("MqrMessage.Queue", g.d, "enter dequeue, idle = " + z);
        }
        c b = b();
        String cVar = b != null ? b.toString() : "null";
        if (b != null) {
            try {
                StringBuilder stringBuilder;
                if (this.c != null) {
                    stringBuilder = new StringBuilder(256);
                    stringBuilder.append(">>>>> Dispatching to ");
                    stringBuilder.append(b.c);
                    stringBuilder.append(" ");
                    stringBuilder.append(b.b.getCallback());
                    stringBuilder.append(": ");
                    stringBuilder.append(b.b.what);
                    this.c.println(stringBuilder.toString());
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                b.c.a(b.b);
                this.d = (SystemClock.uptimeMillis() - uptimeMillis) + this.d;
                this.e++;
                if (this.c != null) {
                    stringBuilder = new StringBuilder(256);
                    stringBuilder.append("<<<<< Finished to ");
                    stringBuilder.append(b.c);
                    stringBuilder.append(" ");
                    stringBuilder.append(b.b.getCallback());
                    this.c.println(stringBuilder.toString());
                }
                b.b();
            } catch (Throwable th) {
                a(th);
            }
        }
        if (f && this.e % 100 == 0 && g.a()) {
            g.a("MqrMessage.Queue", g.d, "dequeue|", this.e, DLConstants.DEPENDENCY_PACKAGE_DIV, this.d);
        }
        if (b != null) {
            if (f && g.a()) {
                g.a("MqrMessage.Queue", g.d, "dequeue, msg = ", cVar);
            }
            return true;
        }
        if (f && g.a()) {
            g.b("MqrMessage.Queue", g.d, "dequeue, msg = null");
        }
        return false;
    }

    private void d() {
        if (f && g.a()) {
            g.a("MqrMessage.Queue", g.d, "reqHook, attached = ", this.i, ", requested = ", this.h);
        }
        if (this.i || this.h) {
            this.b.sendEmptyMessageDelayed(1001, 1000);
            return;
        }
        this.h = true;
        this.b.sendEmptyMessage(1000);
    }

    private void e() {
        this.b.removeMessages(1000);
        this.h = false;
    }

    public boolean handleMessage(Message message) {
        if (f && g.a()) {
            g.a("MqrMessage.Queue", g.d, "handleMessage, what = ", message.what, ", attached = ", this.i);
        }
        if (message.what == 1000) {
            if (!this.i && VERSION.SDK_INT >= 23) {
                e();
                this.i = true;
                this.b.getLooper().getQueue().addIdleHandler(this);
            }
            this.b.sendEmptyMessageDelayed(1001, 1000);
        } else if (message.what == 1001) {
            c();
        }
        return true;
    }
}
