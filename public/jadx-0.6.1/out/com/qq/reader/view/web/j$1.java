package com.qq.reader.view.web;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PopNativeGameDialog */
class j$1 implements OnClickListener {
    final /* synthetic */ j a;

    j$1(j jVar) {
        this.a = jVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, "2");
        i.a("event_A208", hashMap, ReaderApplication.getApplicationImp());
    }
}
