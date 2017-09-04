package com.qq.reader.readengine.kernel.a;

import format.epub.view.r;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: MixedList */
public class b {
    int a;
    int b;
    private List<r> c = Collections.synchronizedList(new ArrayList());
    private List<r> d = Collections.synchronizedList(new ArrayList());

    public void a(List<d> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            d dVar = (d) list.get(i);
            if (i < size / 2 || size == 1) {
                this.c.addAll(dVar.d);
            } else {
                this.d.addAll(dVar.d);
            }
        }
    }

    public void a() {
        this.c.clear();
        this.d.clear();
        this.a = 0;
        this.b = 0;
    }

    public int b() {
        return this.c.size() + this.d.size();
    }

    boolean a(int i) {
        if (i != 0) {
            this.b--;
        } else if (this.b + 1 >= b()) {
            return false;
        } else {
            this.b++;
        }
        return true;
    }

    boolean b(int i) {
        if (i == 0) {
            this.a++;
        } else if (this.a - 1 < 0) {
            return false;
        } else {
            this.a--;
        }
        return true;
    }

    public r c() {
        return c(this.a);
    }

    public r d() {
        return c(this.b);
    }

    public void a(d dVar) {
        dVar.d.clear();
        for (int i = this.a; i <= this.b; i++) {
            r c = c(i);
            if (c != null) {
                dVar.d.add(c);
                if (i == this.a) {
                    dVar.b.a(c.e());
                } else if (i == this.b) {
                    dVar.c.a(c.f());
                }
            }
        }
    }

    public int b(List<d> list) {
        if (list.size() > 0) {
            if (this.a >= this.c.size()) {
                this.b -= this.c.size();
                this.a -= this.c.size();
                List list2 = this.c;
                this.c = this.d;
                this.d = list2;
                this.d.clear();
            }
            for (d dVar : list) {
                this.d.addAll(dVar.d);
            }
            this.b++;
        }
        return this.b;
    }

    public int c(List<d> list) {
        int size = list.size();
        if (size > 0) {
            if (this.b < this.c.size()) {
                List list2 = this.d;
                this.d = this.c;
                this.c = list2;
                this.c.clear();
                for (d dVar : list) {
                    this.c.addAll(dVar.d);
                }
                this.a = this.c.size() - 1;
                this.b += this.c.size();
            } else {
                int size2 = this.c.size();
                for (int i = size - 1; i >= 0; i--) {
                    this.c.addAll(0, ((d) list.get(i)).d);
                }
                size = this.c.size() - size2;
                this.a += size - 1;
                this.b = size + this.b;
            }
        }
        return this.a;
    }

    public r c(int i) {
        int size = this.c.size();
        if (i < size) {
            return (r) this.c.get(i);
        }
        size = i - size;
        if (size < this.d.size()) {
            return (r) this.d.get(size);
        }
        return null;
    }
}
