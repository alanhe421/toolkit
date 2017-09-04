package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.N;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.t;

/* compiled from: RQDSRC */
public abstract class AbstractUploadDatas {
    protected final int a;
    protected final int b;
    protected Context c;

    public abstract N a();

    public abstract void done(boolean z);

    public AbstractUploadDatas(Context context, int i, int i2) {
        this.c = context;
        this.a = i2;
        this.b = i;
    }

    public final int b() {
        return this.a;
    }

    public final String c() {
        try {
            if (this.b == 1111) {
                return t.a(this.c).b().a();
            }
            return t.a(this.c).b().c(this.b).a();
        } catch (Throwable th) {
            e.c("rqdp{  imposiable comStrategy error }%s", th.toString());
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static N a(Context context, int i, byte[] bArr) {
        try {
            return a.a(i, c.a(context), bArr, (byte) 2, (byte) 1, t.a(context).b().d());
        } catch (Throwable th) {
            e.c("rqdp{  imposiable comStrategy error} %s", th.toString());
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final byte[] a(boolean z) {
        byte[] bArr = null;
        try {
            N a = a();
            if (a != null) {
                bArr = a.a();
            }
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  encode to bytes failed}", new Object[0]);
        }
        return bArr;
    }

    public static void d() {
        e.c("rqdp{  encode failed, clear db data}", new Object[0]);
    }
}
