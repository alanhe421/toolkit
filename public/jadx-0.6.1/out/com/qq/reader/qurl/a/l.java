package com.qq.reader.qurl.a;

import android.app.Activity;
import android.text.TextUtils;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.qurl.d;
import java.util.Map;

/* compiled from: URLServerOfGame */
public class l extends d {
    private final String a = "mainpage";
    private final String b = "column";
    private final String c = ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE;

    public l(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        Map e = e();
        if ("mainpage".equalsIgnoreCase(d)) {
            o.w(b(), null);
        } else if ("column".equalsIgnoreCase(d)) {
            d = (String) e.get("cId");
            if (!TextUtils.isEmpty(d)) {
                o.b(b(), null, d);
            }
        } else if (ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE.equalsIgnoreCase(d)) {
            d = e != null ? (String) e.get("cId") : "";
            if (TextUtils.isEmpty(d)) {
                d = "1";
            }
            o.c(b(), null, d);
        }
    }
}
