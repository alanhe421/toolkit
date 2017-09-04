package com.tencent.widget;

class AbsListView$AdapterDataSetObserver extends AdapterView$AdapterDataSetObserver {
    final /* synthetic */ AbsListView this$0;

    AbsListView$AdapterDataSetObserver(AbsListView absListView) {
        this.this$0 = absListView;
        super(absListView);
    }

    public void onChanged() {
        super.onChanged();
        if (this.this$0.mFastScroller != null) {
            this.this$0.mFastScroller.onSectionsChanged();
        }
    }

    public void onInvalidated() {
        super.onInvalidated();
        if (this.this$0.mFastScroller != null) {
            this.this$0.mFastScroller.onSectionsChanged();
        }
    }
}
