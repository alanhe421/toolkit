package com.tencent;

import java.util.List;

final class at implements Runnable {
    private /* synthetic */ TIMGroupMemberUpdateListener a;
    private /* synthetic */ String b;
    private /* synthetic */ List c;

    at(ar arVar, TIMGroupMemberUpdateListener tIMGroupMemberUpdateListener, String str, List list) {
        this.a = tIMGroupMemberUpdateListener;
        this.b = str;
        this.c = list;
    }

    public final void run() {
        this.a.onMemberInfoUpdate(this.b, this.c);
    }
}
