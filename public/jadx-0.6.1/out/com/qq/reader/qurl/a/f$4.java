package com.qq.reader.qurl.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: URLServerOfClient */
class f$4 implements OnCancelListener {
    final /* synthetic */ a a;
    final /* synthetic */ f b;

    f$4(f fVar, a aVar) {
        this.b = fVar;
        this.a = aVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (f.g(this.b) != null) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.a.d()));
            i.a("event_A190", hashMap, f.h(this.b).getApplicationContext());
            b.b = false;
            c.d("ADV", "adv dialog is cancel");
        } else {
            c.d("ADV", "adv dialog is cancel but activity is gone");
        }
        f.f(this.b);
    }
}
