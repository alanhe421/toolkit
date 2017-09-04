package com.tencent.feedback.anr;

import java.util.HashMap;

/* compiled from: RQDSRC */
final class e implements g {
    private /* synthetic */ f a;
    private /* synthetic */ boolean b;

    e(f fVar, boolean z) {
        this.a = fVar;
        this.b = z;
    }

    public final boolean a(String str, int i, String str2, String str3) {
        com.tencent.feedback.common.e.b("new thread %s", new Object[]{str});
        if (this.a.d == null) {
            this.a.d = new HashMap();
        }
        this.a.d.put(str, new String[]{str2, str3, i});
        return true;
    }

    public final boolean a(long j, long j2, String str) {
        com.tencent.feedback.common.e.b("new process %s", new Object[]{str});
        this.a.a = j;
        this.a.b = str;
        this.a.c = j2;
        if (this.b) {
            return true;
        }
        return false;
    }

    public final boolean a(long j) {
        com.tencent.feedback.common.e.b("process end %d", new Object[]{Long.valueOf(j)});
        return false;
    }
}
