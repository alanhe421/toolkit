package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.qq.reader.readengine.kernel.PageIndex;
import com.tencent.feedback.proguard.R;

/* compiled from: AutoReader */
public class b {
    private static float[] p = new float[18];
    private Drawable a;
    private Drawable b;
    private int c;
    private int d;
    private int e = 0;
    private int f = 0;
    private Drawable g;
    private boolean h = false;
    private boolean i = false;
    private float j = 0.0f;
    private Paint k;
    private a l;
    private int m;
    private int n = 0;
    private i o;

    public b(Context context, i iVar, a aVar) {
        a(context);
        this.o = iVar;
        this.l = aVar;
        a();
    }

    public void a() {
        p[0] = 0.2f;
        for (int i = 1; i < p.length; i++) {
            if (p[i - 1] <= 1.0f) {
                p[i] = (float) (((double) p[i - 1]) + 0.2d);
            } else if (p[i - 1] <= 3.0f) {
                p[i] = (float) (((double) p[i - 1]) + 0.5d);
            } else if (p[i - 1] <= 6.0f) {
                p[i] = p[i - 1] + 1.0f;
            } else {
                p[i] = p[i - 1] + 2.0f;
            }
        }
    }

    public boolean a(Canvas canvas, int i, int i2) {
        if (!c() || b()) {
            return false;
        }
        canvas.drawBitmap(this.o.a(PageIndex.current, 1), 0.0f, 0.0f, this.k);
        canvas.save();
        canvas.clipRect((float) i, 0.0f, (float) i2, this.j);
        if (this.o.a().u() != 0) {
            this.o.a().a(canvas, 0);
        }
        canvas.drawBitmap(this.o.a(PageIndex.next), 0.0f, 0.0f, this.k);
        canvas.restore();
        this.g.setBounds(i, ((int) this.j) - 14, i2, ((int) this.j) + 20);
        this.g.draw(canvas);
        a(canvas, i2);
        return true;
    }

    public boolean b() {
        return this.m == 2;
    }

    public boolean c() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void d() {
        a(false);
        this.o.a(true);
    }

    public void a(int i) {
        a(true);
        this.m = i;
        this.j = 0.0f;
    }

    public float e() {
        return this.j;
    }

    public void a(float f) {
        this.j = f;
    }

    public boolean a(float f, float f2) {
        if (c()) {
            return true;
        }
        return false;
    }

    public boolean f() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    private void a(Canvas canvas, int i) {
        int i2 = this.e - 1;
        this.e = i2;
        if (i2 > 0) {
            this.a.setBounds(0, ((int) this.j) - (this.d >> 1), this.c, ((int) this.j) + (this.d >> 1));
            this.a.draw(canvas);
            this.a.setBounds(i - this.c, ((int) this.j) - (this.d >> 1), i, ((int) this.j) + (this.d >> 1));
            this.a.draw(canvas);
            return;
        }
        i2 = this.f - 1;
        this.f = i2;
        if (i2 > 0) {
            this.b.setBounds(0, ((int) this.j) - (this.d >> 1), this.c, ((int) this.j) + (this.d >> 1));
            this.b.draw(canvas);
            this.b.setBounds(i - this.c, ((int) this.j) - (this.d >> 1), i, ((int) this.j) + (this.d >> 1));
            this.b.draw(canvas);
        }
    }

    protected void a(Context context) {
        this.g = context.getResources().getDrawable(R.drawable.auto_line_nor);
        this.a = context.getResources().getDrawable(R.drawable.auto_arrow_down);
        this.b = context.getResources().getDrawable(R.drawable.auto_arrow_up);
        this.c = this.a.getIntrinsicWidth();
        this.d = this.a.getIntrinsicHeight();
        this.k = new Paint();
    }

    public int c(boolean z) {
        int i = z ? this.n + 1 : this.n - 1;
        if (i < p.length && i >= 0) {
            this.n = i;
        }
        return this.n;
    }

    public float g() {
        return (float) this.n;
    }

    public float h() {
        return p[this.n];
    }

    public void b(int i) {
        this.n = i;
    }
}
