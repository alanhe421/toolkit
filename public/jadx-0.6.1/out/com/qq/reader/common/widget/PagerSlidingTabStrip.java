package com.qq.reader.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] b = new int[]{16842901, 16842904};
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private ColorStateList F;
    private Typeface G;
    private int H;
    private int I;
    private int J;
    private Locale K;
    private Scroller L;
    private int M;
    private Drawable N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private Message T;
    private Handler U;
    private float V;
    private float W;
    public e a;
    private boolean aa;
    private int ab;
    private TextView ac;
    private TextView ad;
    private boolean c;
    private boolean d;
    private int e;
    private ArrayList<TabInfo> f;
    private LayoutParams g;
    private LayoutParams h;
    private final b i;
    private e j;
    private LinearLayout k;
    private ViewPager l;
    private int m;
    private int n;
    private float o;
    private Paint p;
    private Paint q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface c {
        View c(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        };
        int a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    private class a extends Scroller {
        final /* synthetic */ PagerSlidingTabStrip a;
        private int b = 1000;

        public a(PagerSlidingTabStrip pagerSlidingTabStrip, Context context, Interpolator interpolator) {
            this.a = pagerSlidingTabStrip;
            super(context, interpolator);
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.b);
        }

        public void startScroll(int i, int i2, int i3, int i4) {
            super.startScroll(i, i2, i3, i4, this.b);
        }
    }

    private class b implements e {
        final /* synthetic */ PagerSlidingTabStrip a;

        private b(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.a = pagerSlidingTabStrip;
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (this.a.k.getChildCount() > 0) {
                int width = this.a.k.getChildAt(0).getWidth();
                if (Math.abs(this.a.ab - i) > 1) {
                    int left = this.a.k.getChildAt(i).getLeft();
                    if (!this.a.aa) {
                        if (this.a.ab > i) {
                            this.a.aa = true;
                            if (this.a.k.getMeasuredWidth() - left > (width / 2) + this.a.getCenterPosition()) {
                                this.a.b(this.a.k.getChildAt(i));
                            }
                        } else if (this.a.ab < i) {
                            this.a.aa = true;
                            if (left > this.a.getCenterPosition() - (this.a.k.getChildAt(0).getMeasuredWidth() / 2)) {
                                this.a.a(this.a.k.getChildAt(i));
                            }
                        }
                    }
                    this.a.n = i;
                    this.a.o = f;
                    this.a.invalidate();
                } else {
                    this.a.n = i;
                    this.a.o = f;
                    this.a.a(i, (int) (((float) this.a.k.getChildAt(i).getWidth()) * f));
                    this.a.invalidate();
                }
                if (this.a.d && this.a.c && this.a.e == 1) {
                    a(i, f);
                }
                if (this.a.a != null) {
                    this.a.a.onPageScrolled(i, f, i2);
                }
                if (this.a.j != null) {
                    this.a.j.onPageScrolled(i, f, i2);
                }
            }
            this.a.ab = i;
        }

        private void a(int i, float f) {
            if ((f != 0.0f || f != 1.0f) && this.a.ac != null && this.a.ad != null) {
                float f2 = ((float) i) + f;
                int color = this.a.getResources().getColor(R.color.skin_set_localstack_tab_textcolor_startcolor);
                int color2 = this.a.getResources().getColor(R.color.skin_set_localstack_tab_textcolor_endcolor);
                int i2 = (color >> 16) & 255;
                int i3 = (color2 >> 16) & 255;
                int i4 = (color >> 8) & 255;
                int i5 = (color2 >> 8) & 255;
                color = (color >> 0) & 255;
                color2 = (color2 >> 0) & 255;
                int i6 = ((int) (((float) (i3 - i2)) * f2)) + i2;
                int i7 = ((int) (((float) (i5 - i4)) * f2)) + i4;
                int i8 = ((int) (f2 * ((float) (color2 - color)))) + color;
                i2 = (i2 + i3) - i6;
                i3 = (i5 + i4) - i7;
                color = (color + color2) - i8;
                if (i == 0) {
                    this.a.ac.setTextColor(ColorStateList.valueOf(Color.rgb(i6, i7, i8)));
                    this.a.ad.setTextColor(ColorStateList.valueOf(Color.rgb(i2, i3, color)));
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.a.aa = false;
                this.a.a(this.a.l.getCurrentItem(), 0);
            }
            if (this.a.a != null) {
                this.a.a.onPageScrollStateChanged(i);
            }
            if (this.a.j != null) {
                this.a.j.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            this.a.k.getChildAt(i).setSelected(true);
            this.a.k.getChildAt(this.a.M).setSelected(false);
            this.a.M = i;
            if (this.a.a != null) {
                this.a.a.onPageSelected(i);
            }
            if (this.a.j != null) {
                this.a.j.onPageSelected(i);
            }
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = true;
        this.d = false;
        this.e = 0;
        this.i = new b();
        this.n = 0;
        this.o = 0.0f;
        this.r = -13395457;
        this.s = 436207616;
        this.t = 436207616;
        this.u = false;
        this.v = true;
        this.w = 52;
        this.x = 8;
        this.y = 10;
        this.z = 2;
        this.A = 12;
        this.B = 15;
        this.C = 1;
        this.D = 30;
        this.E = 268435455;
        this.G = null;
        this.H = 1;
        this.I = 0;
        this.J = -1;
        this.M = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.U = new Handler(this) {
            final /* synthetic */ PagerSlidingTabStrip a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10001:
                        this.a.a((View) message.obj);
                        return;
                    case 20001:
                        this.a.b((View) message.obj);
                        return;
                    default:
                        return;
                }
            }
        };
        this.V = 1.5f;
        this.W = -1.5f;
        this.aa = false;
        setFillViewport(true);
        setWillNotDraw(false);
        this.k = new LinearLayout(context);
        this.k.setOrientation(0);
        this.k.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.k);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(1, (float) this.x, displayMetrics);
        this.y = (int) TypedValue.applyDimension(1, (float) this.y, displayMetrics);
        this.z = (int) TypedValue.applyDimension(1, (float) this.z, displayMetrics);
        this.A = (int) TypedValue.applyDimension(1, (float) this.A, displayMetrics);
        this.B = (int) TypedValue.applyDimension(1, (float) this.B, displayMetrics);
        this.C = (int) TypedValue.applyDimension(1, (float) this.C, displayMetrics);
        this.D = (int) getResources().getDimension(R.dimen.text_size_class_3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
        this.D = obtainStyledAttributes.getDimensionPixelSize(0, this.D);
        this.E = obtainStyledAttributes.getColor(1, this.E);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, com.qq.reader.c.b.PagerSlidingTabStrip);
        this.r = obtainStyledAttributes2.getColor(0, this.r);
        this.s = obtainStyledAttributes2.getColor(1, this.s);
        this.t = obtainStyledAttributes2.getColor(2, this.t);
        this.x = obtainStyledAttributes2.getDimensionPixelSize(3, this.x);
        this.y = obtainStyledAttributes2.getDimensionPixelSize(4, this.y);
        this.z = obtainStyledAttributes2.getDimensionPixelSize(5, this.z);
        this.A = obtainStyledAttributes2.getDimensionPixelSize(6, this.A);
        this.B = obtainStyledAttributes2.getDimensionPixelSize(7, this.B);
        this.J = obtainStyledAttributes2.getResourceId(9, this.J);
        this.u = obtainStyledAttributes2.getBoolean(10, this.u);
        this.w = obtainStyledAttributes2.getDimensionPixelSize(8, this.w);
        this.v = obtainStyledAttributes2.getBoolean(11, this.v);
        this.F = obtainStyledAttributes2.getColorStateList(12);
        com.qq.reader.common.monitor.debug.c.d("bluesky", "mColorStateList == " + (this.F == null));
        obtainStyledAttributes2.recycle();
        this.p = new Paint();
        this.p.setAntiAlias(true);
        this.p.setStyle(Style.FILL);
        this.q = new Paint();
        this.q.setAntiAlias(true);
        this.q.setStrokeWidth((float) this.C);
        this.g = new LayoutParams(-2, -1);
        this.h = new LayoutParams(0, -1, 1.0f);
        if (this.K == null) {
            this.K = getResources().getConfiguration().locale;
        }
        this.L = new Scroller(context);
    }

    public void setExpandedTabLayoutParams(LayoutParams layoutParams) {
        this.h = layoutParams;
    }

    public void setIndicatorResource(int i, int i2, int i3) {
        this.N = getResources().getDrawable(i);
        this.P = i3;
        this.O = i2;
    }

    public void setOffscreenPageLimit(int i) {
        if (this.l != null) {
            this.l.setOffscreenPageLimit(i);
        }
    }

    public int getCurrentPagerViewItem() {
        try {
            if (this.l != null) {
                return this.l.getCurrentItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void setViewPager(ViewPager viewPager) {
        this.l = viewPager;
        this.ab = viewPager.getCurrentItem();
        try {
            Field declaredField = ViewPager.class.getDeclaredField("m");
            declaredField.setAccessible(true);
            declaredField.set(viewPager, new a(this, viewPager.getContext(), null));
        } catch (NoSuchFieldException e) {
            f.d("PagerSlidingTabStrip", " " + e);
        } catch (IllegalArgumentException e2) {
            f.d("PagerSlidingTabStrip", " " + e2);
        } catch (IllegalAccessException e3) {
            f.d("PagerSlidingTabStrip", " " + e3);
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.i);
        a();
    }

    public void a(int i) {
        try {
            this.k.getChildAt(i).findViewById(R.id.red_icon).setVisibility(0);
        } catch (Exception e) {
        }
    }

    public void b(int i) {
        try {
            this.k.getChildAt(i).findViewById(R.id.red_icon).setVisibility(4);
        } catch (Exception e) {
        }
    }

    public void setOnPageChangeListener(e eVar) {
        this.a = eVar;
    }

    public void setOnPageChaneListenerForTitle(e eVar) {
        this.j = eVar;
    }

    public void a() {
        this.k.removeAllViews();
        this.m = this.l.getAdapter().a();
        if (this.d) {
            f();
        } else {
            for (int i = 0; i < this.m; i++) {
                a(i, ((c) this.l.getAdapter()).c(i));
            }
        }
        d();
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ PagerSlidingTabStrip a;

            {
                this.a = r1;
            }

            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                this.a.n = this.a.l.getCurrentItem();
                this.a.o = 0.0f;
                this.a.a(this.a.n, 0);
            }
        });
        this.ab = this.l.getCurrentItem();
        View childAt = this.k.getChildAt(0);
        if (childAt != null) {
            childAt.setSelected(true);
        }
        this.M = 0;
    }

    public void a(final int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagerSlidingTabStrip b;

            public void onClick(View view) {
                if (Math.abs(this.b.l.getCurrentItem() - i) > 1) {
                    this.b.l.setCurrentItem(i, false);
                } else {
                    this.b.l.setCurrentItem(i, true);
                }
            }
        });
        view.setPadding(this.B, 0, this.B, 0);
        this.k.addView(view, i, this.u ? this.h : this.g);
    }

    public void setCurrentItem(int i) {
        try {
            if (Math.abs(this.l.getCurrentItem() - i) > 1) {
                this.l.setCurrentItem(i, false);
            } else {
                this.l.setCurrentItem(i, true);
            }
        } catch (Exception e) {
        }
    }

    private void d() {
        for (int i = 0; i < this.m; i++) {
            TextView textView;
            View childAt = this.k.getChildAt(i);
            View findViewById = childAt.findViewById(R.id.tab_text);
            if (childAt instanceof TextView) {
                textView = (TextView) childAt;
                if (this.v) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.K));
                    }
                }
            }
            if (findViewById != null && (findViewById instanceof TextView)) {
                textView = (TextView) findViewById;
                if (this.ac == null || this.ad == null) {
                    if (this.F != null) {
                        textView.setTextColor(this.F);
                    } else {
                        textView.setTextColor(this.E);
                    }
                }
                textView.setTextSize(0, (float) this.D);
            }
        }
    }

    private void a(int i, int i2) {
        if (this.m != 0) {
            int b = b(i, i2);
            if (i > 0 || i2 > 0) {
                b -= this.w;
            }
            if (b != this.I) {
                this.I = b;
                scrollTo(b, 0);
            }
        }
    }

    private int getCenterPosition() {
        return getMeasuredWidth() / 2;
    }

    private int b(int i, int i2) {
        int left = this.k.getChildAt(i).getLeft() + i2;
        if (((float) left) > ((float) getCenterPosition()) - (((float) this.k.getChildAt(0).getMeasuredWidth()) * 1.5f)) {
            return (int) (((float) left) - (((float) getCenterPosition()) - (((float) this.k.getChildAt(0).getMeasuredWidth()) * 1.5f)));
        }
        return 0;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.m != 0) {
            int height = getHeight();
            this.p.setColor(this.r);
            View childAt = this.k.getChildAt(this.n);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            float left2;
            if (this.Q == 0) {
                if (this.o > 0.0f && this.n < this.m - 1) {
                    View childAt2 = this.k.getChildAt(this.n + 1);
                    left2 = (float) childAt2.getLeft();
                    left = (left * (1.0f - this.o)) + (left2 * this.o);
                    right = (right * (1.0f - this.o)) + (((float) childAt2.getRight()) * this.o);
                }
                if (this.N != null) {
                    this.N.setBounds((int) (left + ((float) this.R)), this.O + 0, (int) (right - ((float) this.S)), height - this.P);
                    this.N.draw(canvas);
                    return;
                }
                canvas.drawRect(left + ((float) this.R), (float) ((height - this.x) - this.y), right - ((float) this.S), (float) (height - this.y), this.p);
                return;
            }
            if (this.o > 0.0f && this.n < this.m - 1) {
                View childAt3 = this.k.getChildAt(this.n + 1);
                float left3 = (float) childAt3.getLeft();
                left = ((left * (1.0f - this.o)) + (left3 * this.o)) + (((float) this.Q) * (1.0f - this.o));
                left2 = ((right * (1.0f - this.o)) + (((float) childAt3.getRight()) * this.o)) - (this.o * ((float) this.Q));
            } else if (this.n == 0) {
                left += (float) this.Q;
                left2 = right;
            } else if (this.n == 1) {
                left2 = right - ((float) this.Q);
            } else {
                left2 = right;
            }
            if (this.N != null) {
                this.N.setBounds((int) left, this.O + 0, (int) left2, height - this.P);
                this.N.draw(canvas);
                return;
            }
            canvas.drawRect(left, 0.0f, left2, (float) height, this.p);
        }
    }

    public void setLinePadding(int i) {
        if (this.k.getChildCount() == 2) {
            this.Q = i;
        }
    }

    public void setLineRightAndLeftPadding(int i, int i2) {
        if (!this.d || !this.c) {
            this.R = i;
            this.S = i2;
        }
    }

    public void setIndicatorBottomPadding(int i) {
        if (!this.d || !this.c) {
            this.y = i;
        }
    }

    private void a(View view) {
        if (this.aa) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int centerPosition = getCenterPosition() - (view.getWidth() / 2);
            int i = (int) (this.V * 16.0f);
            if (iArr[0] == centerPosition) {
                this.aa = false;
                return;
            }
            if (iArr[0] < centerPosition) {
                i = (-centerPosition) + iArr[0];
            }
            iArr = new int[2];
            View childAt = this.k.getChildAt(this.k.getChildCount() - 1);
            childAt.getLocationInWindow(iArr);
            if (iArr[0] <= (getCenterPosition() * 2) - childAt.getMeasuredWidth()) {
                this.aa = false;
                return;
            }
            scrollBy(i, 0);
            invalidate();
            this.T = new Message();
            this.T.obj = view;
            this.T.what = 10001;
            this.U.removeCallbacksAndMessages(this.T);
            this.U.sendMessageDelayed(this.T, 16);
        }
    }

    private void b(View view) {
        if (this.aa) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int centerPosition = getCenterPosition() - (view.getWidth() / 2);
            int i = (int) (this.W * 16.0f);
            if (iArr[0] == centerPosition) {
                this.aa = false;
                return;
            }
            if (iArr[0] > centerPosition) {
                i = (-centerPosition) + iArr[0];
            }
            iArr = new int[2];
            this.k.getChildAt(0).getLocationInWindow(iArr);
            if (iArr[0] >= 0) {
                this.aa = false;
                return;
            }
            scrollBy(i, 0);
            invalidate();
            this.T = new Message();
            this.T.obj = view;
            this.T.what = 20001;
            this.U.removeCallbacksAndMessages(this.T);
            this.U.sendMessageDelayed(this.T, 16);
        }
    }

    public void setIndicatorColor(int i) {
        this.r = i;
        com.qq.reader.common.monitor.debug.c.a("ind", "setIndicatorColor " + i);
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.r = getResources().getColor(i);
        com.qq.reader.common.monitor.debug.c.a("ind", "setIndicatorColorResource " + this.r);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.r;
    }

    public void setIndicatorHeight(int i) {
        this.x = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.x;
    }

    public void setUnderlineColor(int i) {
        if (!this.d || !this.c) {
            this.s = i;
            invalidate();
        }
    }

    public void setUnderlineColorResource(int i) {
        if (!this.d || !this.c) {
            this.s = getResources().getColor(i);
            invalidate();
        }
    }

    public int getUnderlineColor() {
        return this.s;
    }

    public void setDividerColor(int i) {
        this.t = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.t = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.t;
    }

    public void setUnderlineHeight(int i) {
        if (!this.d || !this.c) {
            this.z = i;
            invalidate();
        }
    }

    public int getUnderlineHeight() {
        return this.z;
    }

    public void setDividerPadding(int i) {
        this.A = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.A;
    }

    public void setScrollOffset(int i) {
        this.w = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.w;
    }

    public void setShouldExpand(boolean z) {
        if (!this.d || !this.c) {
            this.u = z;
            requestLayout();
        }
    }

    public boolean getShouldExpand() {
        return this.u;
    }

    public void setAllCaps(boolean z) {
        this.v = z;
    }

    public void setTextSize(int i) {
        this.D = i;
        d();
    }

    public void setTextSizeAndColor(int i, int i2, int i3) {
        for (int i4 = 0; i4 < this.m; i4++) {
            View findViewById = this.k.getChildAt(i4).findViewById(R.id.tab_text);
            if (findViewById != null && (findViewById instanceof TextView)) {
                TextView textView = (TextView) findViewById;
                if (i == i4) {
                    textView.setTextSize(0, (float) i2);
                    textView.setTextColor(i3);
                } else {
                    textView.setTextSize(0, (float) this.D);
                    textView.setTextColor(this.E);
                }
            }
        }
    }

    public int getTextSize() {
        return this.D;
    }

    public void setTextColor(int i) {
        this.E = i;
        d();
    }

    public void setTextColorResource(int i) {
        this.E = getResources().getColor(i);
        d();
    }

    public void setTextColorStateList(int i) {
        this.F = getResources().getColorStateList(i);
        d();
    }

    public int getTextColor() {
        return this.E;
    }

    public void setTypeface(Typeface typeface, int i) {
        this.G = typeface;
        this.H = i;
        d();
    }

    public void setTabBackground(int i) {
        this.J = i;
    }

    public int getTabBackground() {
        return this.J;
    }

    public void setTabPaddingLeftRight(int i) {
        this.B = i;
        d();
    }

    public int getTabPaddingLeftRight() {
        return this.B;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.n = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.n;
        return savedState;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean b() {
        if (this.N != null) {
            return true;
        }
        return false;
    }

    public void a(int i, ArrayList<TabInfo> arrayList) {
        if (arrayList != null && arrayList.size() > 0 && i > 0 && i < 3) {
            this.f = arrayList;
            this.e = i;
            this.c = false;
            this.d = true;
            e();
        }
    }

    private void e() {
        int i = 0;
        if (1 == this.e) {
            setBackgroundColor(getResources().getColor(17170445));
            setIndicatorResource(R.drawable.stacktab_flip, getResources().getDimensionPixelOffset(R.dimen.common_dp_5), getResources().getDimensionPixelOffset(R.dimen.common_dp_5));
            setLineRightAndLeftPadding(0, 0);
            setTabPaddingLeftRight(0);
            setShouldExpand(false);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
            setLayoutParams(layoutParams);
            this.c = true;
            c();
        } else if (2 == this.e) {
            setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
            setIndicatorBottomPadding(0);
            int size = this.f.size();
            int i2 = com.qq.reader.common.c.a.bU / size;
            int i3 = i2 / 8;
            if (size == 2 || size == 3) {
                i3 = (i2 - getResources().getDimensionPixelOffset(R.dimen.common_dp_80)) / 2;
            }
            setLineRightAndLeftPadding(i3, i3);
            setIndicatorColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_common_textcolor));
            setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.common_dp_2));
            ViewGroup viewGroup = (ViewGroup) getParent();
            ViewGroup.LayoutParams layoutParams2 = viewGroup.getLayoutParams();
            i2 = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.bookstore_titlerbar_height);
            ViewGroup.LayoutParams layoutParams3 = layoutParams2;
            ViewGroup viewGroup2 = viewGroup;
            ViewGroup.LayoutParams layoutParams4 = layoutParams3;
            while (i < 2) {
                if (layoutParams4.height == dimensionPixelOffset) {
                    layoutParams4.height = i2;
                }
                viewGroup = (ViewGroup) viewGroup2.getParent();
                i++;
                viewGroup2 = viewGroup;
                layoutParams4 = viewGroup.getLayoutParams();
            }
            this.c = true;
            c();
        }
    }

    public void c() {
        if (this.l != null) {
            a();
        }
    }

    private void f() {
        int i = 0;
        TabInfo tabInfo;
        View inflate;
        if (1 == this.e) {
            tabInfo = (TabInfo) this.f.get(0);
            inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.lbsstackactivtiy_tab_leftitem, null);
            this.ac = (TextView) inflate.findViewById(R.id.tab_text);
            this.ac.setText(tabInfo.title);
            a(0, inflate);
            tabInfo = (TabInfo) this.f.get(1);
            View inflate2 = View.inflate(ReaderApplication.getApplicationImp(), R.layout.lbsstackactivtiy_tab_rightitem, null);
            this.ad = (TextView) inflate2.findViewById(R.id.tab_text);
            this.ad.setText(tabInfo.title);
            a(1, inflate2);
        } else if (2 == this.e) {
            while (i < this.f.size()) {
                tabInfo = (TabInfo) this.f.get(i);
                inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.profileaccount_tab_item, null);
                ((TextView) inflate.findViewById(R.id.tab_text)).setText(tabInfo.title);
                a(i, inflate);
                i++;
            }
        }
    }
}
