package com.qq.reader.module.bookstore.search;

import com.qq.reader.common.monitor.debug.c;

/* compiled from: DistinctSearchTabListener */
public abstract class a implements c {
    private String a;

    public abstract void a_(String str);

    public final void a(String str) {
        if (this.a == null || !this.a.equals(str)) {
            a_(str);
            c.e("SearchTab", String.valueOf(str));
            this.a = str;
        }
    }

    public void a(int i, int i2) {
    }
}
