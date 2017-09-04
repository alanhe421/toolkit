package com.tencent.widget;

import android.view.animation.Interpolator;
import com.tencent.util.AnimateUtils;

class ListView$SimpleInterpolator implements Interpolator {
    final /* synthetic */ ListView this$0;

    private ListView$SimpleInterpolator(ListView listView) {
        this.this$0 = listView;
    }

    public float getInterpolation(float f) {
        return AnimateUtils.viscousFluid(f);
    }
}
