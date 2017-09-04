package com.tencent.widget;

class ListView$FocusSelector implements Runnable {
    private int mPosition;
    private int mPositionTop;
    final /* synthetic */ ListView this$0;

    private ListView$FocusSelector(ListView listView) {
        this.this$0 = listView;
    }

    public ListView$FocusSelector setup(int i, int i2) {
        this.mPosition = i;
        this.mPositionTop = i2;
        return this;
    }

    public void run() {
        this.this$0.setSelectionFromTop(this.mPosition, this.mPositionTop);
    }
}
