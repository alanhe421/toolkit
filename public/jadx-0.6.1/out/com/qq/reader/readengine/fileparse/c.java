package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.monitor.f;
import com.qq.reader.readengine.kernel.c.e;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EncodingUtils;

/* compiled from: IBookBuff */
public abstract class c {
    public static int a = 8192;
    public d b;
    public byte[] c;
    public long d;
    public int e;
    public boolean f;
    public String g = "";
    public ArrayList<float[]> h = new ArrayList();
    public ArrayList<float[]> i = new ArrayList();
    public ArrayList<long[]> j = new ArrayList();
    public int k;
    public boolean l = false;
    public int m;
    public long n = -1;
    public List<e> o = new ArrayList();
    private ArrayList<com.qq.reader.readengine.kernel.e> p = new ArrayList();

    public abstract void a(d dVar, boolean z, int i);

    public abstract int b();

    public abstract c c();

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public void c(int i) {
        this.c = new byte[i];
    }

    public boolean h() {
        return this.d == 0;
    }

    public String d(int i) {
        return ((com.qq.reader.readengine.kernel.e) this.p.get(i)).d();
    }

    public List<com.qq.reader.readengine.kernel.e> j() {
        return this.p;
    }

    public float[] e(int i) {
        return (float[]) this.i.get(i);
    }

    public float[] f(int i) {
        return (float[]) this.h.get(i);
    }

    public int k() {
        return this.p.size();
    }

    private int a(int i) {
        int size = this.o.size();
        if (size <= 1) {
            return 0;
        }
        e eVar = (e) this.o.remove(size - 1);
        size = (eVar.b() - eVar.a()) + 1;
        int i2 = size;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0 || b() < 1) {
                return size;
            }
            i2 = i3;
        }
    }

    private String b(int i) {
        return this.g.substring(0, (int) ((float[]) this.h.get((this.h.size() - i) - 1))[1]);
    }

    public int b(String str) {
        UnsupportedEncodingException e;
        Exception e2;
        int a = a(this.k);
        if (a == 0) {
            return this.e;
        }
        String b = b(a);
        try {
            a = b.getBytes(str).length;
            try {
                this.e = a;
                this.g = b;
                return a;
            } catch (UnsupportedEncodingException e3) {
                e = e3;
                f.a("removeFootRest:", e.toString());
                return a;
            } catch (Exception e4) {
                e2 = e4;
                f.a("removeFootRest:", e2.toString());
                return a;
            }
        } catch (UnsupportedEncodingException e5) {
            UnsupportedEncodingException unsupportedEncodingException = e5;
            a = 0;
            e = unsupportedEncodingException;
            f.a("removeFootRest:", e.toString());
            return a;
        } catch (Exception e6) {
            Exception exception = e6;
            a = 0;
            e2 = exception;
            f.a("removeFootRest:", e2.toString());
            return a;
        }
    }

    public void a(String str) {
        long j = this.d;
        this.j.clear();
        int size = this.p.size();
        e eVar = null;
        int i = 0;
        int size2 = this.o.size();
        if (0 < size2) {
            eVar = (e) this.o.get(0);
        }
        long j2 = j;
        int i2 = 0;
        int i3 = 3;
        e eVar2 = eVar;
        while (i2 < size) {
            e eVar3;
            int i4;
            int i5 = (int) ((float[]) this.h.get(i2))[0];
            Object obj = new long[Math.max(1, ((com.qq.reader.readengine.kernel.e) this.p.get(i2)).d().length())];
            obj[0] = j2;
            if (i2 < size - 1) {
                i3 = EncodingUtils.getBytes(this.g.substring(i5, (int) ((float[]) this.h.get(i2 + 1))[0]), str).length;
            }
            j2 += (long) i3;
            this.j.add(obj);
            if (eVar2 != null && eVar2.a() == i2) {
                eVar2.a(obj[0]);
                i++;
                if (i < size2) {
                    int i6 = i;
                    eVar3 = (e) this.o.get(i);
                    i4 = i6;
                    i2++;
                    eVar2 = eVar3;
                    i = i4;
                }
            }
            i4 = i;
            eVar3 = eVar2;
            i2++;
            eVar2 = eVar3;
            i = i4;
        }
    }
}
