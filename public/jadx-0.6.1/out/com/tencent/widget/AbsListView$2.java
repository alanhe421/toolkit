package com.tencent.widget;

class AbsListView$2 implements Runnable {
    final /* synthetic */ AbsListView this$0;

    AbsListView$2(AbsListView absListView) {
        this.this$0 = absListView;
    }

    public void run() {
        if (this.this$0.mCachingStarted) {
            AbsListView absListView = this.this$0;
            this.this$0.mCachingActive = false;
            absListView.mCachingStarted = false;
            AbsListView.access$3200(this.this$0, false);
            if ((AbsListView.access$3300(this.this$0) & 2) == 0) {
                AbsListView.access$3400(this.this$0, false);
            }
            if (!this.this$0.isAlwaysDrawnWithCacheEnabled()) {
                this.this$0.invalidate();
            }
        }
    }
}
