package com.qq.reader.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.reflect.InvocationTargetException;

public class FlipLayout extends ViewGroup {
    private Rect A;
    private Rect B;
    private boolean C;
    private boolean D;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private long g;
    private boolean h;
    private float i;
    private boolean j;
    private VelocityTracker k;
    private com.qq.reader.view.FlipContainerLayout.b l;
    private a m;
    private b n;
    private View o;
    private View p;
    private View q;
    private boolean r;
    private int s;
    private final Interpolator t;
    private Scroller u;
    private int v;
    private int w;
    private int x;
    private int y;
    private Rect z;

    public interface a {
        void a();

        void a(float f);

        void b();

        void b(float f);

        void c();

        void c(float f);

        void d();

        void e();

        void f();
    }

    public interface b {
        boolean a();
    }

    public FlipLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public FlipLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 101;
        this.c = 100;
        this.d = 0;
        this.e = 0;
        this.f = false;
        this.g = 0;
        this.h = false;
        this.i = 0.0f;
        this.j = true;
        this.r = false;
        this.s = 100;
        this.t = new Interpolator(this) {
            final /* synthetic */ FlipLayout a;

            {
                this.a = r1;
            }

            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
            }
        };
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = new Rect();
        this.A = new Rect();
        this.B = new Rect();
        this.C = false;
        this.D = false;
        d();
    }

    public FlipLayout(Context context) {
        this(context, null);
    }

    private void d() {
        this.u = new Scroller(getContext(), this.t);
        this.a = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
    }

    public void setHeadView(View view) {
        if (view != null) {
            this.p = view;
            this.r = true;
            addView(this.p);
        }
    }

    public void setHeadViewWithoutAdd(View view) {
        this.p = view;
        this.r = false;
    }

    public void setBottomViewWithoutAdd(View view) {
        this.q = view;
    }

    public void a(com.qq.reader.view.FlipContainerLayout.b bVar) {
        this.l = bVar;
        if (bVar != null) {
            View c = bVar.c();
            this.o = c;
            if (c != null) {
                c.setBackgroundColor(-7829368);
                addView(c, new LayoutParams(-1, -1));
            }
        }
    }

    public void setFlipListener(a aVar) {
        this.m = aVar;
    }

    public void setOnTapListener(b bVar) {
        this.n = bVar;
    }

    public void setRightWidth(int i) {
        this.d = i;
    }

    public void setLeftWidth(int i) {
        this.e = i;
    }

    public void a() {
        if (!this.f) {
            this.f = true;
            this.j = false;
            this.c = 102;
            this.g = SystemClock.uptimeMillis();
            a(this.d, getScrollY(), 0);
        }
    }

    public void b() {
        if (!this.f) {
            this.f = true;
            this.j = false;
            this.c = 103;
            this.g = SystemClock.uptimeMillis();
            a(-this.e, getScrollY(), 0);
        }
    }

    public void c() {
        if (!this.f) {
            if (getLeft() > 0) {
                this.f = true;
                this.c = 101;
                this.j = false;
                this.g = SystemClock.uptimeMillis();
            } else if (getLeft() < 0) {
                this.f = true;
                this.c = 101;
                this.j = false;
                this.g = SystemClock.uptimeMillis();
            }
            a(0, 0, (int) this.i);
        }
    }

    void a(int i, int i2, int i3) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        int i6 = (int) (600.0f * getResources().getDisplayMetrics().density);
        Math.abs(i3);
        this.u.startScroll(scrollX, scrollY, i4, i5, (Math.abs(i - getScrollX()) * ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) / getMeasuredWidth());
        invalidate();
    }

    public void computeScroll() {
        if (!this.u.isFinished()) {
            if (this.m != null) {
                float abs = Math.abs(((float) (getScrollX() - this.u.getStartX())) / ((float) (this.u.getFinalX() - this.u.getStartX())));
                if (getScrollX() < 0) {
                    if (this.u.getFinalX() == 0) {
                        if (getScrollX() == (-this.e)) {
                            this.m.c();
                        } else {
                            this.m.c(abs);
                        }
                    } else if (this.u.getFinalX() == (-this.e)) {
                        this.m.a(abs);
                    }
                    if (getScrollX() == (-this.e)) {
                        this.b = 103;
                    }
                } else if (getScrollX() > 0) {
                    if (this.u.getFinalX() == 0) {
                        if (getScrollX() == this.d) {
                            this.m.c();
                        } else {
                            this.m.c(abs);
                        }
                    } else if (this.u.getFinalX() == this.d) {
                        this.m.b(abs);
                    }
                    if (getScrollX() == this.d) {
                        this.b = 102;
                    }
                } else {
                    this.b = 101;
                    if (this.u.getFinalX() == this.d) {
                        this.m.e();
                    }
                    if (this.u.getFinalX() == (-this.e)) {
                        this.m.f();
                    }
                }
            }
            if (this.u.computeScrollOffset()) {
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.u.getCurrX();
                int currY = this.u.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                }
                invalidate();
            }
        } else if (getScrollX() == 0) {
            h();
        } else if (getScrollX() == (-this.e)) {
            j();
        } else if (getScrollX() == this.d) {
            i();
        }
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    private void e() {
        this.j = false;
        this.s = 100;
        this.g = SystemClock.uptimeMillis();
        if (getScrollX() > 0) {
            this.f = true;
            if (this.i > 0.0f) {
                this.c = 101;
                a(0, 0, (int) this.i);
                return;
            }
            this.c = 102;
            a(this.d, getScrollY(), (int) this.i);
        } else if (getScrollX() < 0) {
            this.f = true;
            if (this.i > 0.0f) {
                this.c = 103;
                a(-this.e, getScrollY(), (int) this.i);
                return;
            }
            this.c = 101;
            a(0, getScrollY(), (int) this.i);
        } else {
            this.j = true;
        }
    }

    private void f() {
        try {
            if (!((Boolean) getClass().getMethod("isHardwareAccelerated", new Class[0]).invoke(this, (Object[]) null)).booleanValue()) {
                buildDrawingCache();
            }
        } catch (NoSuchMethodException e) {
            buildDrawingCache();
        } catch (InvocationTargetException e2) {
        } catch (IllegalAccessException e3) {
        }
    }

    private void g() {
        if (this.m != null) {
            this.m.c();
        }
    }

    private void h() {
        this.f = false;
        this.j = true;
        if (this.m != null) {
            this.m.d();
        }
    }

    private void i() {
        this.f = false;
        this.j = true;
        if (this.m != null) {
            this.m.b();
        }
    }

    private void j() {
        this.f = false;
        this.j = true;
        if (this.m != null) {
            this.m.a();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.D = false;
                this.v = x;
                this.w = y;
                this.x = x;
                this.y = y;
                if (this.b == 102 || this.b == 103) {
                    return true;
                }
                if (this.p != null) {
                    this.p.getHitRect(this.z);
                } else {
                    this.z.setEmpty();
                }
                if (this.q != null) {
                    this.q.getHitRect(this.B);
                } else {
                    this.B.setEmpty();
                }
                if (this.o != null) {
                    this.o.getHitRect(this.A);
                }
                this.s = 100;
                if (this.z.contains(x, y)) {
                    this.s = com.tencent.qalsdk.base.a.r;
                }
                if (this.B.contains(x, y)) {
                    this.s = com.tencent.qalsdk.base.a.q;
                }
                return false;
            case 1:
            case 3:
                if (this.b == 101) {
                    if (this.n != null && this.n.a()) {
                        if (this.m != null) {
                            this.m.e();
                        }
                        a();
                        break;
                    }
                }
                this.C = true;
                return true;
            case 2:
                this.D = true;
                if (this.s == com.tencent.qalsdk.base.a.r && Math.abs(x - this.v) > Math.abs(y - this.w)) {
                    this.C = false;
                    return false;
                } else if (this.s == com.tencent.qalsdk.base.a.q) {
                    this.C = false;
                    return false;
                } else if (!this.A.contains(x, y) || Math.abs(x - this.v) <= Math.abs(y - this.w) || Math.abs(x - this.x) <= this.a) {
                    this.s = 100;
                    this.C = false;
                    return false;
                } else if (this.l != null && x - this.v > 0 && this.l.a()) {
                    this.C = false;
                    return false;
                } else if (this.l == null || x - this.v >= 0 || !this.l.b()) {
                    this.v = x;
                    this.w = y;
                    this.C = true;
                    return true;
                } else {
                    this.C = false;
                    return false;
                }
        }
        this.s = 100;
        this.C = super.onInterceptTouchEvent(motionEvent);
        return this.C;
    }

    void a(int i, int i2) {
        this.v = i;
        this.w = i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
        r8 = this;
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2 = 1;
        r7 = 0;
        r1 = 0;
        r0 = r8.C;
        if (r0 != 0) goto L_0x000f;
    L_0x0009:
        r0 = r8.b;
        if (r0 != r5) goto L_0x000f;
    L_0x000d:
        r1 = r2;
    L_0x000e:
        return r1;
    L_0x000f:
        r0 = r8.h;
        if (r0 != 0) goto L_0x000e;
    L_0x0013:
        r0 = r8.k;
        if (r0 != 0) goto L_0x001d;
    L_0x0017:
        r0 = android.view.VelocityTracker.obtain();
        r8.k = r0;
    L_0x001d:
        r0 = r8.j;
        if (r0 != 0) goto L_0x0023;
    L_0x0021:
        r1 = r2;
        goto L_0x000e;
    L_0x0023:
        r0 = r8.k;
        r0.addMovement(r9);
        r0 = r9.getAction();
        r0 = r0 & 255;
        r3 = r9.getX();
        r3 = (int) r3;
        r4 = r9.getY();
        r4 = (int) r4;
        switch(r0) {
            case 0: goto L_0x003d;
            case 1: goto L_0x014e;
            case 2: goto L_0x0045;
            case 3: goto L_0x014e;
            default: goto L_0x003b;
        };
    L_0x003b:
        r1 = r2;
        goto L_0x000e;
    L_0x003d:
        r8.v = r3;
        r8.w = r4;
        r8.f();
        goto L_0x003b;
    L_0x0045:
        r0 = r8.k;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0.computeCurrentVelocity(r5);
        r0 = r8.v;
        r0 = r0 - r3;
        r5 = r8.k;
        r5 = r5.getXVelocity();
        r8.i = r5;
        r5 = r8.getScrollX();
        if (r5 <= 0) goto L_0x0081;
    L_0x005d:
        if (r0 <= 0) goto L_0x0081;
    L_0x005f:
        r5 = r8.getScrollX();
        r6 = r8.d;
        if (r5 != r6) goto L_0x0068;
    L_0x0067:
        r0 = r1;
    L_0x0068:
        r5 = r8.getScrollX();
        r5 = r5 + r0;
        r6 = r8.d;
        if (r5 < r6) goto L_0x0078;
    L_0x0071:
        r0 = r8.d;
        r5 = r8.getScrollX();
        r0 = r0 - r5;
    L_0x0078:
        r5 = r8.m;
        if (r5 == 0) goto L_0x0081;
    L_0x007c:
        r5 = r8.m;
        r5.b(r7);
    L_0x0081:
        r5 = r8.getScrollX();
        if (r5 >= 0) goto L_0x00ae;
    L_0x0087:
        if (r0 >= 0) goto L_0x00ae;
    L_0x0089:
        r5 = r8.getScrollX();
        r6 = r8.e;
        r6 = -r6;
        if (r5 != r6) goto L_0x0093;
    L_0x0092:
        r0 = r1;
    L_0x0093:
        r5 = r8.getScrollX();
        r5 = r5 + r0;
        r6 = r8.e;
        r6 = -r6;
        if (r5 >= r6) goto L_0x00a5;
    L_0x009d:
        r0 = r8.e;
        r0 = -r0;
        r5 = r8.getScrollX();
        r0 = r0 - r5;
    L_0x00a5:
        r5 = r8.m;
        if (r5 == 0) goto L_0x00ae;
    L_0x00a9:
        r5 = r8.m;
        r5.a(r7);
    L_0x00ae:
        r5 = r8.getScrollX();
        if (r5 >= 0) goto L_0x00de;
    L_0x00b4:
        if (r0 <= 0) goto L_0x00de;
    L_0x00b6:
        r5 = r8.getScrollX();
        if (r5 != 0) goto L_0x00bd;
    L_0x00bc:
        r0 = r1;
    L_0x00bd:
        r5 = r8.getScrollX();
        r5 = r5 + r0;
        if (r5 <= 0) goto L_0x00c9;
    L_0x00c4:
        r0 = r8.getScrollX();
        r0 = -r0;
    L_0x00c9:
        r5 = r8.m;
        if (r5 == 0) goto L_0x00de;
    L_0x00cd:
        r5 = r8.getScrollX();
        r6 = r8.e;
        r6 = -r6;
        if (r5 != r6) goto L_0x00d9;
    L_0x00d6:
        r8.g();
    L_0x00d9:
        r5 = r8.m;
        r5.c(r7);
    L_0x00de:
        r5 = r8.getScrollX();
        if (r5 <= 0) goto L_0x010d;
    L_0x00e4:
        if (r0 >= 0) goto L_0x010d;
    L_0x00e6:
        r5 = r8.getScrollX();
        if (r5 != 0) goto L_0x00ed;
    L_0x00ec:
        r0 = r1;
    L_0x00ed:
        r5 = r8.getScrollX();
        r5 = r5 + r0;
        if (r5 >= 0) goto L_0x00f9;
    L_0x00f4:
        r0 = r8.getScrollX();
        r0 = -r0;
    L_0x00f9:
        r5 = r8.m;
        if (r5 == 0) goto L_0x010d;
    L_0x00fd:
        r5 = r8.getScrollX();
        r6 = r8.d;
        if (r5 != r6) goto L_0x0108;
    L_0x0105:
        r8.g();
    L_0x0108:
        r5 = r8.m;
        r5.c(r7);
    L_0x010d:
        r5 = r8.getScrollX();
        if (r5 != 0) goto L_0x01a1;
    L_0x0113:
        if (r0 >= 0) goto L_0x0127;
    L_0x0115:
        r5 = r8.m;
        if (r5 == 0) goto L_0x011e;
    L_0x0119:
        r5 = r8.m;
        r5.f();
    L_0x011e:
        r5 = r8.l;
        r5 = r5.a();
        if (r5 == 0) goto L_0x0127;
    L_0x0126:
        r0 = r1;
    L_0x0127:
        if (r0 <= 0) goto L_0x01a1;
    L_0x0129:
        r5 = r8.m;
        if (r5 == 0) goto L_0x0132;
    L_0x012d:
        r5 = r8.m;
        r5.e();
    L_0x0132:
        r5 = r8.l;
        r5 = r5.b();
        if (r5 == 0) goto L_0x01a1;
    L_0x013a:
        r0 = r8.getScrollX();
        r0 = r0 + r1;
        r0 = (float) r0;
        r0 = (int) r0;
        r1 = r8.getScrollY();
        r8.scrollTo(r0, r1);
        r8.v = r3;
        r8.w = r4;
        goto L_0x003b;
    L_0x014e:
        r0 = r8.b;
        if (r0 != r5) goto L_0x015b;
    L_0x0152:
        r8.v = r1;
        r8.w = r1;
        r8.e();
        goto L_0x003b;
    L_0x015b:
        r0 = r8.getScrollX();
        if (r0 >= 0) goto L_0x016d;
    L_0x0161:
        r0 = r8.i;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 <= 0) goto L_0x016d;
    L_0x0167:
        r8.b();
    L_0x016a:
        r1 = r2;
        goto L_0x000e;
    L_0x016d:
        r0 = r8.getScrollX();
        if (r0 >= 0) goto L_0x017d;
    L_0x0173:
        r0 = r8.i;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 >= 0) goto L_0x017d;
    L_0x0179:
        r8.c();
        goto L_0x016a;
    L_0x017d:
        r0 = r8.getScrollX();
        if (r0 <= 0) goto L_0x018d;
    L_0x0183:
        r0 = r8.i;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 <= 0) goto L_0x018d;
    L_0x0189:
        r8.c();
        goto L_0x016a;
    L_0x018d:
        r0 = r8.getScrollX();
        if (r0 <= 0) goto L_0x019d;
    L_0x0193:
        r0 = r8.i;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 >= 0) goto L_0x019d;
    L_0x0199:
        r8.a();
        goto L_0x016a;
    L_0x019d:
        r8.c();
        goto L_0x016a;
    L_0x01a1:
        r1 = r0;
        goto L_0x013a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.FlipLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        MeasureSpec.getMode(i);
        MeasureSpec.getMode(i2);
        int i3 = 0;
        if (this.p != null && this.r) {
            measureChild(this.p, size + 1073741824, size2 + 1073741824);
            i3 = this.p.getMeasuredHeight();
        }
        if (this.o != null) {
            measureChild(this.o, size + 1073741824, (size2 - i3) + 1073741824);
        }
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        if (VERSION.SDK_INT < 11) {
            i2 = 0;
        }
        if (this.p != null && this.r) {
            this.p.layout(i, i2, this.p.getMeasuredWidth() + i, this.p.getMeasuredHeight() + i2);
        }
        i5 = this.p != null ? this.p.getMeasuredHeight() + i2 : i2;
        if (this.r) {
            i2 = i5;
        }
        if (this.o != null) {
            this.o.layout(i, i2, this.o.getMeasuredWidth() + i, this.o.getMeasuredHeight() + i2);
        }
    }

    public int getPosition() {
        return -getScrollX();
    }

    public int getCurrentState() {
        return this.b;
    }
}
