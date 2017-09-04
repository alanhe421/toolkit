package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class UnTouchGridView extends GridView {
    public UnTouchGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UnTouchGridView(Context context) {
        super(context);
    }

    public UnTouchGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
