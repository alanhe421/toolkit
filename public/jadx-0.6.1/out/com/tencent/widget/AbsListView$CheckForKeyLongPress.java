package com.tencent.widget;

import android.view.View;

class AbsListView$CheckForKeyLongPress extends AbsListView$WindowRunnnable implements Runnable {
    final /* synthetic */ AbsListView this$0;

    private AbsListView$CheckForKeyLongPress(AbsListView absListView) {
        this.this$0 = absListView;
        super(absListView);
    }

    public void run() {
        if (this.this$0.isPressed() && this.this$0.mSelectedPosition >= 0) {
            View childAt = this.this$0.getChildAt(this.this$0.mSelectedPosition - this.this$0.mFirstPosition);
            if (this.this$0.mDataChanged) {
                this.this$0.setPressed(false);
                if (childAt != null) {
                    childAt.setPressed(false);
                    return;
                }
                return;
            }
            boolean performLongPress;
            if (sameWindow()) {
                performLongPress = this.this$0.performLongPress(childAt, this.this$0.mSelectedPosition, this.this$0.mSelectedRowId);
            } else {
                performLongPress = false;
            }
            if (performLongPress) {
                this.this$0.setPressed(false);
                childAt.setPressed(false);
            }
        }
    }
}
