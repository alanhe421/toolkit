package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfTopic */
public class s extends d {
    private final String a = "detail";
    private final String b = "replylist";

    public s(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("detail".equalsIgnoreCase(d)) {
            g();
        } else if ("replylist".equalsIgnoreCase(d)) {
            h();
        }
    }

    public void g() {
        if (e() != null) {
            o.a(b(), (String) e().get("tid"), (String) e().get("ctype"), (String) e().get("itemid"), (String) e().get(com.qq.reader.module.bookstore.qnative.item.s.ALG), a().a(c()));
        }
    }

    public void h() {
        if (e() != null) {
            o.e(b(), (String) e().get("tid"), (String) e().get("ctype"), a());
        }
    }
}
