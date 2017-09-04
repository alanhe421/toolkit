package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.comic.a;
import com.qq.reader.module.comic.activity.NativeBookStoreComicMainPageActivity;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.qurl.d;
import java.util.Map;

/* compiled from: URLServerOfComic */
public class h extends d {
    public h(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        int i = 0;
        String d = d();
        Map e = e();
        if ("open".equals(d)) {
            Object obj;
            String str;
            String str2 = (String) e.get("cid");
            d = (String) e.get("sid");
            String str3 = (String) e.get("sindex");
            if (!TextUtils.isEmpty(str3)) {
                i = Integer.valueOf(str3).intValue();
            }
            str3 = (String) e.get("pid");
            if (TextUtils.isEmpty(d)) {
                obj = "";
            } else {
                String str4 = d;
            }
            if (TextUtils.isEmpty(str3)) {
                str = "";
            } else {
                str = str3;
            }
            if (TextUtils.isEmpty(obj)) {
                a.a().a(b(), str2);
            } else {
                a.a().a(b(), str2, obj, i, str);
            }
        } else if ("bookstore".equals(d)) {
            o.g(b(), new Bundle(), null);
        } else if ("detail".equals(d)) {
            d = (String) e.get("cid");
            if (b() == null || !(b() instanceof NativeBookStoreComicMainPageActivity)) {
                o.l(b(), d, null);
            } else {
                o.b(b(), d, null, "1");
            }
        } else if ("update".equals(d)) {
            o.i(b(), (String) e.get("actionId"), (String) e.get("actionTag"), null);
        } else if ("column".equals(d)) {
            o.m(b(), (String) e.get("actionId"), null);
        } else if ("download".equals(d)) {
            d = (String) e.get("cid");
            int intValue = Integer.valueOf((String) e.get("sindex")).intValue();
            ComicShelfInfo comicShelfInfo = new ComicShelfInfo();
            comicShelfInfo.a(d);
            comicShelfInfo.a(true);
            o.a(b(), comicShelfInfo, intValue, null, false);
        } else if ("freezone".equals(d)) {
            o.F(b(), null);
        } else if ("monthlyzone".equals(d)) {
            o.G(b(), null);
        }
    }
}
