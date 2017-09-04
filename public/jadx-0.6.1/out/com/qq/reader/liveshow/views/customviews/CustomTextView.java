package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomTextView extends TextView {
    private float a;

    public CustomTextView(Context context) {
        this(context, null, 0);
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.a = 0.0f;
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0.0f;
        this.a = (float) ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
    }

    private float a(String str) {
        float paddingRight = (float) ((LinearLayout) getParent()).getPaddingRight();
        return ((float) ((int) Math.ceil((double) (getPaint().measureText(str) / ((((this.a - ((float) ((LinearLayout) getParent()).getPaddingLeft())) - paddingRight) - ((float) getPaddingLeft())) - ((float) getPaddingRight())))))) * (getPaint().getFontMetrics().descent - getPaint().getFontMetrics().ascent);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getLayout() != null) {
            setMeasuredDimension(getMeasuredWidth(), (((int) Math.ceil((double) a(getText().toString()))) + getCompoundPaddingTop()) + getCompoundPaddingBottom());
        }
    }
}
