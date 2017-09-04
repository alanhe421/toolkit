package com.tencent;

final class ao implements Runnable {
    private /* synthetic */ TIMGroupAssistantListener a;
    private /* synthetic */ String b;

    ao(IMCoreGroupAssistantCallback iMCoreGroupAssistantCallback, TIMGroupAssistantListener tIMGroupAssistantListener, String str) {
        this.a = tIMGroupAssistantListener;
        this.b = str;
    }

    public final void run() {
        this.a.onGroupDelete(this.b);
    }
}
