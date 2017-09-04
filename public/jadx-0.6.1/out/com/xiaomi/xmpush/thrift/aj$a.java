package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum aj$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 6, "errorCode"),
    REASON((short) 7, "reason"),
    CATEGORY((short) 8, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
    
    private static final Map<String, aj$a> i = null;
    private final short j;
    private final String k;

    static {
        i = new HashMap();
        Iterator it = EnumSet.allOf(aj$a.class).iterator();
        while (it.hasNext()) {
            aj$a com_xiaomi_xmpush_thrift_aj_a = (aj$a) it.next();
            i.put(com_xiaomi_xmpush_thrift_aj_a.a(), com_xiaomi_xmpush_thrift_aj_a);
        }
    }

    private aj$a(short s, String str) {
        this.j = s;
        this.k = str;
    }

    public String a() {
        return this.k;
    }
}
