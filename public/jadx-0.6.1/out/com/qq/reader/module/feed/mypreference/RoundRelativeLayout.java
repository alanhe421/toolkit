package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class RoundRelativeLayout extends RelativeLayout {
    public RoundRelativeLayout(Context context) {
        super(context);
    }

    public RoundRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RoundRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = (i3 - i) / 2;
        int i6 = (i4 - i2) / 2;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i8 = layoutParams.leftMargin + i5;
                int i9 = i6 - layoutParams.topMargin;
                childAt.layout(i8 - (measuredWidth / 2), i9 - (measuredHeight / 2), measuredWidth + (i8 - (measuredWidth / 2)), (i9 - (measuredHeight / 2)) + measuredHeight);
            }
        }
    }
}
