package com.xiaomi.push.service;

import com.xiaomi.e.e;
import java.util.List;

public class ax implements e {
    private final XMPushService a;

    public ax(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    public void a(List<com.xiaomi.xmpush.thrift.e> list, String str, String str2) {
        this.a.a(new ay(this, 4, str, list, str2));
    }

    public boolean a(com.xiaomi.xmpush.thrift.e eVar, String str) {
        return a(this.a.getPackageName()) != null;
    }
}
