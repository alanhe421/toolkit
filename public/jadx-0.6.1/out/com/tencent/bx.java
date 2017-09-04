package com.tencent;

import com.tencent.TIMConversation.ab;
import java.util.ArrayList;

final class bx implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ ab b;

    bx(ab abVar, ArrayList arrayList) {
        this.b = abVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a.onSuccess(this.a);
    }
}
