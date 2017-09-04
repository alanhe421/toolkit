package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.support.v4.view.ai;
import android.support.v4.view.n;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.qq.reader.c.b;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class CirclePageIndicator extends View implements e {
    private float a;
    private final Paint b;
    private final Paint c;
    private final Paint d;
    private ViewPager e;
    private e f;
    private int g;
    private int h;
    private float i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private int n;
    private float o;
    private int p;
    private boolean q;

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

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.d = new Paint(1);
        this.o = -1.0f;
        this.p = -1;
        if (!isInEditMode()) {
            int color = getResources().getColor(R.color.feed_head_c70);
            float a = (float) ao.a(1.0f);
            float a2 = (float) ao.a(2.0f);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.CirclePageIndicator, i, 0);
            this.l = obtainStyledAttributes.getBoolean(2, false);
            this.k = obtainStyledAttributes.getInt(0, 0);
            this.b.setStyle(Style.FILL);
            this.b.setColor(obtainStyledAttributes.getColor(5, color));
            this.c.setStyle(Style.STROKE);
            this.c.setColor(obtainStyledAttributes.getColor(8, 0));
            this.c.setStrokeWidth(obtainStyledAttributes.getDimension(3, a));
            this.d.setStyle(Style.FILL);
            this.d.setColor(obtainStyledAttributes.getColor(4, -1));
            this.a = obtainStyledAttributes.getDimension(6, a2);
            this.m = obtainStyledAttributes.getBoolean(7, true);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.n = ai.a(ViewConfiguration.get(context.getApplicationContext()));
        }
    }

    public void setCentered(boolean z) {
        this.l = z;
        invalidate();
    }

    public void setPageColor(int i) {
        this.b.setColor(i);
        invalidate();
    }

    public int getPageColor() {
        return this.b.getColor();
    }

    public void setFillColor(int i) {
        this.d.setColor(i);
        invalidate();
    }

    public int getFillColor() {
        return this.d.getColor();
    }

    public void setOrientation(int i) {
        switch (i) {
            case 0:
            case 1:
                this.k = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return this.k;
    }

    public void setStrokeColor(int i) {
        this.c.setColor(i);
        invalidate();
    }

    public int getStrokeColor() {
        return this.c.getColor();
    }

    public void setStrokeWidth(float f) {
        this.c.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.c.getStrokeWidth();
    }

    public void setRadius(float f) {
        this.a = f;
        invalidate();
    }

    public float getRadius() {
        return this.a;
    }

    public void setSnap(boolean z) {
        this.m = z;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.e != null) {
            int a = this.e.getAdapter().a();
            if (a != 0 && a != 1) {
                if (this.g >= a) {
                    setCurrentItem(a - 1);
                    return;
                }
                int width;
                int paddingLeft;
                int paddingRight;
                int paddingTop;
                if (this.k == 0) {
                    width = getWidth();
                    paddingLeft = getPaddingLeft();
                    paddingRight = getPaddingRight();
                    paddingTop = getPaddingTop();
                } else {
                    width = getHeight();
                    paddingLeft = getPaddingTop();
                    paddingRight = getPaddingBottom();
                    paddingTop = getPaddingLeft();
                }
                float f = this.a * 3.0f;
                float f2 = this.a + ((float) paddingTop);
                float f3 = ((float) paddingLeft) + this.a;
                if (this.l) {
                    f3 += (((float) ((width - paddingLeft) - paddingRight)) / 2.0f) - ((((float) a) * f) / 2.0f);
                }
                float f4 = this.a;
                if (this.c.getStrokeWidth() > 0.0f) {
                    f4 -= this.c.getStrokeWidth() / 2.0f;
                }
                for (int i = 0; i < a; i++) {
                    float f5;
                    float f6 = (((float) i) * f) + f3;
                    if (this.k == 0) {
                        f5 = f6;
                        f6 = f2;
                    } else {
                        f5 = f2;
                    }
                    if (this.b.getAlpha() > 0) {
                        canvas.drawCircle(f5, f6, f4, this.b);
                    }
                    if (f4 != this.a) {
                        canvas.drawCircle(f5, f6, this.a, this.c);
                    }
                }
                f4 = ((float) (this.m ? this.h : this.g)) * f;
                if (!this.m) {
                    f4 += this.i * f;
                }
                if (this.k == 0) {
                    f3 += f4;
                } else {
                    float f7 = f3 + f4;
                    f3 = f2;
                    f2 = f7;
                }
                canvas.drawCircle(f3, f2, this.a, this.d);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.e == null || this.e.getAdapter().a() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                this.p = n.b(motionEvent, 0);
                this.o = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.q) {
                    int a = this.e.getAdapter().a();
                    int width = getWidth();
                    float f = ((float) width) / 2.0f;
                    float f2 = ((float) width) / 6.0f;
                    if (this.g <= 0 || motionEvent.getX() >= f - f2) {
                        if (this.g < a - 1 && motionEvent.getX() > f2 + f) {
                            if (action == 3) {
                                return true;
                            }
                            this.e.setCurrentItem(this.g + 1);
                            return true;
                        }
                    } else if (action == 3) {
                        return true;
                    } else {
                        this.e.setCurrentItem(this.g - 1);
                        return true;
                    }
                }
                this.q = false;
                this.p = -1;
                if (!this.e.f()) {
                    return true;
                }
                this.e.e();
                return true;
            case 2:
                float c = n.c(motionEvent, n.a(motionEvent, this.p));
                float f3 = c - this.o;
                if (!this.q && Math.abs(f3) > ((float) this.n)) {
                    this.q = true;
                }
                if (!this.q) {
                    return true;
                }
                this.o = c;
                if (!this.e.f() && !this.e.d()) {
                    return true;
                }
                this.e.b(f3);
                return true;
            case 5:
                i = n.b(motionEvent);
                this.o = n.c(motionEvent, i);
                this.p = n.b(motionEvent, i);
                return true;
            case 6:
                action = n.b(motionEvent);
                if (n.b(motionEvent, action) == this.p) {
                    if (action == 0) {
                        i = 1;
                    }
                    this.p = n.b(motionEvent, i);
                }
                this.o = n.c(motionEvent, n.a(motionEvent, this.p));
                return true;
            default:
                return true;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.e != viewPager) {
            if (this.e != null) {
                this.e.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.e = viewPager;
            this.e.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.e == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.e.setCurrentItem(i);
        this.g = i;
        invalidate();
    }

    public void onPageScrollStateChanged(int i) {
        this.j = i;
        if (this.f != null) {
            this.f.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        int a = this.e.getAdapter().a();
        if (a != 0) {
            a = i % a;
            this.g = a;
            this.i = f;
            invalidate();
            if (this.f != null) {
                this.f.onPageScrolled(a, f, i2);
            }
        }
    }

    public void onPageSelected(int i) {
        int a = this.e.getAdapter().a();
        if (a != 0) {
            a = i % a;
            if (this.m || this.j == 0) {
                this.g = a;
                this.h = a;
                invalidate();
            }
            if (this.f != null) {
                this.f.onPageSelected(a);
            }
        }
    }

    public void setOnPageChangeListener(e eVar) {
        this.f = eVar;
    }

    protected void onMeasure(int i, int i2) {
        if (this.k == 0) {
            setMeasuredDimension(a(i), b(i2));
        } else {
            setMeasuredDimension(b(i), a(i2));
        }
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.e == null) {
            return size;
        }
        int a = this.e.getAdapter().a();
        a = (int) (((((float) (a - 1)) * this.a) + (((float) (getPaddingLeft() + getPaddingRight())) + (((float) (a * 2)) * this.a))) + 1.0f);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(a, size);
        }
        return a;
    }

    private int b(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((((2.0f * this.a) + ((float) getPaddingTop())) + ((float) getPaddingBottom())) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.g = savedState.a;
        this.h = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.g;
        return savedState;
    }
}
