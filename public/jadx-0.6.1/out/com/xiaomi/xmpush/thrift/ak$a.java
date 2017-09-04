package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ak$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    PACKAGE_NAME((short) 5, Constants.FLAG_PACKAGE_NAME),
    TOPIC((short) 6, "topic"),
    ALIAS_NAME((short) 7, "aliasName"),
    MESSAGE((short) 8, "message"),
    NEED_ACK((short) 9, "needAck"),
    PARAMS((short) 10, "params"),
    CATEGORY((short) 11, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE),
    USER_ACCOUNT((short) 12, "userAccount");
    
    private static final Map<String, ak$a> m = null;
    private final short n;
    private final String o;

    static {
        m = new HashMap();
        Iterator it = EnumSet.allOf(ak$a.class).iterator();
        while (it.hasNext()) {
            ak$a com_xiaomi_xmpush_thrift_ak_a = (ak$a) it.next();
            m.put(com_xiaomi_xmpush_thrift_ak_a.a(), com_xiaomi_xmpush_thrift_ak_a);
        }
    }

    private ak$a(short s, String str) {
        this.n = s;
        this.o = str;
    }

    public String a() {
        return this.o;
    }
}
