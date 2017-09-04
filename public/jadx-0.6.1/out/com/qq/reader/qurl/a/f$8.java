package com.qq.reader.qurl.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: URLServerOfClient */
class f$8 implements Callback {
    final /* synthetic */ f a;

    f$8(f fVar) {
        this.a = fVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 65552:
                if (f.i(this.a) != null && !f.j(this.a).isFinishing()) {
                    f.u().f_();
                    c.d("ADV", "rookie show ");
                    break;
                }
                c.d("ADV", "rookie want ot show but activity is gone " + f.k(this.a));
                f.f(this.a);
                break;
        }
        return true;
    }
}
