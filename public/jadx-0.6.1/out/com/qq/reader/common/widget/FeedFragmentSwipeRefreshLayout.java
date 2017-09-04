package com.qq.reader.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class FeedFragmentSwipeRefreshLayout extends SwipeRefreshLayout {
    private View d;
    private int e;

    public FeedFragmentSwipeRefreshLayout(Context context) {
        super(context);
    }

    public FeedFragmentSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getCommonTitleLayout() != null) {
            int measuredWidth = getMeasuredWidth();
            int i5 = this.e;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            getCommonTitleLayout().layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((i5 - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getCommonTitleLayout() != null) {
            measureChild(getCommonTitleLayout(), MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    private View getCommonTitleLayout() {
        if (this.d == null) {
            if (this.a == null) {
                return null;
            }
            int indexOfChild = indexOfChild(this.a) + 1;
            if (getChildCount() <= indexOfChild) {
                return null;
            }
            this.d = getChildAt(indexOfChild);
            this.e = this.d.getLayoutParams().height;
        }
        return this.d;
    }
}
