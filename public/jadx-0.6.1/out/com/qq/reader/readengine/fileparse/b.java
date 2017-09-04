package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.monitor.f;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.util.EncodingUtils;

/* compiled from: BookUmdBuff */
public class b extends c {
    private ArrayList<String> p;
    private int q;
    private int r;
    private boolean s;

    public /* synthetic */ c c() {
        return i();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return i();
    }

    public b() {
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.c = new byte[32768];
        this.p = new ArrayList();
    }

    public void a() {
        this.s = true;
    }

    public boolean d() {
        return this.s;
    }

    public void a(ArrayList<String> arrayList) {
        this.p = arrayList;
    }

    public ArrayList<String> e() {
        return this.p;
    }

    public int f() {
        return this.r;
    }

    public void a(int i) {
        this.r = i;
    }

    public int g() {
        return this.q;
    }

    public void b(int i) {
        this.q = i;
    }

    public boolean h() {
        return this.d == 0 && this.r == 0;
    }

    public int b() {
        if (k() <= 1) {
            return 0;
        }
        this.p.add(d(k() - 1));
        j().remove(k() - 1);
        return 1;
    }

    public b i() {
        b bVar = new b();
        bVar.c = this.c;
        bVar.e = this.e;
        bVar.d = this.d;
        bVar.g = this.g;
        bVar.q = this.q;
        bVar.r = this.r;
        return bVar;
    }

    public void a(d dVar, boolean z, int i) {
        if (z && i == 0) {
            b bVar = (b) dVar.c();
            if (bVar != null) {
                ArrayList e = bVar.e();
                int size = e.size();
                if (size > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i2 = size - 1; i2 >= 0; i2--) {
                        stringBuffer.append((String) e.get(i2));
                    }
                    try {
                        a(0 - stringBuffer.toString().getBytes("UTF-16LE").length);
                        this.g = stringBuffer.toString() + this.g;
                    } catch (UnsupportedEncodingException e2) {
                        f.a("UMD", "formatBuff UnsupportedEncodingException");
                    }
                }
            }
        }
    }

    public void a(String str) {
        this.j.clear();
        int size = j().size();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < size) {
            long j;
            int length;
            Object obj = new long[d(i).length()];
            if (this.r >= 0 || this.r + i3 >= 0) {
                j = (long) (((this.q * 32768) + i3) + this.r);
            } else if (this.q >= 1) {
                j = (long) ((this.q * 32768) + this.r);
            } else {
                j = 0;
            }
            int i4 = (int) ((float[]) this.h.get(i))[0];
            if (i < size - 1) {
                length = EncodingUtils.getBytes(this.g.substring(i4, (int) ((float[]) this.h.get(i + 1))[0]), "UTF-16LE").length;
            } else {
                length = i2;
            }
            i2 = i3 + length;
            obj[0] = j;
            this.j.add(obj);
            i++;
            i3 = i2;
            i2 = length;
        }
    }
}
