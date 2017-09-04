package com.tencent;

import java.util.List;

final class al implements Runnable {
    private /* synthetic */ TIMGroupAssistantListener a;
    private /* synthetic */ String b;
    private /* synthetic */ List c;

    al(IMCoreGroupAssistantCallback iMCoreGroupAssistantCallback, TIMGroupAssistantListener tIMGroupAssistantListener, String str, List list) {
        this.a = tIMGroupAssistantListener;
        this.b = str;
        this.c = list;
    }

    public final void run() {
        this.a.onMemberQuit(this.b, this.c);
    }
}
