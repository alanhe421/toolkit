package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum e$a {
    CHANNEL((short) 1, "channel"),
    DATA((short) 2, "data"),
    NAME((short) 3, "name"),
    COUNTER((short) 4, "counter"),
    TIMESTAMP((short) 5, "timestamp"),
    FROM_SDK((short) 6, "fromSdk"),
    CATEGORY((short) 7, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE),
    SOURCE_PACKAGE((short) 8, "sourcePackage"),
    ID((short) 9, "id"),
    EXTRA((short) 10, "extra");
    
    private static final Map<String, e$a> k = null;
    private final short l;
    private final String m;

    static {
        k = new HashMap();
        Iterator it = EnumSet.allOf(e$a.class).iterator();
        while (it.hasNext()) {
            e$a com_xiaomi_xmpush_thrift_e_a = (e$a) it.next();
            k.put(com_xiaomi_xmpush_thrift_e_a.a(), com_xiaomi_xmpush_thrift_e_a);
        }
    }

    private e$a(short s, String str) {
        this.l = s;
        this.m = str;
    }

    public String a() {
        return this.m;
    }
}
