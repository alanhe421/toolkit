package com.xiaomi.xmpush.thrift;

import com.tencent.android.tpush.common.Constants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum r$a {
    TO((short) 1, "to"),
    ID((short) 2, "id"),
    APP_ID((short) 3, "appId"),
    PAYLOAD((short) 4, "payload"),
    CREATE_AT((short) 5, "createAt"),
    TTL((short) 6, "ttl"),
    COLLAPSE_KEY((short) 7, "collapseKey"),
    PACKAGE_NAME((short) 8, Constants.FLAG_PACKAGE_NAME);
    
    private static final Map<String, r$a> i = null;
    private final short j;
    private final String k;

    static {
        i = new HashMap();
        Iterator it = EnumSet.allOf(r$a.class).iterator();
        while (it.hasNext()) {
            r$a com_xiaomi_xmpush_thrift_r_a = (r$a) it.next();
            i.put(com_xiaomi_xmpush_thrift_r_a.a(), com_xiaomi_xmpush_thrift_r_a);
        }
    }

    private r$a(short s, String str) {
        this.j = s;
        this.k = str;
    }

    public String a() {
        return this.k;
    }
}
