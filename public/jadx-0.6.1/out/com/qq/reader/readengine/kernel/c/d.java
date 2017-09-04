package com.qq.reader.readengine.kernel.c;

import com.qq.reader.readengine.fileparse.c;
import com.qq.reader.readengine.kernel.e;
import com.qq.reader.readengine.kernel.g;
import format.epub.view.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TextLineInfo */
public class d {
    public float[] a;
    public long[] b;
    public int c;
    public g[] d;
    List<h> e = new ArrayList();
    private e f;
    private c g;
    private float[] h;

    public d(c cVar, e eVar, float[] fArr, float[] fArr2, long[] jArr, int i) {
        this.f = eVar;
        this.a = fArr;
        this.b = jArr;
        this.c = i;
        this.g = cVar;
        this.h = fArr2;
    }

    public c a() {
        return this.g;
    }

    public float[] b() {
        return this.h;
    }

    public List<h> c() {
        return this.e;
    }

    public void a(g[] gVarArr) {
        this.d = gVarArr;
    }

    public g[] d() {
        return this.d;
    }

    public g e() {
        return this.d[0];
    }

    public g f() {
        return this.d[this.d.length - 1];
    }

    public String g() {
        return this.f.d();
    }

    public e h() {
        return this.f;
    }
}
