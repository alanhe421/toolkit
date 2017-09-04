package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum y$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    TYPE((short) 5, "type"),
    REQUEST((short) 6, SocialConstants.TYPE_REQUEST),
    ERROR_CODE((short) 7, "errorCode"),
    REASON((short) 8, "reason"),
    EXTRA((short) 9, "extra"),
    PACKAGE_NAME((short) 10, Constants.FLAG_PACKAGE_NAME),
    CATEGORY((short) 11, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
    
    private static final Map<String, y$a> l = null;
    private final short m;
    private final String n;

    static {
        l = new HashMap();
        Iterator it = EnumSet.allOf(y$a.class).iterator();
        while (it.hasNext()) {
            y$a com_xiaomi_xmpush_thrift_y_a = (y$a) it.next();
            l.put(com_xiaomi_xmpush_thrift_y_a.a(), com_xiaomi_xmpush_thrift_y_a);
        }
    }

    private y$a(short s, String str) {
        this.m = s;
        this.n = str;
    }

    public String a() {
        return this.n;
    }
}
