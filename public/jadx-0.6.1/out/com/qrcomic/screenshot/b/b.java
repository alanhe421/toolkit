package com.qrcomic.screenshot.b;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.qrcomic.screenshot.c.e;
import com.qrcomic.screenshot.d.d;
import com.qrcomic.screenshot.d.e.a;
import com.qrcomic.util.g;
import java.util.List;

/* compiled from: QRBubbleLayer */
public class b extends a {
    Matrix A;
    Matrix B;
    Matrix C;
    public PointF D;
    public float E;
    public float F;
    public float G;
    public float H;
    public float I;
    public float J;
    float[] K;
    int L;
    public float M;
    public float N;
    PointF O;
    protected int P;
    boolean Q;
    private volatile boolean R;
    private PointF S;
    private float T;
    private float U;
    private float V;
    private float W;
    private float X;
    private float Y;
    private PointF Z;
    private int aa;
    private List<a> ab;
    String i;
    com.qrcomic.screenshot.a.a j;
    Bitmap k;
    RectF l;
    RectF m;
    PointF n;
    PointF o;
    PointF p;
    PointF q;
    Path r;
    TextPaint s;
    TextPaint t;
    Paint u;
    Paint v;
    PointF w;
    PointF x;
    PointF y;
    Matrix z;

    public /* synthetic */ e d() {
        return g();
    }

    public String e() {
        return this.i;
    }

    public void f() {
        if (this.aa == 0) {
            this.E += 90.0f;
            this.aa = 1;
        } else if (this.aa == 1) {
            this.E -= 90.0f;
            this.aa = 0;
        }
        k();
        l();
    }

    private void k() {
        PointF a = a(this.y, null, true);
        if (!this.c.getRealScaleGoalRect().contains(a.x, a.y)) {
            if (a.x < this.c.getRealScaleGoalRect().left) {
                a.x = this.c.getRealScaleGoalRect().left;
            } else if (a.x > this.c.getRealScaleGoalRect().right) {
                a.x = this.c.getRealScaleGoalRect().right;
            }
            if (a.y < this.c.getRealScaleGoalRect().top) {
                a.y = this.c.getRealScaleGoalRect().top;
            } else if (a.y > this.c.getRealScaleGoalRect().bottom) {
                a.y = this.c.getRealScaleGoalRect().bottom;
            }
            b(a, this.y, false);
            i();
        }
    }

    public com.qrcomic.screenshot.c.a g() {
        if (this.c.a((a) this)) {
            return new com.qrcomic.screenshot.c.a(this.x, this.X, this.G, this.I, this.M, this.N, this.E, this.i, this.j, this.aa, this.b);
        }
        return null;
    }

    private void l() {
        if (!TextUtils.isEmpty(this.i)) {
            this.C.setScale(this.G * this.M, this.I * this.N);
            this.C.mapRect(this.l, this.m);
            float height = this.l.height();
            float f = 0.0f;
            if (this.aa == 0) {
                f = com.qrcomic.screenshot.d.e.a(this.i, this.l.width(), height, this.ab, this.I * this.m.height(), true);
            } else if (this.aa == 1) {
                f = com.qrcomic.screenshot.d.e.b(this.i, height, this.l.width(), this.ab, this.I * this.m.height(), true);
            }
            if (!d.a(f, height)) {
                this.N = (f / this.m.height()) / this.I;
                this.C.setScale(this.G * this.M, this.I * this.N);
                this.C.mapRect(this.l, this.m);
            }
            if (g.a()) {
                g.a("QRBubbleLayer", g.d, "mBubbleTextDirection = " + this.aa + " , layout mPassivityScaleX = " + this.M + " , mPassivityScaleY = " + this.N + " , mScaleX = " + this.G + " , mScaleY = " + this.I);
            }
            m();
            this.c.c();
        } else if (g.a()) {
            g.a("QRBubbleLayer", g.d, "mBubbleContent is empty");
        }
    }

    private void m() {
        if (!this.R) {
            this.R = true;
            n();
        }
        h();
        j();
    }

    private void n() {
        this.w.x = (this.d.width() - ((float) com.qrcomic.screenshot.a.b.e)) / 2.0f;
        this.w.y = (this.d.height() - ((float) com.qrcomic.screenshot.a.b.b(this.k))) / 2.0f;
        this.x.x = this.w.x;
        this.x.y = this.w.y;
    }

    protected void h() {
        this.y.x = this.x.x + ((float) (com.qrcomic.screenshot.a.b.a(this.k) / 2));
        this.y.y = this.x.y + ((float) (com.qrcomic.screenshot.a.b.b(this.k) / 2));
    }

    protected void i() {
        this.x.x = this.y.x - ((float) (com.qrcomic.screenshot.a.b.a(this.k) / 2));
        this.x.y = this.y.y - ((float) (com.qrcomic.screenshot.a.b.b(this.k) / 2));
    }

    private void a(Canvas canvas, Matrix matrix) {
        if (this.a) {
            float[] fArr = new float[]{(float) (com.qrcomic.screenshot.a.b.e + com.qrcomic.screenshot.a.b.h), (float) (-com.qrcomic.screenshot.a.b.h)};
            float[] fArr2 = new float[]{(float) (com.qrcomic.screenshot.a.b.e + com.qrcomic.screenshot.a.b.h), (float) (com.qrcomic.screenshot.a.b.b(this.k) + com.qrcomic.screenshot.a.b.h)};
            float[] fArr3 = new float[]{(float) (-com.qrcomic.screenshot.a.b.h), (float) (-com.qrcomic.screenshot.a.b.h)};
            float[] fArr4 = new float[]{(float) (-com.qrcomic.screenshot.a.b.h), (float) (com.qrcomic.screenshot.a.b.b(this.k) + com.qrcomic.screenshot.a.b.h)};
            matrix.mapPoints(fArr);
            this.o.set(fArr[0], fArr[1]);
            matrix.mapPoints(fArr2);
            this.n.set(fArr2[0], fArr2[1]);
            matrix.mapPoints(fArr3);
            this.p.set(fArr3[0], fArr3[1]);
            matrix.mapPoints(fArr4);
            this.q.set(fArr4[0], fArr4[1]);
            this.r.reset();
            this.r.moveTo(this.p.x, this.p.y);
            this.r.lineTo(this.o.x, this.o.y);
            this.r.lineTo(this.n.x, this.n.y);
            this.r.lineTo(this.q.x, this.q.y);
            this.r.lineTo(this.p.x, this.p.y);
            canvas.drawPath(this.r, this.u);
            if (this.aa == 0) {
                canvas.drawBitmap(this.c.getSwitchBitmap(), this.p.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.p.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
                canvas.drawBitmap(this.c.getCancelBitmap(), this.o.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.o.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
                canvas.drawBitmap(this.c.getCtrlBitmap(), this.n.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.n.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
            } else if (this.aa == 1) {
                canvas.drawBitmap(this.c.getSwitchBitmap(), this.q.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.q.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
                canvas.drawBitmap(this.c.getCancelBitmap(), this.p.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.p.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
                canvas.drawBitmap(this.c.getCtrlBitmap(), this.o.x - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.o.y - ((float) (com.qrcomic.screenshot.a.b.b / 2)), this.v);
            }
        }
    }

    protected void j() {
        if (this.z != null) {
            this.z.reset();
            this.z.preRotate(this.E, this.y.x, this.y.y);
            this.z.preScale(this.X, this.X, this.y.x, this.y.y);
            this.z.preScale(this.G * this.M, this.I * this.N, this.y.x, this.y.y);
            this.z.preTranslate(this.x.x, this.x.y);
        }
    }

    public void a(Canvas canvas) {
        if (canvas != null) {
            this.A.set(this.z);
            this.A.postConcat(this.f);
            this.A.postConcat(this.c.o);
            this.A.postConcat(this.c.l);
            canvas.save();
            canvas.clipRect(this.c.getRealScaleGoalRect());
            if (!(this.k == null || this.k.isRecycled())) {
                canvas.drawBitmap(this.k, this.A, this.v);
            }
            a(canvas, 0);
            canvas.restore();
            if (this.a) {
                a(canvas, this.A);
            }
        }
    }

    public void b(Canvas canvas) {
        if (canvas != null) {
            if (!(this.k == null || this.k.isRecycled())) {
                this.A.set(this.z);
                this.A.postConcat(this.f);
                canvas.drawBitmap(this.k, this.A, this.v);
            }
            a(canvas, 1);
        }
    }

    private void a(Canvas canvas, int i) {
        if (this.ab.size() >= 1) {
            this.C.reset();
            if (this.aa == 0) {
                this.C.preRotate(this.E, this.y.x, this.y.y);
                this.C.preScale(this.X, this.X, this.y.x, this.y.y);
                this.C.preTranslate(this.y.x - (this.l.width() / 2.0f), this.y.y - (this.l.height() / 2.0f));
            } else if (this.aa == 1) {
                this.C.preRotate(this.E - 90.0f, this.y.x, this.y.y);
                this.C.preScale(this.X, this.X, this.y.x, this.y.y);
                this.C.preTranslate(this.y.x - (this.l.height() / 2.0f), this.y.y - (this.l.width() / 2.0f));
            }
            this.C.postConcat(this.f);
            switch (i) {
                case 1:
                    break;
                case 2:
                    this.C.postConcat(this.c.o);
                    break;
                default:
                    this.C.postConcat(this.c.o);
                    this.C.postConcat(this.c.l);
                    break;
            }
            canvas.save();
            canvas.concat(this.C);
            this.s.setAntiAlias(true);
            this.s.setTextSize(((a) this.ab.get(0)).d);
            if (this.j.e && this.t != null) {
                this.t.setTextSize(((a) this.ab.get(0)).d);
            }
            for (a aVar : this.ab) {
                if (!TextUtils.isEmpty(aVar.a)) {
                    if (this.j.e && this.t != null) {
                        canvas.drawText(aVar.a, aVar.b, aVar.c, this.t);
                    }
                    canvas.drawText(aVar.a, aVar.b, aVar.c, this.s);
                }
            }
            canvas.restore();
        }
    }

    private PointF a(PointF pointF, PointF pointF2, boolean z) {
        this.A.reset();
        this.A.postConcat(this.f);
        this.A.postConcat(this.c.o);
        if (z) {
            this.A.postConcat(this.c.l);
        }
        float[] fArr = new float[]{pointF.x, pointF.y};
        this.A.mapPoints(fArr);
        if (pointF2 == null) {
            return new PointF(fArr[0], fArr[1]);
        }
        pointF2.set(fArr[0], fArr[1]);
        return pointF2;
    }

    private PointF b(PointF pointF, PointF pointF2, boolean z) {
        return a(new float[]{pointF.x, pointF.y}, pointF2, z);
    }

    private PointF a(float[] fArr, PointF pointF, boolean z) {
        this.A.set(this.c.m);
        this.A.postConcat(this.c.n);
        this.A.postConcat(this.g);
        if (z) {
            this.A.postConcat(this.e);
        }
        this.A.mapPoints(fArr);
        if (pointF == null) {
            return new PointF(fArr[0], fArr[1]);
        }
        pointF.set(fArr[0], fArr[1]);
        return pointF;
    }

    public boolean b(MotionEvent motionEvent) {
        return a(new PointF(motionEvent.getX(), motionEvent.getY()));
    }

    public boolean a(PointF pointF) {
        if (this.aa == 0) {
            if (d.b(pointF, this.n) <= ((float) com.qrcomic.screenshot.a.b.c)) {
                return true;
            }
            return false;
        } else if (this.aa != 1) {
            return false;
        } else {
            if (d.b(pointF, this.o) > ((float) com.qrcomic.screenshot.a.b.c)) {
                return false;
            }
            return true;
        }
    }

    public boolean c(MotionEvent motionEvent) {
        return b(new PointF(motionEvent.getX(), motionEvent.getY()));
    }

    public boolean b(PointF pointF) {
        if (this.aa == 0) {
            if (d.b(pointF, this.o) <= ((float) com.qrcomic.screenshot.a.b.c)) {
                return true;
            }
            return false;
        } else if (this.aa != 1) {
            return false;
        } else {
            if (d.b(pointF, this.p) > ((float) com.qrcomic.screenshot.a.b.c)) {
                return false;
            }
            return true;
        }
    }

    public boolean d(MotionEvent motionEvent) {
        return c(new PointF(motionEvent.getX(), motionEvent.getY()));
    }

    public boolean c(PointF pointF) {
        if (this.aa == 0) {
            if (d.b(pointF, this.p) <= ((float) com.qrcomic.screenshot.a.b.c)) {
                return true;
            }
            return false;
        } else if (this.aa != 1) {
            return false;
        } else {
            if (d.b(pointF, this.q) > ((float) com.qrcomic.screenshot.a.b.c)) {
                return false;
            }
            return true;
        }
    }

    public boolean d(PointF pointF) {
        float[] fArr = new float[]{pointF.x, pointF.y};
        com.qrcomic.screenshot.d.b.a(this.z, this.A);
        this.A.mapPoints(fArr);
        if (fArr[0] < ((float) (-(com.qrcomic.screenshot.a.b.h + com.qrcomic.screenshot.a.b.g))) || fArr[0] > ((float) (com.qrcomic.screenshot.a.b.a(this.k) + (com.qrcomic.screenshot.a.b.h + com.qrcomic.screenshot.a.b.g))) || fArr[1] < ((float) (-(com.qrcomic.screenshot.a.b.h + com.qrcomic.screenshot.a.b.g))) || fArr[1] > ((float) (com.qrcomic.screenshot.a.b.b(this.k) + (com.qrcomic.screenshot.a.b.h + com.qrcomic.screenshot.a.b.g)))) {
            return false;
        }
        return true;
    }

    public boolean e(PointF pointF) {
        float[] fArr = new float[]{pointF.x, pointF.y};
        com.qrcomic.screenshot.d.b.a(this.z, this.A);
        this.A.mapPoints(fArr);
        if (fArr[0] < ((float) (com.qrcomic.screenshot.a.b.g - com.qrcomic.screenshot.a.b.h)) || fArr[0] > ((float) ((com.qrcomic.screenshot.a.b.a(this.k) + com.qrcomic.screenshot.a.b.h) - com.qrcomic.screenshot.a.b.g)) || fArr[1] < ((float) (com.qrcomic.screenshot.a.b.g - com.qrcomic.screenshot.a.b.h)) || fArr[1] > ((float) ((com.qrcomic.screenshot.a.b.b(this.k) + com.qrcomic.screenshot.a.b.h) - com.qrcomic.screenshot.a.b.g))) {
            return false;
        }
        return true;
    }

    public boolean f(PointF pointF) {
        float[] fArr = new float[]{pointF.x, pointF.y};
        com.qrcomic.screenshot.d.b.a(this.z, this.A);
        this.A.mapPoints(fArr);
        if (fArr[0] < 0.0f || fArr[0] > ((float) com.qrcomic.screenshot.a.b.e) || fArr[1] < 0.0f || fArr[1] > ((float) com.qrcomic.screenshot.a.b.b(this.k))) {
            return false;
        }
        return true;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction() & 255) {
            case 0:
                z = e(motionEvent);
                break;
            case 1:
                z = g(motionEvent);
                break;
            case 2:
                z = f(motionEvent);
                break;
        }
        j();
        return z;
    }

    private boolean e(MotionEvent motionEvent) {
        this.O.set(motionEvent.getX(), motionEvent.getY());
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        b(pointF, pointF, false);
        if (this.a) {
            if (b(motionEvent)) {
                this.P = 16;
                this.V = d.b(pointF, this.y);
                this.Y = this.X;
                this.T = d.c(pointF, this.y);
                this.F = this.E;
                return true;
            } else if (c(motionEvent)) {
                this.P = 32;
                return true;
            } else if (d(motionEvent)) {
                this.P = 64;
                return true;
            } else if (this.c.getRealScaleGoalRect().contains(motionEvent.getX(), motionEvent.getY()) && f(pointF)) {
                this.P = 4;
                this.D.set(this.x);
                this.S.set(pointF);
                this.Q = false;
                return true;
            } else if (e(pointF)) {
                this.P = 2;
                return true;
            } else if (d(pointF)) {
                this.P = 8;
                this.H = this.G;
                this.J = this.I;
                this.K[0] = pointF.x;
                this.K[1] = pointF.y;
                com.qrcomic.screenshot.d.b.a(this.z, this.B);
                this.B.mapPoints(this.K);
                this.Z.set(this.K[0], this.K[1]);
                if (this.Z.x < ((float) (com.qrcomic.screenshot.a.b.g - com.qrcomic.screenshot.a.b.h))) {
                    this.L = 1;
                    return true;
                } else if (this.Z.x > ((float) ((com.qrcomic.screenshot.a.b.a(this.k) + com.qrcomic.screenshot.a.b.h) - com.qrcomic.screenshot.a.b.g))) {
                    this.L = 3;
                    return true;
                } else if (this.Z.y < ((float) (com.qrcomic.screenshot.a.b.g - com.qrcomic.screenshot.a.b.h))) {
                    this.L = 2;
                    return true;
                } else if (this.Z.y <= ((float) ((com.qrcomic.screenshot.a.b.b(this.k) + com.qrcomic.screenshot.a.b.h) - com.qrcomic.screenshot.a.b.g))) {
                    return true;
                } else {
                    this.L = 4;
                    return true;
                }
            }
        } else if (this.c.getRealScaleGoalRect().contains(motionEvent.getX(), motionEvent.getY()) && f(pointF)) {
            this.P = 4;
            this.D.set(this.x);
            this.S.set(pointF);
            this.Q = false;
            return true;
        }
        return false;
    }

    private boolean f(MotionEvent motionEvent) {
        boolean z = false;
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        b(pointF, pointF, false);
        if (this.P == 16) {
            this.U = d.c(pointF, this.y);
            this.E = (this.F + this.U) - this.T;
            this.W = d.b(pointF, this.y);
            this.X = this.Y * (this.W / this.V);
            if (this.X > 3.0f) {
                this.X = 3.0f;
            }
            if (this.X >= 0.4f) {
                return true;
            }
            this.X = 0.4f;
            return true;
        } else if (this.P == 4) {
            if (!this.c.getRealScaleGoalRect().contains(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
            this.x.set((this.D.x + pointF.x) - this.S.x, (pointF.y + this.D.y) - this.S.y);
            h();
            if (this.Q || d.a(motionEvent, this.O) <= ((float) com.qrcomic.screenshot.a.b.a)) {
                return true;
            }
            this.Q = true;
            e(this, this.a);
            return true;
        } else if (this.P != 8) {
            return false;
        } else {
            this.K[0] = pointF.x;
            this.K[1] = pointF.y;
            this.B.mapPoints(this.K);
            float f = this.K[0] - this.Z.x;
            float f2 = this.K[1] - this.Z.y;
            switch (this.L) {
                case 1:
                    if (f >= 0.0f) {
                        if (f > 0.0f) {
                            this.G = (1.0f - (Math.abs(f) / ((float) com.qrcomic.screenshot.a.b.a(this.k)))) * this.H;
                            break;
                        }
                    }
                    this.G = ((Math.abs(f) / ((float) com.qrcomic.screenshot.a.b.a(this.k))) + 1.0f) * this.H;
                    break;
                    break;
                case 2:
                    if (f2 >= 0.0f) {
                        if (f2 > 0.0f) {
                            this.I = this.J * (1.0f - (Math.abs(f2) / ((float) com.qrcomic.screenshot.a.b.b(this.k))));
                            break;
                        }
                    }
                    this.I = this.J * ((Math.abs(f2) / ((float) com.qrcomic.screenshot.a.b.b(this.k))) + 1.0f);
                    break;
                    break;
                case 3:
                    if (f >= 0.0f) {
                        if (f > 0.0f) {
                            this.G = ((Math.abs(f) / ((float) com.qrcomic.screenshot.a.b.a(this.k))) + 1.0f) * this.H;
                            break;
                        }
                    }
                    this.G = (1.0f - (Math.abs(f) / ((float) com.qrcomic.screenshot.a.b.a(this.k)))) * this.H;
                    break;
                    break;
                case 4:
                    if (f2 >= 0.0f) {
                        if (f2 > 0.0f) {
                            this.I = this.J * ((Math.abs(f2) / ((float) com.qrcomic.screenshot.a.b.b(this.k))) + 1.0f);
                            break;
                        }
                    }
                    this.I = this.J * (1.0f - (Math.abs(f2) / ((float) com.qrcomic.screenshot.a.b.b(this.k))));
                    break;
                    break;
            }
            if (this.G < 0.3f) {
                this.G = 0.3f;
            }
            if (this.I < 0.4f) {
                this.I = 0.4f;
            }
            this.C.setScale(this.G * this.M, this.I * this.N);
            this.C.mapRect(this.l, this.m);
            if (this.ab.size() > 0 && this.l.height() < ((a) this.ab.get(0)).f) {
                this.I = (((a) this.ab.get(0)).f / this.m.height()) / this.N;
                this.C.setScale(this.G * this.M, this.I * this.N);
                this.C.mapRect(this.l, this.m);
            }
            f2 = this.l.height();
            if (this.aa == 0) {
                String str = this.i;
                float width = this.l.width();
                List list = this.ab;
                float height = this.I * this.m.height();
                boolean z2 = this.L == 1 || this.L == 3;
                f = com.qrcomic.screenshot.d.e.a(str, width, f2, list, height, z2);
            } else if (this.aa == 1) {
                String str2 = this.i;
                float width2 = this.l.width();
                List list2 = this.ab;
                float height2 = this.m.height() * this.I;
                if (this.L == 1 || this.L == 3) {
                    z = true;
                }
                f = com.qrcomic.screenshot.d.e.b(str2, f2, width2, list2, height2, z);
            } else {
                f = 0.0f;
            }
            if (!d.a(f, f2)) {
                this.N = (f / this.m.height()) / this.I;
                this.C.setScale(this.G * this.M, this.I * this.N);
                this.C.mapRect(this.l, this.m);
            }
            if (!g.a()) {
                return true;
            }
            g.a("QRBubbleLayer", g.d, "mBubbleTextDirection = " + this.aa + " , touch mPassivityScaleX = " + this.M + " , mPassivityScaleY = " + this.N + " , mScaleX = " + this.G + " , mScaleY = " + this.I);
            return true;
        }
    }

    private boolean g(MotionEvent motionEvent) {
        boolean z;
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        b(pointF, pointF, false);
        double a = (double) d.a(motionEvent, this.O);
        if (this.P != 4) {
            if (a < ((double) com.qrcomic.screenshot.a.b.a)) {
                if (this.P == 32 && c(motionEvent)) {
                    a(this, this.a);
                    z = true;
                } else if (this.P == 64 && d(motionEvent)) {
                    b(this, this.a);
                    z = true;
                } else if (this.P == 2) {
                    z = true;
                }
            }
            z = false;
        } else if (this.Q) {
            f(this, this.a);
            z = true;
        } else {
            if (a < ((double) com.qrcomic.screenshot.a.b.a) && f(pointF)) {
                c(this, this.a);
                z = true;
            }
            z = false;
        }
        g(this, this.a);
        if (this.P != 1) {
            this.P = 1;
        }
        this.O.set(0.0f, 0.0f);
        this.B.reset();
        this.L = 0;
        return z;
    }

    public void c() {
    }
}
