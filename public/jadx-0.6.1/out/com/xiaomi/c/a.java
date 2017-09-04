package com.xiaomi.c;

import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.b.b$c;
import com.xiaomi.push.service.am.b;
import java.util.HashMap;
import java.util.Map;

class a {
    public static void a(b bVar, String str, com.xiaomi.smack.a aVar) {
        b$c com_xiaomi_push_b_b_c = new b$c();
        if (!TextUtils.isEmpty(bVar.c)) {
            com_xiaomi_push_b_b_c.a(bVar.c);
        }
        if (!TextUtils.isEmpty(bVar.f)) {
            com_xiaomi_push_b_b_c.d(bVar.f);
        }
        if (!TextUtils.isEmpty(bVar.g)) {
            com_xiaomi_push_b_b_c.e(bVar.g);
        }
        com_xiaomi_push_b_b_c.b(bVar.e ? "1" : "0");
        if (TextUtils.isEmpty(bVar.d)) {
            com_xiaomi_push_b_b_c.c("XIAOMI-SASL");
        } else {
            com_xiaomi_push_b_b_c.c(bVar.d);
        }
        b bVar2 = new b();
        bVar2.c(bVar.b);
        bVar2.a(Integer.parseInt(bVar.h));
        bVar2.b(bVar.a);
        bVar2.a("BIND", null);
        bVar2.a(bVar2.h());
        c.a("[Slim]: bind id=" + bVar2.h());
        Map hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put(Constants.FLAG_TOKEN, bVar.c);
        hashMap.put("chid", bVar.h);
        hashMap.put("from", bVar.b);
        hashMap.put("id", bVar2.h());
        hashMap.put("to", "xiaomi.com");
        if (bVar.e) {
            hashMap.put("kick", "1");
        } else {
            hashMap.put("kick", "0");
        }
        if (TextUtils.isEmpty(bVar.f)) {
            hashMap.put("client_attrs", "");
        } else {
            hashMap.put("client_attrs", bVar.f);
        }
        if (TextUtils.isEmpty(bVar.g)) {
            hashMap.put("cloud_attrs", "");
        } else {
            hashMap.put("cloud_attrs", bVar.g);
        }
        String a = (bVar.d.equals("XIAOMI-PASS") || bVar.d.equals("XMPUSH-PASS")) ? com.xiaomi.channel.commonutils.g.b.a(bVar.d, null, hashMap, bVar.i) : bVar.d.equals("XIAOMI-SASL") ? null : null;
        com_xiaomi_push_b_b_c.f(a);
        bVar2.a(com_xiaomi_push_b_b_c.c(), null);
        aVar.b(bVar2);
    }

    public static void a(String str, String str2, com.xiaomi.smack.a aVar) {
        b bVar = new b();
        bVar.c(str2);
        bVar.a(Integer.parseInt(str));
        bVar.a("UBND", null);
        aVar.b(bVar);
    }
}
