package com.tencent.beacon.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ProGuard */
final class c {
    private int a;
    private int b;
    private int c;
    private List<d> d = Collections.synchronizedList(new ArrayList());

    public final synchronized void a(int i) {
        this.a = i;
    }

    public final synchronized int a() {
        return this.b;
    }

    public final synchronized void b(int i) {
        this.b = i;
    }

    public final synchronized int b() {
        return this.c;
    }

    public final synchronized void c(int i) {
        this.c = i;
    }

    public final List<d> c() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass() && (obj instanceof c) && ((c) obj).a == this.a) {
            return true;
        }
        return false;
    }
}
