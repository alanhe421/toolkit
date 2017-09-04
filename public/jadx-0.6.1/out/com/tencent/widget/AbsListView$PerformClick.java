package com.tencent.widget;

import android.view.View;
import android.widget.ListAdapter;

class AbsListView$PerformClick extends AbsListView$WindowRunnnable implements Runnable {
    int mClickMotionPosition;
    final /* synthetic */ AbsListView this$0;

    private AbsListView$PerformClick(AbsListView absListView) {
        this.this$0 = absListView;
        super(absListView);
    }

    public void run() {
        if (!this.this$0.mDataChanged) {
            ListAdapter listAdapter = this.this$0.mAdapter;
            int i = this.mClickMotionPosition;
            boolean access$500 = AbsListView.access$500(this.this$0, i, this.this$0.mAdapter.getCount());
            if (listAdapter == null) {
                return;
            }
            if ((access$500 || AbsListView.access$600(this.this$0)) && sameWindow()) {
                View childAt = this.this$0.getChildAt(i - this.this$0.mFirstPosition);
                if (childAt != null || AbsListView.access$600(this.this$0)) {
                    this.this$0.performItemClick(childAt, i, access$500 ? listAdapter.getItemId(i) : 0);
                }
            }
        }
    }
}
