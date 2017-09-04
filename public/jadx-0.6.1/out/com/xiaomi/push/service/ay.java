package com.xiaomi.push.service;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.thrift.a;

class ay extends h {
    final /* synthetic */ String a;
    final /* synthetic */ List b;
    final /* synthetic */ String c;
    final /* synthetic */ ax d;

    ay(ax axVar, int i, String str, List list, String str2) {
        this.d = axVar;
        this.a = str;
        this.b = list;
        this.c = str2;
        super(i);
    }

    public void a() {
        String a = ax.a(this.d, this.a);
        ArrayList a2 = y.a(this.b, this.a, a, 32768);
        if (a2 != null) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                af afVar = (af) it.next();
                afVar.a("uploadWay", "longXMPushService");
                a a3 = d.a(this.a, a, afVar, com.xiaomi.xmpush.thrift.a.i);
                if (!(TextUtils.isEmpty(this.c) || TextUtils.equals(this.a, this.c))) {
                    if (a3.m() == null) {
                        s sVar = new s();
                        sVar.a(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
                        a3.a(sVar);
                    }
                    a3.m().b("ext_traffic_source_pkg", this.c);
                }
                ax.a(this.d).a(this.a, ar.a(a3), true);
            }
            for (e eVar : this.b) {
                c.c("A tinyData uploaded by com.xiaomi.push.service.TinyDataUploader." + eVar);
            }
            return;
        }
        c.d("Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
    }

    public String b() {
        return "Send tiny data.";
    }
}
