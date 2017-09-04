package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader.TileMode;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.ab;
import com.qq.reader.module.readpage.i;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.animation.AnimationProvider.AnimState;
import com.qq.reader.view.animation.AnimationProvider.Direction;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.animation.AnimationProvider.a;
import com.tencent.smtt.sdk.WebView;

public class BezierAnimationProvider extends AnimationProvider {
    float A;
    float B;
    float C;
    float D;
    float E;
    float F;
    float G;
    float H;
    float I;
    float J;
    float K;
    float L;
    float M;
    float N;
    float O;
    float P;
    float Q;
    float R;
    float S;
    Path T;
    Paint U;
    private Scroller V;
    private int W;
    private final Paint X;
    private final Paint Y;
    private TouchArea Z;
    private boolean aa;
    private boolean ab;
    boolean p;
    Path q;
    Path r;
    LinearGradient s;
    float t;
    float u;
    float v;
    float w;
    float x;
    float y;
    float z;

    public enum TouchArea {
        leftCenter,
        rightCenter,
        rightTop,
        rightButtom,
        None
    }

    public BezierAnimationProvider(i iVar, Context context, a aVar) {
        super(iVar, context, aVar);
        this.W = -1;
        this.X = new Paint();
        this.Y = new Paint();
        this.Z = TouchArea.None;
        this.aa = false;
        this.p = false;
        this.q = new Path();
        this.r = new Path();
        this.T = new Path();
        this.U = new Paint();
        this.d = AnimState.READY;
        this.Z = TouchArea.None;
        this.V = new Scroller(context, new DecelerateInterpolator(0.8f));
        this.X.setAlpha(32);
        new Options().inPurgeable = true;
        this.ab = d.ao(b());
        iVar.a().e(0);
    }

    private void c(Canvas canvas) {
    }

    private int j() {
        if (com.qq.reader.common.c.a.x) {
            return 0;
        }
        return com.qq.reader.common.c.a.ca;
    }

    public int b(Canvas canvas) {
        return this.ab ? canvas.getHeight() : canvas.getHeight() - j();
    }

    private void d(Canvas canvas) {
    }

    public int c() {
        return this.k.a().t() ? 0 : 0;
    }

    public int d() {
        return 0;
    }

    public void a(int i, int i2) {
        this.l = i - c();
        this.m = i2;
    }

    public boolean e() {
        return this.d == AnimState.DRAGING;
    }

    public boolean f() {
        return this.d == AnimState.ANIMATING;
    }

    public boolean g() {
        if (this.d == AnimState.ANIMATING) {
            if (this.V.computeScrollOffset()) {
                this.b = (float) this.V.getCurrX();
                this.c = (float) this.V.getCurrY();
            } else {
                i();
            }
        }
        return true;
    }

    private void a(Canvas canvas, PageIndex pageIndex, boolean z) {
        a(canvas, this.k.a(pageIndex, z ? 1 : 0));
    }

    private void a(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.Y);
    }

    private Point a(int i, int i2, TouchArea touchArea, int i3, int i4) {
        if (!(i < 0 || touchArea == TouchArea.rightCenter || touchArea == TouchArea.leftCenter)) {
            double atan2;
            int cos;
            int sin;
            if (touchArea == TouchArea.rightTop) {
                atan2 = Math.atan2((double) i2, (double) i);
                cos = (int) (((double) i3) * Math.cos(atan2));
                sin = ((int) (((double) i3) * Math.sin(atan2))) - ((int) ((this.m > 480 ? 2.1d : 1.0d) * ((atan2 * 180.0d) / 3.141592653589793d)));
                if (i > cos) {
                    i = cos;
                }
                if (i2 > sin) {
                    i2 = sin;
                }
            } else {
                atan2 = Math.atan2((double) (i4 - i2), (double) i);
                cos = (int) (((double) i3) * Math.cos(atan2));
                sin = (i4 - ((int) (((double) i3) * Math.sin(atan2)))) + ((int) ((this.m > 480 ? 2.1d : 1.0d) * ((atan2 * 180.0d) / 3.141592653589793d)));
                if (i > cos) {
                    i = cos;
                }
                if (i2 < sin) {
                    i2 = sin;
                }
            }
        }
        return new Point(i, i2);
    }

    private void b(Canvas canvas, PageIndex pageIndex, boolean z) {
        Bitmap a = this.k.a(pageIndex, z ? 1 : 0);
        Object obj = this.Z == TouchArea.rightTop ? 1 : null;
        int i = this.l;
        int i2 = obj != null ? 0 : this.m;
        int abs = Math.abs(this.l - i);
        int abs2 = Math.abs(this.m - i2);
        if (this.b != -1.0f && this.c != -1.0f) {
            float f;
            this.x = this.b;
            this.y = this.c;
            this.v = Math.max(1.0f, Math.abs(this.x - ((float) i)));
            this.w = Math.max(1.0f, Math.abs(this.y - ((float) i2)));
            if (i == 0) {
                f = (((this.w * this.w) / this.v) + this.v) / 2.0f;
            } else {
                f = ((float) i) - ((((this.w * this.w) / this.v) + this.v) / 2.0f);
            }
            this.t = f;
            if (this.t < 0.0f) {
                this.t = 0.0f;
                this.w = Math.max(1.0f, Math.abs(this.y - ((float) i2)));
            }
            f = this.x - this.t;
            float f2 = this.y - ((float) i2);
            f2 = ((float) Math.sqrt((double) ((f * f) + (f2 * f2)))) / 2.0f;
            if (n == -15584170) {
                n = ao.a(a, Math.min(a.getWidth(), 10), Math.min(a.getHeight(), 10));
            }
            float f3 = (((float) this.l) - this.x) / 14.0f;
            float f4 = (((float) this.l) - this.x) / 8.0f;
            if (Math.abs(((float) i2) - this.y) <= 2.0f) {
                int i3 = ((int) ((this.x + (7.0f * this.t)) - (f2 * 2.0f))) / 8;
                canvas.save();
                this.s = new LinearGradient(((float) i3) - f4, 0.0f, ((float) i3) + f4, 0.0f, -1442840576, 0, TileMode.CLAMP);
                a((float) i3, 0.0f, ((float) i3) + f4, (float) this.m, this.s, canvas);
                canvas.restore();
                canvas.save();
                this.q.rewind();
                this.q.moveTo(0.0f, 0.0f);
                this.q.lineTo(this.x, 0.0f);
                this.q.lineTo(this.x, (float) this.m);
                this.q.lineTo(0.0f, (float) this.m);
                this.q.close();
                canvas.clipPath(this.q);
                canvas.drawBitmap(a, 0.0f, 0.0f, this.Y);
                this.s = new LinearGradient(this.x - f3, 0.0f, this.x, 0.0f, 0, Integer.MIN_VALUE, TileMode.CLAMP);
                a(this.x - f3, 0.0f, this.x, (float) this.m, this.s, canvas);
                canvas.restore();
                canvas.save();
                this.r.rewind();
                this.r.moveTo(this.x, (float) this.m);
                this.r.lineTo((float) i3, (float) this.m);
                this.r.lineTo((float) i3, 0.0f);
                this.r.lineTo(this.x, 0.0f);
                this.r.close();
                canvas.clipPath(this.r);
                canvas.drawColor(n);
                Matrix matrix = new Matrix();
                matrix.postScale(1.0f, -1.0f);
                matrix.postTranslate(this.x - ((float) i), this.y + ((float) i2));
                matrix.postRotate(180.0f, this.x, this.y);
                canvas.drawBitmap(a, matrix, this.X);
                this.s = new LinearGradient(((float) i3) - f4, 0.0f, (float) (i3 + 1), 0.0f, new int[]{0, 855638016, 536870912}, new float[]{0.0f, 0.7f, 1.0f}, TileMode.CLAMP);
                a(((float) i3) - f4, 0.0f, (float) (i3 + 1), (float) this.m, this.s, canvas);
                canvas.restore();
                return;
            }
            Object obj2;
            double d;
            if (obj != null) {
                f = (((this.v * this.v) / this.w) + this.w) / 2.0f;
            } else {
                f = ((float) i2) - ((((this.v * this.v) / this.w) + this.w) / 2.0f);
            }
            this.u = f;
            f = this.x - ((float) i);
            float f5 = this.y - this.u;
            f = ((float) Math.sqrt((double) ((f * f) + (f5 * f5)))) / 2.0f;
            if (obj != null) {
                f = -f;
            }
            this.z = (this.x + ((float) i)) / 2.0f;
            this.A = (this.y + this.u) / 2.0f;
            this.B = (float) i;
            this.C = this.u - f;
            this.D = this.t - f2;
            this.E = (float) i2;
            this.F = (this.x + this.t) / 2.0f;
            this.G = (this.y + ((float) i2)) / 2.0f;
            this.H = (this.x + ((float) (i * 7))) / 8.0f;
            this.I = ((this.y + (7.0f * this.u)) - (f * 2.0f)) / 8.0f;
            this.J = ((this.x + (7.0f * this.t)) - (f2 * 2.0f)) / 8.0f;
            this.K = (this.y + ((float) (i2 * 7))) / 8.0f;
            this.L = this.J - (((this.K - ((float) abs2)) / (this.I - this.K)) * (this.H - this.J));
            this.M = this.J - (((this.K - ((float) i2)) / (this.I - this.K)) * (this.H - this.J));
            this.N = this.I - (((this.H - ((float) i)) / (this.H - this.J)) * (this.I - this.K));
            if (this.I < 0.0f || this.I > ((float) this.m)) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            canvas.save();
            double atan2 = Math.atan2((double) (this.H - this.J), (double) (this.I - this.K));
            if (obj == null) {
                d = atan2 + 3.141592653589793d;
            } else {
                d = atan2;
            }
            this.T.rewind();
            this.T.moveTo(this.M - ((2.0f * f4) / ((float) Math.cos(d))), (float) i2);
            this.T.lineTo(this.M + (f4 / ((float) Math.cos(d))), (float) i2);
            if (obj2 != null) {
                this.T.lineTo(this.L + (f4 / ((float) Math.cos(d))), (float) abs2);
                this.T.lineTo(this.L - ((2.0f * f4) / ((float) Math.cos(d))), (float) abs2);
            } else {
                this.T.lineTo((float) i, this.N - (f4 / ((float) Math.sin(d))));
                this.T.lineTo((float) i, this.N + ((2.0f * f4) / ((float) Math.sin(d))));
            }
            this.T.close();
            this.s = new LinearGradient(this.H - ((2.0f * f4) * ((float) Math.cos(d))), this.I + ((2.0f * f4) * ((float) Math.sin(d))), this.H + (((float) Math.cos(d)) * f4), this.I - (((float) Math.sin(d)) * f4), WebView.NIGHT_MODE_COLOR, 0, TileMode.CLAMP);
            this.U.setShader(this.s);
            canvas.clipPath(this.T);
            canvas.drawPaint(this.U);
            canvas.restore();
            canvas.save();
            this.q.rewind();
            this.q.moveTo(this.x, this.y);
            if (this.C > 0.0f || this.C < ((float) this.m)) {
                this.q.lineTo(this.z, this.A);
                this.q.quadTo((float) i, this.u, this.B, this.C);
                this.q.lineTo((float) i, (float) abs2);
            } else if (this.A > 0.0f || this.A < ((float) this.m)) {
                this.q.lineTo(this.z, this.A);
                this.q.quadTo((float) i, this.u, this.B, this.C);
            } else {
                this.q.lineTo(((this.x * this.A) - (this.z * this.y)) / (this.A - this.y), 0.0f);
            }
            this.q.lineTo((float) abs, (float) abs2);
            this.q.lineTo((float) abs, (float) i2);
            this.q.lineTo(this.D, this.E);
            this.q.quadTo(this.t, (float) i2, this.F, this.G);
            this.q.close();
            canvas.clipPath(this.q);
            canvas.drawBitmap(a, 0.0f, 0.0f, this.Y);
            canvas.save();
            atan2 = Math.atan2((double) (this.x - this.F), (double) (this.y - this.G));
            double d2 = 3.141592653589793d - atan2;
            if (obj != null) {
                atan2 = 0.7853981633974483d - atan2;
            } else {
                atan2 += 0.7853981633974483d;
            }
            if (obj != null) {
                this.O = (float) (((double) this.x) - (((double) f3) * Math.sin(atan2)));
            } else {
                this.O = (float) (((double) this.x) + (((double) f3) * Math.sin(atan2)));
            }
            this.P = (float) ((Math.cos(atan2) * ((double) f3)) + ((double) this.y));
            this.Q = this.F - (((this.G - ((float) i2)) / (this.y - this.G)) * (this.x - this.F));
            this.T.rewind();
            this.T.moveTo(this.O, this.P);
            this.T.lineTo(this.x, this.y);
            this.T.lineTo(this.Q, (float) i2);
            this.T.lineTo(this.Q - (f3 / ((float) Math.abs(Math.cos(d2)))), (float) i2);
            this.T.close();
            if (obj != null) {
                this.s = new LinearGradient(this.O, this.P, (float) (((double) this.O) - (((double) f3) * Math.cos(d2))), (float) (((double) this.P) - (((double) f3) * Math.sin(d2))), 0, Integer.MIN_VALUE, TileMode.CLAMP);
            } else {
                this.s = new LinearGradient(this.O, this.P, (float) (((double) this.O) + (((double) f3) * Math.cos(d2))), (float) (((double) this.P) + (((double) f3) * Math.sin(d2))), 0, Integer.MIN_VALUE, TileMode.CLAMP);
            }
            this.U.setShader(this.s);
            canvas.clipPath(this.T);
            canvas.drawPaint(this.U);
            canvas.restore();
            this.R = this.z - (((this.A - ((float) abs2)) / (this.y - this.A)) * (this.x - this.z));
            this.S = this.y - (((this.x - ((float) i)) / (this.x - this.z)) * (this.y - this.A));
            this.T.rewind();
            this.T.moveTo(this.O, this.P);
            this.T.lineTo(this.x, this.y);
            if (obj2 != null) {
                this.T.lineTo(this.R, (float) abs2);
                this.T.lineTo((float) (((double) this.R) + (((double) f3) / Math.sin(d2))), (float) abs2);
            } else {
                this.T.lineTo((float) i, this.S);
                this.T.lineTo((float) i, (float) (((double) this.S) - (((double) f3) / Math.cos(d2))));
            }
            this.T.close();
            this.s = new LinearGradient(this.O, this.P, (float) (((double) this.O) - (((double) f3) * Math.sin(d2))), (float) (((double) this.P) + (((double) f3) * Math.cos(d2))), 0, Integer.MIN_VALUE, TileMode.CLAMP);
            this.U.setShader(this.s);
            canvas.clipPath(this.T);
            canvas.drawPaint(this.U);
            canvas.restore();
            canvas.save();
            this.r.rewind();
            this.r.moveTo(this.x, this.y);
            if (this.A < 0.0f || this.A > ((float) this.m)) {
                this.r.lineTo((((((float) abs2) * (this.z - this.x)) + (this.x * this.A)) - (this.z * this.y)) / (this.A - this.y), (float) abs2);
                this.r.lineTo((((((float) abs2) * (this.H - this.J)) + (this.J * this.I)) - (this.H * this.K)) / (this.I - this.K), (float) abs2);
            } else {
                this.r.lineTo(this.z, this.A);
                this.r.quadTo((this.x + ((float) (i * 3))) / 4.0f, (this.y + (3.0f * this.u)) / 4.0f, this.H, this.I);
            }
            this.r.lineTo(this.J, this.K);
            this.r.quadTo((this.x + (3.0f * this.t)) / 4.0f, (this.y + ((float) (i2 * 3))) / 4.0f, this.F, this.G);
            this.r.close();
            canvas.clipPath(this.r);
            canvas.drawColor(n);
            Matrix matrix2 = new Matrix();
            matrix2.postScale(1.0f, -1.0f);
            matrix2.postTranslate(this.x - ((float) i), this.y + ((float) i2));
            if (obj != null) {
                f = -57.295647f * ((float) Math.atan2((double) (this.x - ((float) i)), (double) (this.y - this.u)));
            } else {
                f = 180.0f - (57.295647f * ((float) Math.atan2((double) (this.x - ((float) i)), (double) (this.y - this.u))));
            }
            matrix2.postRotate(f, this.x, this.y);
            canvas.drawBitmap(a, matrix2, this.X);
            this.s = new LinearGradient(this.H - (((float) Math.cos(d)) * f4), this.I + (((float) Math.sin(d)) * f4), this.H, this.I, new int[]{0, 855638016, 536870912}, new float[]{0.0f, 0.7f, 1.0f}, TileMode.CLAMP);
            this.T.rewind();
            this.T.moveTo(this.M - (f4 / ((float) Math.cos(d))), (float) i2);
            this.T.lineTo(this.M, (float) i2);
            if (obj2 != null) {
                this.T.lineTo(this.L, (float) abs2);
                this.T.lineTo(this.L - (f4 / ((float) Math.cos(d))), (float) abs2);
            } else {
                this.T.lineTo((float) i, this.N);
                this.T.lineTo((float) i, this.N + (f4 / ((float) Math.sin(d))));
            }
            this.T.close();
            this.U.setShader(this.s);
            canvas.clipPath(this.T);
            canvas.drawPaint(this.U);
            canvas.restore();
        }
    }

    private void a(float f, float f2, float f3, float f4, LinearGradient linearGradient, Canvas canvas) {
        this.T.rewind();
        this.T.moveTo(f, f2);
        this.T.lineTo(f, f4);
        this.T.lineTo(f3, f4);
        this.T.lineTo(f3, f2);
        this.T.close();
        this.U.setShader(linearGradient);
        canvas.clipPath(this.T);
        canvas.drawPaint(this.U);
    }

    public boolean a(Canvas canvas) {
        if (h() || e()) {
            canvas.save();
            switch (this.h) {
                case next:
                    a(canvas, PageIndex.next, true);
                    c(canvas);
                    canvas.clipRect(0, 0, canvas.getWidth() - c(), b(canvas));
                    b(canvas, PageIndex.current, true);
                    break;
                case previous:
                    a(canvas, PageIndex.current, true);
                    c(canvas);
                    canvas.clipRect(0, 0, canvas.getWidth() - c(), b(canvas));
                    b(canvas, PageIndex.previous, true);
                    break;
            }
            if (this.d == AnimState.ANIMATE_END) {
                i();
            }
            canvas.restore();
        } else {
            a(canvas, PageIndex.current, false);
            c(canvas);
            d(canvas);
        }
        return true;
    }

    public PageIndex a(float f, float f2) {
        if (((float) this.e.x) < f) {
            this.g = Direction.leftToRight;
            this.Z = TouchArea.leftCenter;
            a(PageIndex.previous);
            this.c = -1.0f;
            this.b = -1.0f;
            this.p = true;
        }
        if (((float) this.e.x) > f) {
            this.g = Direction.rightToLeft;
            int i = this.m;
            int i2 = i / 3;
            int i3 = this.e.y;
            if (i3 <= i2) {
                this.Z = TouchArea.rightTop;
            } else if (i3 >= i - i2) {
                this.Z = TouchArea.rightButtom;
            } else {
                this.Z = TouchArea.rightCenter;
            }
            a(PageIndex.next);
            this.c = -1.0f;
            this.b = -1.0f;
            this.p = true;
        }
        return this.h;
    }

    public void b(float f, float f2) {
        this.e.set((int) f, (int) f2);
        this.c = -1.0f;
        this.b = -1.0f;
        this.aa = false;
        this.p = false;
    }

    private void k() {
        int i = this.l;
        int i2 = this.m;
        int i3 = this.e.x;
        int i4 = this.e.y;
        switch (ab.a((float) i3, (float) i4, i, i2)) {
            case 0:
                this.g = Direction.rightToLeft;
                a(PageIndex.next);
                i = i2 / 3;
                if (i4 <= i) {
                    this.Z = TouchArea.rightTop;
                    return;
                } else if (i4 >= i2 - i) {
                    this.Z = TouchArea.rightButtom;
                    return;
                } else {
                    this.Z = TouchArea.rightCenter;
                    return;
                }
            case 1:
                this.g = Direction.leftToRight;
                a(PageIndex.previous);
                this.Z = TouchArea.leftCenter;
                this.e = new Point(-i, i2);
                return;
            default:
                return;
        }
    }

    public void b(int i, int i2) {
        int i3;
        int i4;
        int i5 = this.l;
        int i6 = this.m;
        if (this.Z == TouchArea.leftCenter) {
            i3 = i - (i5 / 4);
            i4 = i6;
        } else if (this.Z == TouchArea.rightCenter) {
            i4 = i6;
            i3 = i;
        } else {
            i3 = i < 0 ? 0 : i > i5 ? i5 : i;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > i6) {
                i2 = i6;
            }
            i4 = i2;
        }
        Point a = a(i3, i4, this.Z, i5, i6);
        this.b = (float) a.x;
        this.c = (float) a.y;
        this.d = AnimState.DRAGING;
        this.aa = true;
        if (this.o != null) {
            this.o.c();
        }
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        int i6;
        if (!this.p) {
            k();
        }
        if (this.d == AnimState.ANIMATING) {
            i();
        }
        this.a = mode;
        int i7 = this.l;
        int i8 = this.m;
        if (this.g == Direction.leftToRight) {
            if (this.aa) {
                i6 = (int) this.b;
            } else {
                i6 = -i7;
            }
            this.e = new Point(i6, i8);
        } else if (this.g != Direction.rightToLeft) {
            this.e = new Point(i, i2);
        } else if (this.Z == TouchArea.rightCenter) {
            this.e = new Point(this.aa ? (int) this.b : i7, i8);
        } else {
            Point a = a(i, i2, this.Z, i7, i8);
            i6 = a.x;
            int i9 = a.y;
            if (this.aa) {
                i6 = (int) this.b;
            }
            if (this.aa) {
                i9 = (int) this.c;
            }
            this.e = new Point(i6, i9);
        }
        if (mode == Mode.AutoScrollingForward || mode == Mode.ForceScrolling) {
            i6 = -i7;
        } else {
            i6 = i7;
        }
        if (this.g == Direction.leftToRight) {
            this.f = new Point(-i6, i8);
        } else if (this.g != Direction.rightToLeft) {
            this.f = new Point(i3, i4);
        } else if (this.Z == TouchArea.rightTop) {
            this.f = new Point(i6, 0);
        } else {
            this.f = new Point(i6, i8);
        }
        this.d = AnimState.ANIMATING;
        this.V.startScroll(this.e.x, this.e.y, this.f.x - this.e.x, this.f.y - this.e.y, (int) ((((float) Math.abs(this.f.x - this.e.x)) / ((float) (i7 * 2))) * ((float) i5)));
        if (this.o != null) {
            this.o.a();
        }
    }

    public void i() {
        super.i();
        this.Z = TouchArea.None;
        this.d = AnimState.READY;
        this.c = -1.0f;
        this.b = -1.0f;
        this.V.abortAnimation();
        if (!this.V.isFinished()) {
            this.V.forceFinished(true);
        }
        this.aa = false;
        if (this.o != null) {
            this.o.b();
        }
    }

    public int a(b bVar) {
        return bVar.f();
    }

    public int b(b bVar) {
        return bVar.e();
    }
}
