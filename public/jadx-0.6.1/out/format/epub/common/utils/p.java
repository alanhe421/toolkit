package format.epub.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ZLTree */
public abstract class p<T extends p<T>> implements Iterable<T> {
    int b;
    public final T c;
    public final int d;
    ArrayList<T> e;

    /* compiled from: ZLTree */
    private class a implements Iterator<T> {
        final /* synthetic */ p a;
        private T b = this.a;
        private final LinkedList<Integer> c = new LinkedList();
        private final int d;

        public /* synthetic */ Object next() {
            return a();
        }

        a(p pVar, int i) {
            this.a = pVar;
            this.d = i;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public T a() {
            T t = this.b;
            if (!t.e() || t.d >= this.d) {
                p pVar = t;
                while (!this.c.isEmpty()) {
                    int intValue = ((Integer) this.c.removeLast()).intValue() + 1;
                    p pVar2 = pVar.c;
                    if (pVar2.e.size() > intValue) {
                        this.b = (p) pVar2.e.get(intValue);
                        this.c.add(Integer.valueOf(intValue));
                        break;
                    }
                    pVar = pVar2;
                }
                if (this.c.isEmpty()) {
                    this.b = null;
                }
            } else {
                this.b = (p) t.e.get(0);
                this.c.add(Integer.valueOf(0));
            }
            return t;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ Iterator iterator() {
        return g();
    }

    protected p() {
        this(null);
    }

    protected p(T t) {
        this(t, t == null ? 0 : t.f().size());
    }

    protected p(T t, int i) {
        this.b = 1;
        if (t == null || (i >= 0 && i <= t.f().size())) {
            this.c = t;
            if (t != null) {
                this.d = t.d + 1;
                t.a(this, i);
                return;
            }
            this.d = 0;
            return;
        }
        throw new IndexOutOfBoundsException("`position` value equals " + i + " but must be in range [0; " + t.f().size() + "]");
    }

    public final int d() {
        return this.b;
    }

    public final boolean e() {
        return this.e != null;
    }

    public final List<T> f() {
        if (this.e == null) {
            return Collections.emptyList();
        }
        return this.e;
    }

    protected void a(T t, int i) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        int d = t.d();
        while (i < this.e.size()) {
            i++;
            t = (p) this.e.set(i, t);
        }
        this.e.add(t);
        while (this != null) {
            this.b += d;
            this = this.c;
        }
    }

    public final a g() {
        return new a(this, Integer.MAX_VALUE);
    }
}
