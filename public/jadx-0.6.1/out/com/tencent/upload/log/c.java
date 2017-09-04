package com.tencent.upload.log;

import android.content.Context;
import android.util.Log;
import com.tencent.imsdk.QLogImpl;
import com.tencent.upload.log.trace.a;
import com.tencent.upload.log.trace.g;
import java.util.Date;

final class c implements a {
    private g a;

    public c(Context context) {
        this.a = new a(context);
    }

    public final String a(Date date) {
        return this.a.a(date);
    }

    public final void a() {
        this.a.a();
    }

    public final void a(String str, String str2, Throwable th) {
        this.a.a(QLogImpl.TAG_REPORTLEVEL_DEVELOPER, str, str2, th);
        Log.d(str, str2, th);
    }

    public final void b(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
        this.a.a("I", str, str2, th);
    }

    public final void c(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
        this.a.a(QLogImpl.TAG_REPORTLEVEL_COLORUSER, str, str2, th);
    }

    public final void d(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
        this.a.a(QLogImpl.TAG_REPORTLEVEL_USER, str, str2, th);
    }
}
