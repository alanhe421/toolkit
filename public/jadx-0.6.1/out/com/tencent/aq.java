package com.tencent;

final class aq implements Runnable {
    private /* synthetic */ TIMGroupEventListener a;
    private /* synthetic */ TIMGroupTipsElem b;

    aq(IMCoreGroupEventCallback iMCoreGroupEventCallback, TIMGroupEventListener tIMGroupEventListener, TIMGroupTipsElem tIMGroupTipsElem) {
        this.a = tIMGroupEventListener;
        this.b = tIMGroupTipsElem;
    }

    public final void run() {
        this.a.onGroupTipsEvent(this.b);
    }
}
