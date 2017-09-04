package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;

public class MeasureTextLayout extends LinearLayout {
    public MeasureTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public MeasureTextLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MeasureTextLayout(Context context) {
        super(context);
        a();
    }

    private void a() {
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        f.d("mea", "width " + size + " height " + MeasureSpec.getSize(i2));
        int i3 = 0;
        int i4 = 0;
        int i5 = size;
        while (i3 < getChildCount()) {
            int measuredHeight;
            View childAt = getChildAt(i3);
            if (!"showmintag".equals(childAt.getTag())) {
                int a;
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    a = (int) ao.a(textView.getPaint(), textView.getText().toString());
                } else {
                    a = 0;
                }
                if (a == 0) {
                    a = size;
                }
                measureChild(childAt, a + 1073741824, i2);
                i5 = ((i5 - childAt.getMeasuredWidth()) - childAt.getPaddingLeft()) - childAt.getPaddingRight();
            }
            if (i4 < childAt.getMeasuredHeight()) {
                measuredHeight = childAt.getMeasuredHeight();
            } else {
                measuredHeight = i4;
            }
            i3++;
            i4 = measuredHeight;
        }
        if (getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            i5 = (i5 - (marginLayoutParams.leftMargin - marginLayoutParams.rightMargin)) - (getPaddingLeft() - getPaddingRight());
        }
        for (i3 = 0; i3 < getChildCount(); i3++) {
            textView = (TextView) getChildAt(i3);
            if (textView.getLayoutParams() != null && (textView.getLayoutParams() instanceof MarginLayoutParams)) {
                MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) textView.getLayoutParams();
                i5 = ((i5 - marginLayoutParams2.leftMargin) - marginLayoutParams2.rightMargin) - (textView.getPaddingLeft() + textView.getPaddingRight());
            }
            if ("showmintag".equals(textView.getTag()) && textView.getText() != null) {
                String charSequence = textView.getText().toString();
                size = ao.a(textView.getPaint(), (float) i5, charSequence);
                textView.setText(charSequence.subSequence(0, size));
                measureChild(textView, ((int) ao.a(textView.getPaint(), (String) charSequence.subSequence(0, size))) - Integer.MIN_VALUE, i2);
            }
        }
        setMeasuredDimension(i, i4 - Integer.MIN_VALUE);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < getChildCount()) {
            int measuredWidth;
            View childAt = getChildAt(i5);
            if (childAt.getLayoutParams() == null || !(childAt.getLayoutParams() instanceof LayoutParams)) {
                measuredWidth = childAt.getMeasuredWidth() + i7;
                int i8 = i6;
                i6 = i7;
                i7 = i8;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i9 = i7 + layoutParams.leftMargin;
                i7 = layoutParams.topMargin;
                if (layoutParams.gravity == 16) {
                    i7 = (getMeasuredHeight() - childAt.getMeasuredHeight()) / 2;
                } else {
                    i7 = 0;
                }
                measuredWidth = (childAt.getMeasuredWidth() + i9) + layoutParams.rightMargin;
                i6 = i9;
            }
            childAt.layout(i6, i7, measuredWidth, childAt.getMeasuredHeight() + i7);
            i5++;
            i6 = i7;
            i7 = measuredWidth;
        }
    }
}
