package com.qq.reader.module.bookstore.qnative.card;

import android.support.v4.util.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: CardViewTypeGenerate */
public class c {
    public int a = 30;
    private int b = 0;
    private h<String, Integer> c = new h(this.a);

    public c(int i) {
        if (i < 30) {
            i = 30;
        }
        this.a = i;
    }

    public boolean a(Collection<? extends Object> collection) {
        boolean z = false;
        if (collection != null) {
            try {
                Iterator it = ((ArrayList) collection).iterator();
                while (it.hasNext()) {
                    boolean z2;
                    if (a((a) it.next())) {
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    z = z2;
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("FeedPackageDate", "Please use ArrayList to addAll in FeedAdapter");
            }
        }
        return z;
    }

    public boolean a(Object obj) {
        if (this.c.get(obj.getClass().getName()) != null) {
            return false;
        }
        int size = this.c.size() / this.a;
        h hVar = this.c;
        String name = obj.getClass().getName();
        int i = this.b + 1;
        this.b = i;
        hVar.put(name, new Integer(i));
        if (size == this.c.size() / this.a) {
            return false;
        }
        this.a += 15;
        return true;
    }

    public int b(Object obj) {
        Integer num = (Integer) this.c.get(obj.getClass().getName());
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
