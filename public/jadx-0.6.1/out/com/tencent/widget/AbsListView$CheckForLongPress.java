package com.tencent.widget;

import android.view.View;

class AbsListView$CheckForLongPress extends AbsListView$WindowRunnnable implements Runnable {
    final /* synthetic */ AbsListView this$0;

    private AbsListView$CheckForLongPress(AbsListView absListView) {
        this.this$0 = absListView;
        super(absListView);
    }

    public void run() {
        View childAt = this.this$0.getChildAt(this.this$0.mMotionPosition - this.this$0.mFirstPosition);
        if (childAt != null) {
            boolean z;
            int i = this.this$0.mMotionPosition;
            long itemId = this.this$0.mAdapter.getItemId(this.this$0.mMotionPosition);
            if (!sameWindow() || this.this$0.mDataChanged) {
                z = false;
            } else {
                z = this.this$0.performLongPress(childAt, i, itemId);
            }
            if (z) {
                this.this$0.mTouchMode = -1;
                this.this$0.setPressed(false);
                childAt.setPressed(false);
                return;
            }
            this.this$0.mTouchMode = 2;
        }
    }
}
