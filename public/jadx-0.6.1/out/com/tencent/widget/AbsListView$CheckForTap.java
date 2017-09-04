package com.tencent.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewConfiguration;

final class AbsListView$CheckForTap implements Runnable {
    final /* synthetic */ AbsListView this$0;

    AbsListView$CheckForTap(AbsListView absListView) {
        this.this$0 = absListView;
    }

    public void run() {
        if (this.this$0.mTouchMode == 0) {
            this.this$0.mTouchMode = 1;
            View childAt = this.this$0.getChildAt(this.this$0.mMotionPosition - this.this$0.mFirstPosition);
            if (childAt != null && !childAt.hasFocusable()) {
                this.this$0.mLayoutMode = 0;
                if (this.this$0.mDataChanged) {
                    this.this$0.mTouchMode = 2;
                    return;
                }
                childAt.setPressed(true);
                this.this$0.setPressed(true);
                this.this$0.layoutChildren();
                this.this$0.positionSelector(this.this$0.mMotionPosition, childAt);
                this.this$0.refreshDrawableState();
                int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                boolean isLongClickable = this.this$0.isLongClickable();
                if (this.this$0.mSelector != null) {
                    Drawable current = this.this$0.mSelector.getCurrent();
                    if (current != null && (current instanceof TransitionDrawable)) {
                        if (isLongClickable) {
                            ((TransitionDrawable) current).startTransition(longPressTimeout);
                        } else {
                            ((TransitionDrawable) current).resetTransition();
                        }
                    }
                }
                if (isLongClickable) {
                    if (AbsListView.access$700(this.this$0) == null) {
                        AbsListView.access$702(this.this$0, new AbsListView$CheckForLongPress(this.this$0));
                    }
                    AbsListView.access$700(this.this$0).rememberWindowAttachCount();
                    this.this$0.postDelayed(AbsListView.access$700(this.this$0), (long) longPressTimeout);
                    return;
                }
                this.this$0.mTouchMode = 2;
            }
        }
    }
}
