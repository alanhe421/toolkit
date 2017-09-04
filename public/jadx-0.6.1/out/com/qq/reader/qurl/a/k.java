package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfFindBook */
public class k extends d {
    private final String a = "index";

    public k(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        if ("index".equalsIgnoreCase(d())) {
            g();
        }
    }

    public void g() {
        o.r(b(), a().a(c()));
    }
}
