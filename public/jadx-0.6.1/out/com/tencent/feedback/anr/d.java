package com.tencent.feedback.anr;

import com.tencent.feedback.common.e;
import java.util.HashMap;

/* compiled from: RQDSRC */
final class d implements g {
    private /* synthetic */ f a;
    private /* synthetic */ boolean b;

    d(f fVar, boolean z) {
        this.a = fVar;
        this.b = z;
    }

    public final boolean a(String str, int i, String str2, String str3) {
        e.b("new thread %s", new Object[]{str});
        if (this.a.a > 0 && this.a.c > 0 && this.a.b != null) {
            if (this.a.d == null) {
                this.a.d = new HashMap();
            }
            this.a.d.put(str, new String[]{str2, str3, i});
        }
        return true;
    }

    public final boolean a(long j, long j2, String str) {
        e.b("new process %s", new Object[]{str});
        if (!str.equals(str)) {
            return true;
        }
        this.a.a = j;
        this.a.b = str;
        this.a.c = j2;
        if (this.b) {
            return true;
        }
        return false;
    }

    public final boolean a(long j) {
        e.b("process end %d", new Object[]{Long.valueOf(j)});
        if (this.a.a <= 0 || this.a.c <= 0 || this.a.b == null) {
            return true;
        }
        return false;
    }
}
