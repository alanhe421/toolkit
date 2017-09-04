package android.support.v4.widget;

import android.content.Context;
import android.support.v4.view.n;
import android.support.v4.view.x;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.Arrays;

/* compiled from: ViewDragHelper */
public class k {
    private static final Interpolator v = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private int a;
    private int b;
    private int c = -1;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private h q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w = new Runnable(this) {
        final /* synthetic */ k a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c(0);
        }
    };

    /* compiled from: ViewDragHelper */
    public static abstract class a {
        public abstract boolean a(View view, int i);

        public void a(int i) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public void b(View view, int i) {
        }

        public void a(View view, float f, float f2) {
        }

        public void a(int i, int i2) {
        }

        public boolean b(int i) {
            return false;
        }

        public void b(int i, int i2) {
        }

        public int c(int i) {
            return i;
        }

        public int a(View view) {
            return 0;
        }

        public int b(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }
    }

    public static k a(ViewGroup viewGroup, a aVar) {
        return new k(viewGroup.getContext(), viewGroup, aVar);
    }

    public static k a(ViewGroup viewGroup, float f, a aVar) {
        k a = a(viewGroup, aVar);
        a.b = (int) (((float) a.b) * (1.0f / f));
        return a;
    }

    private k(Context context, ViewGroup viewGroup, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.u = viewGroup;
            this.r = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.b = viewConfiguration.getScaledTouchSlop();
            this.m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.q = h.a(context, v);
        }
    }

    public void a(float f) {
        this.n = f;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.p = i;
    }

    public int b() {
        return this.o;
    }

    public void a(View view, int i) {
        if (view.getParent() != this.u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
        }
        this.s = view;
        this.c = i;
        this.r.b(view, i);
        c(1);
    }

    public View c() {
        return this.s;
    }

    public int d() {
        return this.b;
    }

    public void e() {
        this.c = -1;
        g();
        if (this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    public void f() {
        e();
        if (this.a == 2) {
            int b = this.q.b();
            int c = this.q.c();
            this.q.h();
            int b2 = this.q.b();
            int c2 = this.q.c();
            this.r.a(this.s, b2, c2, b2 - b, c2 - c);
        }
        c(0);
    }

    public boolean a(View view, int i, int i2) {
        this.s = view;
        this.c = -1;
        boolean a = a(i, i2, 0, 0);
        if (!(a || this.a != 0 || this.s == null)) {
            this.s = null;
        }
        return a;
    }

    public boolean a(int i, int i2) {
        if (this.t) {
            return a(i, i2, (int) x.a(this.l, this.c), (int) x.b(this.l, this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.h();
            c(0);
            return false;
        }
        this.q.a(left, top, i5, i6, a(this.s, i5, i6, i3, i4));
        c(2);
        return true;
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        int b = b(i3, (int) this.n, (int) this.m);
        int b2 = b(i4, (int) this.n, (int) this.m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) a(i2, b2, this.r.b(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) a(i, b, this.r.a(view)))));
    }

    private int a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        int i4 = width / 2;
        float b = (b(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(b / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f);
        }
        return Math.min(width, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE);
    }

    private int b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    private float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    private float b(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean a(boolean z) {
        if (this.a == 2) {
            int i;
            boolean g = this.q.g();
            int b = this.q.b();
            int c = this.q.c();
            int left = b - this.s.getLeft();
            int top = c - this.s.getTop();
            if (left != 0) {
                this.s.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.s.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.r.a(this.s, b, c, left, top);
            }
            if (g && b == this.q.d() && c == this.q.e()) {
                this.q.h();
                i = 0;
            } else {
                boolean z2 = g;
            }
            if (i == 0) {
                if (z) {
                    this.u.post(this.w);
                } else {
                    c(0);
                }
            }
        }
        return this.a == 2;
    }

    private void a(float f, float f2) {
        this.t = true;
        this.r.a(this.s, f, f2);
        this.t = false;
        if (this.a == 1) {
            c(0);
        }
    }

    private void g() {
        if (this.d != null) {
            Arrays.fill(this.d, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
    }

    private void e(int i) {
        if (this.d != null) {
            this.d[i] = 0.0f;
            this.e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k &= (1 << i) ^ -1;
        }
    }

    private void f(int i) {
        if (this.d == null || this.d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.d != null) {
                System.arraycopy(this.d, 0, obj, 0, this.d.length);
                System.arraycopy(this.e, 0, obj2, 0, this.e.length);
                System.arraycopy(this.f, 0, obj3, 0, this.f.length);
                System.arraycopy(this.g, 0, obj4, 0, this.g.length);
                System.arraycopy(this.h, 0, obj5, 0, this.h.length);
                System.arraycopy(this.i, 0, obj6, 0, this.i.length);
                System.arraycopy(this.j, 0, obj7, 0, this.j.length);
            }
            this.d = obj;
            this.e = obj2;
            this.f = obj3;
            this.g = obj4;
            this.h = obj5;
            this.i = obj6;
            this.j = obj7;
        }
    }

    private void a(float f, float f2, int i) {
        f(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        fArr = this.e;
        this.g[i] = f2;
        fArr[i] = f2;
        this.h[i] = e((int) f, (int) f2);
        this.k |= 1 << i;
    }

    private void c(MotionEvent motionEvent) {
        int c = n.c(motionEvent);
        for (int i = 0; i < c; i++) {
            int b = n.b(motionEvent, i);
            float c2 = n.c(motionEvent, i);
            float d = n.d(motionEvent, i);
            this.f[b] = c2;
            this.g[b] = d;
        }
    }

    public boolean b(int i) {
        return (this.k & (1 << i)) != 0;
    }

    void c(int i) {
        this.u.removeCallbacks(this.w);
        if (this.a != i) {
            this.a = i;
            this.r.a(i);
            if (this.a == 0) {
                this.s = null;
            }
        }
    }

    boolean b(View view, int i) {
        if (view == this.s && this.c == i) {
            return true;
        }
        if (view == null || !this.r.a(view, i)) {
            return false;
        }
        this.c = i;
        a(view, i);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.MotionEvent r14) {
        /*
        r13 = this;
        r0 = android.support.v4.view.n.a(r14);
        r1 = android.support.v4.view.n.b(r14);
        if (r0 != 0) goto L_0x000d;
    L_0x000a:
        r13.e();
    L_0x000d:
        r2 = r13.l;
        if (r2 != 0) goto L_0x0017;
    L_0x0011:
        r2 = android.view.VelocityTracker.obtain();
        r13.l = r2;
    L_0x0017:
        r2 = r13.l;
        r2.addMovement(r14);
        switch(r0) {
            case 0: goto L_0x0026;
            case 1: goto L_0x0121;
            case 2: goto L_0x0092;
            case 3: goto L_0x0121;
            case 4: goto L_0x001f;
            case 5: goto L_0x005a;
            case 6: goto L_0x0118;
            default: goto L_0x001f;
        };
    L_0x001f:
        r0 = r13.a;
        r1 = 1;
        if (r0 != r1) goto L_0x0126;
    L_0x0024:
        r0 = 1;
    L_0x0025:
        return r0;
    L_0x0026:
        r0 = r14.getX();
        r1 = r14.getY();
        r2 = 0;
        r2 = android.support.v4.view.n.b(r14, r2);
        r13.a(r0, r1, r2);
        r0 = (int) r0;
        r1 = (int) r1;
        r0 = r13.d(r0, r1);
        r1 = r13.s;
        if (r0 != r1) goto L_0x0048;
    L_0x0040:
        r1 = r13.a;
        r3 = 2;
        if (r1 != r3) goto L_0x0048;
    L_0x0045:
        r13.b(r0, r2);
    L_0x0048:
        r0 = r13.h;
        r0 = r0[r2];
        r1 = r13.p;
        r1 = r1 & r0;
        if (r1 == 0) goto L_0x001f;
    L_0x0051:
        r1 = r13.r;
        r3 = r13.p;
        r0 = r0 & r3;
        r1.a(r0, r2);
        goto L_0x001f;
    L_0x005a:
        r0 = android.support.v4.view.n.b(r14, r1);
        r2 = android.support.v4.view.n.c(r14, r1);
        r1 = android.support.v4.view.n.d(r14, r1);
        r13.a(r2, r1, r0);
        r3 = r13.a;
        if (r3 != 0) goto L_0x007f;
    L_0x006d:
        r1 = r13.h;
        r1 = r1[r0];
        r2 = r13.p;
        r2 = r2 & r1;
        if (r2 == 0) goto L_0x001f;
    L_0x0076:
        r2 = r13.r;
        r3 = r13.p;
        r1 = r1 & r3;
        r2.a(r1, r0);
        goto L_0x001f;
    L_0x007f:
        r3 = r13.a;
        r4 = 2;
        if (r3 != r4) goto L_0x001f;
    L_0x0084:
        r2 = (int) r2;
        r1 = (int) r1;
        r1 = r13.d(r2, r1);
        r2 = r13.s;
        if (r1 != r2) goto L_0x001f;
    L_0x008e:
        r13.b(r1, r0);
        goto L_0x001f;
    L_0x0092:
        r0 = r13.d;
        if (r0 == 0) goto L_0x001f;
    L_0x0096:
        r0 = r13.e;
        if (r0 == 0) goto L_0x001f;
    L_0x009a:
        r2 = android.support.v4.view.n.c(r14);
        r0 = 0;
        r1 = r0;
    L_0x00a0:
        if (r1 >= r2) goto L_0x00fd;
    L_0x00a2:
        r3 = android.support.v4.view.n.b(r14, r1);
        r0 = android.support.v4.view.n.c(r14, r1);
        r4 = android.support.v4.view.n.d(r14, r1);
        r5 = r13.d;
        r5 = r5[r3];
        r5 = r0 - r5;
        r6 = r13.e;
        r6 = r6[r3];
        r6 = r4 - r6;
        r0 = (int) r0;
        r4 = (int) r4;
        r4 = r13.d(r0, r4);
        if (r4 == 0) goto L_0x0102;
    L_0x00c2:
        r0 = r13.a(r4, r5, r6);
        if (r0 == 0) goto L_0x0102;
    L_0x00c8:
        r0 = 1;
    L_0x00c9:
        if (r0 == 0) goto L_0x0104;
    L_0x00cb:
        r7 = r4.getLeft();
        r8 = (int) r5;
        r8 = r8 + r7;
        r9 = r13.r;
        r10 = (int) r5;
        r8 = r9.a(r4, r8, r10);
        r9 = r4.getTop();
        r10 = (int) r6;
        r10 = r10 + r9;
        r11 = r13.r;
        r12 = (int) r6;
        r10 = r11.b(r4, r10, r12);
        r11 = r13.r;
        r11 = r11.a(r4);
        r12 = r13.r;
        r12 = r12.b(r4);
        if (r11 == 0) goto L_0x00f7;
    L_0x00f3:
        if (r11 <= 0) goto L_0x0104;
    L_0x00f5:
        if (r8 != r7) goto L_0x0104;
    L_0x00f7:
        if (r12 == 0) goto L_0x00fd;
    L_0x00f9:
        if (r12 <= 0) goto L_0x0104;
    L_0x00fb:
        if (r10 != r9) goto L_0x0104;
    L_0x00fd:
        r13.c(r14);
        goto L_0x001f;
    L_0x0102:
        r0 = 0;
        goto L_0x00c9;
    L_0x0104:
        r13.b(r5, r6, r3);
        r5 = r13.a;
        r6 = 1;
        if (r5 == r6) goto L_0x00fd;
    L_0x010c:
        if (r0 == 0) goto L_0x0114;
    L_0x010e:
        r0 = r13.b(r4, r3);
        if (r0 != 0) goto L_0x00fd;
    L_0x0114:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x00a0;
    L_0x0118:
        r0 = android.support.v4.view.n.b(r14, r1);
        r13.e(r0);
        goto L_0x001f;
    L_0x0121:
        r13.e();
        goto L_0x001f;
    L_0x0126:
        r0 = 0;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.k.a(android.view.MotionEvent):boolean");
    }

    public void b(MotionEvent motionEvent) {
        int i = 0;
        int a = n.a(motionEvent);
        int b = n.b(motionEvent);
        if (a == 0) {
            e();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        float x;
        float y;
        View d;
        int i2;
        switch (a) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = n.b(motionEvent, 0);
                d = d((int) x, (int) y);
                a(x, y, i);
                b(d, i);
                i2 = this.h[i];
                if ((this.p & i2) != 0) {
                    this.r.a(i2 & this.p, i);
                    return;
                }
                return;
            case 1:
                if (this.a == 1) {
                    h();
                }
                e();
                return;
            case 2:
                if (this.a == 1) {
                    i = n.a(motionEvent, this.c);
                    x = n.c(motionEvent, i);
                    i2 = (int) (x - this.f[this.c]);
                    i = (int) (n.d(motionEvent, i) - this.g[this.c]);
                    b(this.s.getLeft() + i2, this.s.getTop() + i, i2, i);
                    c(motionEvent);
                    return;
                }
                i2 = n.c(motionEvent);
                while (i < i2) {
                    a = n.b(motionEvent, i);
                    float c = n.c(motionEvent, i);
                    float d2 = n.d(motionEvent, i);
                    float f = c - this.d[a];
                    float f2 = d2 - this.e[a];
                    b(f, f2, a);
                    if (this.a != 1) {
                        d = d((int) c, (int) d2);
                        if (!a(d, f, f2) || !b(d, a)) {
                            i++;
                        }
                    }
                    c(motionEvent);
                    return;
                }
                c(motionEvent);
                return;
            case 3:
                if (this.a == 1) {
                    a(0.0f, 0.0f);
                }
                e();
                return;
            case 5:
                i = n.b(motionEvent, b);
                x = n.c(motionEvent, b);
                y = n.d(motionEvent, b);
                a(x, y, i);
                if (this.a == 0) {
                    b(d((int) x, (int) y), i);
                    i2 = this.h[i];
                    if ((this.p & i2) != 0) {
                        this.r.a(i2 & this.p, i);
                        return;
                    }
                    return;
                } else if (c((int) x, (int) y)) {
                    b(this.s, i);
                    return;
                } else {
                    return;
                }
            case 6:
                a = n.b(motionEvent, b);
                if (this.a == 1 && a == this.c) {
                    b = n.c(motionEvent);
                    while (i < b) {
                        int b2 = n.b(motionEvent, i);
                        if (b2 != this.c) {
                            if (d((int) n.c(motionEvent, i), (int) n.d(motionEvent, i)) == this.s && b(this.s, b2)) {
                                i = this.c;
                                if (i == -1) {
                                    h();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        h();
                    }
                }
                e(a);
                return;
            default:
                return;
        }
    }

    private void b(float f, float f2, int i) {
        int i2 = 1;
        if (!a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | i2;
            this.r.b(i2, i);
        }
    }

    private boolean a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.b) && abs2 <= ((float) this.b)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.r.b(i2)) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.i[i] & i2) != 0 || abs <= ((float) this.b)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.r.a(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.r.b(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            if ((f * f) + (f2 * f2) <= ((float) (this.b * this.b))) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= ((float) this.b)) {
                return false;
            }
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.b)) {
                return false;
            }
            return true;
        }
    }

    public boolean d(int i) {
        int length = this.d.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (b(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(int i, int i2) {
        if (!b(i2)) {
            return false;
        }
        boolean z;
        boolean z2 = (i & 1) == 1;
        if ((i & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        float f = this.f[i2] - this.d[i2];
        float f2 = this.g[i2] - this.e[i2];
        if (z2 && z) {
            if ((f * f) + (f2 * f2) <= ((float) (this.b * this.b))) {
                return false;
            }
            return true;
        } else if (z2) {
            if (Math.abs(f) <= ((float) this.b)) {
                return false;
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.b)) {
                return false;
            }
            return true;
        }
    }

    private void h() {
        this.l.computeCurrentVelocity(1000, this.m);
        a(a(x.a(this.l, this.c), this.n, this.m), a(x.b(this.l, this.c), this.n, this.m));
    }

    private void b(int i, int i2, int i3, int i4) {
        int a;
        int b;
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            a = this.r.a(this.s, i, i3);
            this.s.offsetLeftAndRight(a - left);
        } else {
            a = i;
        }
        if (i4 != 0) {
            b = this.r.b(this.s, i2, i4);
            this.s.offsetTopAndBottom(b - top);
        } else {
            b = i2;
        }
        if (i3 != 0 || i4 != 0) {
            this.r.a(this.s, a, b, a - left, b - top);
        }
    }

    public boolean c(int i, int i2) {
        return b(this.s, i, i2);
    }

    public boolean b(View view, int i, int i2) {
        if (view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View d(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int e(int i, int i2) {
        int i3 = 0;
        if (i < this.u.getLeft() + this.o) {
            i3 = 1;
        }
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        if (i2 > this.u.getBottom() - this.o) {
            return i3 | 8;
        }
        return i3;
    }
}
