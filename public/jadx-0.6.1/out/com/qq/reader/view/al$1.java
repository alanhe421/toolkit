package com.qq.reader.view;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SpalshNativeUI */
class al$1 implements OnClickListener {
    final /* synthetic */ Handler a;
    final /* synthetic */ al b;

    al$1(al alVar, Handler handler) {
        this.b = alVar;
        this.a = handler;
    }

    public void onClick(View view) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, "0");
        if (al.a(this.b).getTag() != null) {
            a aVar = (a) al.a(this.b).getTag();
            if (aVar != null) {
                hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
            }
        }
        i.a("event_C100", hashMap, al.b(this.b).getApplicationContext());
        if (this.a.hasMessages(200)) {
            this.a.removeMessages(200);
        }
        this.a.sendEmptyMessage(200);
    }
}
