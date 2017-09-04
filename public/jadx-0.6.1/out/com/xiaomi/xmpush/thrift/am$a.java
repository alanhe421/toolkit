package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum am$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 6, "errorCode"),
    REASON((short) 7, "reason"),
    TOPIC((short) 8, "topic"),
    PACKAGE_NAME((short) 9, Constants.FLAG_PACKAGE_NAME),
    CATEGORY((short) 10, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
    
    private static final Map<String, am$a> k = null;
    private final short l;
    private final String m;

    static {
        k = new HashMap();
        Iterator it = EnumSet.allOf(am$a.class).iterator();
        while (it.hasNext()) {
            am$a com_xiaomi_xmpush_thrift_am_a = (am$a) it.next();
            k.put(com_xiaomi_xmpush_thrift_am_a.a(), com_xiaomi_xmpush_thrift_am_a);
        }
    }

    private am$a(short s, String str) {
        this.l = s;
        this.m = str;
    }

    public String a() {
        return this.m;
    }
}
