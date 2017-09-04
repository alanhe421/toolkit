package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.b.b.c;
import com.tencent.beacon.b.d;
import com.tencent.beacon.c.a.b;

/* compiled from: ProGuard */
public abstract class a {
    protected final int a;
    protected final int b;
    protected Context c;
    protected String d;
    protected int e;

    public abstract b a();

    public abstract void b(boolean z);

    public a(Context context, int i, int i2) {
        this.c = context;
        this.a = i2;
        this.b = i;
    }

    public final int c() {
        return this.a;
    }

    public final synchronized String d() {
        return this.d;
    }

    public final String e() {
        try {
            if (this.b == 0) {
                return c.a(this.c).e().b();
            }
            return c.a(this.c).e().b(this.b).b();
        } catch (Throwable th) {
            com.tencent.beacon.e.a.c("imposiable comStrategy error:%s", th.toString());
            com.tencent.beacon.e.a.a(th);
            return null;
        }
    }

    public static b a(int i, byte[] bArr) {
        try {
            return com.tencent.beacon.net.a.a(i, d.m(), bArr, 2, 3);
        } catch (Throwable th) {
            com.tencent.beacon.e.a.c("imposiable comStrategy error:%s", th.toString());
            com.tencent.beacon.e.a.a(th);
            return null;
        }
    }

    public final synchronized int f() {
        return this.e;
    }

    public void b() {
        com.tencent.beacon.e.a.c("encode failed, clear db data", new Object[0]);
    }
}
