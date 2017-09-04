package com.tencent.widget;

class AbsListView$WindowRunnnable {
    private int mOriginalAttachCount;
    final /* synthetic */ AbsListView this$0;

    private AbsListView$WindowRunnnable(AbsListView absListView) {
        this.this$0 = absListView;
    }

    public void rememberWindowAttachCount() {
        this.mOriginalAttachCount = AbsListView.access$200(this.this$0);
    }

    public boolean sameWindow() {
        return this.this$0.hasWindowFocus() && AbsListView.access$300(this.this$0) == this.mOriginalAttachCount;
    }
}
