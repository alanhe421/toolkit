package com.qq.reader.qurl.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.view.af;

/* compiled from: URLServerOfClient */
class f$1 implements Callback {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 65538:
                b.a = false;
                if (f.a(this.a) != null && !f.b(this.a).isFinishing()) {
                    c.d("ADV", "adv dialog is show , activity is " + f.c(this.a));
                    f.u().f_();
                    b.b = true;
                    break;
                }
                c.d("ADV", "adv activity is already gone");
                break;
                break;
            case 65542:
                b.a = false;
                if (f.d(this.a) != null) {
                    af.a(f.e(this.a), "请登录", 0).a();
                    c.d("ADV", "adv need login ");
                } else {
                    c.d("ADV", "adv need login but activity is gone");
                }
                f.f(this.a);
                break;
        }
        return true;
    }
}
