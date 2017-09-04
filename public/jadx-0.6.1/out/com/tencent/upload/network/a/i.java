package com.tencent.upload.network.a;

import java.io.Serializable;

public final class i implements Serializable {
    private k a = null;
    private k b = null;
    private long c = 0;

    public final k a() {
        return this.a;
    }

    public final void a(long j) {
        this.c = j;
    }

    public final void a(k kVar) {
        int f = kVar.f();
        if (f == 2) {
            this.b = kVar;
        } else if (f == 1) {
            this.a = kVar;
        }
    }

    public final k b() {
        return this.b;
    }

    public final long c() {
        return this.c;
    }

    public final String toString() {
        return "mRecentTcpRoute = " + this.a + ", mRecentHttpRoute = " + this.b + ",mTimeStamp = " + this.c;
    }
}
