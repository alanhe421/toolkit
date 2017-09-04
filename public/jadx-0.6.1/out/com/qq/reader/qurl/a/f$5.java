package com.qq.reader.qurl.a;

import android.content.DialogInterface;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.t;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.view.r;

/* compiled from: URLServerOfClient */
class f$5 extends r {
    final /* synthetic */ f a;

    f$5(f fVar) {
        this.a = fVar;
    }

    public t a() {
        if (f.u() != null) {
            return f.u().c();
        }
        return null;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        b.b = false;
        a.c(ReaderApplication.getApplicationImp(), "CHANNEL_ADV_CLOSE_TIME");
        c.d("ADV", "adv dialog is dismiss");
        f.f(this.a);
    }
}
