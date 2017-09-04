package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.c.b;

public class CustomTailIconTextView extends FrameLayout {
    private int a;
    private int b;
    private float c;
    private int d;

    public CustomTailIconTextView(Context context) {
        super(context);
    }

    public CustomTailIconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public CustomTailIconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, b.CustomTailIconTextView, 0, 0);
        try {
            setMaxlines(obtainStyledAttributes.getInt(0, -1));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int size = MeasureSpec.getSize(i);
        if (childCount == 2) {
            measureChildWithMargins(getChildAt(0), i, 0, i2, 0);
            measureChildWithMargins(getChildAt(1), i, 0, i2, 0);
            if (getChildAt(0) instanceof TextView) {
                TextView textView = (TextView) getChildAt(0);
                a(textView);
                View childAt = getChildAt(1);
                LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                int i3 = layoutParams.leftMargin + layoutParams.rightMargin;
                int i4 = layoutParams.topMargin + layoutParams.bottomMargin;
                int i5 = layoutParams2.topMargin + layoutParams2.bottomMargin;
                int i6 = layoutParams2.rightMargin + layoutParams2.leftMargin;
                if (childAt.getVisibility() == 8) {
                    setMeasuredDimension(textView.getMeasuredWidth() + i3, textView.getMeasuredHeight() + i4);
                    textView.setMaxLines(getMaxlines());
                    return;
                } else if (!(getChildAt(0) instanceof TextView)) {
                    return;
                } else {
                    if (getMaxlines() == 1) {
                        a(textView, childAt, size, i, i2);
                        return;
                    } else if (textView.getLineCount() != 1) {
                        if (((float) i6) + (this.c + ((float) childAt.getMeasuredWidth())) <= ((float) size)) {
                            setMeasuredDimension(size, Math.max(textView.getMeasuredHeight() + i4, layoutParams.topMargin + ((childAt.getMeasuredHeight() + this.b) + i5)));
                            textView.setMaxLines(getMaxlines());
                            this.a = 2;
                            return;
                        }
                        setMeasuredDimension(size, ((textView.getMeasuredHeight() + childAt.getMeasuredHeight()) + i4) + i5);
                        textView.setMaxLines(getMaxlines());
                        this.a = 3;
                        return;
                    } else if (((textView.getMeasuredWidth() + i3) + i6) + childAt.getMeasuredWidth() <= size) {
                        a(textView, childAt, size, i, i2);
                        return;
                    } else {
                        setMeasuredDimension(size, ((textView.getMeasuredHeight() + childAt.getMeasuredHeight()) + i4) + i5);
                        textView.setMaxLines(getMaxlines());
                        this.a = 3;
                        return;
                    }
                }
            }
            throw new RuntimeException("CustomLayout first child view not a TextView");
        }
        throw new RuntimeException("CustomLayout child count must is 2");
    }

    private void a(TextView textView, View view, int i, int i2, int i3) {
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
        int i4 = layoutParams.leftMargin + layoutParams.rightMargin;
        int i5 = layoutParams.topMargin + layoutParams.bottomMargin;
        int i6 = layoutParams2.topMargin + layoutParams2.bottomMargin;
        int i7 = layoutParams2.rightMargin + layoutParams2.leftMargin;
        this.a = 1;
        setMeasuredDimension(i, Math.max(textView.getMeasuredHeight() + i5, (layoutParams.topMargin + view.getMeasuredHeight()) + i6));
        int childMeasureSpec = getChildMeasureSpec(i2, (getPaddingLeft() + getPaddingRight()) + i4, Math.min(((i - view.getMeasuredWidth()) - i7) - i4, textView.getMeasuredWidth() + i4));
        i7 = getChildMeasureSpec(i3, (getPaddingLeft() + getPaddingRight()) + i5, textView.getMeasuredHeight());
        textView.setSingleLine();
        textView.measure(childMeasureSpec, i7);
        a(textView);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TextView textView = (TextView) getChildAt(0);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        View childAt = getChildAt(1);
        LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
        int i5 = ((LayoutParams) childAt.getLayoutParams()).leftMargin;
        textView.layout(layoutParams.leftMargin, layoutParams.topMargin, textView.getMeasuredWidth() + layoutParams.leftMargin, textView.getMeasuredHeight() + layoutParams.topMargin);
        if (childAt.getVisibility() != 8) {
            if (this.a == 1 || this.a == 2 || this.a == 4) {
                if (this.a == 1) {
                    i5 += (textView.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                } else {
                    i5 += ((int) this.c) + layoutParams.leftMargin;
                }
                int i6 = layoutParams2.topMargin + (this.b + layoutParams.topMargin);
                int bottom = ((textView.getBottom() - textView.getPaddingBottom()) - this.b) - layoutParams.bottomMargin;
                if (childAt.getMeasuredHeight() < bottom) {
                    bottom = ((bottom - childAt.getMeasuredHeight()) / 2) + (layoutParams.topMargin + this.b);
                } else {
                    bottom = i6;
                }
                childAt.layout(i5, bottom, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight() + bottom);
            } else if (this.a == 3) {
                childAt.layout(layoutParams.leftMargin + i5, textView.getMeasuredHeight() + layoutParams.topMargin, i5 + (childAt.getMeasuredWidth() + layoutParams.leftMargin), (textView.getMeasuredHeight() + childAt.getMeasuredHeight()) + layoutParams.topMargin);
            }
        }
    }

    private void a(TextView textView) {
        Layout layout = textView.getLayout();
        if (layout != null) {
            int lineCount = layout.getLineCount();
            this.b = layout.getLineTop(lineCount - 1);
            this.c = layout.getLineRight(lineCount - 1);
        }
    }

    public int getMaxlines() {
        return this.d;
    }

    public void setMaxlines(int i) {
        this.d = i;
    }
}
