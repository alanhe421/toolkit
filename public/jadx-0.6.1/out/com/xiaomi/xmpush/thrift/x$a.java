package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum x$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, "id"),
    APP_ID((short) 4, "appId"),
    MESSAGE_TS((short) 5, "messageTs"),
    TOPIC((short) 6, "topic"),
    ALIAS_NAME((short) 7, "aliasName"),
    REQUEST((short) 8, SocialConstants.TYPE_REQUEST),
    PACKAGE_NAME((short) 9, Constants.FLAG_PACKAGE_NAME),
    CATEGORY((short) 10, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE),
    IS_ONLINE((short) 11, "isOnline"),
    REG_ID((short) 12, "regId"),
    CALLBACK_URL((short) 13, "callbackUrl"),
    USER_ACCOUNT((short) 14, "userAccount"),
    DEVICE_STATUS((short) 15, "deviceStatus"),
    GEO_MSG_STATUS((short) 16, "geoMsgStatus"),
    IMEI_MD5((short) 20, "imeiMd5"),
    DEVICE_ID((short) 21, Constants.FLAG_DEVICE_ID),
    PASS_THROUGH((short) 22, "passThrough"),
    EXTRA((short) 23, "extra");
    
    private static final Map<String, x$a> u = null;
    private final short v;
    private final String w;

    static {
        u = new HashMap();
        Iterator it = EnumSet.allOf(x$a.class).iterator();
        while (it.hasNext()) {
            x$a com_xiaomi_xmpush_thrift_x_a = (x$a) it.next();
            u.put(com_xiaomi_xmpush_thrift_x_a.a(), com_xiaomi_xmpush_thrift_x_a);
        }
    }

    private x$a(short s, String str) {
        this.v = s;
        this.w = str;
    }

    public String a() {
        return this.w;
    }
}
