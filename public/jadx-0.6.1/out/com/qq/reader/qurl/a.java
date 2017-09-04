package com.qq.reader.qurl;

import android.support.v4.util.h;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.mm.performance.WxPerformanceHandle;

/* compiled from: ServerName */
public class a {
    private static h<String, Integer> a = new h(24);

    private static synchronized void a() {
        synchronized (a.class) {
            if (a.size() <= 0) {
                a.put("book", Integer.valueOf(1));
                a.put("topic", Integer.valueOf(2));
                a.put("coin", Integer.valueOf(3));
                a.put("vip", Integer.valueOf(4));
                a.put("comment", Integer.valueOf(5));
                a.put("client", Integer.valueOf(6));
                a.put("readgene", Integer.valueOf(7));
                a.put("infostream", Integer.valueOf(8));
                a.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(9));
                a.put("discover", Integer.valueOf(10));
                a.put("rank", Integer.valueOf(11));
                a.put("getAcctInfo", Integer.valueOf(12));
                a.put("findbook", Integer.valueOf(13));
                a.put("authors", Integer.valueOf(14));
                a.put("webpage", Integer.valueOf(15));
                a.put("search", Integer.valueOf(16));
                a.put(WxPerformanceHandle.MESSAGE_TAG, Integer.valueOf(17));
                a.put("publisher", Integer.valueOf(18));
                a.put("audioquestion", Integer.valueOf(19));
                a.put("game", Integer.valueOf(20));
                a.put("redpacket", Integer.valueOf(21));
                a.put("comic", Integer.valueOf(22));
            }
        }
    }

    public static int a(String str) {
        try {
            if (a.size() == 0) {
                a();
            }
            return ((Integer) a.get(str)).intValue();
        } catch (Exception e) {
            return -1;
        }
    }
}
