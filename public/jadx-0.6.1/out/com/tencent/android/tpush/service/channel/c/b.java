package com.tencent.android.tpush.service.channel.c;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ProGuard */
public class b extends InputStream {
    final /* synthetic */ a a;

    protected b(a aVar) {
        this.a = aVar;
    }

    public int available() {
        int a;
        synchronized (this.a) {
            if (this.a.i) {
                throw new IOException("InputStream has been closed, it is not ready.");
            }
            a = this.a.g();
        }
        return a;
    }

    public void close() {
        synchronized (this.a) {
            this.a.i = true;
        }
    }

    public void mark(int i) {
        synchronized (this.a) {
            if (this.a.a.length - 1 > i) {
                this.a.e = i;
                this.a.d = this.a.b;
            }
        }
    }

    public boolean markSupported() {
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() {
        /*
        r4 = this;
    L_0x0000:
        r1 = r4.a;
        monitor-enter(r1);
        r0 = r4.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.i;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0015;
    L_0x0009:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0012 }
        r2 = "InputStream has been closed; cannot read from a closed InputStream.";
        r0.<init>(r2);	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        r0 = r4.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.g();	 Catch:{ all -> 0x0012 }
        if (r0 <= 0) goto L_0x0048;
    L_0x001d:
        r0 = r4.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.a;	 Catch:{ all -> 0x0012 }
        r2 = r4.a;	 Catch:{ all -> 0x0012 }
        r2 = r2.b;	 Catch:{ all -> 0x0012 }
        r0 = r0[r2];	 Catch:{ all -> 0x0012 }
        r0 = r0 & 255;
        r2 = r4.a;	 Catch:{ all -> 0x0012 }
        r3 = r2.b;	 Catch:{ all -> 0x0012 }
        r3 = r3 + 1;
        r2.b = r3;	 Catch:{ all -> 0x0012 }
        r2 = r4.a;	 Catch:{ all -> 0x0012 }
        r2 = r2.b;	 Catch:{ all -> 0x0012 }
        r3 = r4.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.length;	 Catch:{ all -> 0x0012 }
        if (r2 != r3) goto L_0x0041;
    L_0x003c:
        r2 = r4.a;	 Catch:{ all -> 0x0012 }
        r3 = 0;
        r2.b = r3;	 Catch:{ all -> 0x0012 }
    L_0x0041:
        r2 = r4.a;	 Catch:{ all -> 0x0012 }
        r2.i();	 Catch:{ all -> 0x0012 }
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
    L_0x0047:
        return r0;
    L_0x0048:
        r0 = r4.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.k;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0051;
    L_0x004e:
        r0 = -1;
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0047;
    L_0x0051:
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        r0 = 100;
        java.lang.Thread.sleep(r0);	 Catch:{ Exception -> 0x0058 }
        goto L_0x0000;
    L_0x0058:
        r0 = move-exception;
        r0 = new java.io.IOException;
        r1 = "Blocking read operation interrupted.";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.b.read():int");
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r7, int r8, int r9) {
        /*
        r6 = this;
    L_0x0000:
        r1 = r6.a;
        monitor-enter(r1);
        r0 = r6.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.i;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0015;
    L_0x0009:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0012 }
        r2 = "InputStream has been closed; cannot read from a closed InputStream.";
        r0.<init>(r2);	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        r0 = r6.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.g();	 Catch:{ all -> 0x0012 }
        if (r0 <= 0) goto L_0x006a;
    L_0x001d:
        r0 = java.lang.Math.min(r9, r0);	 Catch:{ all -> 0x0012 }
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r2 = r2.a;	 Catch:{ all -> 0x0012 }
        r2 = r2.length;	 Catch:{ all -> 0x0012 }
        r3 = r6.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.b;	 Catch:{ all -> 0x0012 }
        r2 = r2 - r3;
        r2 = java.lang.Math.min(r0, r2);	 Catch:{ all -> 0x0012 }
        r3 = r0 - r2;
        r4 = r6.a;	 Catch:{ all -> 0x0012 }
        r4 = r4.a;	 Catch:{ all -> 0x0012 }
        r5 = r6.a;	 Catch:{ all -> 0x0012 }
        r5 = r5.b;	 Catch:{ all -> 0x0012 }
        java.lang.System.arraycopy(r4, r5, r7, r8, r2);	 Catch:{ all -> 0x0012 }
        if (r3 <= 0) goto L_0x0062;
    L_0x003e:
        r4 = r6.a;	 Catch:{ all -> 0x0012 }
        r4 = r4.a;	 Catch:{ all -> 0x0012 }
        r5 = 0;
        r2 = r2 + r8;
        java.lang.System.arraycopy(r4, r5, r7, r2, r3);	 Catch:{ all -> 0x0012 }
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r2.b = r3;	 Catch:{ all -> 0x0012 }
    L_0x004b:
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r2 = r2.b;	 Catch:{ all -> 0x0012 }
        r3 = r6.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.length;	 Catch:{ all -> 0x0012 }
        if (r2 != r3) goto L_0x005b;
    L_0x0056:
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r3 = 0;
        r2.b = r3;	 Catch:{ all -> 0x0012 }
    L_0x005b:
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r2.i();	 Catch:{ all -> 0x0012 }
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
    L_0x0061:
        return r0;
    L_0x0062:
        r2 = r6.a;	 Catch:{ all -> 0x0012 }
        r3 = r2.b;	 Catch:{ all -> 0x0012 }
        r3 = r3 + r0;
        r2.b = r3;	 Catch:{ all -> 0x0012 }
        goto L_0x004b;
    L_0x006a:
        r0 = r6.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.k;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0073;
    L_0x0070:
        r0 = -1;
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0061;
    L_0x0073:
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        r0 = 100;
        java.lang.Thread.sleep(r0);	 Catch:{ Exception -> 0x007a }
        goto L_0x0000;
    L_0x007a:
        r0 = move-exception;
        r0 = new java.io.IOException;
        r1 = "Blocking read operation interrupted.";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.b.read(byte[], int, int):int");
    }

    public void reset() {
        synchronized (this.a) {
            if (this.a.i) {
                throw new IOException("InputStream has been closed; cannot reset a closed InputStream.");
            }
            this.a.b = this.a.d;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long skip(long r6) {
        /*
        r5 = this;
    L_0x0000:
        r2 = r5.a;
        monitor-enter(r2);
        r0 = r5.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.i;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0015;
    L_0x0009:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0012 }
        r1 = "InputStream has been closed; cannot skip bytes on a closed InputStream.";
        r0.<init>(r1);	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        r0 = r5.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.g();	 Catch:{ all -> 0x0012 }
        if (r0 <= 0) goto L_0x0058;
    L_0x001d:
        r1 = (int) r6;	 Catch:{ all -> 0x0012 }
        r0 = java.lang.Math.min(r1, r0);	 Catch:{ all -> 0x0012 }
        r1 = r5.a;	 Catch:{ all -> 0x0012 }
        r1 = r1.a;	 Catch:{ all -> 0x0012 }
        r1 = r1.length;	 Catch:{ all -> 0x0012 }
        r3 = r5.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.b;	 Catch:{ all -> 0x0012 }
        r1 = r1 - r3;
        r1 = java.lang.Math.min(r0, r1);	 Catch:{ all -> 0x0012 }
        r1 = r0 - r1;
        if (r1 <= 0) goto L_0x0050;
    L_0x0034:
        r3 = r5.a;	 Catch:{ all -> 0x0012 }
        r3.b = r1;	 Catch:{ all -> 0x0012 }
    L_0x0038:
        r1 = r5.a;	 Catch:{ all -> 0x0012 }
        r1 = r1.b;	 Catch:{ all -> 0x0012 }
        r3 = r5.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.a;	 Catch:{ all -> 0x0012 }
        r3 = r3.length;	 Catch:{ all -> 0x0012 }
        if (r1 != r3) goto L_0x0048;
    L_0x0043:
        r1 = r5.a;	 Catch:{ all -> 0x0012 }
        r3 = 0;
        r1.b = r3;	 Catch:{ all -> 0x0012 }
    L_0x0048:
        r1 = r5.a;	 Catch:{ all -> 0x0012 }
        r1.i();	 Catch:{ all -> 0x0012 }
        r0 = (long) r0;	 Catch:{ all -> 0x0012 }
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
    L_0x004f:
        return r0;
    L_0x0050:
        r1 = r5.a;	 Catch:{ all -> 0x0012 }
        r3 = r1.b;	 Catch:{ all -> 0x0012 }
        r3 = r3 + r0;
        r1.b = r3;	 Catch:{ all -> 0x0012 }
        goto L_0x0038;
    L_0x0058:
        r0 = r5.a;	 Catch:{ all -> 0x0012 }
        r0 = r0.k;	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0062;
    L_0x005e:
        r0 = 0;
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        goto L_0x004f;
    L_0x0062:
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        r0 = 100;
        java.lang.Thread.sleep(r0);	 Catch:{ Exception -> 0x0069 }
        goto L_0x0000;
    L_0x0069:
        r0 = move-exception;
        r0 = new java.io.IOException;
        r1 = "Blocking read operation interrupted.";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.b.skip(long):long");
    }
}
