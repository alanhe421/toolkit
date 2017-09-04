package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.qq.reader.c.b;

public class FlowLayout extends ViewGroup {
    private int a;
    private int b;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.FlowLayout);
        try {
            this.b = obtainStyledAttributes.getDimensionPixelSize(0, 5);
            this.a = obtainStyledAttributes.getDimensionPixelSize(1, 5);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setHorizontalSpacing(int i) {
        this.b = i;
    }

    public void setVerticalSpacing(int i) {
        this.a = i;
    }

    protected void onMeasure(int i, int i2) {
        int resolveSize = resolveSize(0, i);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int i3 = 0;
        int childCount = getChildCount();
        int i4 = 0;
        int i5 = paddingTop;
        int i6 = paddingLeft;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i, paddingLeft + paddingRight, layoutParams.width), getChildMeasureSpec(i2, paddingTop + paddingBottom, layoutParams.height));
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            i3 = Math.max(measuredHeight, i3);
            if ((i6 + measuredWidth) + paddingRight > resolveSize) {
                i3 = (i3 + this.a) + i5;
                i5 = (paddingLeft + measuredWidth) + this.b;
            } else {
                int i7 = i3;
                i3 = i5;
                i5 = (this.b + measuredWidth) + i6;
                measuredHeight = i7;
            }
            i4++;
            i6 = i5;
            i5 = i3;
            i3 = measuredHeight;
        }
        setMeasuredDimension(resolveSize, resolveSize((i5 + i3) + paddingBottom, i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int childCount = getChildCount();
        int i6 = paddingLeft;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i7 = Math.max(measuredHeight, i7);
                if ((i6 + measuredWidth) + paddingRight > i5) {
                    paddingTop = (i7 + this.a) + paddingTop;
                    i6 = paddingLeft;
                    i7 = measuredHeight;
                }
                childAt.layout(i6, paddingTop, i6 + measuredWidth, measuredHeight + paddingTop);
                i6 += this.b + measuredWidth;
            }
        }
    }
}
