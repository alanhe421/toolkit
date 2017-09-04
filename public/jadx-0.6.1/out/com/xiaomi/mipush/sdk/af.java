package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.c.g.a;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.l;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.z;

public class af extends a {
    private Context a;

    public af(Context context) {
        this.a = context;
    }

    public int a() {
        return 2;
    }

    public void run() {
        k a = k.a(this.a);
        org.apache.thrift.a zVar = new z();
        zVar.a(l.a(a, g.a));
        zVar.b(l.a(a, g.b));
        org.apache.thrift.a afVar = new com.xiaomi.xmpush.thrift.af(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        afVar.c(p.DailyCheckClientConfig.T);
        afVar.a(ar.a(zVar));
        al.a(this.a).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, null);
    }
}
