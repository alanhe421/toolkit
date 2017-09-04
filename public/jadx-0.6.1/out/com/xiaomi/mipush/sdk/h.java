package com.xiaomi.mipush.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class h extends Handler {
    final /* synthetic */ al a;

    h(al alVar, Looper looper) {
        this.a = alVar;
        super(looper);
    }

    public void dispatchMessage(Message message) {
        String str = (String) message.obj;
        int i = message.arg1;
        synchronized (ag.class) {
            if (ag.a(this.a.c).e(str)) {
                if (ag.a(this.a.c).c(str) < 10) {
                    if (1 == i && "disable_syncing".equals(ag.a(this.a.c).a())) {
                        this.a.a(str, true);
                    } else if (i == 0 && "enable_syncing".equals(ag.a(this.a.c).a())) {
                        this.a.a(str, false);
                    }
                    ag.a(this.a.c).b(str);
                } else {
                    ag.a(this.a.c).d(str);
                }
            }
        }
    }
}
