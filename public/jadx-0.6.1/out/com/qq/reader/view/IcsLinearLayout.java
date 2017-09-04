package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class IcsLinearLayout extends LinearLayout {
    private static final int[] c = new int[]{16843049, 16843476, 16843561, 16843562};
    private static final boolean d = (VERSION.SDK_INT >= 11);
    protected int a;
    protected int b;
    private Drawable e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;

    public IcsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.f = obtainStyledAttributes.getInt(2, 0);
        this.g = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.i = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.f) {
            requestLayout();
            invalidate();
        }
        this.f = i;
    }

    public int getShowDividers() {
        return this.f;
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.e) {
            this.e = drawable;
            this.h = drawable instanceof ColorDrawable;
            if (drawable != null) {
                this.a = drawable.getIntrinsicWidth();
                this.b = drawable.getIntrinsicHeight();
            } else {
                this.a = 0;
                this.b = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.g = i;
    }

    public int getDividerPadding() {
        return this.g;
    }

    public int getDividerWidth() {
        return this.a;
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.b;
            } else {
                layoutParams.leftMargin = this.a;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.b;
            } else {
                layoutParams.rightMargin = this.a;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.e != null) {
            if (getOrientation() == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    void a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
            }
            i++;
        }
        if (a(childCount)) {
            int height;
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                height = (getHeight() - getPaddingBottom()) - this.b;
            } else {
                height = childAt2.getBottom();
            }
            a(canvas, height);
        }
    }

    void b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
            i++;
        }
        if (a(childCount)) {
            int width;
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                width = (getWidth() - getPaddingRight()) - this.a;
            } else {
                width = childAt2.getRight();
            }
            b(canvas, width);
        }
    }

    void a(Canvas canvas, int i) {
        if (!this.h || d) {
            this.e.setBounds(getPaddingLeft() + this.g, i, (getWidth() - getPaddingRight()) - this.g, this.b + i);
            this.e.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(getPaddingLeft() + this.g, i, (getWidth() - getPaddingRight()) - this.g, this.b + i);
        this.e.draw(canvas);
        canvas.restore();
    }

    void b(Canvas canvas, int i) {
        if (!this.h || d) {
            this.e.setBounds(i, getPaddingTop() + this.g, this.a + i, (getHeight() - getPaddingBottom()) - this.g);
            this.e.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(i, getPaddingTop() + this.g, this.a + i, (getHeight() - getPaddingBottom()) - this.g);
        this.e.draw(canvas);
        canvas.restore();
    }

    protected boolean a(int i) {
        if (i == 0) {
            if ((this.f & 1) != 0) {
                return true;
            }
            return false;
        } else if (i == getChildCount()) {
            if ((this.f & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.f & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.i;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.i = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.i) {
            switch (getOrientation()) {
                case 0:
                    a();
                    return;
                case 1:
                    b();
                    return;
                default:
                    return;
            }
        }
    }

    private void a() {
        int childCount = getChildCount();
        int i = 0;
        int i2 = 0;
        while (i < childCount) {
            i++;
            i2 = Math.max(getChildAt(i).getMeasuredWidth(), i2);
        }
        int i3 = 0;
        i = 0;
        while (i3 < childCount) {
            int i4;
            View childAt = getChildAt(i3);
            if (childAt == null) {
                i4 = i;
            } else if (childAt.getVisibility() == 8) {
                i4 = i;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.weight > 0.0f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(i2, 1073741824), MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
                    i += i2;
                } else {
                    i += childAt.getMeasuredWidth();
                }
                i4 = (layoutParams.rightMargin + layoutParams.leftMargin) + i;
            }
            i3++;
            i = i4;
        }
        setMeasuredDimension((getPaddingLeft() + getPaddingRight()) + i, getMeasuredHeight());
    }

    private void b() {
        int childCount = getChildCount();
        int i = 0;
        int i2 = 0;
        while (i < childCount) {
            i++;
            i2 = Math.max(getChildAt(i).getMeasuredHeight(), i2);
        }
        int i3 = 0;
        i = 0;
        while (i3 < childCount) {
            int i4;
            View childAt = getChildAt(i3);
            if (childAt == null) {
                i4 = i;
            } else if (childAt.getVisibility() == 8) {
                i4 = i;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.weight > 0.0f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(childAt.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i2, 1073741824));
                    i += i2;
                } else {
                    i += childAt.getMeasuredHeight();
                }
                i4 = (layoutParams.rightMargin + layoutParams.leftMargin) + i;
            }
            i3++;
            i = i4;
        }
        setMeasuredDimension(getMeasuredWidth(), (getPaddingLeft() + getPaddingRight()) + i);
    }
}
