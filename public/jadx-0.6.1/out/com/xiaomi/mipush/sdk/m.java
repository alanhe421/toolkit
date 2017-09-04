package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.p;
import java.util.HashMap;

final class m implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;

    m(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public void run() {
        c.a("do sync info");
        af afVar = new af(c.a(), false);
        q a = q.a(this.a);
        afVar.c(p.v.T);
        afVar.b(a.c());
        afVar.d(this.a.getPackageName());
        afVar.h = new HashMap();
        h.a(afVar.h, "app_version", b.a(this.a, this.a.getPackageName()));
        h.a(afVar.h, "app_version_code", Integer.toString(b.b(this.a, this.a.getPackageName())));
        h.a(afVar.h, "push_sdk_vn", "3_4_3");
        h.a(afVar.h, "push_sdk_vc", Integer.toString(30403));
        h.a(afVar.h, Constants.FLAG_TOKEN, a.d());
        String a2 = d.a(e.c(this.a));
        Object e = e.e(this.a);
        if (!TextUtils.isEmpty(e)) {
            a2 = a2 + "," + e;
        }
        h.a(afVar.h, "imei_md5", a2);
        h.a(afVar.h, "reg_id", a.e());
        h.a(afVar.h, "reg_secret", a.f());
        h.a(afVar.h, MessageKey.MSG_ACCEPT_TIME, c.p(this.a).replace(",", "-"));
        if (this.b) {
            h.a(afVar.h, "aliases_md5", l.a(c.b(this.a)));
            h.a(afVar.h, "topics_md5", l.a(c.c(this.a)));
            h.a(afVar.h, "accounts_md5", l.a(c.d(this.a)));
        } else {
            h.a(afVar.h, "aliases", l.b(c.b(this.a)));
            h.a(afVar.h, "topics", l.b(c.c(this.a)));
            h.a(afVar.h, "user_accounts", l.b(c.d(this.a)));
        }
        al.a(this.a).a(afVar, a.i, false, null);
    }
}
