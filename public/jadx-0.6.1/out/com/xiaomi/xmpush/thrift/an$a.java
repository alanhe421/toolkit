package com.xiaomi.xmpush.thrift;

import com.tencent.android.tpush.common.Constants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum an$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    REG_ID((short) 5, "regId"),
    APP_VERSION((short) 6, "appVersion"),
    PACKAGE_NAME((short) 7, Constants.FLAG_PACKAGE_NAME),
    TOKEN((short) 8, Constants.FLAG_TOKEN),
    DEVICE_ID((short) 9, Constants.FLAG_DEVICE_ID),
    ALIAS_NAME((short) 10, "aliasName");
    
    private static final Map<String, an$a> k = null;
    private final short l;
    private final String m;

    static {
        k = new HashMap();
        Iterator it = EnumSet.allOf(an$a.class).iterator();
        while (it.hasNext()) {
            an$a com_xiaomi_xmpush_thrift_an_a = (an$a) it.next();
            k.put(com_xiaomi_xmpush_thrift_an_a.a(), com_xiaomi_xmpush_thrift_an_a);
        }
    }

    private an$a(short s, String str) {
        this.l = s;
        this.m = str;
    }

    public String a() {
        return this.m;
    }
}
