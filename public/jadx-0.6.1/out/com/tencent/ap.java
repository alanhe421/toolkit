package com.tencent;

final class ap implements Runnable {
    private /* synthetic */ TIMGroupAssistantListener a;
    private /* synthetic */ TIMGroupCacheInfo b;

    ap(IMCoreGroupAssistantCallback iMCoreGroupAssistantCallback, TIMGroupAssistantListener tIMGroupAssistantListener, TIMGroupCacheInfo tIMGroupCacheInfo) {
        this.a = tIMGroupAssistantListener;
        this.b = tIMGroupCacheInfo;
    }

    public final void run() {
        this.a.onGroupUpdate(this.b);
    }
}
