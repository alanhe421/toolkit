package com.tencent.android.tpush.service.d;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
public class c {
    public static String a() {
        String str = null;
        if (m.e() != null) {
            try {
                str = TpnsSecurity.getBusinessDeviceId(m.e());
            } catch (Throwable e) {
                a.c("ServiceLogTag", ">>get deviceid err", e);
            }
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            return str;
        }
        a.h(Constants.ServiceLogTag, ">>> getDeviceId() > context == null");
        return str;
    }
}
