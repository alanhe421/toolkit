package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.android.tpush.common.MessageKey;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.b;
import com.xiaomi.channel.commonutils.c.g;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.f;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class l {
    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long a = (long) k.a(context).a(f.SyncInfoFrequency.a(), 1209600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        } else if (Math.abs(currentTimeMillis - j) > a) {
            a(context, true);
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        }
    }

    public static void a(Context context, af afVar) {
        c.a("need to update local info with: " + afVar.i());
        String str = (String) afVar.i().get(MessageKey.MSG_ACCEPT_TIME);
        if (str != null) {
            c.o(context);
            String[] split = str.split("-");
            if (split.length == 2) {
                c.g(context, split[0], split[1]);
                if ("00:00".equals(split[0]) && "00:00".equals(split[1])) {
                    q.a(context).a(true);
                } else {
                    q.a(context).a(false);
                }
            }
        }
        str = (String) afVar.i().get("aliases");
        if (str != null) {
            c.l(context);
            if (!"".equals(str)) {
                for (String a : str.split(",")) {
                    c.a(context, a);
                }
            }
        }
        str = (String) afVar.i().get("topics");
        if (str != null) {
            c.n(context);
            if (!"".equals(str)) {
                for (String a2 : str.split(",")) {
                    c.e(context, a2);
                }
            }
        }
        str = (String) afVar.i().get("user_accounts");
        if (str != null) {
            c.m(context);
            if (!"".equals(str)) {
                for (String c : str.split(",")) {
                    c.c(context, c);
                }
            }
        }
    }

    public static void a(Context context, boolean z) {
        g.a(context).a(new m(context, z));
    }

    private static String c(List<String> list) {
        String a = d.a(d(list));
        return (TextUtils.isEmpty(a) || a.length() <= 4) ? "" : a.substring(0, 4).toLowerCase();
    }

    private static String d(List<String> list) {
        if (b.a(list)) {
            return "";
        }
        List<String> arrayList = new ArrayList(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        String str = "";
        for (String str2 : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                str = str + ",";
            }
            str = str + str2;
        }
        return str;
    }
}
