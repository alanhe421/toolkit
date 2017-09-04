package android.support.v4.util;

/* compiled from: SparseArrayCompat */
public class i<E> implements Cloneable {
    private static final Object a = new Object();
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public i() {
        this(10);
    }

    public i(int i) {
        this.b = false;
        if (i == 0) {
            this.c = b.a;
            this.d = b.c;
        } else {
            int a = b.a(i);
            this.c = new int[a];
            this.d = new Object[a];
        }
        this.e = 0;
    }

    public i<E> a() {
        try {
            i<E> iVar = (i) super.clone();
            try {
                iVar.c = (int[]) this.c.clone();
                iVar.d = (Object[]) this.d.clone();
                return iVar;
            } catch (CloneNotSupportedException e) {
                return iVar;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    private void d() {
        int i = this.e;
        int[] iArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    public int b() {
        if (this.b) {
            d();
        }
        return this.e;
    }

    public int a(int i) {
        if (this.b) {
            d();
        }
        return this.c[i];
    }

    public E b(int i) {
        if (this.b) {
            d();
        }
        return this.d[i];
    }

    public void c() {
        int i = this.e;
        Object[] objArr = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(a(i));
            stringBuilder.append('=');
            i b = b(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
