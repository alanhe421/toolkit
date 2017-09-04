package com.tencent;

import java.util.List;

final class as implements Runnable {
    private /* synthetic */ TIMGroupMemberUpdateListener a;
    private /* synthetic */ String b;
    private /* synthetic */ TIMGroupTipsType c;
    private /* synthetic */ List d;

    as(ar arVar, TIMGroupMemberUpdateListener tIMGroupMemberUpdateListener, String str, TIMGroupTipsType tIMGroupTipsType, List list) {
        this.a = tIMGroupMemberUpdateListener;
        this.b = str;
        this.c = tIMGroupTipsType;
        this.d = list;
    }

    public final void run() {
        this.a.onMemberUpdate(this.b, this.c, this.d);
    }
}
