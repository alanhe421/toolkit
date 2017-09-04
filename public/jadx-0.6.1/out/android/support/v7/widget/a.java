package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.s;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AdapterHelper */
class a implements a {
    final ArrayList<b> a;
    final ArrayList<b> b;
    final a c;
    Runnable d;
    final boolean e;
    final e f;
    private android.support.v4.util.g.a<b> g;
    private int h;

    /* compiled from: AdapterHelper */
    interface a {
        s a(int i);

        void a(int i, int i2);

        void a(int i, int i2, Object obj);

        void a(b bVar);

        void b(int i, int i2);

        void b(b bVar);

        void c(int i, int i2);

        void d(int i, int i2);
    }

    /* compiled from: AdapterHelper */
    static class b {
        int a;
        int b;
        Object c;
        int d;

        b(int i, int i2, int i3, Object obj) {
            this.a = i;
            this.b = i2;
            this.d = i3;
            this.c = obj;
        }

        String a() {
            switch (this.a) {
                case 1:
                    return "add";
                case 2:
                    return "rm";
                case 4:
                    return "up";
                case 8:
                    return "mv";
                default:
                    return "??";
            }
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.b + "c:" + this.d + ",p:" + this.c + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a != bVar.a) {
                return false;
            }
            if (this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == bVar.b && this.b == bVar.d) {
                return true;
            }
            if (this.d != bVar.d) {
                return false;
            }
            if (this.b != bVar.b) {
                return false;
            }
            if (this.c != null) {
                if (this.c.equals(bVar.c)) {
                    return true;
                }
                return false;
            } else if (bVar.c != null) {
                return false;
            } else {
                return true;
            }
        }

        public int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.d;
        }
    }

    a(a aVar) {
        this(aVar, false);
    }

    a(a aVar, boolean z) {
        this.g = new android.support.v4.util.g.b(30);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.h = 0;
        this.c = aVar;
        this.e = z;
        this.f = new e(this);
    }

    void a() {
        a(this.a);
        a(this.b);
        this.h = 0;
    }

    void b() {
        this.f.a(this.a);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.a.get(i);
            switch (bVar.a) {
                case 1:
                    f(bVar);
                    break;
                case 2:
                    c(bVar);
                    break;
                case 4:
                    d(bVar);
                    break;
                case 8:
                    b(bVar);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        this.a.clear();
    }

    void c() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.c.b((b) this.b.get(i));
        }
        a(this.b);
        this.h = 0;
    }

    private void b(b bVar) {
        g(bVar);
    }

    private void c(b bVar) {
        int i = bVar.b;
        int i2 = bVar.b + bVar.d;
        Object obj = -1;
        int i3 = bVar.b;
        int i4 = 0;
        while (i3 < i2) {
            Object obj2;
            int i5;
            if (this.c.a(i3) != null || d(i3)) {
                if (obj == null) {
                    e(a(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = 1;
            } else {
                if (obj == 1) {
                    g(a(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = null;
            }
            if (obj2 != null) {
                i5 = i3 - i4;
                i3 = i2 - i4;
                i2 = 1;
            } else {
                int i6 = i3;
                i3 = i2;
                i2 = i4 + 1;
                i5 = i6;
            }
            i4 = i2;
            i2 = i3;
            i3 = i5 + 1;
        }
        if (i4 != bVar.d) {
            a(bVar);
            bVar = a(2, i, i4, null);
        }
        if (obj == null) {
            e(bVar);
        } else {
            g(bVar);
        }
    }

    private void d(b bVar) {
        int i = bVar.b;
        int i2 = bVar.b + bVar.d;
        int i3 = bVar.b;
        Object obj = -1;
        int i4 = 0;
        while (i3 < i2) {
            int i5;
            Object obj2;
            if (this.c.a(i3) != null || d(i3)) {
                if (obj == null) {
                    e(a(4, i, i4, bVar.c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = 1;
            } else {
                if (obj == 1) {
                    g(a(4, i, i4, bVar.c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = null;
            }
            i3++;
            Object obj3 = obj2;
            i4 = i + 1;
            i = i5;
            obj = obj3;
        }
        if (i4 != bVar.d) {
            Object obj4 = bVar.c;
            a(bVar);
            bVar = a(4, i, i4, obj4);
        }
        if (obj == null) {
            e(bVar);
        } else {
            g(bVar);
        }
    }

    private void e(b bVar) {
        if (bVar.a == 1 || bVar.a == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int i;
        int b = b(bVar.b, bVar.a);
        int i2 = bVar.b;
        switch (bVar.a) {
            case 2:
                i = 0;
                break;
            case 4:
                i = 1;
                break;
            default:
                throw new IllegalArgumentException("op should be remove or update." + bVar);
        }
        int i3 = 1;
        int i4 = b;
        b = i2;
        for (i2 = 1; i2 < bVar.d; i2++) {
            Object obj;
            int b2 = b(bVar.b + (i * i2), bVar.a);
            int i5;
            switch (bVar.a) {
                case 2:
                    if (b2 != i4) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                case 4:
                    if (b2 != i4 + 1) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                default:
                    obj = null;
                    break;
            }
            if (obj != null) {
                i3++;
            } else {
                b a = a(bVar.a, i4, i3, bVar.c);
                a(a, b);
                a(a);
                if (bVar.a == 4) {
                    b += i3;
                }
                i3 = 1;
                i4 = b2;
            }
        }
        Object obj2 = bVar.c;
        a(bVar);
        if (i3 > 0) {
            b a2 = a(bVar.a, i4, i3, obj2);
            a(a2, b);
            a(a2);
        }
    }

    void a(b bVar, int i) {
        this.c.a(bVar);
        switch (bVar.a) {
            case 2:
                this.c.a(i, bVar.d);
                return;
            case 4:
                this.c.a(i, bVar.d, bVar.c);
                return;
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int b(int i, int i2) {
        int i3;
        int i4 = i;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            b bVar = (b) this.b.get(size);
            if (bVar.a == 8) {
                int i5;
                int i6;
                if (bVar.b < bVar.d) {
                    i5 = bVar.b;
                    i3 = bVar.d;
                } else {
                    i5 = bVar.d;
                    i3 = bVar.b;
                }
                if (i4 < i5 || i4 > r2) {
                    if (i4 < bVar.b) {
                        if (i2 == 1) {
                            bVar.b++;
                            bVar.d++;
                            i6 = i4;
                        } else if (i2 == 2) {
                            bVar.b--;
                            bVar.d--;
                        }
                    }
                    i6 = i4;
                } else if (i5 == bVar.b) {
                    if (i2 == 1) {
                        bVar.d++;
                    } else if (i2 == 2) {
                        bVar.d--;
                    }
                    i6 = i4 + 1;
                } else {
                    if (i2 == 1) {
                        bVar.b++;
                    } else if (i2 == 2) {
                        bVar.b--;
                    }
                    i6 = i4 - 1;
                }
                i4 = i6;
            } else if (bVar.b <= i4) {
                if (bVar.a == 1) {
                    i4 -= bVar.d;
                } else if (bVar.a == 2) {
                    i4 += bVar.d;
                }
            } else if (i2 == 1) {
                bVar.b++;
            } else if (i2 == 2) {
                bVar.b--;
            }
        }
        for (i3 = this.b.size() - 1; i3 >= 0; i3--) {
            bVar = (b) this.b.get(i3);
            if (bVar.a == 8) {
                if (bVar.d == bVar.b || bVar.d < 0) {
                    this.b.remove(i3);
                    a(bVar);
                }
            } else if (bVar.d <= 0) {
                this.b.remove(i3);
                a(bVar);
            }
        }
        return i4;
    }

    private boolean d(int i) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (a(bVar.d, i2 + 1) == i) {
                    return true;
                }
            } else if (bVar.a == 1) {
                int i3 = bVar.b + bVar.d;
                for (int i4 = bVar.b; i4 < i3; i4++) {
                    if (a(i4, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void f(b bVar) {
        g(bVar);
    }

    private void g(b bVar) {
        this.b.add(bVar);
        switch (bVar.a) {
            case 1:
                this.c.c(bVar.b, bVar.d);
                return;
            case 2:
                this.c.b(bVar.b, bVar.d);
                return;
            case 4:
                this.c.a(bVar.b, bVar.d, bVar.c);
                return;
            case 8:
                this.c.d(bVar.b, bVar.d);
                return;
            default:
                throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    boolean d() {
        return this.a.size() > 0;
    }

    boolean a(int i) {
        return (this.h & i) != 0;
    }

    int b(int i) {
        return a(i, 0);
    }

    int a(int i, int i2) {
        int size = this.b.size();
        int i3 = i;
        while (i2 < size) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (bVar.b == i3) {
                    i3 = bVar.d;
                } else {
                    if (bVar.b < i3) {
                        i3--;
                    }
                    if (bVar.d <= i3) {
                        i3++;
                    }
                }
            } else if (bVar.b > i3) {
                continue;
            } else if (bVar.a == 2) {
                if (i3 < bVar.b + bVar.d) {
                    return -1;
                }
                i3 -= bVar.d;
            } else if (bVar.a == 1) {
                i3 += bVar.d;
            }
            i2++;
        }
        return i3;
    }

    void e() {
        c();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.a.get(i);
            switch (bVar.a) {
                case 1:
                    this.c.b(bVar);
                    this.c.c(bVar.b, bVar.d);
                    break;
                case 2:
                    this.c.b(bVar);
                    this.c.a(bVar.b, bVar.d);
                    break;
                case 4:
                    this.c.b(bVar);
                    this.c.a(bVar.b, bVar.d, bVar.c);
                    break;
                case 8:
                    this.c.b(bVar);
                    this.c.d(bVar.b, bVar.d);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        a(this.a);
        this.h = 0;
    }

    public int c(int i) {
        int size = this.a.size();
        int i2 = i;
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = (b) this.a.get(i3);
            switch (bVar.a) {
                case 1:
                    if (bVar.b > i2) {
                        break;
                    }
                    i2 += bVar.d;
                    break;
                case 2:
                    if (bVar.b <= i2) {
                        if (bVar.b + bVar.d <= i2) {
                            i2 -= bVar.d;
                            break;
                        }
                        return -1;
                    }
                    continue;
                case 8:
                    if (bVar.b != i2) {
                        if (bVar.b < i2) {
                            i2--;
                        }
                        if (bVar.d > i2) {
                            break;
                        }
                        i2++;
                        break;
                    }
                    i2 = bVar.d;
                    break;
                default:
                    break;
            }
        }
        return i2;
    }

    public b a(int i, int i2, int i3, Object obj) {
        b bVar = (b) this.g.a();
        if (bVar == null) {
            return new b(i, i2, i3, obj);
        }
        bVar.a = i;
        bVar.b = i2;
        bVar.d = i3;
        bVar.c = obj;
        return bVar;
    }

    public void a(b bVar) {
        if (!this.e) {
            bVar.c = null;
            this.g.a(bVar);
        }
    }

    void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a((b) list.get(i));
        }
        list.clear();
    }
}
