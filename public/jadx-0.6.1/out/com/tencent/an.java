package com.tencent;

final class an implements Runnable {
    private /* synthetic */ TIMGroupAssistantListener a;
    private /* synthetic */ TIMGroupCacheInfo b;

    an(IMCoreGroupAssistantCallback iMCoreGroupAssistantCallback, TIMGroupAssistantListener tIMGroupAssistantListener, TIMGroupCacheInfo tIMGroupCacheInfo) {
        this.a = tIMGroupAssistantListener;
        this.b = tIMGroupCacheInfo;
    }

    public final void run() {
        this.a.onGroupAdd(this.b);
    }
}
