package com.qq.reader.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* compiled from: FixedSpeedScroller */
public class i extends Scroller {
    private int a = 1000;

    public i(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.a);
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.a);
    }
}
