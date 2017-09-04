package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.widget.k;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.qq.reader.c.b;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagContainerLayout extends ViewGroup {
    private int A;
    private float B;
    private com.qq.reader.view.TagView.a C;
    private Paint D;
    private RectF E;
    private k F;
    private List<View> G;
    private int[] H;
    private int I;
    private int J;
    private int K;
    private boolean L;
    private float M;
    private float N;
    private int O;
    private float P;
    private boolean Q;
    private int a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private float g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private float n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private Typeface w;
    private boolean x;
    private List<String> y;
    private boolean z;

    private class a extends android.support.v4.widget.k.a {
        final /* synthetic */ TagContainerLayout a;

        private a(TagContainerLayout tagContainerLayout) {
            this.a = tagContainerLayout;
        }

        public void a(int i) {
            super.a(i);
            this.a.A = i;
        }

        public boolean a(View view, int i) {
            this.a.requestDisallowInterceptTouchEvent(true);
            return this.a.z;
        }

        public int a(View view, int i, int i2) {
            return Math.min(Math.max(i, this.a.getPaddingLeft()), (this.a.getWidth() - view.getWidth()) - this.a.getPaddingRight());
        }

        public int b(View view, int i, int i2) {
            return Math.min(Math.max(i, this.a.getPaddingTop()), (this.a.getHeight() - view.getHeight()) - this.a.getPaddingBottom());
        }

        public int a(View view) {
            return this.a.getMeasuredWidth() - view.getMeasuredWidth();
        }

        public int b(View view) {
            return this.a.getMeasuredHeight() - view.getMeasuredHeight();
        }

        public void a(View view, float f, float f2) {
            super.a(view, f, f2);
            this.a.requestDisallowInterceptTouchEvent(false);
            int[] a = this.a.a(view);
            this.a.a(view, this.a.a(a[0], a[1]), ((Integer) view.getTag()).intValue());
            this.a.F.a(a[0], a[1]);
            this.a.invalidate();
        }
    }

    public void setTagRippleAnimationState(boolean z) {
        this.Q = z;
    }

    public TagContainerLayout(Context context) {
        this(context, null);
    }

    public TagContainerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagContainerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = -1;
        this.b = -16776961;
        this.e = 0.5f;
        this.f = 0.0f;
        this.g = 1.0f;
        this.i = Color.parseColor("#FFFFFF");
        this.j = Color.parseColor("#FFFFFF");
        this.k = 3;
        this.l = 0;
        this.m = 23;
        this.n = 0.5f;
        this.o = 15.0f;
        this.p = 14.0f;
        this.q = 3;
        this.r = 10;
        this.s = 8;
        this.t = Color.parseColor("#88F44336");
        this.u = Color.parseColor("#FFFFFF");
        this.v = Color.parseColor("#FF666666");
        this.w = Typeface.DEFAULT;
        this.A = 0;
        this.B = 2.75f;
        this.I = 1000;
        this.K = 128;
        this.L = false;
        this.M = 0.0f;
        this.N = 10.0f;
        this.O = WebView.NIGHT_MODE_COLOR;
        this.P = 1.0f;
        this.Q = false;
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.AndroidTagView, i, 0);
        this.c = (int) obtainStyledAttributes.getDimension(0, a(context, 5.0f));
        this.d = (int) obtainStyledAttributes.getDimension(1, a(context, 5.0f));
        this.e = obtainStyledAttributes.getDimension(2, a(context, this.e));
        this.f = obtainStyledAttributes.getDimension(3, a(context, this.f));
        this.B = obtainStyledAttributes.getDimension(15, a(context, this.B));
        this.i = obtainStyledAttributes.getColor(4, this.i);
        this.j = obtainStyledAttributes.getColor(5, this.j);
        this.z = obtainStyledAttributes.getBoolean(6, false);
        this.g = obtainStyledAttributes.getFloat(7, this.g);
        this.k = obtainStyledAttributes.getInt(9, this.k);
        this.l = obtainStyledAttributes.getInt(8, this.l);
        this.m = obtainStyledAttributes.getInt(21, this.m);
        this.n = obtainStyledAttributes.getDimension(10, a(context, this.n));
        this.o = obtainStyledAttributes.getDimension(11, a(context, this.o));
        this.r = (int) obtainStyledAttributes.getDimension(12, a(context, (float) this.r));
        this.s = (int) obtainStyledAttributes.getDimension(13, a(context, (float) this.s));
        this.p = obtainStyledAttributes.getDimension(14, b(context, this.p));
        this.t = obtainStyledAttributes.getColor(18, this.t);
        this.u = obtainStyledAttributes.getColor(19, this.u);
        this.v = obtainStyledAttributes.getColor(16, this.v);
        this.q = obtainStyledAttributes.getInt(23, this.q);
        this.x = obtainStyledAttributes.getBoolean(22, false);
        this.J = obtainStyledAttributes.getColor(24, Color.parseColor("#EEEEEE"));
        this.K = obtainStyledAttributes.getInteger(25, this.K);
        this.I = obtainStyledAttributes.getInteger(26, this.I);
        this.L = obtainStyledAttributes.getBoolean(27, this.L);
        this.M = obtainStyledAttributes.getDimension(28, a(context, this.M));
        this.N = obtainStyledAttributes.getDimension(31, a(context, this.N));
        this.O = obtainStyledAttributes.getColor(29, this.O);
        this.P = obtainStyledAttributes.getDimension(30, a(context, this.P));
        this.a = obtainStyledAttributes.getColor(17, this.a);
        this.b = obtainStyledAttributes.getColor(20, this.b);
        obtainStyledAttributes.recycle();
        this.D = new Paint(1);
        this.E = new RectF();
        this.G = new ArrayList();
        this.F = k.a(this, this.g, new a());
        setWillNotDraw(false);
        setTagMaxLength(this.m);
        setTagHorizontalPadding(this.r);
        setTagVerticalPadding(this.s);
        if (isInEditMode()) {
            a("sample tag");
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
        int childCount = getChildCount();
        int a = childCount == 0 ? 0 : a(childCount);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i2);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (mode == Integer.MIN_VALUE || mode == 0) {
            setMeasuredDimension(size, (((a * (this.c + this.h)) - this.c) + getPaddingTop()) + getPaddingBottom());
        } else {
            setMeasuredDimension(size, size2);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.E.set(0.0f, 0.0f, (float) i, (float) i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int measuredWidth2 = getMeasuredWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingLeft = getPaddingLeft();
            int i5 = 0;
            this.H = new int[(childCount * 2)];
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                if (childAt.getVisibility() != 8) {
                    int measuredWidth3 = childAt.getMeasuredWidth();
                    if (this.k == 5) {
                        if (measuredWidth2 - measuredWidth3 < getPaddingLeft()) {
                            measuredWidth2 = getMeasuredWidth() - getPaddingRight();
                            paddingTop += this.h + this.c;
                        }
                        this.H[i6 * 2] = measuredWidth2 - measuredWidth3;
                        this.H[(i6 * 2) + 1] = paddingTop;
                        measuredWidth2 -= this.d + measuredWidth3;
                    } else if (this.k == 17) {
                        int i7;
                        if ((paddingLeft + measuredWidth3) - getPaddingLeft() > measuredWidth) {
                            paddingLeft = ((getMeasuredWidth() - this.H[(i6 - 1) * 2]) - getChildAt(i6 - 1).getMeasuredWidth()) - getPaddingRight();
                            while (i5 < i6) {
                                this.H[i5 * 2] = this.H[i5 * 2] + (paddingLeft / 2);
                                i5++;
                            }
                            i5 = getPaddingLeft();
                            paddingLeft = paddingTop + (this.h + this.c);
                            paddingTop = i6;
                        } else {
                            i7 = i5;
                            i5 = paddingLeft;
                            paddingLeft = paddingTop;
                            paddingTop = i7;
                        }
                        this.H[i6 * 2] = i5;
                        this.H[(i6 * 2) + 1] = paddingLeft;
                        i5 += measuredWidth3 + this.d;
                        if (i6 == childCount - 1) {
                            measuredWidth3 = ((getMeasuredWidth() - this.H[i6 * 2]) - childAt.getMeasuredWidth()) - getPaddingRight();
                            for (int i8 = paddingTop; i8 < childCount; i8++) {
                                this.H[i8 * 2] = this.H[i8 * 2] + (measuredWidth3 / 2);
                            }
                            i7 = paddingTop;
                            paddingTop = paddingLeft;
                            paddingLeft = i5;
                            i5 = i7;
                        } else {
                            i7 = paddingTop;
                            paddingTop = paddingLeft;
                            paddingLeft = i5;
                            i5 = i7;
                        }
                    } else {
                        if ((paddingLeft + measuredWidth3) - getPaddingLeft() > measuredWidth) {
                            paddingLeft = getPaddingLeft();
                            paddingTop += this.h + this.c;
                        }
                        this.H[i6 * 2] = paddingLeft;
                        this.H[(i6 * 2) + 1] = paddingTop;
                        paddingLeft += this.d + measuredWidth3;
                    }
                }
            }
            for (paddingTop = 0; paddingTop < this.H.length / 2; paddingTop++) {
                View childAt2 = getChildAt(paddingTop);
                childAt2.layout(this.H[paddingTop * 2], this.H[(paddingTop * 2) + 1], this.H[paddingTop * 2] + childAt2.getMeasuredWidth(), this.H[(paddingTop * 2) + 1] + this.h);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.D.setStyle(Style.FILL);
        this.D.setColor(this.j);
        canvas.drawRoundRect(this.E, this.f, this.f, this.D);
        this.D.setStyle(Style.STROKE);
        this.D.setStrokeWidth(this.e);
        this.D.setColor(this.i);
        canvas.drawRoundRect(this.E, this.f, this.f, this.D);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.F.a(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.F.b(motionEvent);
        return true;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.F.a(true)) {
            requestLayout();
        }
    }

    private int a(int i) {
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            View childAt = getChildAt(i4);
            int measuredWidth2 = childAt.getMeasuredWidth() + this.d;
            int measuredHeight = childAt.getMeasuredHeight();
            if (i4 != 0) {
                measuredHeight = Math.min(this.h, measuredHeight);
            }
            this.h = measuredHeight;
            measuredHeight = i3 + measuredWidth2;
            if (measuredHeight - this.d > measuredWidth) {
                int i5 = measuredWidth2;
                measuredWidth2 = i2 + 1;
                measuredHeight = i5;
            } else {
                measuredWidth2 = i2;
            }
            i3 = measuredHeight;
            i4++;
            i2 = measuredWidth2;
        }
        return this.l <= 0 ? i2 : this.l;
    }

    private int[] b() {
        int tagBackgroundColor = getTagBackgroundColor();
        int tagBorderColor = getTagBorderColor();
        int tagTextColor = getTagTextColor();
        return new int[]{tagBackgroundColor, tagBorderColor, tagTextColor};
    }

    private void c() {
        if (this.y == null) {
            throw new RuntimeException("NullPointer exception!");
        }
        a();
        if (this.y.size() != 0) {
            for (int i = 0; i < this.y.size(); i++) {
                b((String) this.y.get(i), this.G.size());
            }
            postInvalidate();
        }
    }

    private void b(String str, int i) {
        if (i < 0 || i > this.G.size()) {
            throw new RuntimeException("Illegal position!");
        }
        TagView tagView = new TagView(getContext(), str);
        a(tagView);
        this.G.add(i, tagView);
        if (i < this.G.size()) {
            for (int i2 = i; i2 < this.G.size(); i2++) {
                ((View) this.G.get(i2)).setTag(Integer.valueOf(i2));
            }
        } else {
            tagView.setTag(Integer.valueOf(i));
        }
        addView(tagView, i);
    }

    private void a(TagView tagView) {
        int[] b = b();
        tagView.setTagBackgroundColor(b[0]);
        tagView.setTagBorderColor(b[1]);
        tagView.setTagTextColor(b[2]);
        tagView.setTagMaxLength(this.m);
        tagView.setTextDirection(this.q);
        tagView.setTypeface(this.w);
        tagView.setBorderWidth(this.n);
        tagView.setBorderRadius(this.o);
        tagView.setTextSize(this.p);
        tagView.setHorizontalPadding(this.r);
        tagView.setVerticalPadding(this.s);
        tagView.setIsViewClickable(this.x);
        tagView.setBdDistance(this.B);
        tagView.setOnTagClickListener(this.C);
        tagView.setRippleAlpha(this.K);
        tagView.setRippleColor(this.J);
        tagView.setRippleDuration(this.I);
        tagView.setEnableCross(this.L);
        tagView.setCrossAreaWidth(this.M);
        tagView.setCrossAreaPadding(this.N);
        tagView.setCrossColor(this.O);
        tagView.setCrossLineWidth(this.P);
        tagView.setRippleAnimationState(getTagRippleAnimationState());
        tagView.setPressBackgroundColor(this.a);
        tagView.setPressTextColor(this.b);
        tagView.setClickable(true);
    }

    private void d() {
        for (View view : this.G) {
            ((TagView) view).setOnTagClickListener(this.C);
        }
    }

    private int[] a(View view) {
        int i;
        int left = view.getLeft();
        int top = view.getTop();
        int i2 = this.H[((Integer) view.getTag()).intValue() * 2];
        int i3 = this.H[(((Integer) view.getTag()).intValue() * 2) + 1];
        int i4 = i3;
        i3 = Math.abs(top - i3);
        for (i = 0; i < this.H.length / 2; i++) {
            if (Math.abs(top - this.H[(i * 2) + 1]) < i3) {
                i4 = this.H[(i * 2) + 1];
                i3 = Math.abs(top - this.H[(i * 2) + 1]);
            }
        }
        i3 = 0;
        top = i2;
        i2 = 0;
        for (i = 0; i < this.H.length / 2; i++) {
            if (this.H[(i * 2) + 1] == i4) {
                if (i2 == 0) {
                    top = this.H[i * 2];
                    i3 = Math.abs(left - top);
                } else if (Math.abs(left - this.H[i * 2]) < i3) {
                    top = this.H[i * 2];
                    i3 = Math.abs(left - top);
                }
                i2++;
            }
        }
        return new int[]{top, i4};
    }

    private int a(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.H.length / 2) {
            if (i == this.H[i3 * 2] && i2 == this.H[(i3 * 2) + 1]) {
                i4 = i3;
            }
            i3++;
        }
        return i4;
    }

    private void a(View view, int i, int i2) {
        this.G.remove(i2);
        this.G.add(i, view);
        for (View view2 : this.G) {
            view2.setTag(Integer.valueOf(this.G.indexOf(view2)));
        }
        removeViewAt(i2);
        addView(view, i);
    }

    private int e() {
        return (int) Math.ceil((double) this.n);
    }

    public boolean getTagRippleAnimationState() {
        return this.Q;
    }

    public int getTagViewState() {
        return this.A;
    }

    public float getTagBdDistance() {
        return this.B;
    }

    public void setTagBdDistance(float f) {
        this.B = a(getContext(), f);
    }

    public float a(Context context, float f) {
        return (context.getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    public float b(Context context, float f) {
        return context.getResources().getDisplayMetrics().scaledDensity * f;
    }

    public void setTags(List<String> list) {
        this.y = list;
        c();
    }

    public void setTags(String... strArr) {
        this.y = Arrays.asList(strArr);
        c();
    }

    public void a(String str) {
        a(str, this.G.size());
    }

    public void a(String str, int i) {
        b(str, i);
        postInvalidate();
    }

    public void a() {
        this.G.clear();
        removeAllViews();
        postInvalidate();
    }

    public void setOnTagClickListener(com.qq.reader.view.TagView.a aVar) {
        this.C = aVar;
        d();
    }

    public List<String> getTags() {
        List<String> arrayList = new ArrayList();
        for (View view : this.G) {
            if (view instanceof TagView) {
                arrayList.add(((TagView) view).getText());
            }
        }
        return arrayList;
    }

    public void setDragEnable(boolean z) {
        this.z = z;
    }

    public boolean getDragEnable() {
        return this.z;
    }

    public void setVerticalInterval(float f) {
        this.c = (int) a(getContext(), f);
        postInvalidate();
    }

    public int getVerticalInterval() {
        return this.c;
    }

    public void setHorizontalInterval(float f) {
        this.d = (int) a(getContext(), f);
        postInvalidate();
    }

    public int getHorizontalInterval() {
        return this.d;
    }

    public float getBorderWidth() {
        return this.e;
    }

    public void setBorderWidth(float f) {
        this.e = f;
    }

    public float getBorderRadius() {
        return this.f;
    }

    public void setBorderRadius(float f) {
        this.f = f;
    }

    public int getBorderColor() {
        return this.i;
    }

    public void setBorderColor(int i) {
        this.i = i;
    }

    public int getBackgroundColor() {
        return this.j;
    }

    public void setBackgroundColor(int i) {
        this.j = i;
    }

    public int getGravity() {
        return this.k;
    }

    public void setGravity(int i) {
        this.k = i;
    }

    public float getSensitivity() {
        return this.g;
    }

    public void setSensitivity(float f) {
        this.g = f;
    }

    public void setMaxLines(int i) {
        this.l = i;
        postInvalidate();
    }

    public int getMaxLines() {
        return this.l;
    }

    public void setTagMaxLength(int i) {
        if (i < 3) {
            i = 3;
        }
        this.m = i;
    }

    public int getTagMaxLength() {
        return this.m;
    }

    public boolean getIsTagViewClickable() {
        return this.x;
    }

    public void setIsTagViewClickable(boolean z) {
        this.x = z;
    }

    public float getTagBorderWidth() {
        return this.n;
    }

    public void setTagBorderWidth(float f) {
        this.n = f;
    }

    public float getTagBorderRadius() {
        return this.o;
    }

    public void setTagBorderRadius(float f) {
        this.o = f;
    }

    public float getTagTextSize() {
        return this.p;
    }

    public void setTagTextSize(float f) {
        this.p = f;
    }

    public int getTagHorizontalPadding() {
        return this.r;
    }

    public void setTagHorizontalPadding(int i) {
        int e = e();
        if (i < e) {
            i = e;
        }
        this.r = i;
    }

    public int getTagVerticalPadding() {
        return this.s;
    }

    public void setTagVerticalPadding(int i) {
        int e = e();
        if (i < e) {
            i = e;
        }
        this.s = i;
    }

    public int getTagBorderColor() {
        return this.t;
    }

    public void setTagBorderColor(int i) {
        this.t = i;
    }

    public int getTagBackgroundColor() {
        return this.u;
    }

    public void setTagBackgroundColor(int i) {
        this.u = i;
    }

    public int getTagTextColor() {
        return this.v;
    }

    public void setTagTextDirection(int i) {
        this.q = i;
    }

    public Typeface getTagTypeface() {
        return this.w;
    }

    public void setTagTypeface(Typeface typeface) {
        this.w = typeface;
    }

    public int getTagTextDirection() {
        return this.q;
    }

    public void setTagTextColor(int i) {
        this.v = i;
    }

    public int getRippleAlpha() {
        return this.K;
    }

    public void setRippleAlpha(int i) {
        this.K = i;
    }

    public int getRippleColor() {
        return this.J;
    }

    public void setRippleColor(int i) {
        this.J = i;
    }

    public int getRippleDuration() {
        return this.I;
    }

    public void setRippleDuration(int i) {
        this.I = i;
    }

    public int getCrossColor() {
        return this.O;
    }

    public void setCrossColor(int i) {
        this.O = i;
    }

    public float getCrossAreaPadding() {
        return this.N;
    }

    public void setCrossAreaPadding(float f) {
        this.N = f;
    }

    public void setEnableCross(boolean z) {
        this.L = z;
    }

    public float getCrossAreaWidth() {
        return this.M;
    }

    public void setCrossAreaWidth(float f) {
        this.M = f;
    }

    public float getCrossLineWidth() {
        return this.P;
    }

    public void setCrossLineWidth(float f) {
        this.P = f;
    }

    public void setTagTextPressColor(int i) {
        this.b = i;
    }

    public int getTagTextPressColor() {
        return this.b;
    }

    public int getTagPressBgColor() {
        return this.a;
    }

    public void setTagPressBgColor(int i) {
        this.a = i;
    }
}
