package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.readpage.a;
import com.qq.reader.module.readpage.b;
import com.qq.reader.module.readpage.j;
import com.qq.reader.module.readpage.y;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.view.animation.AnimationProvider.Mode;

/* compiled from: UpDownScrollingProvider */
public class i extends AnimationProvider {
    private a A;
    private b B;
    private y C;
    private float D = 0.0f;
    private int E = 0;
    private int F = 0;
    private int G = 0;
    private int H = 0;
    private float I = 0.0f;
    PageIndex p = PageIndex.current;
    public float q = 0.0f;
    g r;
    private int s = 12;
    private float t;
    private float u;
    private int v;
    private boolean w = false;
    private com.qq.reader.module.readpage.i x;
    private Scroller y = null;
    private int z = 0;

    public i(com.qq.reader.module.readpage.i iVar, Context context, a aVar, y yVar, b bVar, AnimationProvider.a aVar2) {
        super(iVar, context);
        this.r = new g(iVar, context, aVar2);
        this.x = iVar;
        this.y = new Scroller(context, new LinearInterpolator());
        this.A = aVar;
        this.v = ViewConfiguration.get(context.getApplicationContext()).getScaledTouchSlop();
        this.B = bVar;
        this.C = yVar;
    }

    public boolean j() {
        if (!this.w) {
            this.x.g();
            l();
            this.w = true;
        } else if (!(this.k.e(PageIndex.next) || this.k.e(PageIndex.previous))) {
            this.p = PageIndex.current;
            k();
        }
        return this.w;
    }

    public void k() {
        this.D = 0.0f;
    }

    public void l() {
        this.x.a();
        this.G = j.k();
        this.x.a();
        this.H = j.j();
        this.E = this.x.a().b();
        this.F = this.x.a().n();
        this.I = this.x.a().a();
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        l();
        this.r.a(i, i2);
    }

    public void m() {
        this.s = 12;
    }

    public void a(boolean z, int i) {
        this.y.fling(0, 0, 0, i, 0, 0, this.E * -2, this.E);
        this.z = 0;
    }

    public boolean g() {
        if (this.r.g()) {
            f.b("doStep", "mScorllController doStep");
            return true;
        } else if (this.y.computeScrollOffset()) {
            int currY = this.y.getCurrY() - this.z;
            this.z = this.y.getCurrY();
            if (a((float) currY)) {
                return true;
            }
            this.y.forceFinished(true);
            return true;
        } else if (o()) {
            this.s = 14;
            if (a(-this.B.h())) {
                this.A.b();
                return true;
            }
            this.A.a();
            return false;
        } else {
            this.s = 12;
            return true;
        }
    }

    public void n() {
        if (!this.y.isFinished()) {
            this.y.forceFinished(true);
        }
    }

    public boolean f() {
        return !this.y.isFinished() || o() || this.r.f();
    }

    public boolean o() {
        return this.B.c() && this.B.b() && !this.B.f();
    }

    public boolean a(float f) {
        return this.C.b(f);
    }

    public boolean a(Canvas canvas) {
        if (p()) {
            this.r.a(canvas);
        } else {
            b(canvas);
        }
        return true;
    }

    private void b(Canvas canvas) {
        this.x.a().a(canvas, 0);
        canvas.save();
        canvas.clipRect(0, this.G, this.x.a().n(), this.E + this.G);
        this.x.a(canvas, PageIndex.current);
        canvas.restore();
    }

    public boolean p() {
        return !this.C.B() || this.r.f() || this.r.e();
    }

    public boolean a(MotionEvent motionEvent, VelocityTracker velocityTracker) {
        if ((this.B.c() && !this.B.b()) || !this.C.B() || this.r.e()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.u = motionEvent.getY();
                this.t = this.u;
                if (this.s == 15) {
                    n();
                }
                switch (this.s) {
                    case 14:
                        this.B.b(true);
                        break;
                    case 15:
                        this.s = 12;
                        break;
                }
                return true;
            case 1:
                if (this.s == 14) {
                    this.B.b(false);
                    return false;
                } else if (velocityTracker == null) {
                    return false;
                } else {
                    velocityTracker.computeCurrentVelocity(1000);
                    int yVelocity = (int) velocityTracker.getYVelocity();
                    if (Math.abs(yVelocity) > 400) {
                        a(true, yVelocity);
                        this.s = 15;
                    } else {
                        this.s = 12;
                    }
                    return true;
                }
            case 2:
                float y = motionEvent.getY() - this.t;
                this.t = motionEvent.getY();
                this.q += y;
                switch (this.s) {
                    case 12:
                        if (Math.abs(motionEvent.getY() - this.u) >= ((float) this.v)) {
                            this.s = 13;
                            break;
                        }
                        break;
                    case 13:
                    case 14:
                        a(y);
                        break;
                }
                return true;
            default:
                return false;
        }
    }

    public int q() {
        float f = this.D;
        switch (this.p) {
            case next:
                return Math.round((-(((float) this.E) + f)) / this.I);
            case previous:
                return Math.round((((float) this.E) - f) / this.I);
            default:
                return 0;
        }
    }

    public void b(int i, int i2) {
        this.r.b(i, i2);
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        this.r.a(i, i2, i3, i4, mode, i5);
    }

    public PageIndex a(float f, float f2) {
        return this.r.a(f, f2);
    }

    public void b(float f, float f2) {
        this.r.b(f, f2);
    }

    public boolean e() {
        return this.r.e();
    }

    public int a(com.qq.reader.readengine.kernel.b bVar) {
        if (this.C.B()) {
            return 2;
        }
        return this.r.a(bVar);
    }

    public int b(com.qq.reader.readengine.kernel.b bVar) {
        if (this.C.B()) {
            return 2;
        }
        return this.r.b(bVar);
    }
}
