package com.qq.reader.qurl.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: URLServerOfClient */
class f$2 implements Callback {
    final /* synthetic */ f a;

    f$2(f fVar) {
        this.a = fVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 65552:
                if (f.o(this.a) != null && !f.p(this.a).isFinishing()) {
                    if (f.u() != null) {
                        f.u().f_();
                        c.d("ADV", "rookie show ");
                        break;
                    }
                }
                c.d("ADV", "rookie want ot show but activity is gone " + f.q(this.a));
                f.f(this.a);
                break;
                break;
        }
        return true;
    }
}
