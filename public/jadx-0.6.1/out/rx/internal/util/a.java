package rx.internal.util;

import java.util.ArrayList;
import java.util.List;

/* compiled from: LinkedArrayList */
public class a {
    final int h;
    Object[] i;
    Object[] j;
    volatile int k;
    int l;

    public void a(Object obj) {
        if (this.k == 0) {
            this.i = new Object[(this.h + 1)];
            this.j = this.i;
            this.i[0] = obj;
            this.l = 1;
            this.k = 1;
        } else if (this.l == this.h) {
            Object[] objArr = new Object[(this.h + 1)];
            objArr[0] = obj;
            this.j[this.h] = objArr;
            this.j = objArr;
            this.l = 1;
            this.k++;
        } else {
            this.j[this.l] = obj;
            this.l++;
            this.k++;
        }
    }

    public Object[] c() {
        return this.i;
    }

    public int d() {
        return this.k;
    }

    List<Object> e() {
        int i = this.h;
        int i2 = this.k;
        List<Object> arrayList = new ArrayList(i2 + 1);
        int i3 = 0;
        Object[] c = c();
        int i4 = 0;
        while (i3 < i2) {
            arrayList.add(c[i4]);
            i3++;
            i4++;
            if (i4 == i) {
                c = (Object[]) c[i];
                i4 = 0;
            }
        }
        return arrayList;
    }

    public String toString() {
        return e().toString();
    }
}
