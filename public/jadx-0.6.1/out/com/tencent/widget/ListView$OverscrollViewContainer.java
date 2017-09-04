package com.tencent.widget;

import android.content.Context;
import android.view.ViewParent;
import android.widget.FrameLayout;

class ListView$OverscrollViewContainer extends FrameLayout {
    public ListView$OverscrollViewContainer(Context context) {
        super(context);
    }

    private void setParent(ViewParent viewParent) {
        this.mParent = viewParent;
    }
}
