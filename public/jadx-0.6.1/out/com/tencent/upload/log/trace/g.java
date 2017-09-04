package com.tencent.upload.log.trace;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class g implements Callback {
    private List<f> a = Collections.synchronizedList(new ArrayList());
    private volatile long b = 0;
    private HandlerThread c;
    private Handler d;

    public g() {
        if (this.c == null || !this.c.isAlive()) {
            try {
                if (this.c == null) {
                    this.c = new HandlerThread("LoggerThread", 1);
                }
                this.c.start();
                if (this.c.isAlive()) {
                    this.d = new Handler(this.c.getLooper(), this);
                }
            } catch (Throwable th) {
                this.d = new Handler(Looper.getMainLooper(), this);
            }
        }
        b();
    }

    private void b() {
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.d.sendMessageDelayed(obtain, TracerConfig.LOG_FLUSH_DURATION);
    }

    public abstract String a(Date date);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
        r4 = this;
        r2 = 0;
        r0 = r4.b;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        monitor-enter(r4);
        r0 = r4.b;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x0015;
    L_0x0010:
        monitor-exit(r4);	 Catch:{ all -> 0x0012 }
        goto L_0x0008;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0015:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0012 }
        r1 = r4.a;	 Catch:{ all -> 0x0012 }
        r0.<init>(r1);	 Catch:{ all -> 0x0012 }
        r1 = r4.a;	 Catch:{ all -> 0x0012 }
        r1.clear();	 Catch:{ all -> 0x0012 }
        r2 = 0;
        r4.b = r2;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x002d;
    L_0x0027:
        r1 = r0.size();	 Catch:{ all -> 0x0012 }
        if (r1 > 0) goto L_0x002f;
    L_0x002d:
        monitor-exit(r4);	 Catch:{ all -> 0x0012 }
        goto L_0x0008;
    L_0x002f:
        r1 = android.os.Message.obtain();	 Catch:{ all -> 0x0012 }
        r2 = 2;
        r1.what = r2;	 Catch:{ all -> 0x0012 }
        r1.obj = r0;	 Catch:{ all -> 0x0012 }
        r0 = r4.d;	 Catch:{ all -> 0x0012 }
        r0.sendMessage(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.log.trace.g.a():void");
    }

    public final void a(String str, String str2, String str3, Throwable th) {
        f fVar = new f(str, str2, str3, th);
        synchronized (this) {
            if (this.b >= TracerConfig.MEMORY_SIZE || this.b + fVar.a() > TracerConfig.MEMORY_SIZE) {
                a();
            }
            this.a.add(fVar);
            this.b = fVar.a() + this.b;
        }
    }

    protected abstract void a(List<f> list);

    public boolean handleMessage(Message message) {
        if (message != null) {
            switch (message.what) {
                case 1:
                    a();
                    b();
                    break;
                case 2:
                    try {
                        a((List) message.obj);
                        break;
                    } catch (Throwable th) {
                        break;
                    }
                default:
                    break;
            }
        }
        return false;
    }
}
