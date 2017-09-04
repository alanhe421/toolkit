package com.tencent.widget;

import android.view.View;

class AbsListView$1 implements Runnable {
    final /* synthetic */ AbsListView this$0;
    final /* synthetic */ View val$child;
    final /* synthetic */ AbsListView$PerformClick val$performClick;

    AbsListView$1(AbsListView absListView, View view, AbsListView$PerformClick absListView$PerformClick) {
        this.this$0 = absListView;
        this.val$child = view;
        this.val$performClick = absListView$PerformClick;
    }

    public void run() {
        this.this$0.mTouchMode = -1;
        this.val$child.setPressed(false);
        this.this$0.setPressed(false);
        if (!this.this$0.mDataChanged) {
            this.val$performClick.run();
        }
        AbsListView.access$1002(this.this$0, null);
    }
}
