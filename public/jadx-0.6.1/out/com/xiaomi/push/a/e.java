package com.xiaomi.push.a;

import com.xiaomi.channel.commonutils.b.a;

public class e implements a {
    private a a = null;
    private a b = null;

    public e(a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public void a(String str) {
        if (this.a != null) {
            this.a.a(str);
        }
        if (this.b != null) {
            this.b.a(str);
        }
    }

    public void a(String str, Throwable th) {
        if (this.a != null) {
            this.a.a(str, th);
        }
        if (this.b != null) {
            this.b.a(str, th);
        }
    }
}
