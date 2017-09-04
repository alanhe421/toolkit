package com.tencent.upload.network.base;

import android.util.SparseArray;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.common.i;
import com.tencent.upload.common.j;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class a implements c, d, e {
    private static final AtomicInteger c = new AtomicInteger(0);
    private ConnectionImpl a = null;
    private WeakReference<d> b;
    private SparseArray<Object> d = new SparseArray();
    private byte[] e = new byte[0];
    private volatile boolean f = false;
    private String g;

    static final class a {
        public final String a;
        public final int b;
        public final String c;
        public final int d;
        public final int e;

        public a(String str, int i, String str2, int i2, int i3) {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = i2;
            this.e = i3;
        }
    }

    static final class b {
        public final byte[] a;
        public final int b;
        public final int c;
        public final int d;

        public b(byte[] bArr, int i, int i2, int i3) {
            this.a = bArr;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public a(d dVar) {
        int k = j.k();
        this.b = new WeakReference(dVar);
        if (ConnectionImpl.isLibraryPrepared()) {
            this.a = new ConnectionImpl(e(), k);
            this.a.setCallback(this);
            this.a.setMsgCallback(this);
            return;
        }
        com.tencent.upload.common.a.a.d(f(), "!isLibraryPrepared");
    }

    private String f() {
        return "Connection_" + d();
    }

    public final void a(int i, int i2) {
        boolean z = true;
        synchronized (this.e) {
            Object obj = this.d.get(i2);
            this.d.remove(i2);
        }
        switch (i) {
            case 1:
                if (this.a == null || !(obj instanceof a)) {
                    com.tencent.upload.common.a.a.d(f(), " OperationMsg.CONNECT, obj instanceof ConnectParam:" + (obj instanceof a) + " mNativeConnection != null:" + (this.a != null));
                    if (this.a != null) {
                        this.a.disconnect();
                        return;
                    }
                    return;
                }
                a aVar = (a) obj;
                String str = aVar.a;
                int a = j.a(str);
                if (!i.a(aVar.a)) {
                    com.tencent.upload.b.a.a.b bVar = new com.tencent.upload.b.a.a.b();
                    com.tencent.upload.network.a.b.a(aVar.a, bVar);
                    str = bVar.a;
                    if (str == null) {
                        this.f = false;
                        d dVar = (d) this.b.get();
                        if (dVar != null) {
                            dVar.a(this, false, 558, str);
                            return;
                        }
                        return;
                    }
                }
                this.g = str;
                this.a.connect(str, aVar.b, aVar.c, aVar.d, aVar.e, a);
                return;
            case 2:
                if (this.a != null) {
                    this.a.disconnect();
                    return;
                } else {
                    com.tencent.upload.common.a.a.c(f(), " OperationMsg.DISCONNECT, mNativeConnection == null");
                    return;
                }
            case 3:
                if (this.a == null || !(obj instanceof b)) {
                    String f = f();
                    StringBuilder append = new StringBuilder(" OperationMsg.SEND, obj instanceof ConnectParam:").append(obj instanceof b).append("mNativeConnection != null:");
                    if (this.a == null) {
                        z = false;
                    }
                    com.tencent.upload.common.a.a.c(f, append.append(z).toString());
                    return;
                }
                b bVar2 = (b) obj;
                this.a.SendData(bVar2.a, bVar2.b, bVar2.c, bVar2.d);
                return;
            default:
                return;
        }
    }

    public final void a(d dVar) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.a(dVar);
        }
    }

    public final void a(d dVar, int i) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.a(dVar, i);
        }
    }

    public final void a(d dVar, int i, int i2) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.a(dVar, i, i2);
        }
    }

    public final void a(d dVar, boolean z, int i, String str) {
        this.f = z;
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            if (str == null) {
                str = this.g;
            }
            dVar2.a(dVar, z, i, str);
        }
    }

    public final void a(d dVar, byte[] bArr) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.a(dVar, bArr);
        }
    }

    public final boolean a() {
        if (this.a == null) {
            return false;
        }
        if (!this.a.isRunning()) {
            return this.a.start();
        }
        com.tencent.upload.common.a.a.c(f(), "start, is running, return false");
        return false;
    }

    public final boolean a(String str, int i, String str2, int i2, int i3) {
        if (this.a == null) {
            return false;
        }
        a aVar = new a(str, i, str2, i2, i3);
        int incrementAndGet = c.incrementAndGet();
        synchronized (this.e) {
            this.d.put(incrementAndGet, aVar);
        }
        return this.a.PostMessage(1, null, incrementAndGet);
    }

    public final boolean a(byte[] bArr, int i, int i2, int i3) {
        if (this.a == null) {
            return false;
        }
        b bVar = new b(bArr, i, i2, i3);
        int incrementAndGet = c.incrementAndGet();
        synchronized (this.e) {
            this.d.put(incrementAndGet, bVar);
        }
        return this.a.PostMessage(3, null, incrementAndGet);
    }

    public final void b(d dVar) {
        this.f = false;
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.b(dVar);
        }
    }

    public final void b(d dVar, int i) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.b(dVar, i);
        }
    }

    public final boolean b() {
        if (this.a == null) {
            return false;
        }
        this.a.removeAllSendData();
        boolean stop = this.a.stop();
        synchronized (this.e) {
            this.d.clear();
        }
        return stop;
    }

    public final void c() {
        if (this.a != null) {
            this.a.wakeUp();
        }
    }

    public final void c(d dVar, int i) {
        d dVar2 = (d) this.b.get();
        if (dVar2 != null) {
            dVar2.c(dVar, i);
        }
    }

    public abstract FileType d();
}
