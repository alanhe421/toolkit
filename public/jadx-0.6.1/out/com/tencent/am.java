package com.tencent;

import java.util.List;

final class am implements Runnable {
    private /* synthetic */ TIMGroupAssistantListener a;
    private /* synthetic */ String b;
    private /* synthetic */ List c;

    am(IMCoreGroupAssistantCallback iMCoreGroupAssistantCallback, TIMGroupAssistantListener tIMGroupAssistantListener, String str, List list) {
        this.a = tIMGroupAssistantListener;
        this.b = str;
        this.c = list;
    }

    public final void run() {
        this.a.onMemberUpdate(this.b, this.c);
    }
}
