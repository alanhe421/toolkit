package com.qq.reader.view;

import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SpalshNativeUI */
class al$3 implements e<String, b> {
    final /* synthetic */ a a;
    final /* synthetic */ al b;

    al$3(al alVar, a aVar) {
        this.b = alVar;
        this.a = aVar;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        this.b.b();
        return true;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        if (!(bVar instanceof com.bumptech.glide.load.resource.bitmap.j)) {
            return false;
        }
        al.c(this.b).setImageBitmap(((com.bumptech.glide.load.resource.bitmap.j) bVar).b());
        ReaderApplication.timeLog.addSplit("setSplashImage setImageBitmap");
        al.a(this.b, true);
        com.qq.reader.common.monitor.j.a(90, 1);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.a.d()));
        i.a("event_B91", hashMap, al.b(this.b).getApplicationContext());
        ReaderApplication.timeLog.addSplit("setSplashImage RDMEvent");
        return true;
    }
}
