package com.qq.reader.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.n;
import android.support.v4.view.z;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.feedback.proguard.R;

public class SwipeRefreshLayout extends ViewGroup {
    private static final String d = SwipeRefreshLayout.class.getSimpleName();
    private static int e = -1;
    private static final int[] u = new int[]{16842766};
    private b A;
    private int B;
    private int C;
    private float D;
    private c E;
    private Animation F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    private float K;
    private boolean L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private boolean Q;
    private Handler R;
    private int S;
    private AnimationListener T;
    private boolean U;
    private final Animation V;
    private final Animation W;
    protected View a;
    private a aa;
    protected int b;
    protected int c;
    private b f;
    private boolean g;
    private int h;
    private float i;
    private int j;
    private int k;
    private boolean l;
    private float m;
    private float n;
    private boolean o;
    private int p;
    private boolean q;
    private String r;
    private boolean s;
    private final DecelerateInterpolator t;
    private TextView v;
    private Animation w;
    private Animation x;
    private AnimationListener y;
    private AnimationListener z;

    public interface b {
        void a();
    }

    public interface a {
        void onDispatchTouchEvent(MotionEvent motionEvent);
    }

    public void setMannuallySetSchemeColor(int i) {
        this.S = i;
    }

    private void d() {
        this.R = new Handler(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }
        };
        this.w = AnimationUtils.loadAnimation(getContext(), R.anim.topbar_enter);
        this.x = AnimationUtils.loadAnimation(getContext(), R.anim.topbar_out);
        this.w.setDuration(500);
        this.x.setDuration(500);
        this.y = new AnimationListener(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                if (this.a.v != null) {
                    this.a.v.setVisibility(0);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.a.v != null) {
                    this.a.v.clearAnimation();
                    this.a.U = true;
                    this.a.R.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f();
                        }
                    }, 1000);
                }
            }
        };
        this.z = new AnimationListener(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.a.v != null) {
                    this.a.v.setText(null);
                    this.a.v.clearAnimation();
                    this.a.v.setVisibility(8);
                    this.a.U = false;
                    this.a.r = null;
                }
            }
        };
        this.w.setAnimationListener(this.y);
        this.x.setAnimationListener(this.z);
    }

    private void setColorViewAlpha(int i) {
        this.A.getBackground().setAlpha(i);
        this.E.setAlpha(i);
    }

    public void setProgressViewOffset(boolean z, int i, int i2) {
        this.q = z;
        this.A.setVisibility(8);
        this.k = i;
        this.c = i;
        this.K = (float) i2;
        this.Q = true;
        this.A.invalidate();
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.K = (float) i;
        this.q = z;
        this.A.invalidate();
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2;
            if (i == 0) {
                i2 = (int) (displayMetrics.density * 56.0f);
                this.M = i2;
                this.N = i2;
            } else {
                i2 = (int) (displayMetrics.density * 40.0f);
                this.M = i2;
                this.N = i2;
            }
            this.A.setImageDrawable(null);
            this.E.a(i);
            this.A.setImageDrawable(this.E);
        }
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.i = -1.0f;
        this.l = false;
        this.p = -1;
        this.r = null;
        this.B = -1;
        this.C = -1;
        this.P = false;
        this.S = 0;
        this.T = new AnimationListener(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.a.g) {
                    this.a.E.setAlpha(255);
                    this.a.E.start();
                    if (this.a.L && this.a.f != null) {
                        this.a.f.a();
                    }
                } else {
                    this.a.E.stop();
                    this.a.A.setVisibility(8);
                    this.a.setColorViewAlpha(255);
                    if (this.a.q) {
                        this.a.setAnimationProgress(0.0f);
                    } else {
                        this.a.a(this.a.c - this.a.k, true);
                    }
                    if (this.a.v == null || this.a.v.getVisibility() != 0 || TextUtils.isEmpty(this.a.r) || !this.a.U) {
                        this.a.e();
                    } else {
                        this.a.v.setText(this.a.r);
                    }
                }
                this.a.k = this.a.A.getTop();
            }
        };
        this.U = false;
        this.V = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                int q;
                if (this.a.Q) {
                    q = (int) this.a.K;
                } else {
                    q = (int) (this.a.K - ((float) Math.abs(this.a.c)));
                }
                this.a.a((((int) (((float) (q - this.a.b)) * f)) + this.a.b) - this.a.A.getTop(), false);
                this.a.E.a(1.0f - f);
            }
        };
        this.W = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.a(f);
            }
        };
        e = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_pulldown_refresh_bg_color);
        this.h = ViewConfiguration.get(context.getApplicationContext()).getScaledTouchSlop();
        this.j = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.t = new DecelerateInterpolator(2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, u);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.M = (int) (displayMetrics.density * 40.0f);
        this.N = (int) (displayMetrics.density * 40.0f);
        h();
        z.a((ViewGroup) this, true);
        this.K = displayMetrics.density * 64.0f;
        this.i = this.K;
        setColorSchemeColors(ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_pulldown_refresh_scheme_color));
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.B < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return this.B;
        }
        if (i2 >= this.B) {
            return i2 + 1;
        }
        return i2;
    }

    private void e() {
        if (this.P) {
            this.P = false;
            return;
        }
        if (this.v == null) {
            g();
        }
        if (this.v != null && this.v.getVisibility() != 0) {
            if (this.w == null || this.x == null || this.y == null || this.z == null) {
                d();
            }
            if (this.f != null && !TextUtils.isEmpty(this.r)) {
                this.v.setVisibility(4);
                this.v.setText(this.r);
                this.w.reset();
                this.v.clearAnimation();
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ SwipeRefreshLayout a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.w != null) {
                            this.a.v.startAnimation(this.a.w);
                        }
                    }
                });
            }
        }
    }

    public void a() {
        if (this.v != null) {
            this.v.clearAnimation();
            this.v.setVisibility(8);
        }
        this.P = true;
    }

    private void f() {
        if (this.v == null) {
            g();
        }
        if (this.v != null && this.v.getVisibility() != 8) {
            if (this.w == null || this.x == null || this.y == null || this.z == null) {
                d();
            }
            this.x.reset();
            this.v.clearAnimation();
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ SwipeRefreshLayout a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.x != null) {
                        this.a.v.startAnimation(this.a.x);
                    }
                }
            });
        }
    }

    private void g() {
        this.O = (int) getResources().getDimension(R.dimen.refresh_titlebarheight);
        this.v = new TextView(getContext());
        this.v.setVisibility(8);
        this.v.setBackgroundResource(R.drawable.refresh_title_bg);
        this.v.setAlpha(0.95f);
        this.v.setTextSize(2, 16.0f);
        this.v.setGravity(17);
        this.v.setTextColor(-1);
        addView(this.v);
    }

    private void h() {
        this.A = new b(getContext(), e, 20.0f);
        this.E = new c(getContext(), this);
        this.E.b(e);
        this.A.setImageDrawable(this.E);
        this.A.setVisibility(8);
        addView(this.A);
    }

    public void setOnRefreshListener(b bVar) {
        this.f = bVar;
    }

    private boolean i() {
        return VERSION.SDK_INT < 11;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.g == z) {
            a(z, false);
            return;
        }
        int i;
        this.g = z;
        if (this.Q) {
            i = (int) this.K;
        } else {
            i = (int) (this.K + ((float) this.c));
        }
        a(i - this.k, true);
        this.L = false;
        a(this.T);
    }

    private void a(AnimationListener animationListener) {
        this.A.setVisibility(0);
        this.E.setAlpha(255);
        this.F = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(f);
            }
        };
        this.F.setDuration((long) this.j);
        if (animationListener != null) {
            this.A.a(animationListener);
        }
        this.A.clearAnimation();
        this.A.startAnimation(this.F);
    }

    private void setAnimationProgress(float f) {
        if (i()) {
            setColorViewAlpha((int) (255.0f * f));
            return;
        }
        z.f(this.A, f);
        z.g(this.A, f);
    }

    private void a(boolean z, boolean z2) {
        if (this.g != z) {
            this.L = z2;
            l();
            this.g = z;
            if (this.g) {
                a(this.k, this.T);
            } else {
                b(this.T);
            }
        }
    }

    private void b(AnimationListener animationListener) {
        this.G = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(1.0f - f);
            }
        };
        this.G.setDuration(150);
        this.A.a(animationListener);
        this.A.clearAnimation();
        this.A.startAnimation(this.G);
    }

    private void j() {
        this.H = a(this.E.getAlpha(), 76);
    }

    private void k() {
        this.I = a(this.E.getAlpha(), 255);
    }

    private Animation a(final int i, final int i2) {
        if (this.q && i()) {
            return null;
        }
        Animation anonymousClass13 = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout c;

            public void applyTransformation(float f, Transformation transformation) {
                this.c.E.setAlpha((int) (((float) i) + (((float) (i2 - i)) * f)));
            }
        };
        anonymousClass13.setDuration(300);
        this.A.a(null);
        this.A.clearAnimation();
        this.A.startAnimation(anonymousClass13);
        return anonymousClass13;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.A.setBackgroundColor(i);
        this.E.b(i);
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
        l();
        this.E.a(iArr);
    }

    public boolean b() {
        return this.g;
    }

    private void l() {
        if (this.a == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.A)) {
                    i++;
                } else {
                    this.a = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.i = (float) i;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.a == null) {
                l();
            }
            if (this.a != null) {
                View view = this.a;
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
                view.layout(paddingLeft, paddingTop, paddingLeft + paddingLeft2, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                measuredHeight = this.O;
                if (this.v != null) {
                    this.v.layout(paddingLeft, paddingTop - 2, paddingLeft2 + paddingLeft, measuredHeight + (paddingTop - 2));
                }
                measuredHeight = this.A.getMeasuredWidth();
                this.A.layout((measuredWidth / 2) - (measuredHeight / 2), this.k, (measuredWidth / 2) + (measuredHeight / 2), this.k + this.A.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a == null) {
            l();
        }
        if (this.a != null) {
            int i3;
            if (this.v != null) {
                this.v.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec(this.O + 2, 1073741824));
            }
            this.a.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.A.measure(MeasureSpec.makeMeasureSpec(this.M, 1073741824), MeasureSpec.makeMeasureSpec(this.N, 1073741824));
            if (!(this.Q || this.l)) {
                this.l = true;
                i3 = -this.A.getMeasuredHeight();
                this.c = i3;
                this.k = i3;
            }
            this.B = -1;
            for (i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3) == this.A) {
                    this.B = i3;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.A != null ? this.A.getMeasuredHeight() : 0;
    }

    public boolean c() {
        return z.b(this.a, -1);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        l();
        int a = n.a(motionEvent);
        if (this.s && a == 0) {
            this.s = false;
        }
        if (!isEnabled() || this.s || c() || this.g) {
            return false;
        }
        this.P = false;
        float a2;
        switch (a) {
            case 0:
                a(this.c - this.A.getTop(), true);
                this.p = n.b(motionEvent, 0);
                this.o = false;
                a2 = a(motionEvent, this.p);
                if (a2 != -1.0f) {
                    this.n = a2;
                    f();
                    break;
                }
                return false;
            case 1:
            case 3:
                this.o = false;
                this.p = -1;
                break;
            case 2:
                if (this.p == -1) {
                    c.e(d, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
                a2 = a(motionEvent, this.p);
                if (a2 != -1.0f) {
                    if (a2 - this.n > ((float) this.h) && !this.o) {
                        this.m = this.n + ((float) this.h);
                        this.o = true;
                        this.E.setAlpha(76);
                        break;
                    }
                }
                return false;
            case 6:
                a(motionEvent);
                break;
        }
        return this.o;
    }

    private float a(MotionEvent motionEvent, int i) {
        int a = n.a(motionEvent, i);
        if (a < 0) {
            return -1.0f;
        }
        return n.d(motionEvent, a);
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int a = n.a(motionEvent);
        if (this.s && a == 0) {
            this.s = false;
        }
        if (!isEnabled() || this.s || c()) {
            return false;
        }
        this.P = false;
        float d;
        switch (a) {
            case 0:
                this.p = n.b(motionEvent, 0);
                this.o = false;
                break;
            case 1:
            case 3:
                if (this.p == -1) {
                    if (a == 1) {
                        c.e(d, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                int a2 = n.a(motionEvent, this.p);
                if (a2 < 0) {
                    if (a == 1) {
                        c.e(d, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                d = (n.d(motionEvent, a2) - this.m) * 0.5f;
                this.o = false;
                if (d > this.i) {
                    a(true, true);
                } else {
                    this.g = false;
                    this.E.a(0.0f, 0.0f);
                    AnimationListener animationListener = null;
                    if (!this.q) {
                        animationListener = new AnimationListener(this) {
                            final /* synthetic */ SwipeRefreshLayout a;

                            {
                                this.a = r1;
                            }

                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if (!this.a.q) {
                                    this.a.b(null);
                                }
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }
                        };
                    }
                    b(this.k, animationListener);
                    this.E.a(false);
                }
                this.p = -1;
                return false;
            case 2:
                a = n.a(motionEvent, this.p);
                if (a >= 0) {
                    float d2 = 0.5f * (n.d(motionEvent, a) - this.m);
                    if (this.o) {
                        a = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_pulldown_refresh_scheme_color);
                        if (this.S != 0) {
                            a = this.S;
                        }
                        setColorSchemeColors(a);
                        this.E.a(true);
                        d = d2 / this.i;
                        if (d >= 0.0f) {
                            float min = Math.min(1.0f, Math.abs(d));
                            float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                            float abs = Math.abs(d2) - this.i;
                            d = this.Q ? this.K - ((float) this.c) : this.K;
                            abs = Math.max(0.0f, Math.min(abs, 2.0f * d) / d);
                            abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * 2.0f;
                            a = ((int) ((d * min) + ((d * abs) * 2.0f))) + this.c;
                            if (this.A.getVisibility() != 0) {
                                this.A.setVisibility(0);
                            }
                            if (!this.q) {
                                z.f(this.A, 1.0f);
                                z.g(this.A, 1.0f);
                            }
                            if (d2 < this.i) {
                                if (this.q) {
                                    setAnimationProgress(d2 / this.i);
                                }
                                if (this.E.getAlpha() > 76 && !a(this.H)) {
                                    j();
                                }
                                this.E.a(0.0f, Math.min(0.8f, 0.8f * max));
                                this.E.a(Math.min(1.0f, max));
                            } else if (this.E.getAlpha() < 255 && !a(this.I)) {
                                k();
                            }
                            this.E.b(((-0.25f + (0.4f * max)) + (2.0f * abs)) * 0.5f);
                            a(a - this.k, true);
                            break;
                        }
                        return false;
                    }
                }
                c.e(d, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
                break;
            case 5:
                this.p = n.b(motionEvent, n.b(motionEvent));
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(int i, AnimationListener animationListener) {
        this.b = i;
        this.V.reset();
        this.V.setDuration(200);
        this.V.setInterpolator(this.t);
        if (animationListener != null) {
            this.A.a(animationListener);
        }
        this.A.clearAnimation();
        this.A.startAnimation(this.V);
    }

    private void b(int i, AnimationListener animationListener) {
        if (this.q) {
            c(i, animationListener);
            return;
        }
        this.b = i;
        this.W.reset();
        this.W.setDuration(200);
        this.W.setInterpolator(this.t);
        if (animationListener != null) {
            this.A.a(animationListener);
        }
        this.A.clearAnimation();
        this.A.startAnimation(this.W);
    }

    private void a(float f) {
        a((this.b + ((int) (((float) (this.c - this.b)) * f))) - this.A.getTop(), false);
    }

    private void c(int i, AnimationListener animationListener) {
        this.b = i;
        if (i()) {
            this.D = (float) this.E.getAlpha();
        } else {
            this.D = z.q(this.A);
        }
        this.J = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = r1;
            }

            public void applyTransformation(float f, Transformation transformation) {
                this.a.setAnimationProgress(this.a.D + ((-this.a.D) * f));
                this.a.a(f);
            }
        };
        this.J.setDuration(150);
        if (animationListener != null) {
            this.A.a(animationListener);
        }
        this.A.clearAnimation();
        this.A.startAnimation(this.J);
    }

    private void a(int i, boolean z) {
        this.A.bringToFront();
        this.A.offsetTopAndBottom(i);
        this.k = this.A.getTop();
        if (z && VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    private void a(MotionEvent motionEvent) {
        int b = n.b(motionEvent);
        if (n.b(motionEvent, b) == this.p) {
            this.p = n.b(motionEvent, b == 0 ? 1 : 0);
        }
    }

    public void a(String str) {
        setRefreshing(false);
        this.r = str;
    }

    public void a(String str, TextView textView) {
        setRefreshing(false);
        this.r = str;
        this.v = textView;
    }

    public void setIsInterptAnimation(boolean z) {
        this.P = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.aa != null) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                this.aa.onDispatchTouchEvent(obtain);
                obtain.recycle();
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            return true;
        }
    }

    public void setDispatchEventListener(a aVar) {
        this.aa = aVar;
    }
}
