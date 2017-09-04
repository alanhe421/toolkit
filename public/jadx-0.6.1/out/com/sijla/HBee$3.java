package com.sijla;

import android.content.Context;
import com.sijla.j.b;
import com.tencent.imsdk.QLogImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class HBee$3 implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Map d;
    final /* synthetic */ String e;
    final /* synthetic */ HBee f;

    HBee$3(HBee hBee, Context context, String str, String str2, Map map, String str3) {
        this.f = hBee;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = map;
        this.e = str3;
    }

    public void run() {
        try {
            List arrayList = new ArrayList();
            arrayList.add(b.p(this.a));
            arrayList.add(this.b);
            arrayList.add(this.c);
            arrayList.add("1");
            arrayList.add(this.d != null ? HBee.access$100(this.f, this.d) : "");
            arrayList.add(b.h(this.a));
            arrayList.add(b.g() + "");
            arrayList.add(this.e);
            com.sijla.common.b.e().a(b.f(QLogImpl.TAG_REPORTLEVEL_USER), arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
