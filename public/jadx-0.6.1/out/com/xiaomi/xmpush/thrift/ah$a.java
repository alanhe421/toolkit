package com.xiaomi.xmpush.thrift;

import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ah$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 6, "errorCode"),
    REASON((short) 7, "reason"),
    REG_ID((short) 8, "regId"),
    REG_SECRET((short) 9, "regSecret"),
    PACKAGE_NAME((short) 10, Constants.FLAG_PACKAGE_NAME);
    
    private static final Map<String, ah$a> k = null;
    private final short l;
    private final String m;

    static {
        k = new HashMap();
        Iterator it = EnumSet.allOf(ah$a.class).iterator();
        while (it.hasNext()) {
            ah$a com_xiaomi_xmpush_thrift_ah_a = (ah$a) it.next();
            k.put(com_xiaomi_xmpush_thrift_ah_a.a(), com_xiaomi_xmpush_thrift_ah_a);
        }
    }

    private ah$a(short s, String str) {
        this.l = s;
        this.m = str;
    }

    public String a() {
        return this.m;
    }
}
