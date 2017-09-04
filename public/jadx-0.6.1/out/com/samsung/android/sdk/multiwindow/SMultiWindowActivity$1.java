package com.samsung.android.sdk.multiwindow;

import android.graphics.Rect;
import com.samsung.android.sdk.multiwindow.SMultiWindowListener.StateChangeListener;

class SMultiWindowActivity$1 implements StateChangeListener {
    final /* synthetic */ SMultiWindowActivity this$0;

    SMultiWindowActivity$1(SMultiWindowActivity sMultiWindowActivity) {
        this.this$0 = sMultiWindowActivity;
    }

    public void onModeChanged(boolean z) {
        SMultiWindowActivity.access$000(this.this$0).onModeChanged(z);
    }

    public void onZoneChanged(int i) {
        SMultiWindowActivity.access$000(this.this$0).onZoneChanged(i);
    }

    public void onSizeChanged(Rect rect) {
        SMultiWindowActivity.access$000(this.this$0).onSizeChanged(rect);
    }
}
