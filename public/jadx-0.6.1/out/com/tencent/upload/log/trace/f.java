package com.tencent.upload.log.trace;

import android.util.Log;
import com.tencent.upload.log.a.a;

public final class f {
    private static a h = new a();
    private String a;
    private String b;
    private Throwable c;
    private String d;
    private long e = System.currentTimeMillis();
    private long f = Thread.currentThread().getId();
    private String g = Thread.currentThread().getName();

    public f(String str, String str2, String str3, Throwable th) {
        this.d = str;
        this.a = str2;
        this.b = str3;
        this.c = th;
    }

    public final long a() {
        return (long) ((this.b != null ? this.b.length() : 0) + 40);
    }

    public final void a(StringBuilder stringBuilder) {
        try {
            stringBuilder.append(this.d).append('/');
            h.a(this.e);
            h.a(stringBuilder);
            stringBuilder.append(" [").append(this.g + " " + this.f);
            stringBuilder.append("][").append(this.a).append("] ").append(this.b).append('\n');
            if (this.c != null) {
                stringBuilder.append("* Exception : \n").append(Log.getStackTraceString(this.c)).append('\n');
            }
        } catch (OutOfMemoryError e) {
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        a(stringBuilder);
        return stringBuilder.toString();
    }
}
