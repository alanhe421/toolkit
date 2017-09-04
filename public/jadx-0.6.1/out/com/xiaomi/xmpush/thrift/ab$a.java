package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ab$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    CMD_NAME((short) 5, "cmdName"),
    REQUEST((short) 6, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 7, "errorCode"),
    REASON((short) 8, "reason"),
    PACKAGE_NAME((short) 9, Constants.FLAG_PACKAGE_NAME),
    CMD_ARGS((short) 10, "cmdArgs"),
    CATEGORY((short) 12, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
    
    private static final Map<String, ab$a> l = null;
    private final short m;
    private final String n;

    static {
        l = new HashMap();
        Iterator it = EnumSet.allOf(ab$a.class).iterator();
        while (it.hasNext()) {
            ab$a com_xiaomi_xmpush_thrift_ab_a = (ab$a) it.next();
            l.put(com_xiaomi_xmpush_thrift_ab_a.a(), com_xiaomi_xmpush_thrift_ab_a);
        }
    }

    private ab$a(short s, String str) {
        this.m = s;
        this.n = str;
    }

    public String a() {
        return this.n;
    }
}
