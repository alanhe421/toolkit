package format.epub.paint;

import android.graphics.Canvas;
import format.epub.common.c.a.g.b;
import format.epub.common.text.model.h;
import format.epub.common.utils.k;
import format.epub.view.p;
import format.epub.view.v;
import java.util.ArrayList;

public abstract class ZLPaintContext {
    private final ArrayList<String> a = new ArrayList();
    private boolean b = true;
    private String c = "";
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private float i = -1.0f;
    private float j = -1.0f;
    private float k = -1.0f;
    private float l = -1.0f;
    private float m = -1.0f;

    public enum ScalingType {
        OriginalSize,
        IntegerCoefficient,
        FitMaximum,
        FULLSCREEN,
        SCALEWIDTH,
        FILLSCREEN,
        SCALEHEIGHT,
        SCALEWH
    }

    public abstract float a(char[] cArr, int i, int i2);

    public abstract int a(p pVar, h hVar, ScalingType scalingType);

    public abstract int a(p pVar, h hVar, ScalingType scalingType, v vVar);

    public abstract void a(float f, float f2, float f3, float f4, Canvas canvas);

    public abstract void a(float f, float f2, p pVar, h hVar, ScalingType scalingType, Canvas canvas);

    public abstract void a(float f, float f2, char[] cArr, int i, int i2, Canvas canvas);

    public abstract void a(k kVar);

    public abstract void a(k kVar, int i);

    public abstract void a(k kVar, Canvas canvas);

    protected abstract void a(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, b bVar);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract int b();

    public abstract int c();

    protected abstract float d();

    protected abstract float e();

    protected abstract float f();

    protected abstract float g();

    public abstract void h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    protected abstract float m();

    protected ZLPaintContext() {
    }

    public final void b(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, b bVar) {
        if (!(str == null || this.c.equals(str))) {
            this.c = str;
            this.b = true;
        }
        if (this.d != i) {
            this.d = i;
            this.b = true;
        }
        if (this.e != z) {
            this.e = z;
            this.b = true;
        }
        if (this.f != z2) {
            this.f = z2;
            this.b = true;
        }
        if (this.g != z3) {
            this.g = z3;
            this.b = true;
        }
        if (this.h != z4) {
            this.h = z4;
            this.b = true;
        }
        if (this.b) {
            this.b = false;
            a(this.c, i, z, z2, z3, z4, bVar);
            this.i = -1.0f;
            this.j = -1.0f;
            this.k = -1.0f;
            this.l = -1.0f;
            this.m = -1.0f;
        }
    }

    public final void b(k kVar) {
        a(kVar, 0);
    }

    public final float n() {
        float f = this.i;
        if (f != -1.0f) {
            return f;
        }
        f = d();
        this.i = f;
        return f;
    }

    public final float o() {
        float f = this.j;
        if (f != -1.0f) {
            return f;
        }
        f = e();
        this.j = f;
        return f;
    }

    public final float p() {
        float f = this.k;
        if (f != -1.0f) {
            return f;
        }
        f = f();
        this.k = f;
        return f;
    }

    public final float q() {
        float f = this.l;
        if (f != -1.0f) {
            return f;
        }
        f = g();
        this.l = f;
        return f;
    }

    public final float r() {
        float f = this.m;
        if (f != -1.0f) {
            return f;
        }
        f = m();
        this.m = f;
        return f;
    }

    public void s() {
        this.c = "";
        h();
        t();
    }

    public void t() {
        this.m = -1.0f;
        this.l = -1.0f;
        this.j = -1.0f;
        this.k = -1.0f;
    }
}
