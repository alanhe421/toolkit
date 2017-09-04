package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.tencent.theme.SkinEngine;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.ref.WeakReference;

public class PagerTitleStrip extends ViewGroup implements a {
    private static final int[] n = new int[]{16842804, 16842901, 16842904, 16842927};
    private static final int[] o = new int[]{16843660};
    private static final b q;
    ViewPager a;
    TextView b;
    TextView c;
    TextView d;
    int e;
    private int f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private final a l;
    private WeakReference<PagerAdapter> m;
    private int p;

    private class a extends DataSetObserver implements d, e {
        final /* synthetic */ PagerTitleStrip a;
        private int b;

        private a(PagerTitleStrip pagerTitleStrip) {
            this.a = pagerTitleStrip;
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            this.a.a(i, f, false);
        }

        public void onPageSelected(int i) {
            float f = 0.0f;
            if (this.b == 0) {
                this.a.a(this.a.a.getCurrentItem(), this.a.a.getAdapter());
                if (this.a.g >= 0.0f) {
                    f = this.a.g;
                }
                this.a.a(this.a.a.getCurrentItem(), f, true);
            }
        }

        public void onPageScrollStateChanged(int i) {
            this.b = i;
        }

        public void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            this.a.a(pagerAdapter, pagerAdapter2);
        }

        public void onChanged() {
            float f = 0.0f;
            this.a.a(this.a.a.getCurrentItem(), this.a.a.getAdapter());
            if (this.a.g >= 0.0f) {
                f = this.a.g;
            }
            this.a.a(this.a.a.getCurrentItem(), f, true);
        }
    }

    interface b {
        void a(TextView textView);
    }

    static class c implements b {
        c() {
        }

        public void a(TextView textView) {
            textView.setSingleLine();
        }
    }

    static class d implements b {
        d() {
        }

        public void a(TextView textView) {
            v.a(textView);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            q = new d();
        } else {
            q = new c();
        }
    }

    private static void setSingleLineAllCaps(TextView textView) {
        q.a(textView);
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        boolean z = false;
        super(context, attributeSet);
        this.f = -1;
        this.g = -1.0f;
        this.l = new a();
        View textView = new TextView(context);
        this.b = textView;
        addView(textView);
        textView = new TextView(context);
        this.c = textView;
        addView(textView);
        textView = new TextView(context);
        this.d = textView;
        addView(textView);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, n);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.b.setTextAppearance(context, resourceId);
            this.c.setTextAppearance(context, resourceId);
            this.d.setTextAppearance(context, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, (float) dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            dimensionPixelSize = obtainStyledAttributes.getColor(2, 0);
            this.b.setTextColor(dimensionPixelSize);
            this.c.setTextColor(dimensionPixelSize);
            this.d.setTextColor(dimensionPixelSize);
        }
        this.i = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.e = this.c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.b.setEllipsize(TruncateAt.END);
        this.c.setEllipsize(TruncateAt.END);
        this.d.setEllipsize(TruncateAt.END);
        if (resourceId != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId, o);
            z = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.b);
            setSingleLineAllCaps(this.c);
            setSingleLineAllCaps(this.d);
        } else {
            this.b.setSingleLine();
            this.c.setSingleLine();
            this.d.setSingleLine();
        }
        this.h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    public void setTextSpacing(int i) {
        this.h = i;
        requestLayout();
    }

    public int getTextSpacing() {
        return this.h;
    }

    public void setNonPrimaryAlpha(float f) {
        this.p = ((int) (255.0f * f)) & 255;
        int i = (this.p << 24) | (this.e & SkinEngine.TYPE_FILE);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
    }

    public void setTextColor(int i) {
        this.e = i;
        this.c.setTextColor(i);
        int i2 = (this.p << 24) | (this.e & SkinEngine.TYPE_FILE);
        this.b.setTextColor(i2);
        this.d.setTextColor(i2);
    }

    public void setTextSize(int i, float f) {
        this.b.setTextSize(i, f);
        this.c.setTextSize(i, f);
        this.d.setTextSize(i, f);
    }

    public void setGravity(int i) {
        this.i = i;
        requestLayout();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            PagerAdapter adapter = viewPager.getAdapter();
            viewPager.b(this.l);
            viewPager.setOnAdapterChangeListener(this.l);
            this.a = viewPager;
            a(this.m != null ? (PagerAdapter) this.m.get() : null, adapter);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            a(this.a.getAdapter(), null);
            this.a.b(null);
            this.a.setOnAdapterChangeListener(null);
            this.a = null;
        }
    }

    void a(int i, PagerAdapter pagerAdapter) {
        CharSequence charSequence;
        CharSequence charSequence2 = null;
        int a = pagerAdapter != null ? pagerAdapter.a() : 0;
        this.j = true;
        if (i < 1 || pagerAdapter == null) {
            charSequence = null;
        } else {
            charSequence = pagerAdapter.a(i - 1);
        }
        this.b.setText(charSequence);
        TextView textView = this.c;
        if (pagerAdapter == null || i >= a) {
            charSequence = null;
        } else {
            charSequence = pagerAdapter.a(i);
        }
        textView.setText(charSequence);
        if (i + 1 < a && pagerAdapter != null) {
            charSequence2 = pagerAdapter.a(i + 1);
        }
        this.d.setText(charSequence2);
        a = MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.b.measure(a, makeMeasureSpec);
        this.c.measure(a, makeMeasureSpec);
        this.d.measure(a, makeMeasureSpec);
        this.f = i;
        if (!this.k) {
            a(i, this.g, false);
        }
        this.j = false;
    }

    public void requestLayout() {
        if (!this.j) {
            super.requestLayout();
        }
    }

    void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.b(this.l);
            this.m = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.a(this.l);
            this.m = new WeakReference(pagerAdapter2);
        }
        if (this.a != null) {
            this.f = -1;
            this.g = -1.0f;
            a(this.a.getCurrentItem(), pagerAdapter2);
            requestLayout();
        }
    }

    void a(int i, float f, boolean z) {
        if (i != this.f) {
            a(i, this.a.getAdapter());
        } else if (!z && f == this.g) {
            return;
        }
        this.k = true;
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredWidth2 = this.c.getMeasuredWidth();
        int measuredWidth3 = this.d.getMeasuredWidth();
        int i2 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i3 = paddingRight + i2;
        int i4 = (width - (paddingLeft + i2)) - i3;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        i4 = ((width - i3) - ((int) (f2 * ((float) i4)))) - (measuredWidth2 / 2);
        i3 = i4 + measuredWidth2;
        i2 = this.b.getBaseline();
        measuredWidth2 = this.c.getBaseline();
        int baseline = this.d.getBaseline();
        int max = Math.max(Math.max(i2, measuredWidth2), baseline);
        i2 = max - i2;
        measuredWidth2 = max - measuredWidth2;
        baseline = max - baseline;
        int measuredHeight = this.d.getMeasuredHeight() + baseline;
        max = Math.max(Math.max(this.b.getMeasuredHeight() + i2, this.c.getMeasuredHeight() + measuredWidth2), measuredHeight);
        switch (this.i & 112) {
            case 16:
                paddingTop = (((height - paddingTop) - paddingBottom) - max) / 2;
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
            case Opcodes.APUT_CHAR /*80*/:
                paddingTop = (height - paddingBottom) - max;
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
            default:
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
        }
        this.c.layout(i4, measuredWidth2, i3, this.c.getMeasuredHeight() + measuredWidth2);
        measuredWidth2 = Math.min(paddingLeft, (i4 - this.h) - measuredWidth);
        this.b.layout(measuredWidth2, height, measuredWidth + measuredWidth2, this.b.getMeasuredHeight() + height);
        measuredWidth2 = Math.max((width - paddingRight) - measuredWidth3, this.h + i3);
        this.d.layout(measuredWidth2, i2, measuredWidth2 + measuredWidth3, this.d.getMeasuredHeight() + i2);
        this.g = f;
        this.k = false;
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
        int size = MeasureSpec.getSize(i);
        int childMeasureSpec2 = getChildMeasureSpec(i, (int) (((float) size) * 0.2f), -2);
        this.b.measure(childMeasureSpec2, childMeasureSpec);
        this.c.measure(childMeasureSpec2, childMeasureSpec);
        this.d.measure(childMeasureSpec2, childMeasureSpec);
        if (MeasureSpec.getMode(i2) == 1073741824) {
            paddingTop = MeasureSpec.getSize(i2);
        } else {
            paddingTop = Math.max(getMinHeight(), paddingTop + this.c.getMeasuredHeight());
        }
        setMeasuredDimension(size, z.a(paddingTop, i2, z.k(this.c) << 16));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f = 0.0f;
        if (this.a != null) {
            if (this.g >= 0.0f) {
                f = this.g;
            }
            a(this.f, f, true);
        }
    }

    int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }
}
