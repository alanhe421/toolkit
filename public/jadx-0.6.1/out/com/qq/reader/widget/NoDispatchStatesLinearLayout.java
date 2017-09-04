package com.qq.reader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class NoDispatchStatesLinearLayout extends LinearLayout {
    public NoDispatchStatesLinearLayout(Context context) {
        super(context);
    }

    public NoDispatchStatesLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoDispatchStatesLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void dispatchSetSelected(boolean z) {
    }
}
