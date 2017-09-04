package com.qq.reader.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.qq.reader.a.a.a;

public class StaticWidthAutoHeightRelativeLayout extends RelativeLayout {
    private float a = 1.0f;

    public StaticWidthAutoHeightRelativeLayout(Context context) {
        super(context);
    }

    public StaticWidthAutoHeightRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.StaticWidthAutoHeightRelativeLayout);
        this.a = obtainStyledAttributes.getFloat(a.StaticWidthAutoHeightRelativeLayout_height_of_width, 1.0f);
        obtainStyledAttributes.recycle();
    }

    public StaticWidthAutoHeightRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec((int) (((float) MeasureSpec.getSize(i)) * this.a), 1073741824));
    }
}
