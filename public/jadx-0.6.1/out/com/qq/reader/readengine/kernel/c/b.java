package com.qq.reader.readengine.kernel.c;

import com.qq.reader.readengine.kernel.f;
import com.qq.reader.readengine.kernel.g;
import format.epub.view.h;
import format.epub.view.y;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EncodingUtils;

/* compiled from: QTxtPage */
public class b extends f {
    private static String e = "utf-8";
    public float b = 0.0f;
    public float c = 0.0f;
    private ArrayList<d> d = new ArrayList();
    private int f = 1;
    private boolean g = false;
    private int h;

    public static void a(String str) {
        if (str != null) {
            e = new String(str);
        }
    }

    public boolean b() {
        return this.d.size() > 0;
    }

    public void a(d dVar) {
        b(dVar);
        this.d.add(dVar);
    }

    public void b(d dVar) {
        if (dVar.h().b() && dVar.a() != null) {
            List c = dVar.c();
            c.clear();
            long j = 0;
            String str = "";
            String str2 = dVar.a().g;
            float[] b = dVar.b();
            g[] gVarArr = new g[(dVar.a.length / 2)];
            for (int i = 0; i < dVar.a.length; i += 2) {
                format.epub.view.g gVar;
                String substring = str2.substring(((int) b[0]) + (i / 2), (((int) b[0]) + (i / 2)) + 1);
                if (substring.equals(" ")) {
                    gVar = format.epub.view.g.c;
                } else {
                    gVar = new y(substring.toCharArray(), 0, 1, 0);
                }
                if (i / 2 != 0) {
                    dVar.b[i / 2] = j;
                } else {
                    j = dVar.b[0];
                }
                g gVar2 = new g();
                if (dVar.c > 0) {
                    gVar2.a(dVar.c, j);
                } else {
                    gVar2.a(j);
                }
                c.add(new h(gVar2, 0, false, false, null, gVar, 0.0f, 0.0f, 0.0f, 0.0f, j));
                gVarArr[i / 2] = gVar2;
                j += (long) EncodingUtils.getBytes(substring, e).length;
            }
            dVar.a(gVarArr);
        }
    }

    public void c(d dVar) {
        b(dVar);
        this.d.add(0, dVar);
    }

    public void c() {
        this.d.remove(f() - 1);
    }

    public void d() {
        this.d.remove(0);
    }

    public void e() {
        this.d.clear();
    }

    public int f() {
        return this.d.size();
    }

    public float[] a(int i) {
        return ((d) this.d.get(i)).a;
    }

    public d b(int i) {
        return (d) this.d.get(i);
    }

    public boolean g() {
        return this.g;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public int h() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }
}
