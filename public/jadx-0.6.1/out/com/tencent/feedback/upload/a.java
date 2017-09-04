package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.N;
import com.tencent.feedback.proguard.t;

/* compiled from: RQDSRC */
public final class a extends AbstractUploadDatas {
    public a(Context context, int i, int i2) {
        super(context, i, i2);
    }

    public final void done(boolean z) {
    }

    public final N a() {
        try {
            N a = com.tencent.feedback.proguard.a.a(b(), c.a(this.c), "".getBytes(), (byte) -1, (byte) -1, t.a(this.c).b().d());
            if (a != null) {
                return a;
            }
            return null;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  encode2RequestPackage failed}", new Object[0]);
            return null;
        }
    }
}
