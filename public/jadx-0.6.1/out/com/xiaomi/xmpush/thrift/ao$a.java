package com.xiaomi.xmpush.thrift;

import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ao$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 6, "errorCode"),
    REASON((short) 7, "reason"),
    PACKAGE_NAME((short) 8, Constants.FLAG_PACKAGE_NAME);
    
    private static final Map<String, ao$a> i = null;
    private final short j;
    private final String k;

    static {
        i = new HashMap();
        Iterator it = EnumSet.allOf(ao$a.class).iterator();
        while (it.hasNext()) {
            ao$a com_xiaomi_xmpush_thrift_ao_a = (ao$a) it.next();
            i.put(com_xiaomi_xmpush_thrift_ao_a.a(), com_xiaomi_xmpush_thrift_ao_a);
        }
    }

    private ao$a(short s, String str) {
        this.j = s;
        this.k = str;
    }

    public String a() {
        return this.k;
    }
}
