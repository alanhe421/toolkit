package com.tencent;

import com.tencent.TIMConversation.ab;
import java.util.ArrayList;

final class by implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ ab b;

    by(ab abVar, ArrayList arrayList) {
        this.b = abVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a.onSuccess(this.a);
    }
}
