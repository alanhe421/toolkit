package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.n;
import android.support.v4.view.r;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v4.view.u;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;

public class SwipeRefreshLayout extends ViewGroup implements r, t {
    private static final String c = SwipeRefreshLayout.class.getSimpleName();
    private static final int[] w = new int[]{16842766};
    private g A;
    private Animation B;
    private Animation C;
    private Animation D;
    private Animation E;
    private Animation F;
    private float G;
    private boolean H;
    private int I;
    private int J;
    private boolean K;
    private AnimationListener L;
    private final Animation M;
    private final Animation N;
    protected int a;
    protected int b;
    private View d;
    private a e;
    private boolean f;
    private int g;
    private float h;
    private float i;
    private final u j;
    private final s k;
    private final int[] l;
    private int m;
    private int n;
    private boolean o;
    private float p;
    private float q;
    private boolean r;
    private int s;
    private boolean t;
    private boolean u;
    private final DecelerateInterpolator v;
    private a x;
    private int y;
    private float z;

    public interface a {
        void a();
    }

    private void setColorViewAlpha(int i) {
        this.x.getBackground().setAlpha(i);
        this.A.setAlpha(i);
    }

    public void setProgressViewOffset(boolean z, int i, int i2) {
        this.t = z;
        this.x.setVisibility(8);
        this.n = i;
        this.b = i;
        this.G = (float) i2;
        this.K = true;
        this.x.invalidate();
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.G = (float) i;
        this.t = z;
        this.x.invalidate();
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2;
            if (i == 0) {
                i2 = (int) (displayMetrics.density * 56.0f);
                this.I = i2;
                this.J = i2;
            } else {
                i2 = (int) (displayMetrics.density * 40.0f);
                this.I = i2;
                this.J = i2;
            }
            this.x.setImageDrawable(null);
            this.A.a(i);
            this.x.setImageDrawable(this.A);
        }
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.h = -1.0f;
        this.l = new int[2];
        this.o = false;
        this.s = -1;
        this.y = -1;
        this.L = new AnimationListener(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.a.f) {
                    this.a.A.setAlpha(255);
                    this.a.A.start();
                    if (this.a.H && this.a.e != null) {
                        this.a.e.a();
                    }
                } else {
                    this.a.A.stop();
                    this.a.x.setVisibility(8);
                    this.a.setColorViewAlpha(255);
                    if (this.a.t) {
                        this.a.setAnimationProgress(0.0f);
                    } else {
                        this.a.a(this.a.b - this.a.n, true);
                    }
                }
                this.a.n = this.a.x.getTop();
            }
        };
        this.M = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                int i;
                if (this.a.K) {
                    i = (int) this.a.G;
                } else {
                    i = (int) (this.a.G - ((float) Math.abs(this.a.b)));
                }
                this.a.a((((int) (((float) (i - this.a.a)) * f)) + this.a.a) - this.a.x.getTop(), false);
                this.a.A.a(1.0f - f);
            }
        };
        this.N = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.c(f);
            }
        };
        this.g = ViewConfiguration.get(context).getScaledTouchSlop();
        this.m = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.v = new DecelerateInterpolator(2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, w);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.I = (int) (displayMetrics.density * 40.0f);
        this.J = (int) (displayMetrics.density * 40.0f);
        b();
        z.a((ViewGroup) this, true);
        this.G = displayMetrics.density * 64.0f;
        this.h = this.G;
        this.j = new u(this);
        this.k = new s(this);
        setNestedScrollingEnabled(true);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.y < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.y;
        }
        if (i2 >= this.y) {
            return i2 + 1;
        }
        return i2;
    }

    private void b() {
        this.x = new a(getContext(), -328966, 20.0f);
        this.A = new g(getContext(), this);
        this.A.b(-328966);
        this.x.setImageDrawable(this.A);
        this.x.setVisibility(8);
        addView(this.x);
    }

    public void setOnRefreshListener(a aVar) {
        this.e = aVar;
    }

    private boolean c() {
        return VERSION.SDK_INT < 11;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.f == z) {
            a(z, false);
            return;
        }
        int i;
        this.f = z;
        if (this.K) {
            i = (int) this.G;
        } else {
            i = (int) (this.G + ((float) this.b));
        }
        a(i - this.n, true);
        this.H = false;
        a(this.L);
    }

    private void a(AnimationListener animationListener) {
        this.x.setVisibility(0);
        if (VERSION.SDK_INT >= 11) {
            this.A.setAlpha(255);
        }
        this.B = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(f);
            }
        };
        this.B.setDuration((long) this.m);
        if (animationListener != null) {
            this.x.a(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.B);
    }

    private void setAnimationProgress(float f) {
        if (c()) {
            setColorViewAlpha((int) (255.0f * f));
            return;
        }
        z.f(this.x, f);
        z.g(this.x, f);
    }

    private void a(boolean z, boolean z2) {
        if (this.f != z) {
            this.H = z2;
            f();
            this.f = z;
            if (this.f) {
                a(this.n, this.L);
            } else {
                b(this.L);
            }
        }
    }

    private void b(AnimationListener animationListener) {
        this.C = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(1.0f - f);
            }
        };
        this.C.setDuration(150);
        this.x.a(animationListener);
        this.x.clearAnimation();
        this.x.startAnimation(this.C);
    }

    private void d() {
        this.D = a(this.A.getAlpha(), 76);
    }

    private void e() {
        this.E = a(this.A.getAlpha(), 255);
    }

    private Animation a(final int i, final int i2) {
        if (this.t && c()) {
            return null;
        }
        Animation anonymousClass4 = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout c;

            public void applyTransformation(float f, Transformation transformation) {
                this.c.A.setAlpha((int) (((float) i) + (((float) (i2 - i)) * f)));
            }
        };
        anonymousClass4.setDuration(300);
        this.x.a(null);
        this.x.clearAnimation();
        this.x.startAnimation(anonymousClass4);
        return anonymousClass4;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.x.setBackgroundColor(i);
        this.A.b(i);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        f();
        this.A.a(iArr);
    }

    private void f() {
        if (this.d == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.x)) {
                    i++;
                } else {
                    this.d = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.h = (float) i;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.d == null) {
                f();
            }
            if (this.d != null) {
                View view = this.d;
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                measuredHeight = this.x.getMeasuredWidth();
                this.x.layout((measuredWidth / 2) - (measuredHeight / 2), this.n, (measuredWidth / 2) + (measuredHeight / 2), this.n + this.x.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.d == null) {
            f();
        }
        if (this.d != null) {
            int i3;
            this.d.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.x.measure(MeasureSpec.makeMeasureSpec(this.I, 1073741824), MeasureSpec.makeMeasureSpec(this.J, 1073741824));
            if (!(this.K || this.o)) {
                this.o = true;
                i3 = -this.x.getMeasuredHeight();
                this.b = i3;
                this.n = i3;
            }
            this.y = -1;
            for (i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3) == this.x) {
                    this.y = i3;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.x != null ? this.x.getMeasuredHeight() : 0;
    }

    public boolean a() {
        boolean z = false;
        if (VERSION.SDK_INT >= 14) {
            return z.b(this.d, -1);
        }
        if (this.d instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.d;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else {
            if (z.b(this.d, -1) || this.d.getScrollY() > 0) {
                z = true;
            }
            return z;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        f();
        int a = n.a(motionEvent);
        if (this.u && a == 0) {
            this.u = false;
        }
        if (!isEnabled() || this.u || a() || this.f) {
            return false;
        }
        float a2;
        switch (a) {
            case 0:
                a(this.b - this.x.getTop(), true);
                this.s = n.b(motionEvent, 0);
                this.r = false;
                a2 = a(motionEvent, this.s);
                if (a2 != -1.0f) {
                    this.q = a2;
                    break;
                }
                return false;
            case 1:
            case 3:
                this.r = false;
                this.s = -1;
                break;
            case 2:
                if (this.s == -1) {
                    Log.e(c, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
                a2 = a(motionEvent, this.s);
                if (a2 != -1.0f) {
                    if (a2 - this.q > ((float) this.g) && !this.r) {
                        this.p = this.q + ((float) this.g);
                        this.r = true;
                        this.A.setAlpha(76);
                        break;
                    }
                }
                return false;
            case 6:
                a(motionEvent);
                break;
        }
        return this.r;
    }

    private float a(MotionEvent motionEvent, int i) {
        int a = n.a(motionEvent, i);
        if (a < 0) {
            return -1.0f;
        }
        return n.d(motionEvent, a);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (VERSION.SDK_INT < 21 && (this.d instanceof AbsListView)) {
            return;
        }
        if (this.d == null || z.u(this.d)) {
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if (!isEnabled() || this.u || a() || this.f || (i & 2) == 0) {
            return false;
        }
        startNestedScroll(i & 2);
        return true;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.j.a(view, view2, i);
        this.i = 0.0f;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0 && this.i > 0.0f) {
            if (((float) i2) > this.i) {
                iArr[1] = i2 - ((int) this.i);
                this.i = 0.0f;
            } else {
                this.i -= (float) i2;
                iArr[1] = i2;
            }
            a(this.i);
        }
        if (this.K && i2 > 0 && this.i == 0.0f && Math.abs(i2 - iArr[1]) > 0) {
            this.x.setVisibility(8);
        }
        int[] iArr2 = this.l;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr2[1] + iArr[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.j.a();
    }

    public void onStopNestedScroll(View view) {
        this.j.a(view);
        if (this.i > 0.0f) {
            b(this.i);
            this.i = 0.0f;
        }
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        if (i4 < 0) {
            this.i = ((float) Math.abs(i4)) + this.i;
            a(this.i);
        }
        dispatchNestedScroll(i, i2, i3, i, null);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.k.a(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.k.a();
    }

    public boolean startNestedScroll(int i) {
        return this.k.a(i);
    }

    public void stopNestedScroll() {
        this.k.c();
    }

    public boolean hasNestedScrollingParent() {
        return this.k.b();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.k.a(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.k.a(i, i2, iArr, iArr2);
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.k.a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.k.a(f, f2);
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void a(float f) {
        this.A.a(true);
        float min = Math.min(1.0f, Math.abs(f / this.h));
        float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f) - this.h;
        float f2 = this.K ? this.G - ((float) this.b) : this.G;
        abs = Math.max(0.0f, Math.min(abs, f2 * 2.0f) / f2);
        abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * 2.0f;
        int i = ((int) ((f2 * min) + ((f2 * abs) * 2.0f))) + this.b;
        if (this.x.getVisibility() != 0) {
            this.x.setVisibility(0);
        }
        if (!this.t) {
            z.f(this.x, 1.0f);
            z.g(this.x, 1.0f);
        }
        if (f < this.h) {
            if (this.t) {
                setAnimationProgress(f / this.h);
            }
            if (this.A.getAlpha() > 76 && !a(this.D)) {
                d();
            }
            this.A.a(0.0f, Math.min(0.8f, max * 0.8f));
            this.A.a(Math.min(1.0f, max));
        } else if (this.A.getAlpha() < 255 && !a(this.E)) {
            e();
        }
        this.A.b(((-0.25f + (max * 0.4f)) + (abs * 2.0f)) * 0.5f);
        a(i - this.n, true);
    }

    private void b(float f) {
        if (f > this.h) {
            a(true, true);
            return;
        }
        this.f = false;
        this.A.a(0.0f, 0.0f);
        AnimationListener animationListener = null;
        if (!this.t) {
            animationListener = new AnimationListener(this) {
                final /* synthetic */ SwipeRefreshLayout a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (!this.a.t) {
                        this.a.b(null);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }
            };
        }
        b(this.n, animationListener);
        this.A.a(false);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int a = n.a(motionEvent);
        if (this.u && a == 0) {
            this.u = false;
        }
        if (!isEnabled() || this.u || a()) {
            return false;
        }
        float d;
        switch (a) {
            case 0:
                this.s = n.b(motionEvent, 0);
                this.r = false;
                break;
            case 1:
                a = n.a(motionEvent, this.s);
                if (a < 0) {
                    Log.e(c, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                d = (n.d(motionEvent, a) - this.p) * 0.5f;
                this.r = false;
                b(d);
                this.s = -1;
                return false;
            case 2:
                a = n.a(motionEvent, this.s);
                if (a < 0) {
                    Log.e(c, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                d = (n.d(motionEvent, a) - this.p) * 0.5f;
                if (this.r) {
                    if (d > 0.0f) {
                        a(d);
                        break;
                    }
                    return false;
                }
                break;
            case 3:
                return false;
            case 5:
                a = n.b(motionEvent);
                if (a >= 0) {
                    this.s = n.b(motionEvent, a);
                    break;
                }
                Log.e(c, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                return false;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(int i, AnimationListener animationListener) {
        this.a = i;
        this.M.reset();
        this.M.setDuration(200);
        this.M.setInterpolator(this.v);
        if (animationListener != null) {
            this.x.a(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.M);
    }

    private void b(int i, AnimationListener animationListener) {
        if (this.t) {
            c(i, animationListener);
            return;
        }
        this.a = i;
        this.N.reset();
        this.N.setDuration(200);
        this.N.setInterpolator(this.v);
        if (animationListener != null) {
            this.x.a(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.N);
    }

    private void c(float f) {
        a((this.a + ((int) (((float) (this.b - this.a)) * f))) - this.x.getTop(), false);
    }

    private void c(int i, AnimationListener animationListener) {
        this.a = i;
        if (c()) {
            this.z = (float) this.A.getAlpha();
        } else {
            this.z = z.q(this.x);
        }
        this.F = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(this.a.z + ((-this.a.z) * f));
                this.a.c(f);
            }
        };
        this.F.setDuration(150);
        if (animationListener != null) {
            this.x.a(animationListener);
        }
        this.x.clearAnimation();
        this.x.startAnimation(this.F);
    }

    private void a(int i, boolean z) {
        this.x.bringToFront();
        this.x.offsetTopAndBottom(i);
        this.n = this.x.getTop();
        if (z && VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    private void a(MotionEvent motionEvent) {
        int b = n.b(motionEvent);
        if (n.b(motionEvent, b) == this.s) {
            this.s = n.b(motionEvent, b == 0 ? 1 : 0);
        }
    }
}
