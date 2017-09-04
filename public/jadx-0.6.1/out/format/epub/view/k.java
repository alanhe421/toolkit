package format.epub.view;

import com.qq.reader.readengine.kernel.h;

/* compiled from: ZLTextFixedPosition */
public class k extends h {
    public final int u;
    public final int v;
    public final int w;

    public k(int i, int i2, int i3) {
        this.u = i;
        this.v = i2;
        this.w = i3;
    }

    public k(h hVar) {
        this.u = hVar.a();
        this.v = hVar.b();
        this.w = hVar.c();
    }

    public final int a() {
        return this.u;
    }

    public final int b() {
        return this.v;
    }

    public final int c() {
        return this.w;
    }
}
