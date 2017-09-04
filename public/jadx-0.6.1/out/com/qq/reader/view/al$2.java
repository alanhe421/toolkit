package com.qq.reader.view;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.Constants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SpalshNativeUI */
class al$2 implements OnClickListener {
    final /* synthetic */ Handler a;
    final /* synthetic */ al b;

    al$2(al alVar, Handler handler) {
        this.b = alVar;
        this.a = handler;
    }

    public void onClick(View view) {
        if (view.getTag() != null && (view.getTag() instanceof a)) {
            a aVar = (a) view.getTag();
            String h = aVar.h();
            if (c.a(h)) {
                try {
                    c.a(al.b(this.b), h, new JumpActivityParameter().a(Constants.ERRORCODE_UNKNOWN));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                aVar.w().a(al.b(this.b));
            }
            if (this.a.hasMessages(200)) {
                this.a.removeMessages(200);
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
            i.a("event_C101", hashMap, ReaderApplication.getApplicationImp());
        }
    }
}
