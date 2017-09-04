package com.tencent.kapalaiadapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class KapalaiRadioGroupBase extends RadioGroup {
    public KapalaiRadioGroupBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KapalaiRadioGroupBase(Context context) {
        super(context);
    }

    protected void init() {
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        init();
    }
}
