package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.net.URLDecoder;
import java.util.Map;

/* compiled from: URLServerOfBook */
public class d extends com.qq.reader.qurl.d {
    private final String a = "detail";
    private final String b = "surrounding";
    private final String c = "audiodetail";

    public d(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("detail".equalsIgnoreCase(d)) {
            g();
        } else if ("surrounding".equalsIgnoreCase(d)) {
            i();
        } else if ("audiodetail".equalsIgnoreCase(d)) {
            h();
        }
    }

    public void g() {
        if (e() != null) {
            try {
                String str = (String) e().get("bid");
                Bundle bundle = new Bundle();
                String str2 = (String) e().get(s.ALG);
                String str3 = (String) e().get("itemid");
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putString(s.ALG, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    bundle.putString("itemid", str3);
                }
                try {
                    Map map = (Map) a().a();
                    if (map != null) {
                        for (String str32 : map.keySet()) {
                            bundle.putString(str32, (String) map.get(str32));
                        }
                    }
                } catch (Exception e) {
                    c.e("URLServerOfBook", e.getMessage());
                }
                str2 = (String) e().get("statInfo");
                if (TextUtils.isEmpty(str2)) {
                    o.a(b(), str, "", bundle, a().a(c()));
                    return;
                }
                o.a(b(), str, URLDecoder.decode(str2, "utf-8"), bundle, a().a(c()));
            } catch (Exception e2) {
                c.e("error", e2.getMessage());
            }
        }
    }

    private void i() {
        if (e() != null) {
            try {
                o.b(b(), Long.valueOf((String) e().get("bookId")).longValue(), (String) e().get("bname"), a());
            } catch (Exception e) {
            }
        }
    }

    public void h() {
        if (e() != null) {
            try {
                String str = (String) e().get("mediaId");
                Bundle bundle = new Bundle();
                String str2 = (String) e().get(s.ALG);
                String str3 = (String) e().get("itemid");
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putString(s.ALG, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    bundle.putString("itemid", str3);
                }
                try {
                    Map map = (Map) a().a();
                    if (map != null) {
                        for (String str32 : map.keySet()) {
                            bundle.putString(str32, (String) map.get(str32));
                        }
                    }
                } catch (Exception e) {
                    c.e("URLServerOfBook", e.getMessage());
                }
                str2 = (String) e().get("statInfo");
                if (TextUtils.isEmpty(str2)) {
                    o.b(b(), str, null, bundle, null);
                    return;
                }
                o.b(b(), str, URLDecoder.decode(str2, "utf-8"), bundle, null);
            } catch (Exception e2) {
                c.e("error", e2.getMessage());
            }
        }
    }
}
